package musterija;

import automobili.Voznja;
import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OstaleVoznje extends Voznja {

    private String napomena;

    public OstaleVoznje(){
        this.napomena = "";
    }
    public OstaleVoznje(int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje, String napomena, boolean obrisan) {
        super(id, datumIvremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojKMpredjenih, trajanjVoznje, statusVoznje, obrisan);
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "OstaleVoznje{} " + super.toString();
    }

    public String pripremiZaSnimanje() {
        return id + "," + datumIvremePorudzbine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "," + adresaPolaska + "," + adresaDestinacije + "," + musterija.getKorisnickoIme() + "," + vozac.getKorisnickoIme() + "," + brojKMpredjenih + "," + trajanjVoznje + "," + statusVoznje + "," + napomena + "," + obrisan + "\n";
    }
}
