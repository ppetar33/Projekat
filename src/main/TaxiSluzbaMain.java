package main;

import gui.LoginProzor;
import podaci.Liste;

public class TaxiSluzbaMain {

    private static final String KORISNICI_FAJL = "korisnici.txt";
    private static final String AUTOMOBILI_FAJL = "automobil.txt";
    private static final String TAKSI_SLUZBA_FAJL = "taksiSluzba.txt";
    private static final String VOZNJE_FAJL = "voznje.txt";

    public static void main(String[] args) {

        Liste ucitavanje = new Liste();

        ucitavanje.ucitajAutomobila(AUTOMOBILI_FAJL);
        ucitavanje.ucitajKorisnike(KORISNICI_FAJL);
        ucitavanje.ucitavanjeVoznji(VOZNJE_FAJL);
        ucitavanje.ucitajTaksiSluzbe(TAKSI_SLUZBA_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);

    }

}
// ucitati sve podatke
// identifikator mora da postoji i to je deo iz algoritama
// liste i mape su deo iz algoritama, pitati asistenta
// putem aplikacije se unosi dodatna napomena i to je jedina razlika
// za izvestaje pitati asistenta iz algoritama
// pretrage algoritmi

/*
    6,Ibiza,Seat,2012,SU-132-DW,351,PUTNICKI_AUTOMOBIL,TRUE,SLOBODAN
    7,Golf 5,Volkswagen,2020,VA-221-LS,314,PUTNICKI_AUTOMOBIL,TRUE,SLOBODAN
*/