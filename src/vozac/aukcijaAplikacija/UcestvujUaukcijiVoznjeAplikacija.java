package vozac.aukcijaAplikacija;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import aukcija.Aukcija;
import vozac.aukcijaAplikacija.ProzorZaUnosVremenaVozacaAplikacijom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class UcestvujUaukcijiVoznjeAplikacija extends JFrame {

    private JButton btnUcestvujUaukciji = new JButton("Ucestvuj u aukciji");
    private DefaultTableModel tableModel;
    private JToolBar mainToolBar = new JToolBar();
    private JTable voznjeTabela;
    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoAplikacije voznja;

    public UcestvujUaukcijiVoznjeAplikacija(Liste ucitavanje, NarucivanjeVoznjePrekoAplikacije voznja){
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Aukcija");
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
        btnUcestvujUaukciji.setIcon(deleteIcon);
        mainToolBar.add(btnUcestvujUaukciji);
        add(btnUcestvujUaukciji, BorderLayout.NORTH);
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
        btnUcestvujUaukciji.addActionListener(new ActionListener() {
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
                    NarucivanjeVoznjePrekoAplikacije trazenaVoznja = sveVoznjePrekoAplikacije.get(indexGdeSeNalazi);

                    DoublyLinkedList<Aukcija> aukcijaDoublyLinkedList = ucitavanje.getIstorijaAukcija();
                    DoublyLinkedList<String> listaVozacaKojiUcestvujuUaukciji = new DoublyLinkedList<>();
                    String ulogovanVozac = ucitavanje.ulogovanKorisnik();
                    for(Aukcija aukcija : aukcijaDoublyLinkedList){
                        if(trazenaVoznja.getId() == aukcija.getIDvoznje() && aukcija.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA)){
                            listaVozacaKojiUcestvujuUaukciji.add(aukcija.getVozacKojiUcestvujeUaukciji());
                        }
                    }
                    DoublyLinkedList<String> poredjenjeVozaca = new DoublyLinkedList<>();
                    for(String s : listaVozacaKojiUcestvujuUaukciji){
                        if (ulogovanVozac.equals(s)){
                            poredjenjeVozaca.add(s);
                        }
                    }
                    if(poredjenjeVozaca.isEmpty()) {
                        ProzorZaUnosVremenaVozacaAplikacijom prozorZaUnosVremenaVozaca = new ProzorZaUnosVremenaVozacaAplikacijom(ucitavanje, trazenaVoznja);
                        prozorZaUnosVremenaVozaca.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Za datu voznju, vec ucestvujete u aukciji, pokusajte sa drugom voznjom.","Obavestenje",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }
}
