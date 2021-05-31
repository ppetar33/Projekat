package main;

import loginProzor.LoginProzor;
import liste.Liste;


public class TaxiSluzbaMain {

    public static final String KORISNICI_FAJL = "korisnici.txt";
    public static final String AUTOMOBILI_FAJL = "automobil.txt";
    public static final String TAKSI_SLUZBA_FAJL = "taksiSluzba.txt";
    public static final String VOZNJE_FAJL = "voznje.txt";
    public static final String AUKCIJE_FAJL = "istorijaAukcija.txt";

    public static void main(String[] args) {

        Liste ucitavanje = new Liste();

        ucitavanje.ucitajIstorijuAukcija(AUKCIJE_FAJL);
        ucitavanje.ucitajAutomobila(AUTOMOBILI_FAJL);
        ucitavanje.sortiranjeAutomobila();
        ucitavanje.ucitajTaksiSluzbe(TAKSI_SLUZBA_FAJL);
        ucitavanje.ucitajKorisnike(KORISNICI_FAJL);
        ucitavanje.ucitavanjeVoznji(VOZNJE_FAJL);
        ucitavanje.sortiranjeVoznji();

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);

    }
}

/*

    Odraditi ASIP:
        1. Aukcija (20 bodova)

    GOTOVO:
        1. Pretraga (7.5 bodova)
        2. Sortiranje u tabeli (5 bodova)
        3. Doubly linked list
        4. Hash map
        5. Kreiranje, izmena i pregled svih potrebnih entiteta (20 bodova)
        6. Izvestaj (10 bodova)
        7. Pri pokretanju programa potrebno je sortirati sve podatke po ID-u
        8. Binarna pretraga (5 bodova)
        9. Ocenjivanje vozaca

    ODRADITI:
        1. obrisati visak klasa i rasporediti klase koje su za aukciju gde treba
        2. napraviti algoritam ako je musteriji svejedno kakav ce vozac biti, onda da izbaci
        najbolju mogucu situaciju vozaca npr najbrzi, da je pet friendly i da je najbolje ocenjen
        4. refaktorisati kod za pretragu i za izvestaje

    PITATI ASISTENTA:
        automobili i voznje imaju ID i pri ucitavanju treba da ih sortiramo po kljucu (id)
        a posto korisnici nemaju id da li treba da ih sortiramo?

    Ako odgovori sa da treba odraditi:
        ubaciti ID za korisnike i potrebno ih je sortirati po kljucu pri ucitavanju kao voznje i izmeniti
        binarnu pretragu gde god postoji pretraga korisnika po korisnickom imenu da tu bude binarna pretraga
        po id-u

*/
