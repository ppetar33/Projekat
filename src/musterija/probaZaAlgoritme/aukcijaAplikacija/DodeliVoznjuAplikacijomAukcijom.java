package musterija.probaZaAlgoritme.aukcijaAplikacija;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import musterija.probaZaAlgoritme.Aukcija;
import musterija.probaZaAlgoritme.aukcijaTelefon.DodeliVoznjuTelefonomAukcijom;
import musterija.probaZaAlgoritme.aukcijaTelefon.ProzorZaDodeljivanjeVoznjiTelefonomAukcijom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class DodeliVoznjuAplikacijomAukcijom extends JFrame {

    private DefaultTableModel tableModel;
    private JToolBar mainToolBar = new JToolBar();
    public JTable voznjeTabela;
    private JButton btnEdit = new JButton("Dodeli");
    private JButton btnOsvezi = new JButton("Osvezi tabelu");
    public Liste ucitavanje;
    public NarucivanjeVoznjePrekoAplikacije voznja;

    public DodeliVoznjuAplikacijomAukcijom(Liste ucitavanje, NarucivanjeVoznjePrekoAplikacije voznja){
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Dodeljivanje voznji");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        initTable();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        mainToolBar.add(btnOsvezi);
        add(btnEdit, BorderLayout.NORTH);
        add(btnOsvezi, BorderLayout.SOUTH);
    }
    private void initTable(){
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemAplikacije().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemAplikacije().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemAplikacije().get(i);
            sadrzaj[i][0] = voznje.getId();
            sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            sadrzaj[i][2] = voznje.getAdresaPolaska();
            sadrzaj[i][3] = voznje.getAdresaDestinacije();
            sadrzaj[i][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
            sadrzaj[i][5] = "/";
            sadrzaj[i][6] = "/";
            sadrzaj[i][7] = "/";
            sadrzaj[i][8] = voznje.getStatusVoznje();
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavnje);
        voznjeTabela = new JTable(tableModel);

        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
        voznjeTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(voznjeTabela);
        add(jsp, BorderLayout.CENTER);
    }
    private void initListeners(){
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sveVoznjePrekoAplikacije = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeAplikacijaBinarySearch(sveVoznjePrekoAplikacije,id);
                    NarucivanjeVoznjePrekoAplikacije voznja = sveVoznjePrekoAplikacije.get(indexGdeSeNalazi);

                    DoublyLinkedList<Aukcija> aukcija = ucitavanje.getIstorijaAukcija();
                    DoublyLinkedList<String> izborMusterije = new DoublyLinkedList<>();
                    for(Aukcija aukcija1 : aukcija){
                        if(aukcija1.getIDvoznje() == voznja.getId() && aukcija1.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA)){
                            izborMusterije.add(aukcija1.getIzborMusterije());
                        }
                    }

                    Set<String> izborMusterijeBezDupliranihElemenata = findDuplicatesStrings(izborMusterije);
                    DoublyLinkedList<Double> oceneVozacaLista = new DoublyLinkedList<>();
                    DoublyLinkedList<Integer> brzinaVozacaLista = new DoublyLinkedList<>();
                    DoublyLinkedList<Boolean> petFriendlyLista = new DoublyLinkedList<>();
                    DoublyLinkedList<Integer> najnovijiAutomobilLista = new DoublyLinkedList<>();
                    DoublyLinkedList<String> listaPetFriendlyAuta = new DoublyLinkedList<>();

                    for(String i : izborMusterijeBezDupliranihElemenata){
                        for(Aukcija aukcija1 : aukcija){
                            if(aukcija1.getIDvoznje() == voznja.getId() && aukcija1.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA)){
                                if(i.equals("Najbolje ocenjen vozac")){
                                    oceneVozacaLista.add(aukcija1.getOcenaVozaca());
                                }else if(i.equals("Najbrzi vozac")){
                                    brzinaVozacaLista.add(aukcija1.getVremeKojeJeUneoVozac());
                                }else if(i.equals("Pet friendly automobil")){
                                    petFriendlyLista.add(aukcija1.isPetFriendly());
                                    if (aukcija1.isPetFriendly()) {
                                        String petFriendlyAutaVozaca = aukcija1.getVozacKojiUcestvujeUaukciji();
                                        listaPetFriendlyAuta.add(petFriendlyAutaVozaca);
                                    }
                                }else if(i.equals("Najnoviji automobil")){
                                    najnovijiAutomobilLista.add(aukcija1.getGodisteAutomobila());
                                }else{
                                    oceneVozacaLista.add(aukcija1.getOcenaVozaca());
                                }
                            }
                        }
                    }
                    DoublyLinkedList<String> najbrziVozac = new DoublyLinkedList<>();
                    double n = brzinaVozacaLista.size();
                    if(brzinaVozacaLista.size() != 0) {
                        double min = brzinaVozacaLista.get(0);
                        for (int j = 0; j < n; j++) {
                            if (brzinaVozacaLista.get(j) < min) {
                                min = brzinaVozacaLista.get(j);
                            }
                        }
                        for (Aukcija aukcija1 : aukcija) {
                            if(aukcija1.getIDvoznje() == voznja.getId() && aukcija1.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA)) {
                                if (min == aukcija1.getVremeKojeJeUneoVozac()) {
                                    String najbrziVozacKorisnickoIme = aukcija1.getVozacKojiUcestvujeUaukciji();
                                    najbrziVozac.add(najbrziVozacKorisnickoIme);
                                }
                            }
                        }
                    }

                    DoublyLinkedList<String> oceneVozaca = new DoublyLinkedList<>();
                    if(oceneVozacaLista.size() != 0) {
                        double ocenaVozaca = oceneVozacaLista.getFirst();
                        for (int j = 0; j < oceneVozacaLista.size(); j++) {
                            if (oceneVozacaLista.get(j) > ocenaVozaca) {
                                ocenaVozaca = oceneVozacaLista.get(j);
                            }
                        }
                        for (Aukcija aukcija1 : aukcija) {
                            if(aukcija1.getIDvoznje() == voznja.getId() && aukcija1.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA)) {
                                if (aukcija1.getOcenaVozaca() == ocenaVozaca) {
                                    String najboljeOcenjenVozacKorisnickoIme = aukcija1.getVozacKojiUcestvujeUaukciji();
                                    oceneVozaca.add(najboljeOcenjenVozacKorisnickoIme);
                                }
                            }
                        }
                    }

                    DoublyLinkedList<String> najnovijiAutomobil = new DoublyLinkedList<>();
                    if(najnovijiAutomobilLista.size() != 0){
                        double najnovijiAuto = najnovijiAutomobilLista.getFirst();
                        for (int j = 0; j < najnovijiAutomobilLista.size(); j++) {
                            if (najnovijiAutomobilLista.get(j) > najnovijiAuto) {
                                najnovijiAuto = najnovijiAutomobilLista.get(j);
                            }
                        }
                        for (Aukcija aukcija1 : aukcija) {
                            if(aukcija1.getIDvoznje() == voznja.getId() && aukcija1.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA)) {
                                if (aukcija1.getGodisteAutomobila() == najnovijiAuto) {
                                    String najboljeOcenjenVozacKorisnickoIme = aukcija1.getVozacKojiUcestvujeUaukciji();
                                    najnovijiAutomobil.add(najboljeOcenjenVozacKorisnickoIme);
                                }
                            }
                        }
                    }

                    if(oceneVozacaLista.isEmpty() && petFriendlyLista.isEmpty() && brzinaVozacaLista.isEmpty() && najnovijiAutomobilLista.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ni jedan vozac ne ucestvuje u aukciji, molimo vas sacekajte.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                    }

                    if(oceneVozaca.size() != 0){
                        ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom(ucitavanje,voznja,oceneVozaca);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }else if(najnovijiAutomobil.size() != 0){
                        ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom(ucitavanje,voznja,najnovijiAutomobil);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }else if(najbrziVozac.size() != 0){
                        ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom(ucitavanje,voznja,najbrziVozac);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }else if(listaPetFriendlyAuta.size() != 0) {
                        ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom(ucitavanje, voznja, listaPetFriendlyAuta);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }
                }
            }
        });
        btnOsvezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodeliVoznjuAplikacijomAukcijom.this.setVisible(false);
                DodeliVoznjuAplikacijomAukcijom.this.dispose();
                DodeliVoznjuAplikacijomAukcijom dodeljivanjeVoznje = new DodeliVoznjuAplikacijomAukcijom(ucitavanje,voznja);
                dodeljivanjeVoznje.setVisible(true);
            }
        });
    }
    private Set<String> findDuplicatesStrings(DoublyLinkedList<String> list){
        Set<String> items = new HashSet<String>();
        Set<String> duplicates = new HashSet<String>();
        for (String item : list) {
            if (items.contains(item)) {
                duplicates.remove(item);
            } else {
                items.add(item);
            }
        }
        return items;
    }
}
