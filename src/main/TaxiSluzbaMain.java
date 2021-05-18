package main;

import enumi.StatusVozacaIautomobila;
import loginProzor.LoginProzor;
import liste.Liste;
import musterija.NarucivanjeVoznjePrekoTelefona;
import osobe.Vozac;

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

        if(ucitavanje.listaVoznjiKojeNemajuVozaca().isEmpty()){
            System.out.println("Sve voznje imaju vozaca");
        }else {
            Vozac slobodanVozac = ucitavanje.nadjiVozacaKojiJeSlobodan();
            NarucivanjeVoznjePrekoTelefona voznjaKojaNemaVozaca = ucitavanje.nadjiVoznjuKojaNemaVozaca();
            if (slobodanVozac != null) {
                voznjaKojaNemaVozaca.getVozac().setKorisnickoIme(slobodanVozac.getKorisnickoIme());
                slobodanVozac.setStatusVozaca(StatusVozacaIautomobila.ZAUZET);
                ucitavanje.dodavanjeKorisnika();
                ucitavanje.snimanjeVoznji(VOZNJE_FAJL);
            }
        }

    }

}

/*
    Odraditi ASIP:
        1. Zavrsiti pretragu i izvestaje
        2. Aukcija
        3. Binarna pretraga
        4. Sortiranje u tabeli
        5. Doubly linked list
*/
