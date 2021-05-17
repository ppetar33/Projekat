package dispecer.pretragaVozaca;

import osobe.Vozac;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ProzorZaPrikazRezultataPretragePoImenu extends JFrame {

    private DefaultTableModel table_model;
    private JTable vozaciTabela;

    public ProzorZaPrikazRezultataPretragePoImenu(ArrayList<Vozac> vozaci) {
        setTitle("Prikaz pretrage");
        setSize(1050, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGui(vozaci);
    }

    private void initGui(ArrayList<Vozac> vozaci){
        String[] zaglavnje = new String[] {"Korisnicko ime", "Ime", "Prezime", "Adresa", "Pol", "Broj telefona", "Plata", "Broj clanske karte", "Automobil"};
        Object[][] sadrzaj = new Object[vozaci.size()][zaglavnje.length];
        for (int i = 0; i < vozaci.size(); i++) {
            Vozac vozac = vozaci.get(i);
            if(vozac.isObrisan()) {
                sadrzaj[i][0] = vozac.getKorisnickoIme();
                sadrzaj[i][1] = vozac.getIme().substring(0, 1).toUpperCase() + vozac.getIme().substring(1);
                sadrzaj[i][2] = vozac.getPrezime().substring(0, 1).toUpperCase() + vozac.getPrezime().substring(1);
                sadrzaj[i][3] = vozac.getAdresa();
                sadrzaj[i][4] = vozac.getPol().toString().toLowerCase();
                sadrzaj[i][5] = vozac.getBrojTelefona();
                sadrzaj[i][6] = vozac.getPlata();
                sadrzaj[i][7] = vozac.getBrojClanskeKarte();
                sadrzaj[i][8] = vozac.getAutomobili().getModel();
                if (vozac.getAutomobili().getId() == 0) {
                    sadrzaj[i][8] = "Vozac nema automobil";
                }
            }
        }
        table_model = new DefaultTableModel(sadrzaj, zaglavnje);

        vozaciTabela = new JTable(table_model);

        vozaciTabela.setRowSelectionAllowed(true);
        vozaciTabela.setColumnSelectionAllowed(false);
        vozaciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vozaciTabela.setDefaultEditor(Object.class, null);
        vozaciTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane jsp = new JScrollPane(vozaciTabela);
        add(jsp, BorderLayout.CENTER);
    }
}