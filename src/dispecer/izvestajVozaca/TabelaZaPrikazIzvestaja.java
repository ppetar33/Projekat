package dispecer.izvestajVozaca;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDateTime;

public class TabelaZaPrikazIzvestaja extends JFrame {

    public JToolBar mainToolBar = new JToolBar();
    public DefaultTableModel table_model;
    public JTable izvestajiTabela;
    public Liste ucitavanje;


//    public TabelaZaPrikazIzvestaja(Liste ucitavanje){
//        this.ucitavanje = ucitavanje;
//        setTitle("Izvestaji vozaca");
//        setSize(1050,300);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        initGUI(ukupanBrojVoznjiAplikacija, ukupnoKilometara, prosekKilometara, ukupnoTrajanje, prosekTrajanja, ukupnaZarada, prosecnaZarada, prosecnoBezVoznje);
//    }

    private void initGUI(int ukupanBrojVoznjiAplikacija, double ukupnoKilometara, double prosekKilometara, double ukupnoTrajanje, double prosekTrajanja, double ukupnaZarada, double prosecnaZarada, double prosecnoBezVoznje) {
        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavlje = new String[]{"Vozac", "Ukupan broj voznji", "Ukupan broj predjenih kilometara", "Ukupno trajanje voznji", "Prosecan broj predjenih km po voznji", "Prosecno trajanje voznje", "Prosecno bez voznje", "Ukupna zarada", "Prosecna zarada"};
        Object[][] sadrzaj = new Object[ucitavanje.zavrsenePutemAplikacije().size()][zaglavlje.length];
        for (int i = 0; i < ucitavanje.zavrsenePutemAplikacije().size(); i ++){
            NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije = ucitavanje.zavrsenePutemAplikacije().get(i);
            if (voznjePrekoAplikacije.isObrisan()){
                sadrzaj[i][0] = voznjePrekoAplikacije.getVozac().getKorisnickoIme();
                sadrzaj[i][1] = ukupanBrojVoznjiAplikacija;
                sadrzaj[i][2] = ukupnoKilometara;
                sadrzaj[i][3] = ukupnoTrajanje;
                sadrzaj[i][4] = prosekKilometara;
                sadrzaj[i][5] = prosekTrajanja;
                sadrzaj[i][6] = prosecnoBezVoznje;
                sadrzaj[i][7] = ukupnaZarada;
                sadrzaj[i][8] = prosecnaZarada;

            }
        }


        table_model = new DefaultTableModel(sadrzaj, zaglavlje);
        izvestajiTabela = new JTable(table_model);

        izvestajiTabela.setRowSelectionAllowed(true);
        izvestajiTabela.setColumnSelectionAllowed(false);
        izvestajiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        izvestajiTabela.setDefaultEditor(Object.class, null);
        izvestajiTabela.getTableHeader().setReorderingAllowed(false);
        izvestajiTabela.setVisible(true);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        izvestajiTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(izvestajiTabela);
        add(jsp, BorderLayout.CENTER);
    }
}
