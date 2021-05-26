package main;

import loginProzor.LoginProzor;
import liste.Liste;

public class TaxiSluzbaMain {

    public static final String KORISNICI_FAJL = "korisnici.txt";
    public static final String AUTOMOBILI_FAJL = "automobil.txt";
    public static final String TAKSI_SLUZBA_FAJL = "taksiSluzba.txt";
    public static final String VOZNJE_FAJL = "voznje.txt";

    public static void main(String[] args) {

        Liste ucitavanje = new Liste();

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
        2. Binarna pretraga (5 bodova)

    GOTOVO:
        1. Pretraga (7.5 bodova)
        2. Sortiranje u tabeli (5 bodova)
        3. Doubly linked list
        4. Hash map
        5. Kreiranje, izmena i pregled svih potrebnih entiteta (20 bodova)
        6. Izvestaj (10 bodova)
        7. Pri pokretanju programa potrebno je sortirati sve podatke po ID-u


        AUKCIJA

          Specifikacija:
            Omogućiti vozačima da učestvuju u aukciji za novu vožnju. Vozač unosi koliko minuta mu je
            potrebno da stigne do date adrese. Dizajnirati algoritam koji će dodeljivati vozačima vožnje.
            Na dodeljivanje vožnje može uticati potrebno vreme dolaska, ocena vozača, zarada vozača u
            nekom intervalu, starost vozila (mušterija može da traži novija vozila), pet friendly itd.
            Kreirati simulaciju ponuda više vozača i dodelu vožnje. Potrebno je čuvati istoriju aukcija.
            Za veći broj bodova potrebno je proširiti podatke o vozilima ili vozačima, takođe, potrebno
            je omogućiti mušterijama da ocene vozača nakon vožnje (ocena od 1 do 5).

          Odgovor asistenta:
            Musterija pri narucivanju voznje bira da li hoce pet friendly i novija vozila itd. Treba da
            prosirimo formu za narucivanje voznje putem telefona ili aplikacije (da ne bude samo adresa
            polaska i adresa dolaska)
            Napravimo malu formu jedan input za ono vozac unosi koliko mu treba do date adrese. Vozac moze
            da vidi sve dodeljene voznje i onda umesto ono prihvati da stoji da moze da ucestvuje u aukciji
            i tu prikazujemo tu formu. Posto je jedan ulogovan onda treba da se odjavi pa se uloguje drugi
            unese koliko mu treba, pa se uloguje treci vozac i unese koliko mu treba itd. Kad unesu,
            ulogujem se kao dispecer i pokrenem aukciju ovo nema smisla za nasu trenutnu situaciju
            ali ako vise korisnika naruci istovremeno voznju onda ima smisla. E sad za dodeljivanje
            voznje kod ove aukcije moze da utice vise faktora to pise u specifikaciji npr ocena vozaca,
            kome treba najmanje vremena do lokacije, koji vozac je najjeftiniji itd i sad je pitanje na
            koji faktor najvise da obracamo paznju, kaze da je to nama ostavljeno da izaberemo i na odbrani
            cemo prodiskutovati zasto smo bas to nesto izabrali. Musterija moze da vidi svoju istoriju
            voznje i kada je status voznje zavrsena tad moze da oceni vozaca ako nije zavrsena ako jos
            traje ispises samo voznja nije zavrsena. Takodje je potrebno cuvati istoriju aukcija kao
            tekstualni fajl. U tom fajlu ce biti korisnicko ime vozaca koji je ucestvovao u aukciji,
            koliko vremena je stavio da mu treba do date adrese, true ako je dobio voznju i false ako
            nije dobio voznju.

        BINARNA PRETRAGA
            Kaze mozemo da vracamo objekat samo getujemo po indeksu iz strukture ono sto smo pronasli. A i
            ne moramo  vratiti objekat nego mozemo da vratimo gde se nalazi u txt fajlu to sto trazimo pa
            tu liniju obrisemo ili izmenimo sta smo vec hteli

*/
