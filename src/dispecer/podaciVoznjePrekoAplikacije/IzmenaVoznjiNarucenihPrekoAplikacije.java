package dispecer.podaciVoznjePrekoAplikacije;

import automobili.Automobil;
import automobili.Voznja;
import dispecer.podaciAutomobila.ProzorZaIzmenuAutomobila;
import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import liste.Liste;
import musterija.NarucivanjeVoznjePrekoAplikacije;
import musterija.NarucivanjeVoznjePrekoTelefona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class IzmenaVoznjiNarucenihPrekoAplikacije extends JFrame {

    private JToolBar mainJtoolBar = new JToolBar();

    private DefaultTableModel tableModel;
    private JToolBar mainToolBar = new JToolBar();
    private JTable voznjeTabela;
    private JButton btnEdit = new JButton("Izmeni");

    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoAplikacije voznja;

    public IzmenaVoznjiNarucenihPrekoAplikacije(Liste ucitavanje, NarucivanjeVoznjePrekoAplikacije voznja){
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Izmena voznji kreiranih putem aplikacije");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGui();
        initListeners();
        initTable();
    }

    private void initGui(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(deleteIcon);
        mainJtoolBar.add(btnEdit);
        add(mainJtoolBar, BorderLayout.NORTH);
    }

    private void initTable(){
        add(mainJtoolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje", "Napmena"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size()][zaglavnje.length];
        int j = 0;
        for(int i = 0; i < ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().get(i);
            if(voznje.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA)){
                sadrzaj[j][0] = voznje.getId();
                sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[j][2] = voznje.getAdresaPolaska();
                sadrzaj[j][3] = voznje.getAdresaDestinacije();
                sadrzaj[j][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
                sadrzaj[j][5] = voznje.getBrojKMpredjenih();
                sadrzaj[j][6] = voznje.getTrajanjVoznje();
                sadrzaj[j][7] = voznje.getStatusVoznje();
                sadrzaj[j][9] = voznje.getNapomena();
                j ++;
            }
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavnje);
        voznjeTabela = new JTable(tableModel);

        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(voznjeTabela);
        add(jsp, BorderLayout.CENTER);
    }

    private void initListeners() {
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == - 1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel)voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    Voznja voznja = ucitavanje.nadjiVoznju();
                    if (voznja != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da izmenite voznju? ", "Potvrda brisanja", JOptionPane.YES_NO_OPTION );
                        if (izbor == JOptionPane.YES_OPTION){
                            ProzorZaIzmenuVoznjiPutemAplikacije prozorZaIzmenuVoznjiPutemAplikacije = new ProzorZaIzmenuVoznjiPutemAplikacije(ucitavanje, (NarucivanjeVoznjePrekoAplikacije) voznja);
                            prozorZaIzmenuVoznjiPutemAplikacije.setVisible(true);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu voznju!", "Greska", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });
    }
}
