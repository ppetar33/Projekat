package musterija;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NarucivanjeVoznjePrekoAplikacije extends Voznja {

    public NarucivanjeVoznjePrekoAplikacije(int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje, String napomena, boolean obrisan, StatusNaruceneVoznje statusNaruceneVoznje) {
        super(id, datumIvremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojKMpredjenih, trajanjVoznje, statusVoznje, napomena, obrisan, statusNaruceneVoznje);
    }

    @Override
    public String toString() {
        return "NarucivanjeVoznjePrekoAplikacije{} " + super.toString();
    }

    public String pripremiZaSnimanjePrekoAplikacije() {
        return id + "," + datumIvremePorudzbine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "," + adresaPolaska + "," + adresaDestinacije + "," + musterija.getKorisnickoIme() + "," + vozac.getKorisnickoIme() + "," + brojKMpredjenih + "," + trajanjVoznje + "," + statusVoznje + "," + napomena + "," + obrisan + "," + statusNaruceneVoznje + "\n";
    }
}
