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
        ucitavanje.ucitajKorisnike(KORISNICI_FAJL);
        ucitavanje.ucitavanjeVoznji(VOZNJE_FAJL);
        ucitavanje.ucitajTaksiSluzbe(TAKSI_SLUZBA_FAJL);

        LoginProzor lp = new LoginProzor(ucitavanje);
        lp.setVisible(true);

    }

}

/*
    Status vožnje može biti KREIRANA, KREIRANA-NA ČEKANJU, DODELJENA, PRIHVAĆENA, ZAVRŠENA, ODBIJENA.

        1. Kada se naruci voznja telefonom status je KREIRANA
            1.1 Funkcionalnost dispecera
                    Sledeci korak je postaljanje vozaca za voznju i status vozenje se menja sa KREIRANA na DODELJENA
            1.2 Funkcionalnost vozaca
                    Vozac ima mogucnost da prihvati ili odbije voznju, ako prihvati status voznje se menja na PRIHVACENA, ako odbije status voznje se menja na ODBIJENA.
            1.3 ZAJEDNO
                Neophodno je da voznja moze da se zavrsi sto podrazumeva promenu statusa voznje iz PRIHVACENA u ZAVRSENA, unos broja km predjenih u voznji i trajanje vožnje.

    Uraditi:

        1. ID se generise funkcijom.
        2. Datum i vreme porudzbine je LocalDateTime.now (trenutno vreme)
        3. Narucivanje voznje, status je kreirana.
        4. Adresa polaska se unosi.
        5. Adresa dolaska se unosi.
        6. Musterija koja je narucila voznju se cita iz fajla (file se overwrite-uje svaki put kada se novi korisnik uloguje).
        7. Dodeljivanje voznje vozacu, ako je voznja zavrsena to znaci da je vozac slobodan i da moze da prihvati sledecu voznju. Status se prebacuje sa kreirane na dodeljenu.
        8. Vozac prihvata ili odbija voznju. Status voznje dodeljena se menja na prihvacena ili odbijena.
        9. Zavrsavanje voznje, status se menja sa prihvacene u zavrsena. Vozac unosi broj kilometara koje je presao (gde unosi????) a trajanje se racuna po formuli:
                trajanjeVoznje = brojPredjenihKilometara * 0.5 (uzeli smo prosek brzine da je 30km/h jer je ogranicenje po gradu 50km/h)

*/


/*

    OOP (uraditi):
        1. Dodeljivanje vožnji kreiranih putem telefona vozaču (5 bodova)
        2. Pregled dodeljenih vožnji (vožnje kreirane putem telefona) uz mogućnost prihvatanja/odbijanja vožnje (10 bodova)
        3. Za sve prihvaćene vožnje neophodno je obezbediti da se vožnja može završiti , što podrazumeva promenu statusa vožnje iz PRIHVAĆENA u ZAVRŠENA, unos broja km pređenih u vožnji i trajanje vožnje. (5 bodova)

    OOP (zavrseno):
        1. Kreiranje svih klasa I konstruktora sa odgovarajucim atributima za sve potrebne entitete (15 bodova)
        2. Kreiranje osnovnih odgovarajućih metoda za rad sa datim entitetima bez GUI-a (15 bodova)
        3. Prijava na sistem (10 bodova)
        4. Prikaz, izmena, dodavanje, brisanje vozaca (20 bodova)
        5. Naručivanje vožnji putem telefona (5 bodova)
        6. Navigacija kroz aplikaciju (10 bodova)

    ASIP (uraditi):
    PITANJE = pitati asistenta iz ASIP
        1. Pretraga dispecera (7.5 bodova) + binarna (5 bodova)
        2. Izvestaj dispecera (10 bodova) PITANJE: da li se na nedeljni izvestaj misli 7 dana ili ako je sreda onda od ponedeljka do srede?
        3. Aukcija (20 bodova) PITANJE: gde vozac unosi koliko mu je potrebno minuta za voznju? gde treba da bude podatak ocena vozaca, pet friendly? gde cuvamo istoriju aukcija?
        4. Sortiranje svih tabelarnih prikaza u aplikaciji (5 bodova) PITANJE: kakvo sortiranje?
        5. Podatke treba cuvati sortirane po kljucu PITANJE: sta moze biti kljuc i u kom smislu sortiranje?
        6. Potrebno je implementirati svoje strukture podataka, za liste i mape. PITANJE: jel moze da da neki primer za liste i mape?

    ASIP (zavrseno):
        1. Kreiranje, izmena i pregled svih potrebnih entiteta. (20 bodova)
        2. Svi entiteti osim korisnika treba da imaju identifikator koji ce biti tipa int ili long
*/
