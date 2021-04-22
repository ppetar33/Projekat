package musterija;

import automobili.Voznja;
import enumi.StatusVoznje;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Vozac;
import podaci.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class NarucivanjeVoznjePrekoTelefona extends JFrame {

    //Adresa polaska
    private JLabel adresaPolaska = new JLabel("Adresa polaska");
    private JTextField tadresaPolaska = new JTextField(20);
    //Adresa dolaska
    private JLabel adresaDolaska = new JLabel("Adresa dolaska");
    private JTextField tadresaDolaska = new JTextField(20);

    private JButton naruci = new JButton("Naruci");
    private JButton odustani = new JButton("Odustani");

    private Liste ucitavanje;
    private Voznja voznja;
    private Musterija musterija;

    public NarucivanjeVoznjePrekoTelefona(Liste ucitavanje,Musterija musterija){
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
        add(new JLabel());
        add(naruci,"split 2");
        add(odustani);

    }

    private void initListeners(){
        naruci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                    int id = ucitavanje.generisiNoviIdZaVoznje();
                    LocalDateTime trenutnoVreme = LocalDateTime.now();

                    String adresaPolaska = tadresaPolaska.getText().trim();
                    String adresaDolaska = tadresaDolaska.getText().trim();

                    //vozac koji je preuzeo voznju
                    //broj km predjenih u voznji
                    //trajanje voznje


                    if(voznja != null){
                        voznja.setId(id);
                        voznja.setDatumIvremePorudzbine(trenutnoVreme);
                        voznja.setAdresaPolaska(adresaPolaska);
                        voznja.setAdresaDestinacije(adresaDolaska);
                    }
                    Vozac vozac = new Vozac();
                    try {
                        File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                        Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                        while (citanjeUlogovanogKorisnika.hasNextLine()) {
                            String data = citanjeUlogovanogKorisnika.nextLine();
                            Musterija ulogovanaMusterija = new Musterija();
                            ulogovanaMusterija.setKorisnickoIme(data);
                            Voznja voznja = new Voznja(id,trenutnoVreme,adresaPolaska,adresaDolaska,ulogovanaMusterija,vozac,12,12,StatusVoznje.KREIRANA);
                            ucitavanje.getVoznja().add(voznja);
                        }
                        citanjeUlogovanogKorisnika.close();
                    }  catch (IOException ioException) {
                        ioException.printStackTrace();
                        System.out.println("Greska");
                    }

                    JOptionPane.showMessageDialog(null,"Uspesno ste narucili voznju!","Cestitam",JOptionPane.INFORMATION_MESSAGE);
                    ucitavanje.snimanjeVoznji("voznje.txt");
                    NarucivanjeVoznjePrekoTelefona.this.dispose();
                    NarucivanjeVoznjePrekoTelefona.this.setVisible(false);
                }
            }
        });
        odustani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od narucivanja voznje!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                NarucivanjeVoznjePrekoTelefona.this.setVisible(false);
                NarucivanjeVoznjePrekoTelefona.this.dispose();
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
        if(ok == false) {
            JOptionPane.showMessageDialog(null, porukaObavestenja, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;

    }

}
