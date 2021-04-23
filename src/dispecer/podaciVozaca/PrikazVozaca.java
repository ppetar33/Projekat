package dispecer.podaciVozaca;

import enumi.Obrisan;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrikazVozaca extends JFrame {

    public JToolBar mainToolBar = new JToolBar();

    public DefaultTableModel table_model;
    public JTable vozaciTabela;

    public Liste ucitavanje;
    public Vozac vozac;

    public PrikazVozaca(Liste ucitavanje,Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Prikaz vozaca");
        setSize(1000, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI(){
        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"Korisnicko ime", "Ime", "Prezime", "Adresa", "Pol", "Broj telefona", "Plata", "Broj clanske karte", "Automobil"};
        Object[][] sadrzaj = new Object[ucitavanje.getVozaci().size()][zaglavnje.length];
        for (int i = 0; i < ucitavanje.getVozaci().size(); i++){
            Vozac vozac = ucitavanje.getVozaci().get(i);
            if(vozac.getObrisan() == Obrisan.TRUE){
                sadrzaj[i][0] = vozac.getKorisnickoIme();
                sadrzaj[i][1] = vozac.getIme().substring(0, 1).toUpperCase() + vozac.getIme().substring(1);
                sadrzaj[i][2] = vozac.getPrezime().substring(0, 1).toUpperCase() + vozac.getPrezime().substring(1);
                sadrzaj[i][3] = vozac.getAdresa();
                sadrzaj[i][4] = vozac.getPol().toString().toLowerCase();
                sadrzaj[i][5] = vozac.getBrojTelefona();
                sadrzaj[i][6] = vozac.getPlata();
                sadrzaj[i][7] = vozac.getBrojClanskeKarte();
                sadrzaj[i][8] = vozac.getAutomobili().getModel();
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
