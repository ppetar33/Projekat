package musterija;

import automobili.Voznja;
import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NarucivanjeVoznjePrekoAplikacije extends Voznja {
    //Za sve porudžbine preko aplikacije potrebno je evidentirati tekstualnu napomenu koju unose korisnici.
    // Kada se kreira ova vožnja njen inicijalni status je KREIRANA-NA ČEKANJU.
    private String napomena;

    public NarucivanjeVoznjePrekoAplikacije(){
        this.napomena = "";
    }

    public NarucivanjeVoznjePrekoAplikacije(int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje, String napomena) {
        super(id, datumIvremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojKMpredjenih, trajanjVoznje, statusVoznje, obrisan);
        this.napomena = napomena;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "NarucivanjeVoznjePrekoAplikacije{" +
                "napomena='" + napomena + '\'' +
                "} " + super.toString();
    }

    public String pripremiZaSnimanjePrekoAplikacije() {
        return id + "," + datumIvremePorudzbine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "," + adresaPolaska + "," + adresaDestinacije + "," + musterija.getKorisnickoIme() + "," + vozac.getKorisnickoIme() + "," + brojKMpredjenih + "," + trajanjVoznje + "," + napomena + "," + statusVoznje  + "\n";
    }
}
