package main;

import enumi.StatusVozaca;
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
                slobodanVozac.setStatusVozaca(StatusVozaca.ZAUZET);
                ucitavanje.dodavanjeKorisnika();
                ucitavanje.snimanjeVoznji(VOZNJE_FAJL);
            }
        }

    }

}
/*

    Ako se naruci nova voznja a vozac nije slobodan potrebno je da vozac zavrsi ili odbije voznju
    ako odbije voznju to znaci da ce taj vozac ako je u voznji "" biti dodeljen
    isto vazi za zavrsavanje voznje

 */
/*
                    Vozac vozac = ucitavanje.nadjiVozacaKojiJeSlobodan();
                    if(vozac != null){
                        vozac.getKorisnickoIme();
                        vozac.setStatusVozaca(StatusVozaca.ZAUZET);
                        ucitavanje.dodavanjeKorisnika();
                    }else{
                        Vozac nePostojiSlobodanVozac = new Vozac();
                        vozac = nePostojiSlobodanVozac;
                    }
 */

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
