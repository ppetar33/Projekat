package dispecer.podaciVoznjePrekoAplikacije;

import automobili.Voznja;
import enumi.StatusVoznje;
import liste.Liste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class PrikazVoznjiPutemAplikacije extends JFrame {


    private JToolBar mainJtoolBar = new JToolBar();

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;

    private Liste ucitavanje;

    public PrikazVoznjiPutemAplikacije(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;
        setTitle("Prikaz voznji narucenih putem aplikacije");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGui();
    }

    private void initGui(){
        add(mainJtoolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().get(i);
            if(voznje.getStatusVoznje() == StatusVoznje.KREIRANA_NA_CEKANJU){
                sadrzaj[i][0] = voznje.getId();
                sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                sadrzaj[i][2] = voznje.getAdresaPolaska();
                sadrzaj[i][3] = voznje.getAdresaDestinacije();
                sadrzaj[i][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
                sadrzaj[i][5] = voznje.getVozac().getIme().substring(0,1).toUpperCase() + voznje.getVozac().getIme().substring(1);
                sadrzaj[i][6] = voznje.getBrojKMpredjenih();
                sadrzaj[i][7] = voznje.getTrajanjVoznje();
                sadrzaj[i][8] = voznje.getStatusVoznje();
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
}
