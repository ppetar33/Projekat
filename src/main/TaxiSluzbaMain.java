package main;

import gui.LoginProzor;
import osobe.Odeljenje;
import ucitavanje.Ucitavanje;

public class TaxiSluzbaMain {

    private static final String KORISNICI_FAJL = "korisnici.txt";

    public static void main(String[] args) {


        Ucitavanje ucitavanje = new Ucitavanje();
        ucitavanje.ucitajZaposlene(KORISNICI_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);


        Odeljenje prvo = Odeljenje.ODELJENJE_ZA_PRIJEM_VOZNJI;
        Odeljenje drugo = Odeljenje.ODELJENJE_ZA_REKLAMACIJE;
        Odeljenje treci = Odeljenje.ODELJENJE_ZA_PRIJEM_VOZNJI;
    }

}
