package musterija;

import automobili.Voznja;
import enumi.StatusVoznje;
import liste.Liste;
import osobe.Musterija;
import osobe.Osoba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class IstorijaVoznjiMusterija extends JFrame {

    private JToolBar mainJToolBar = new JToolBar();

    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Musterija musterija;

    public IstorijaVoznjiMusterija(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.musterija = musterija;
        setTitle("Prikaz istorije sopstvene voznje" + "(" + musterija.getIme().substring(0,1).toUpperCase() + musterija.getIme().substring(1) + ")");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI() {
        add(mainJToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Vozac","Broj predjenih km","Trajanje voznje","Status voznje", "Napomena"};
        Object[][] sadrzaj = new Object[ucitavanje.getVoznja().size()][zaglavnje.length];
        for (int i = 0; i < ucitavanje.getVoznja().size(); i ++){
            Voznja voznje = ucitavanje.getVoznja().get(i);

            try {
                File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                while (citanjeUlogovanogKorisnika.hasNextLine()) {
                    String data = citanjeUlogovanogKorisnika.nextLine();
                    Musterija ulogovanaMusterija = new Musterija();
                    ulogovanaMusterija.setKorisnickoIme(data);
                }
                citanjeUlogovanogKorisnika.close();
            }  catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Greska");
            }
            //TODO: PRIKAZ SAMO VOZNJI OD KORISNIKA KOJI JE PRIJAVLJEN
            if(voznje.getStatusVoznje() == StatusVoznje.ZAVRSENA){
                sadrzaj[i][0] = voznje.getId();
                sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[i][2] = voznje.getAdresaPolaska();
                sadrzaj[i][3] = voznje.getAdresaDestinacije();
                sadrzaj[i][4] = voznje.getVozac().getIme().substring(0,1).toUpperCase() + voznje.getVozac().getIme().substring(1);
                sadrzaj[i][5] = voznje.getBrojKMpredjenih();
                sadrzaj[i][6] = voznje.getTrajanjVoznje();
                sadrzaj[i][7] = voznje.getStatusVoznje();
                //sadrzaj[i][8] = NarucivanjeVoznjePrekoAplikacije.getNapomena();
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
