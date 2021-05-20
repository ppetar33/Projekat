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


    Omogućiti vozačima da učestvuju u aukciji za novu vožnju. Vozač unosi koliko minuta mu je potrebno
    da stigne do date adrese. Dizajnirati algoritam koji će dodeljivati vozačima vožnje. Na dodeljivanje
    vožnje može uticati potrebno vreme dolaska, ocena vozača, zarada vozača u nekom intervalu,
    starost vozila (mušterija može da traži novija vozila), pet friendly itd. Kreirati simulaciju ponuda
    više vozača i dodelu vožnje. Potrebno je čuvati istoriju aukcija. Za veći broj bodova potrebno je
    proširiti podatke o vozilima ili vozačima, takođe, potrebno je omogućiti mušterijama da ocene vozača
    nakon vožnje (ocena od 1 do 5).

    Gde unose vozaci koliko im je potrebno da dodju do date adrese?
    Izaberem vozaca kojem treba najmanje vremena i koji ima najbolju ocenu i koji je najjeftiniji
    Da li musterija pri narucivanju voznje trazi pet friendly i novija vozila?
    I da li treba da postoji funkcionalnost oceni vozaca kod musterije to radim ako je voznja zavrsena?
    ako nije ispisacu da ne moze da oceni vozaca jer voznja nije zavrsena

    kada napravim svoju listu ja treba da napravim algoritam za sortiranje te nove liste
    i kada pokrenem program ja te sve podatke treba da sortiram to kljucu a kljuc mi je ID
    i onda ja tek kad sortiram sve mogu da implementiram binarnu pretragu i onda mogu da omogucim sortiranje
    svih podataka za ovo tabelarno pirazivanje u aplikaciji
    i kako da vratim objekat jer mi u binarnoj pretrazi vraca samo id gde se taj objekat nalazi

    kod svoje liste previous mi je null
*/
