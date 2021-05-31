package musterija.probaZaAlgoritme;

import automobili.Voznja;
import dispecer.dodeljivanjeVoznje.DodeljivanjeVoznje;
import dispecer.dodeljivanjeVoznje.ProzorZaDodeljivanjeVoznji;
import dispecer.izvestaj.Izvestaj;
import enumi.StatusVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
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

/*
        AUKCIJA

          Specifikacija:
            Omogućiti vozačima da učestvuju u aukciji za novu vožnju. Vozač unosi koliko minuta mu je
            potrebno da stigne do date adrese. Dizajnirati algoritam koji će dodeljivati vozačima vožnje.
            Na dodeljivanje vožnje može uticati potrebno vreme dolaska, ocena vozača, zarada vozača u
            nekom intervalu, starost vozila (mušterija može da traži novija vozila), pet friendly itd.
            Kreirati simulaciju ponuda više vozača i dodelu vožnje. Potrebno je čuvati istoriju aukcija.
            Za veći broj bodova potrebno je proširiti podatke o vozilima ili vozačima, takođe, potrebno
            je omogućiti mušterijama da ocene vozača nakon vožnje (ocena od 1 do 5).

          Odgovor asistenta:
            Musterija pri narucivanju voznje bira da li hoce pet friendly i novija vozila itd. Treba da
            prosirimo formu za narucivanje voznje putem telefona ili aplikacije (da ne bude samo adresa
            polaska i adresa dolaska)
            Napravimo malu formu jedan input za ono vozac unosi koliko mu treba do date adrese. Vozac moze
            da vidi sve dodeljene voznje i onda umesto ono prihvati da stoji da moze da ucestvuje u aukciji
            i tu prikazujemo tu formu. Posto je jedan ulogovan onda treba da se odjavi pa se uloguje drugi
            unese koliko mu treba, pa se uloguje treci vozac i unese koliko mu treba itd. Kad unesu,
            ulogujem se kao dispecer i pokrenem aukciju ovo nema smisla za nasu trenutnu situaciju
            ali ako vise korisnika naruci istovremeno voznju onda ima smisla. E sad za dodeljivanje
            voznje kod ove aukcije moze da utice vise faktora to pise u specifikaciji npr ocena vozaca,
            kome treba najmanje vremena do lokacije, koji vozac je najjeftiniji itd i sad je pitanje na
            koji faktor najvise da obracamo paznju, kaze da je to nama ostavljeno da izaberemo i na odbrani
            cemo prodiskutovati zasto smo bas to nesto izabrali. Musterija moze da vidi svoju istoriju
            voznje i kada je status voznje zavrsena tad moze da oceni vozaca ako nije zavrsena ako jos
            traje ispises samo voznja nije zavrsena. Takodje je potrebno cuvati istoriju aukcija kao
            tekstualni fajl.
*/
public class DodeliVoznjuAukcijom extends JFrame {
    private DefaultTableModel tableModel;
    private JToolBar mainToolBar = new JToolBar();
    public JTable voznjeTabela;
    private JButton btnEdit = new JButton("Dodeli");
    private JButton btnOsvezi = new JButton("Osvezi tabelu");
    public Liste ucitavanje;
    public NarucivanjeVoznjePrekoTelefona voznja;

    public DodeliVoznjuAukcijom(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja) {
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
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().get(i);
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
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> sveVoznjePrekoTelefona = ucitavanje.neobrisaneVoznjeKreiranePutemTelefona();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeTelefonBinarySearch(sveVoznjePrekoTelefona,id);
                    NarucivanjeVoznjePrekoTelefona voznja = sveVoznjePrekoTelefona.get(indexGdeSeNalazi);

                    DoublyLinkedList<Aukcija> aukcija = ucitavanje.getIstorijaAukcija();
                    DoublyLinkedList<String> izborMusterije = new DoublyLinkedList<>();
                    for(Aukcija aukcija1 : aukcija){
                        if(aukcija1.getIDvoznje() == voznja.getId()){
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
                            if(aukcija1.getIDvoznje() == voznja.getId()){
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
                            if(aukcija1.getIDvoznje() == voznja.getId()) {
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
                            if(aukcija1.getIDvoznje() == voznja.getId()) {
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
                            if(aukcija1.getIDvoznje() == voznja.getId()) {
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
                        ProzorZaDodeljivanjeVoznjiAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAukcijom(ucitavanje,voznja,oceneVozaca);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }else if(najnovijiAutomobil.size() != 0){
                        ProzorZaDodeljivanjeVoznjiAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAukcijom(ucitavanje,voznja,najnovijiAutomobil);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }else if(najbrziVozac.size() != 0){
                        ProzorZaDodeljivanjeVoznjiAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAukcijom(ucitavanje,voznja,najbrziVozac);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }else if(listaPetFriendlyAuta.size() != 0){
                        ProzorZaDodeljivanjeVoznjiAukcijom prozorZaDodeljivanjeVoznjiAukcijom = new ProzorZaDodeljivanjeVoznjiAukcijom(ucitavanje,voznja,listaPetFriendlyAuta);
                        prozorZaDodeljivanjeVoznjiAukcijom.setVisible(true);
                    }

                }
            }
        });
        btnOsvezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodeliVoznjuAukcijom.this.setVisible(false);
                DodeliVoznjuAukcijom.this.dispose();
                DodeliVoznjuAukcijom dodeljivanjeVoznje = new DodeliVoznjuAukcijom(ucitavanje,voznja);
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
