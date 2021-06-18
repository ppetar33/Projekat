package dispecer.izvestajVozaca;

import liste.doublyLinkedList.DoublyLinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TabelaPrikaz extends JFrame {

    private JToolBar mainToolBar = new JToolBar();
    private DefaultTableModel table_model = new DefaultTableModel();
    private JTable izvestajiTabela = new JTable();

    public TabelaPrikaz(DoublyLinkedList<Izvestaji> tests) {
        setTitle("Prikaz izvestaja");
        setSize(1000, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI(tests);
    }

    private void initGUI(DoublyLinkedList<Izvestaji> tests) {
        String[] zaglavlje = new String[]{"Vozac", "Ukupan broj voznji", "Ukupan broj predjenih kilometara", "Ukupno trajanje voznji", "Prosecan broj predjenih km po voznji", "Prosecno trajanje voznje", "Prosecno bez voznje", "Ukupna zarada", "Prosecna zarada"};

        add(mainToolBar, BorderLayout.SOUTH);
        izvestajiTabela.setModel(table_model);
        table_model.setColumnIdentifiers(zaglavlje);

        for(Izvestaji x : tests) {
            Object[] o = new Object[9];
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            o[0] = x.getTrenutniVozac();
            o[1] = x.getUkupnoVoznji();
            o[2] = x.getUkupnoKilometara();
            o[3] = x.getUkupnoTrajanje();
            o[4] = df.format(x.getProsekKilometara());
            o[5] = df.format(x.getProsekTrajanja());
            o[6] = df.format(x.getProsecnoBezVoznje());
            o[7] = x.getUkupnaZarada();
            o[8] = df.format(x.getProsecnaZarada());
            table_model.addRow(o);
        }

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
