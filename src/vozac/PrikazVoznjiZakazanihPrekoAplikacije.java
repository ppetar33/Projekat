package vozac;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import liste.Liste;
import musterija.NarucivanjeVoznjePrekoAplikacije;
import osobe.Vozac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrikazVoznjiZakazanihPrekoAplikacije extends JFrame {
    /*
        Prikaz svih vožnji zakazanih putem aplikacije kao i mogućnost da se “prihvati” ili “odbije”
        određena vožnja. Prihvatanjem vožnje menja se status iz KREIRANA u PRIHVAĆENA, a odbijanjem
        vožnje menja se status iz KREIRANA u ODBIJENA.
    */
    private JToolBar mainJToolBar = new JToolBar();
    private JButton btnPrihvati = new JButton();
    private JButton btnOdbi = new JButton();



    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Vozac ulogovaniVozac;


    public PrikazVoznjiZakazanihPrekoAplikacije(Liste ucitavanje, Vozac ulogovaniVozac){
        this.ucitavanje = ucitavanje;
        this.ulogovaniVozac = ulogovaniVozac;
        btnPrihvati.setText("Prihvati");
        btnOdbi.setText("Odbi");
        setTitle("Prikaz istorije sopstvene voznje");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
//        initListeners();
    }


    private void initGUI(){
        add(mainJToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Broj predjenih km","Trajanje voznje","Status voznje", "Napomena", "Prihvati", "Odbi"};
        Object[][] sadrzaj = new Object[ucitavanje.getVoznja().size()][zaglavnje.length];
        int j = 0;
        for(int i = 0; i < ucitavanje.getVoznja().size(); i++){
            Voznja voznje = ucitavanje.getVoznja().get(i);

            Vozac ulogovaniVozac = null;
            try {
                File ulogovanVozac = new File("src/fajlovi/ulogovanKorisnik.txt");
                Scanner citanjeUlogovanogVozaca = new Scanner(ulogovanVozac);
                while (citanjeUlogovanogVozaca.hasNextLine()) {
                    String data = citanjeUlogovanogVozaca.nextLine();
                    ulogovaniVozac = new Vozac();
                    ulogovaniVozac.setKorisnickoIme(data);
                }
                citanjeUlogovanogVozaca.close();
            }  catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Greska");
            }

            System.out.println(voznje.getClass());
            //NE RADI ZATO STO DOBIJAM SAMO OSTALE VOZNJE instanceof
            if(voznje.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA) && voznje.getVozac().getKorisnickoIme().equals(ulogovaniVozac.getKorisnickoIme())){
                sadrzaj[j][0] = voznje.getId();
                sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[j][2] = voznje.getAdresaPolaska();
                sadrzaj[j][3] = voznje.getAdresaDestinacije();
                sadrzaj[j][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
                sadrzaj[j][5] = voznje.getBrojKMpredjenih();
                sadrzaj[j][6] = voznje.getTrajanjVoznje();
                sadrzaj[j][7] = voznje.getStatusVoznje();
                sadrzaj[j][8] = voznje.getNapomena();
                sadrzaj[j][9] = btnPrihvati;
                sadrzaj[j][10] = btnOdbi;
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

//    private void initListeners() {
//        btnPrihvati.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //iz kreirana u prihvacena
//
//            }
//        });
//        btnOdbi.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //iz kreirana u odbijena
//            }
//        });
//    }

}
