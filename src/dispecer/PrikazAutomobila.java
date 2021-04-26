package dispecer;

import automobili.Automobil;
import enumi.Obrisan;
import liste.Liste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Locale;

public class PrikazAutomobila extends JFrame {

    public JToolBar mainToolBar = new JToolBar();

    public DefaultTableModel table_model;
    public JTable automobiliTabela;

    public Liste ucitavanje;

    public PrikazAutomobila(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        setTitle("Prikaz automobila");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI(){
        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavlje = new String[]{"ID", "Model", "Proizvodjac", "Godina proizvodnje", "Broj registarske oznake", "Broj taksi vozila", "Vrsta automobila", "Status Automobila", "Pet Friendly"};
        Object[][] sadrzaj = new Object[ucitavanje.getAutomobili().size()][zaglavlje.length];
        for (int i = 0; i < ucitavanje.getAutomobili().size(); i ++){
            Automobil automobil = ucitavanje.getAutomobili().get(i);
            if (automobil.getObrisan() == Obrisan.TRUE){
                sadrzaj[i][0] = automobil.getId();
                sadrzaj[i][1] = automobil.getModel();
                sadrzaj[i][2] = automobil.getProizvodjac();
                sadrzaj[i][3] = automobil.getGodinaProizvodnje();
                sadrzaj[i][4] = automobil.getRegistarskiBroj();
                sadrzaj[i][5] = automobil.getBrojVozila();
                sadrzaj[i][6] = automobil.getVrstaVozila().toString().toLowerCase().replace("_"," ");
                sadrzaj[i][7] = automobil.getStatusAutomobila().toString().toLowerCase();
                sadrzaj[i][8] = automobil.getPetFriendly().toString().toLowerCase();
            }
        }

        table_model = new DefaultTableModel(sadrzaj, zaglavlje);
        automobiliTabela = new JTable(table_model);

        automobiliTabela.setRowSelectionAllowed(true);
        automobiliTabela.setColumnSelectionAllowed(false);
        automobiliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        automobiliTabela.setDefaultEditor(Object.class, null);
        automobiliTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(automobiliTabela);
        add(jsp, BorderLayout.CENTER);
    }
}
