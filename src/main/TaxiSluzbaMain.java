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

    ODRADITI:
        1. Obrisati visak klasa i rasporediti klase koje su za aukciju gde treba
        2. Popraviti kod ako je moguce za:
                pretrage
                izvestaje
                aukciju
        3. Prekontrolisati ceo projekat

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
        10. Aukcija (20 bodova)

    PITATI ASISTENTA:
        automobili i voznje imaju ID i pri ucitavanju treba da ih sortiramo po kljucu (id)
        a posto korisnici nemaju id da li treba da ih sortiramo?
        da li je problem input za izvestaje?
        procitati kako sam odradio aukciju

*/










/*                                               AUKCIJA


    Imam 4 kriterijuma svi su podjenako vazni (po 25% = 0.25)

    U aukciji ucestvuju:
        petar123
        mirko123

    OCENE VOZACA:
            3.5
            4.0
            -najveca vrednost 4.5
            -podeliti svaku vrednost sa najvecnom i pomnozim sa 0.25
            3.5/4.5 = 0,78 * 0.25 = 0,195
            4.0/4.5 = 0,88 * 0.25 = 0,22

    VREME DOLASKA KOJE JE UNEO VOZAC:
            10
            5
            -najmanja vrednost 5
            -podaliti najmanju vrednost sa svakim podatkom i pomnozim sa 0.25
            5/10 = 0,5 * 0.25 = 0,125
            5/5 = 1 * 0.25 = 0,25

    UKUPAN BROJ ODRADJENIH VOZNJI (PO VOZACU):
            0
            0
            -najveca vrednost 0
            -podaliti svaku vrednost sa najvecom i pomnozim sa 0.25
            0/4 = 0 * 0.25 = 0
            0/4 = 0 * 0.25 = 0

     GODISTA AUTA:
            2012
            2007
            -najveca vrednost 2012
            -podeliti svaku vrednost sa najvecom i pomnozim sa 0.25
            2012/2012 = 1 * 0.25 = 0,25
            2007/2012 = 0,996 * 0.25 = 0,249

    Sabrati sve prve vrednosti:  0,57
    Sabrati sve druge vrednosti: 0,719
    Prvi vozac koji je ucestvovao u aukciji petar123
    Drugi vozac koji je ucestvovao u aukciji mirko123

    Vozac kojem treba dodeliti voznju: mirko123

*/
