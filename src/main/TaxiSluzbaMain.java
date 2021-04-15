package main;

import gui.LoginProzor;
import osobe.Odeljenje;
import osobe.Vozac;
import ucitavanje.Ucitavanje_i_Snimanje;

public class TaxiSluzbaMain {

    private static final String KORISNICI_FAJL = "korisnici.txt";
    private static final String automobil = "automobil.txt";

    public static void main(String[] args) {


        Ucitavanje_i_Snimanje ucitavanje = new Ucitavanje_i_Snimanje();
        ucitavanje.ucitajKorisnike(KORISNICI_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);


        Odeljenje prvo = Odeljenje.ODELJENJE_ZA_PRIJEM_VOZNJI;
        Odeljenje drugo = Odeljenje.ODELJENJE_ZA_REKLAMACIJE;
        Odeljenje treci = Odeljenje.ODELJENJE_ZA_PRIJEM_VOZNJI;

        Ucitavanje_i_Snimanje prikazAutomobila = new Ucitavanje_i_Snimanje();
        prikazAutomobila.ucitajAutomobila(automobil);
        System.out.println(prikazAutomobila.getAutomobil());


        /*
            PRIKAZ VOZACA
        */

//        Ucitavanje_i_Snimanje prikazVozaca = new Ucitavanje_i_Snimanje();
//        prikazVozaca.ucitajKorisnike(KORISNICI_FAJL);
//        System.out.println(prikazVozaca.getVozaci());
    }

}
