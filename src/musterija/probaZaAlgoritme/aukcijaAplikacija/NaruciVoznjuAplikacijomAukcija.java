package musterija.probaZaAlgoritme.aukcijaAplikacija;

import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Vozac;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class NaruciVoznjuAplikacijomAukcija extends JFrame {

    private JLabel adresaPolaska = new JLabel("Adresa polaska");
    private JTextField tadresaPolaska = new JTextField(29);
    private JLabel adresaDolaska = new JLabel("Adresa dolaska");
    private JTextField tadresaDolaska = new JTextField(29);
    private JLabel napomena = new JLabel("Napomena");
    private JTextField tnapomena = new JTextField(29);
    private JLabel izaberi = new JLabel("Izaberi");
    private JComboBox<String> mogucnosti;
    private JButton naruci = new JButton("Naruci");
    private JButton odustani = new JButton("Odustani");
    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije;
    private Musterija musterija;

    public NaruciVoznjuAplikacijomAukcija(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.musterija = musterija;
        setTitle(musterija.getIme().substring(0,1).toUpperCase() + musterija.getIme().substring(1) + ", Naruci voznju telefonom");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGui();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGui(){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(adresaPolaska);
        add(tadresaPolaska);
        add(adresaDolaska);
        add(tadresaDolaska);
        add(napomena);
        add(tnapomena);
        String[] mogucnostiZaBiranje = new String[]{"Svejedno","Najbrzi vozac","Najbolje ocenjen vozac","Pet friendly automobil","Najnoviji automobil","Najiskusniji vozac"};
        mogucnosti = new JComboBox(mogucnostiZaBiranje);
        add(izaberi);
        add(mogucnosti);
        add(new JLabel());
        add(naruci,"split 2");
        this.getRootPane().setDefaultButton(naruci);
        add(odustani);
    }
    private void initListeners(){
        naruci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                    int id = ucitavanje.generisiNoviIdZaVoznjePutemAplikacije();
                    LocalDateTime trenutnoVreme = LocalDateTime.now();
                    String adresaPolaska = tadresaPolaska.getText().trim();
                    String adresaDolaska = tadresaDolaska.getText().trim();
                    String napomena = tnapomena.getText().trim();

                    String selektovanaMogucnost = (String) mogucnosti.getSelectedItem();

                    Vozac vozac = new Vozac();
                    vozac.setKorisnickoIme("");
                    if(narucivanjeVoznjePrekoAplikacije != null){
                        narucivanjeVoznjePrekoAplikacije.setId(id);
                        narucivanjeVoznjePrekoAplikacije.setDatumIvremePorudzbine(trenutnoVreme);
                        narucivanjeVoznjePrekoAplikacije.setAdresaPolaska(adresaPolaska);
                        narucivanjeVoznjePrekoAplikacije.setAdresaDestinacije(adresaDolaska);
                        narucivanjeVoznjePrekoAplikacije.setVozac(vozac);
                    }
                    try {
                        File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                        Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                        while (citanjeUlogovanogKorisnika.hasNextLine()) {
                            String data = citanjeUlogovanogKorisnika.nextLine();
                            Musterija ulogovanaMusterija = new Musterija();
                            ulogovanaMusterija.setKorisnickoIme(data);
                            NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije = new NarucivanjeVoznjePrekoAplikacije(id,trenutnoVreme,adresaPolaska,adresaDolaska,ulogovanaMusterija,vozac,0,0, StatusVoznje.KREIRANA_NA_CEKANJU,true, StatusNaruceneVoznje.APLIKACIJA,0,false,selektovanaMogucnost,napomena);
                            DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sveVoznje = ucitavanje.getSortiranaListaVoznjiAplikacija();
                            sveVoznje.add(narucivanjeVoznjePrekoAplikacije);
                            ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                        }
                        citanjeUlogovanogKorisnika.close();
                    }  catch (IOException ioException) {
                        ioException.printStackTrace();
                        System.out.println("Greska");
                    }

                    JOptionPane.showMessageDialog(null,"Uspesno ste narucili voznju!","Cestitam",JOptionPane.INFORMATION_MESSAGE);
                    NaruciVoznjuAplikacijomAukcija.this.dispose();
                    NaruciVoznjuAplikacijomAukcija.this.setVisible(false);
                }
            }
        });
        odustani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od narucivanja voznje!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                NaruciVoznjuAplikacijomAukcija.this.setVisible(false);
                NaruciVoznjuAplikacijomAukcija.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String porukaObavestenja = "Molimo vas ispravite sta je potrebno! \n";
        if(tadresaPolaska.getText().equals("")){
            porukaObavestenja += "Polje za adresu polaska ne sme biti prazno! \n";
            ok = false;
        }
        if(tadresaDolaska.getText().equals("")){
            porukaObavestenja += "Polje za adresu dolaska ne sme biti prazno! \n";
            ok = false;
        }
        if (napomena.getText().trim().equals("")) {
            porukaObavestenja += "Polje za napomenu ne sme biti prazno! \n";
            ok = false;
        }
        if(ok == false) {
            JOptionPane.showMessageDialog(null, porukaObavestenja, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;

    }
}
