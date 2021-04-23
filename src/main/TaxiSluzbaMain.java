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
