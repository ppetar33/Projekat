package main;

import loginProzor.LoginProzor;
import liste.Liste;

public class TaxiSluzbaMain {

    private static final String KORISNICI_FAJL = "korisnici.txt";
    private static final String AUTOMOBILI_FAJL = "automobil.txt";
    private static final String TAKSI_SLUZBA_FAJL = "taksiSluzba.txt";
    private static final String VOZNJE_FAJL = "voznje.txt";

    public static void main(String[] args) {

        Liste ucitavanje = new Liste();

        ucitavanje.ucitajAutomobila(AUTOMOBILI_FAJL);
        ucitavanje.ucitajTaksiSluzbe(TAKSI_SLUZBA_FAJL);
        ucitavanje.ucitajKorisnike(KORISNICI_FAJL);
        ucitavanje.ucitavanjeVoznji(VOZNJE_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);

    }

}

/*

    Odraditi OOP:
        1. Dodeliti vozaca voznji
        2. Pregled dodeljenih voznji i prihvati/odbij voznju
        3. Zavrsavanje voznje i unos broja km predjenih i trajanje voznje

    Odraditi ASIP:
        1. Zavrsiti pretragu i izvestaje
        2. Aukcija
        3. Binarna pretraga
        4. Sortiranje u tabeli
        5. Doubly linked list

*/
