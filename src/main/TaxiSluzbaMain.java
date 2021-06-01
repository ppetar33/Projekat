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

    PITATI ASISTENTA:
        automobili i voznje imaju ID i pri ucitavanju treba da ih sortiramo po kljucu (id)
        a posto korisnici nemaju id da li treba da ih sortiramo?

    Ako odgovori sa da treba odraditi:
        ubaciti ID za korisnike i potrebno ih je sortirati po kljucu pri ucitavanju kao voznje i izmeniti
        binarnu pretragu gde god postoji pretraga korisnika po korisnickom imenu da tu bude binarna pretraga
        po id-u


    ako je musteriji svejdno to znaci da: (sve preuzimam iz txt fajla istorijaAukcija.txt)

        vreme dolaska (non-beneficial)
        ocena vozaca (beneficial)
        broj odradjenih voznji (beneficial)
        godista auta (beneficial)
        napraviti tabelu gde je ponuda vise vozaca jer je nemoguce da se sve ispuni
        u zaglavlju ce biti:
            korisnicko ime vozaca, ocena vozaca, vreme dolaska, broj odradjenih voznji, godiste auta, rank (koji cu izracunati u programu)
        postoji nesto sto se zove non-beneficial i beneficial kriterijumi
        non-beneficial su oni gde se gleda najmanji podatak
        beneficial su oni gde se gleda najveci podatak
        non-beneficial se racuna tako sto podelimo najmanju vrednost sa svakom vrednoscu
        beneficial se racuna tako sto podelimo svaki podatak sa najvecom vrednoscu

        ocena vozaca: (preuzeti sve ocene vozaca)
            3.5
            4.0
            3.4
            4.5
            3.0
        najveca vrednost 4.5 (pronaci najvecu vrednost)
        podeliti svaku vrednost sa najvecnom
            3.5/4.5 = 0,78
            4.0/4.5 = 0,88
            3.4/4.5 = 0,75
            4.5/4.5 = 1,0
            3.0/4.5 = 0,67

        vreme dolaska: (preuzeti sva vremena dolaska koje su uneli vozaci)
            10
            5
            12
            8
            7
        najmanja vrednost 5 (pronaci najmanju vrednost)
        podaliti najmanju vrednost sa svakim podatkom
            5/10 = 0,5
            5/5 = 1
            5/12 = 0,42
            5/8 = 0,62
            5/7 = 0,71

        broj odradjenih voznji: (preuzeti sve podatke za broj odradjenih voznji)
            0
            0
            0
            2
            4
        najveca vrednost 4 (pronaci najvecu vrednost)
        podaliti svaku vrednost sa najvecom
            0/4 = 0
            0/4 = 0
            0/4 = 0
            2/4 = 0,5
            4/4 = 1

         godiste auta: (preuzeti sva godista auta)
            2012
            2007
            2009
            2014
            2012
         najveca vrednost 2014 (pronaci najvecu vrednost)
         podeliti svaku vrednost sa najvecom
            2012/2014 = 0,999
            2007/2014 = 0,996
            2009/2014 = 0,997
            2014/2014 = 1
            2012/2014 = 0,999

        stavljam da su sva 4 kriterijuma jednako vazna (25% = 0.25)
        sada je potrebno da svaki rezultat koji sam dobio pomnozim sa 0.25
        na kraju dobijam:

        ocena vozaca:
            0,195
            0,22
            0,185
            0,25
            0,167
        vreme dolaska:
            0,125
            0,25
            0,105
            0,155
            0,177
        broj voznji:
            0
            0
            0
            0,125
            0,25
        godiste auta:
            0,2497
            0,2490
            0,2492
            0,2500
            0,2497

        sada je potrebno da sve vrednosti za odredjenog vozaca saberem i dobijem jedan rezultat
        jer cu po njemu da sortiram
        nemanja123, 0,5697 CETVRTI
        mirko123, 0,7190 TRECI
        jovana123, 0,5392 PETI
        marina123, 0,7800 DRUGI
        stefan123, 0,8437  PRVI

        racunanje uraditi u kodu
        a sortiranje je potrebno napraviti metodu za sortiranje double vrednosti
        nakon sortiranja cu dobiti listu vozaca:
            1. stefan123
            2. marina123
            3. mirko123
            4. nemanja123
            5. jovana123



     engleski 16 jun
     softver 21 jun
     programiranje 23 jun
     algoritmi 26 jun


*/
