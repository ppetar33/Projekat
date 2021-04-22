package dispecer;

import automobili.Automobil;
import enumi.Obrisan;
import podaci.Liste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrikazAutomobila extends JFrame {

    public JToolBar mainToolBar = new JToolBar();

    public DefaultTableModel table_model;
    public JTable automobiliTabela;

    public Liste ucitavanje;

    public PrikazAutomobila(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        setTitle("Prikaz automobila");
        setSize(1000,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }

    public void initGUI(){
        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavlje = new String[]{"Model", "Proizvodjac", "Godina proizvodnje", "Broj registarske oznake", "Broj taksi vozila", "Vrsta automobila"};
        Object[][] sadrzaj = new Object[ucitavanje.getAutomobili().size()][zaglavlje.length];
        for (int i = 0; i < ucitavanje.getVozaci().size(); i ++){
            Automobil automobil = ucitavanje.getAutomobili().get(i);
            if (automobil.getObrisan() == Obrisan.TRUE){
                sadrzaj[i][0] = automobil.getModel();
                sadrzaj[i][1] = automobil.getProizvodjac();
                sadrzaj[i][2] = automobil.getGodinaProizvodnje();
                sadrzaj[i][3] = automobil.getRegistarskiBroj();
                sadrzaj[i][4] = automobil.getBrojVozila();
                sadrzaj[i][5] = automobil.getVrstaVozila();
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
