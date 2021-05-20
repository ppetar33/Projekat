package musterija;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NarucivanjeVoznjePrekoTelefona extends Voznja{


    public NarucivanjeVoznjePrekoTelefona (int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje, boolean obrisan, StatusNaruceneVoznje statusNaruceneVoznje) {
        super(id, datumIvremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojKMpredjenih, trajanjVoznje, statusVoznje, obrisan, statusNaruceneVoznje);
    }

    @Override
    public String toString() {
        return "NarucivanjeVoznjePrekoTelefona{} " + super.toString();
    }


    public String pripremiZaSnimanje() {
        return id + "," + datumIvremePorudzbine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "," + adresaPolaska + "," + adresaDestinacije + "," + musterija.getKorisnickoIme() + "," + vozac.getKorisnickoIme() + "," + brojKMpredjenih + "," + trajanjVoznje + "," + statusVoznje + ",,"  + obrisan + "," + statusNaruceneVoznje + "\n";
    }

}
