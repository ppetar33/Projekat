package aukcija;


import enumi.StatusNaruceneVoznje;

public class Aukcija {

    private int id;
    private String izborMusterije;
    private int IDvoznje;
    private String vozacKojiUcestvujeUaukciji;
    private int vremeKojeJeUneoVozac;
    private double ocenaVozaca;
    private boolean petFriendly;
    private int godisteAutomobila;
    private boolean dobioVoznju;
    private StatusNaruceneVoznje statusNaruceneVoznje;
    private int brojVoznjiKojeJeObavioVozac;

    public Aukcija(){
        this.id = 0;
        this.izborMusterije = "";
        this.IDvoznje = 0;
        this.vozacKojiUcestvujeUaukciji = "";
        this.vremeKojeJeUneoVozac = 0;
        this.ocenaVozaca = 0;
        this.petFriendly = false;
        this.godisteAutomobila = 0;
        this.dobioVoznju = false;
        this.statusNaruceneVoznje = null;
        this.brojVoznjiKojeJeObavioVozac = 0;
    }

    public Aukcija(int id, String izborMusterije, int IDvoznje, String vozacKojiUcestvujeUaukciji, int vremeKojeJeUneoVozac, double ocenaVozaca, boolean petFriendly, int godisteAutomobila, boolean dobioVoznju, StatusNaruceneVoznje statusNaruceneVoznje, int brojVoznjiKojeJeObavioVozac) {
        this.id = id;
        this.izborMusterije = izborMusterije;
        this.IDvoznje = IDvoznje;
        this.vozacKojiUcestvujeUaukciji = vozacKojiUcestvujeUaukciji;
        this.vremeKojeJeUneoVozac = vremeKojeJeUneoVozac;
        this.ocenaVozaca = ocenaVozaca;
        this.petFriendly = petFriendly;
        this.godisteAutomobila = godisteAutomobila;
        this.dobioVoznju = dobioVoznju;
        this.statusNaruceneVoznje = statusNaruceneVoznje;
        this.brojVoznjiKojeJeObavioVozac = brojVoznjiKojeJeObavioVozac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzborMusterije() {
        return izborMusterije;
    }

    public void setIzborMusterije(String izborMusterije) {
        this.izborMusterije = izborMusterije;
    }

    public int getIDvoznje() {
        return IDvoznje;
    }

    public void setIDvoznje(int IDvoznje) {
        this.IDvoznje = IDvoznje;
    }

    public String getVozacKojiUcestvujeUaukciji() {
        return vozacKojiUcestvujeUaukciji;
    }

    public void setVozacKojiUcestvujeUaukciji(String vozacKojiUcestvujeUaukciji) {
        this.vozacKojiUcestvujeUaukciji = vozacKojiUcestvujeUaukciji;
    }

    public int getVremeKojeJeUneoVozac() {
        return vremeKojeJeUneoVozac;
    }

    public void setVremeKojeJeUneoVozac(int vremeKojeJeUneoVozac) {
        this.vremeKojeJeUneoVozac = vremeKojeJeUneoVozac;
    }

    public double getOcenaVozaca() {
        return ocenaVozaca;
    }

    public void setOcenaVozaca(double ocenaVozaca) {
        this.ocenaVozaca = ocenaVozaca;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public int getGodisteAutomobila() {
        return godisteAutomobila;
    }

    public void setGodisteAutomobila(int godisteAutomobila) {
        this.godisteAutomobila = godisteAutomobila;
    }

    public boolean isDobioVoznju() {
        return dobioVoznju;
    }

    public void setDobioVoznju(boolean dobioVoznju) {
        this.dobioVoznju = dobioVoznju;
    }

    public StatusNaruceneVoznje getStatusNaruceneVoznje() {
        return statusNaruceneVoznje;
    }

    public void setStatusNaruceneVoznje(StatusNaruceneVoznje statusNaruceneVoznje) {
        this.statusNaruceneVoznje = statusNaruceneVoznje;
    }

    public int getBrojVoznjiKojeJeObavioVozac() {
        return brojVoznjiKojeJeObavioVozac;
    }

    public void setBrojVoznjiKojeJeObavioVozac(int brojVoznjiKojeJeObavioVozac) {
        this.brojVoznjiKojeJeObavioVozac = brojVoznjiKojeJeObavioVozac;
    }

    @Override
    public String toString() {
        return "Aukcija{" +
                "id=" + id +
                ", izborMusterije='" + izborMusterije + '\'' +
                ", IDvoznje=" + IDvoznje +
                ", vozacKojiUcestvujeUaukciji='" + vozacKojiUcestvujeUaukciji + '\'' +
                ", vremeKojeJeUneoVozac=" + vremeKojeJeUneoVozac +
                ", ocenaVozaca=" + ocenaVozaca +
                ", petFriendly=" + petFriendly +
                ", godisteAutomobila=" + godisteAutomobila +
                ", dobioVoznju=" + dobioVoznju +
                ", statusNaruceneVoznje=" + statusNaruceneVoznje +
                ", brojVoznjiKojeJeObavioVozac=" + brojVoznjiKojeJeObavioVozac +
                '}';
    }

    public String pripremiZaSnimanjeIstorijuAukcija(){
        return id + "," + izborMusterije + "," + IDvoznje + "," + vozacKojiUcestvujeUaukciji + "," + vremeKojeJeUneoVozac + "," + ocenaVozaca + "," + petFriendly + "," + godisteAutomobila + "," + dobioVoznju + "," + statusNaruceneVoznje + "," + brojVoznjiKojeJeObavioVozac + "\n";
    }
}
