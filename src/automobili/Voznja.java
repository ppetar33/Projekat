package automobili;

import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Voznja {

    private int id;
    private LocalDateTime datumIvremePorudzbine;
    private String adresaPolaska;
    private String adresaDestinacije;
    private Musterija musterija;
    private Vozac vozac;
    private double brojKMpredjenih;
    private double trajanjVoznje;
    private StatusVoznje statusVoznje;

    public Voznja(){
        this.id = 0;
        this.datumIvremePorudzbine = LocalDateTime.parse("");
        this.adresaPolaska = "";
        this.adresaDestinacije = "";
        this.musterija = null;
        this.vozac = null;
        this.brojKMpredjenih = 0;
        this.trajanjVoznje = 0;
        this.statusVoznje = null;
    }

    public Voznja(int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje) {
        this.id = id;
        this.datumIvremePorudzbine = datumIvremePorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.musterija = musterija;
        this.vozac = vozac;
        this.brojKMpredjenih = brojKMpredjenih;
        this.trajanjVoznje = trajanjVoznje;
        this.statusVoznje = statusVoznje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatumIvremePorudzbine() {
        return datumIvremePorudzbine;
    }

    public void setDatumIvremePorudzbine(LocalDateTime datumIvremePorudzbine) {
        this.datumIvremePorudzbine = datumIvremePorudzbine;
    }

    public String getAdresaPolaska() {
        return adresaPolaska;
    }

    public void setAdresaPolaska(String adresaPolaska) {
        this.adresaPolaska = adresaPolaska;
    }

    public String getAdresaDestinacije() {
        return adresaDestinacije;
    }

    public void setAdresaDestinacije(String adresaDestinacije) {
        this.adresaDestinacije = adresaDestinacije;
    }

    public Musterija getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterija musterija) {
        this.musterija = musterija;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public double getBrojKMpredjenih() {
        return brojKMpredjenih;
    }

    public void setBrojKMpredjenih(double brojKMpredjenih) {
        this.brojKMpredjenih = brojKMpredjenih;
    }

    public double getTrajanjVoznje() {
        return trajanjVoznje;
    }

    public void setTrajanjVoznje(double trajanjVoznje) {
        this.trajanjVoznje = trajanjVoznje;
    }

    public StatusVoznje getStatusVoznje() {
        return statusVoznje;
    }

    public void setStatusVoznje(StatusVoznje statusVoznje) {
        this.statusVoznje = statusVoznje;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "id=" + id +
                ", datumIvremePorudzbine=" + datumIvremePorudzbine +
                ", adresaPolaska='" + adresaPolaska + '\'' +
                ", adresaDestinacije='" + adresaDestinacije + '\'' +
                ", musterija=" + musterija +
                ", vozac=" + vozac +
                ", brojKMpredjenih=" + brojKMpredjenih +
                ", trajanjVoznje=" + trajanjVoznje +
                ", statusVoznje=" + statusVoznje +
                '}';
    }

    public String pripremiZaSnimanjeVoznju() {
        return id + "," + datumIvremePorudzbine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "," + adresaPolaska + "," + adresaDestinacije + "," + musterija.getKorisnickoIme() + "," + vozac.getKorisnickoIme() + "," + brojKMpredjenih + "," + trajanjVoznje + "," + statusVoznje  + "\n";
    }
}
