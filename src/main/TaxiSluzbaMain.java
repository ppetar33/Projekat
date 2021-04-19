package main;

import gui.LoginProzor;
import osobe.Odeljenje;
import podaci.Liste;


public class TaxiSluzbaMain {

    private static final String KORISNICI_FAJL = "korisnici.txt";
    private static final String AUTOMOBILI_FAJL = "automobil.txt";
    private static final String TAKSI_SLUZBA_FAJL = "taksiSluzba.txt";
    private static final String VOZNJE_FAJL = "voznje.txt";

    // ucitati sve podatke
    // identifikator mora da postoji i to je deo iz algoritama
    // liste i mape su deo iz algoritama, pitati asistenta
    // putem aplikacije se unosi dodatna napomena i to je jedina razlika
    // za izvestaje pitati asistenta iz algoritama
    // pretrage algoritmi

    public static void main(String[] args) {


        Liste ucitavanje = new Liste();
        ucitavanje.ucitajKorisnike(KORISNICI_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);


        Odeljenje prvo = Odeljenje.ODELJENJE_ZA_PRIJEM_VOZNJI;
        Odeljenje drugo = Odeljenje.ODELJENJE_ZA_REKLAMACIJE;
        Odeljenje treci = Odeljenje.ODELJENJE_ZA_PRIJEM_VOZNJI;

    }

}
