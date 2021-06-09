package dispecer.izvestajVozaca;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import osobe.Vozac;

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

    String[] zaglavlje = new String[]{"Vozac", "Ukupan broj voznji", "Ukupan broj predjenih kilometara", "Ukupno trajanje voznji", "Prosecan broj predjenih km po voznji", "Prosecno trajanje voznje", "Prosecno bez voznje", "Ukupna zarada", "Prosecna zarada"};
    Object[][] sadrzaj = new Object[ucitavanje.dohvatiVozace().size()][zaglavlje.length];

    public TabelaZaPrikazIzvestaja(int rb, String trenutniVozac, int ukupnoVoznji, double ukupnoKilometara, double ukupnoTrajanje, double prosekKilometara, double prosekTrajanja, double prosecnoBezVoznje, double ukupnaZarada, double prosecnaZarada){
        setTitle("Izvestaji vozaca");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        popunjavanjePolja(rb, trenutniVozac, ukupnoVoznji, ukupnoKilometara, ukupnoTrajanje, prosekKilometara, prosekTrajanja, prosecnoBezVoznje, ukupnaZarada, prosecnaZarada);
    }

    private void initGUI() {
        add(mainToolBar, BorderLayout.SOUTH);

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

    public void popunjavanjePolja(int rb, String trenutniVozac, int ukupnoVoznji, double ukupnoKilometara, double ukupnoTrajanje, double prosekKilometara, double prosekTrajanja, double prosecnoBezVoznje, double ukupnaZarada, double prosecnaZarada) {
        sadrzaj[rb][0] = trenutniVozac;
        sadrzaj[rb][1] = ukupnoVoznji;
        sadrzaj[rb][2] = ukupnoKilometara;
        sadrzaj[rb][3] = ukupnoTrajanje;
        sadrzaj[rb][4] = prosekKilometara;
        sadrzaj[rb][5] = prosekTrajanja;
        sadrzaj[rb][6] = prosecnoBezVoznje;
        sadrzaj[rb][7] = ukupnaZarada;
        sadrzaj[rb][8] = prosecnaZarada;
    }
}

