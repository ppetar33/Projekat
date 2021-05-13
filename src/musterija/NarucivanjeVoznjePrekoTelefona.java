package musterija;

import automobili.Voznja;
import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NarucivanjeVoznjePrekoTelefona extends Voznja{

    private String prazanString;

    public NarucivanjeVoznjePrekoTelefona(){
        this.prazanString = "";
    }

    public NarucivanjeVoznjePrekoTelefona (int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje, boolean obrisan, String prazanString) {
        super(id, datumIvremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojKMpredjenih, trajanjVoznje, statusVoznje, obrisan);
        this.prazanString = prazanString;
    }

    @Override
    public String toString() {
        return "NarucivanjeVoznjePrekoTelefona{} " + super.toString();
    }


    public String pripremiZaSnimanje() {
        return id + "," + datumIvremePorudzbine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "," + adresaPolaska + "," + adresaDestinacije + "," + musterija.getKorisnickoIme() + "," + vozac.getKorisnickoIme() + "," + brojKMpredjenih + "," + trajanjVoznje + "," + statusVoznje + "," + prazanString + "," + obrisan + "\n";
    }

}
