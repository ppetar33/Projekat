package automobili;

/*
    Automobili predstavljaju poseban entitet u sistemu i za svaki se evidentira sledeće:
    model, proizvođač, godina proizvodnje, broj registarske oznake,
    broj taksi vozila (jedinstvena oznaka koju svako vozilo ima u okviru
    svoje taksi službe), vrsta automobila (taksi služba raspolaže sa
    putničkim automobilima i kombi vozilima).
*/

import enumi.Obrisan;
import enumi.VrstaVozila;

public class Automobil {

    private int id;
    private String model;
    private String proizvodjac;
    private int godinaProizvodnje;
    private String registarskiBroj;
    private int brojVozila;
    private VrstaVozila vrstaVozila;
    private Obrisan obrisan;

    public Automobil(){}

    public Automobil(int id, String model, String proizvodjac, int godinaProizvodnje, String registarskiBroj, int brojVozila, VrstaVozila vrstaVozila, Obrisan obrisan) {
        this.id = id;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godinaProizvodnje = godinaProizvodnje;
        this.registarskiBroj = registarskiBroj;
        this.brojVozila = brojVozila;
        this.vrstaVozila = vrstaVozila;
        this.obrisan = obrisan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public int getBrojVozila() {
        return brojVozila;
    }

    public void setBrojVozila(int brojVozila) {
        this.brojVozila = brojVozila;
    }

    public VrstaVozila getVrstaVozila() {
        return vrstaVozila;
    }

    public void setVrstaVozila(VrstaVozila vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    public Obrisan getObrisan() {
        return obrisan;
    }

    public void setObrisan(Obrisan obrisan) {
        this.obrisan = obrisan;
    }

    @Override
    public String toString() {
        return "Automobil{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", godinaProizvodnje=" + godinaProizvodnje +
                ", registarskiBroj='" + registarskiBroj + '\'' +
                ", brojVozila=" + brojVozila +
                ", obrisan=" + obrisan +
                ", vrstaVozila=" + vrstaVozila +
                '}';
    }

    public String pripremiZaSnimanjeAutomobil() {
        return id + "," + model + "," + proizvodjac + "," + godinaProizvodnje + "," + registarskiBroj + "," + brojVozila +"," + obrisan +"," + vrstaVozila + "\n";
    }
}
