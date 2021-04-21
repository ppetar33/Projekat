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

    private void initGUI(){
        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavlje = new String[]{"Model", "Proizvodjac", "Godina proizvodnje", "Broj registarske oznake", "Broj taksi vozila", "Vrsta automobila"};
        Object[][] sadrzaj = new Object[ucitavanje.getAutomobili().size()][zaglavlje.length];
        for (int i = 0; i < ucitavanje.getVozaci().size(); i ++){
            Automobil automobil = ucitavanje.getAutomobili().get(i);
            if (automobil.getObrisan() == Obrisan.TRUE){

            }
        }
    }
}
