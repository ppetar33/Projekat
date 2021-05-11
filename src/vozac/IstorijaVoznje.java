package vozac;

import automobili.Voznja;
import enumi.StatusVoznje;
import liste.Liste;
import osobe.Vozac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class IstorijaVoznje extends JFrame {
    //Prikaz istorije sopstvenih vožnji.
    private JToolBar mainJToolBar = new JToolBar();

    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Vozac prijavljaniVozac;

    public IstorijaVoznje(Liste ucitavanje, Vozac prijavljaniVozac){
        this.ucitavanje = ucitavanje;
        this.prijavljaniVozac = prijavljaniVozac;
        setTitle("Prikaz istorije sopstvene voznje");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI() {
        add(mainJToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.getVoznja().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.getVoznja().size(); i++){
            Voznja voznje = ucitavanje.getVoznja().get(i);
            if(voznje.getStatusVoznje() == StatusVoznje.ZAVRSENA){
                sadrzaj[i][0] = voznje.getId();
                sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[i][2] = voznje.getAdresaPolaska();
                sadrzaj[i][3] = voznje.getAdresaDestinacije();
                sadrzaj[i][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
                sadrzaj[i][5] = voznje.getBrojKMpredjenih();
                sadrzaj[i][6] = voznje.getTrajanjVoznje();
                sadrzaj[i][7] = voznje.getStatusVoznje();
            }
        }
        table_model = new DefaultTableModel(sadrzaj, zaglavnje);
        istorijaVoznjeTabela = new JTable(table_model);

        istorijaVoznjeTabela.setRowSelectionAllowed(true);
        istorijaVoznjeTabela.setColumnSelectionAllowed(false);
        istorijaVoznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        istorijaVoznjeTabela.setDefaultEditor(Object.class, null);
        istorijaVoznjeTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(istorijaVoznjeTabela);
        add(jsp, BorderLayout.CENTER);
    }

}
