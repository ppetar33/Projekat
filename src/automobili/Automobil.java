package automobili;


/*
    Automobili predstavljaju poseban entitet u sistemu i za svaki se evidentira sledeće:
    model, proizvođač, godina proizvodnje, broj registarske oznake,
    broj taksi vozila (jedinstvena oznaka koju svako vozilo ima u okviru
    svoje taksi službe), vrsta automobila (taksi služba raspolaže sa
    putničkim automobilima i kombi vozilima).
 */



public class Automobil {

    private String model;
    private String proizvodjac;
    private int godinaProizvodnje;
    private int registarskiBroj;
    private int brojVozila;
    private VrstaVozila vrstaVozila;

    public Automobil(){

    }

    public Automobil(String model, String proizvodjac, int godinaProizvodnje, int registarskiBroj, int brojVozila, VrstaVozila vrstaVozila){
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godinaProizvodnje = godinaProizvodnje;
        this.registarskiBroj = registarskiBroj;
        this.brojVozila = brojVozila;
        this.vrstaVozila = vrstaVozila;
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

    public int getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(int registarskiBroj) {
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

    @Override
    public String toString() {
        return "Automobil{" +
                "model='" + model + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", godinaProizvodnje=" + godinaProizvodnje +
                ", registarskiBroj=" + registarskiBroj +
                ", brojVozila=" + brojVozila +
                ", vrstaVozila=" + vrstaVozila +
                '}';
    }
}
