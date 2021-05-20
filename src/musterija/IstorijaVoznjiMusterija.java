package musterija;

import automobili.Voznja;
import enumi.StatusVoznje;
import liste.Liste;
import osobe.Musterija;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IstorijaVoznjiMusterija extends JFrame {

    private JToolBar mainJToolBar = new JToolBar();

    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Musterija ulogovanaMusterija;

    public IstorijaVoznjiMusterija(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.ulogovanaMusterija = ulogovanaMusterija;
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
        Object[][] sadrzaj = new Object[ucitavanje.getVoznjaTelefoni().size()][zaglavnje.length];
        int j = 0;
        for (int i = 0; i < ucitavanje.getVoznjaTelefoni().size(); i ++){
            Voznja voznje = ucitavanje.getVoznjaTelefoni().get(i);

            Musterija ulogovanaMusterija = null;
            try {
                File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                while (citanjeUlogovanogKorisnika.hasNextLine()) {
                    String data = citanjeUlogovanogKorisnika.nextLine();
                    ulogovanaMusterija = new Musterija();
                    ulogovanaMusterija.setKorisnickoIme(data);
                }
                citanjeUlogovanogKorisnika.close();
            }  catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Greska");
            }

            if(voznje.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznje.getMusterija().getKorisnickoIme().equals(ulogovanaMusterija.getKorisnickoIme())){
                sadrzaj[j][0] = voznje.getId();
                sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[j][2] = voznje.getAdresaPolaska();
                sadrzaj[j][3] = voznje.getAdresaDestinacije();
                sadrzaj[j][4] = voznje.getVozac().getIme().substring(0,1).toUpperCase() + voznje.getVozac().getIme().substring(1);
                sadrzaj[j][5] = voznje.getBrojKMpredjenih();
                sadrzaj[j][6] = voznje.getTrajanjVoznje();
                sadrzaj[j][7] = voznje.getStatusVoznje();
//                sadrzaj[j][8] = voznje.getNapomena();
                j++;
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
