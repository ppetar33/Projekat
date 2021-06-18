package dispecer.dodeljivanjeVoznjiAukcijom.voznjeAplikacija;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;

public class IzborMusterijeSvejednoAplikacija {
    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoAplikacije voznja;

    public IzborMusterijeSvejednoAplikacija(Liste ucitavanje, NarucivanjeVoznjePrekoAplikacije voznja, DoublyLinkedList<Double> svejednoListaOcena, DoublyLinkedList<Integer> svejednoListaBrojVoznji, DoublyLinkedList<Integer> svejednoListaVreme, DoublyLinkedList<Integer> svejednoListaGodisteAuta, DoublyLinkedList<String> vozaciKorisnickaImena) {
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        System.out.println("Vozaci koji ucestvuju u aukciji: ");
        for(String s : vozaciKorisnickaImena){
            System.out.println(s);
        }
        System.out.println("---------------");
        racunanjeVremenaDolaska(svejednoListaVreme);
        racunanjeOcene(svejednoListaOcena);
        racunanjeBrojaVoznji(svejednoListaBrojVoznji);
        racunanjeGostaAutomobila(svejednoListaGodisteAuta);
        rezultati(svejednoListaOcena,svejednoListaBrojVoznji,svejednoListaVreme,svejednoListaGodisteAuta,vozaciKorisnickaImena);
    }

    private DoublyLinkedList<Double> racunanjeVremenaDolaska(DoublyLinkedList<Integer> svejednoListaVreme){
        double min = svejednoListaVreme.get(0);
        double n = svejednoListaVreme.size();
        for (int j = 0; j < n; j++) {
            if (svejednoListaVreme.get(j) < min) {
                min = svejednoListaVreme.get(j);
            }
        }
        double vrednost = 0.25;
        double rezultat;
        DoublyLinkedList<Double> rezultatRacunanja = new DoublyLinkedList<>();
        for(Integer i : svejednoListaVreme){
            rezultat = (min/i) * vrednost;
            if(i == 0){
                rezultatRacunanja.add(0.0);
            }else {
                rezultatRacunanja.add(rezultat);
            }
        }
        return rezultatRacunanja;
    }
    private DoublyLinkedList<Double> racunanjeOcene(DoublyLinkedList<Double> svejednoListaOcena){
        double ocenaVozaca = svejednoListaOcena.getFirst();
        for (int j = 0; j < svejednoListaOcena.size(); j++) {
            if (svejednoListaOcena.get(j) > ocenaVozaca) {
                ocenaVozaca = svejednoListaOcena.get(j);
            }
        }
        double vrednost = 0.25;
        double rezultat;
        DoublyLinkedList<Double> rezultatRacunanja = new DoublyLinkedList<>();
        for(Double d : svejednoListaOcena){
            rezultat = (d/ocenaVozaca) * vrednost;
            rezultatRacunanja.add(rezultat);
        }
        return rezultatRacunanja;
    }
    private DoublyLinkedList<Double> racunanjeBrojaVoznji(DoublyLinkedList<Integer> svejednoListaBrojVoznji){
        double najveciBrojVoznji = svejednoListaBrojVoznji.getFirst();
        for (int j = 0; j < svejednoListaBrojVoznji.size(); j++) {
            if (svejednoListaBrojVoznji.get(j) > najveciBrojVoznji) {
                najveciBrojVoznji = svejednoListaBrojVoznji.get(j);
            }
        }
        double vrednost = 0.25;
        double rezultat;
        DoublyLinkedList<Double> rezultatRacunanja = new DoublyLinkedList<>();
        for(Integer d : svejednoListaBrojVoznji){
            rezultat = (d/najveciBrojVoznji) * vrednost;
            if(d == 0){
                rezultatRacunanja.add(0.0);
            }else{
                rezultatRacunanja.add(rezultat);
            }
        }
        return rezultatRacunanja;
    }
    private DoublyLinkedList<Double> racunanjeGostaAutomobila(DoublyLinkedList<Integer> svejednoListaGodisteAuta){
        double najveceGodisteAuta = svejednoListaGodisteAuta.getFirst();
        for (int j = 0; j < svejednoListaGodisteAuta.size(); j++) {
            if (svejednoListaGodisteAuta.get(j) > najveceGodisteAuta) {
                najveceGodisteAuta = svejednoListaGodisteAuta.get(j);
            }
        }
        double vrednost = 0.25;
        double rezultat;
        DoublyLinkedList<Double> rezultatRacunanja = new DoublyLinkedList<>();
        for(Integer d : svejednoListaGodisteAuta){
            rezultat = (d/najveceGodisteAuta) * vrednost;
            rezultatRacunanja.add(rezultat);
        }
        return rezultatRacunanja;
    }

    private void rezultati(DoublyLinkedList<Double> svejednoListaOcena, DoublyLinkedList<Integer> svejednoListaBrojVoznji, DoublyLinkedList<Integer> svejednoListaVreme, DoublyLinkedList<Integer> svejednoListaGodisteAuta, DoublyLinkedList<String> vozaciKorisnickaImena) {

        DoublyLinkedList<Double> vremeDolaska = racunanjeVremenaDolaska(svejednoListaVreme);
        DoublyLinkedList<Double> ocene = racunanjeOcene(svejednoListaOcena);
        DoublyLinkedList<Double> brojOdradjenihVoznji =  racunanjeBrojaVoznji(svejednoListaBrojVoznji);
        DoublyLinkedList<Double> godistaAuta = racunanjeGostaAutomobila(svejednoListaGodisteAuta);

        System.out.println("Izracunato vreme dolaska: ");
        for(Double d : vremeDolaska){
            System.out.println(d);
        }
        System.out.println("-----------------");
        System.out.println("Izracunate ocene vozaca: ");
        for(Double d : ocene){
            System.out.println(d);
        }
        System.out.println("-----------------");
        System.out.println("Izracunato broj odradjenih voznji: ");
        for(Double d : brojOdradjenihVoznji){
            System.out.println(d);
        }
        System.out.println("-----------------");
        System.out.println("Izracunata godista automobila: ");
        for(Double d : godistaAuta){
            System.out.println(d);
        }
        DoublyLinkedList<Double> rezultat = new DoublyLinkedList<>();

        for(int i = 0; i < vremeDolaska.size(); i++){
            double rez = vremeDolaska.get(i) + ocene.get(i) + brojOdradjenihVoznji.get(i) + godistaAuta.get(i);
            rezultat.add(rez);
        }

        double max = rezultat.getFirst();

        System.out.println("-------------");
        System.out.println("Konacni rezultat: ");
        for(Double d : rezultat){
            System.out.println(d);
        }

        for (int j = 0; j < rezultat.size(); j++) {
            if (rezultat.get(j) > max) {
                max = rezultat.get(j);
            }
        }

        System.out.println("-----------------");
        System.out.println("Maksimalna vrednost: " + max);

        int index = rezultat.indexOf(max);

        System.out.println("-----------------");
        System.out.println("Index gde se maksimalna vrednost nalazi: " + index);

        String vozacKojiTrebaDaDobijeVoznju = vozaciKorisnickaImena.get(index);

        System.out.println("-----------------");
        System.out.println("Vozac koji treba da dobije voznju: " + vozacKojiTrebaDaDobijeVoznju);

        DoublyLinkedList<String> vozac = new DoublyLinkedList<>();
        vozac.add(vozacKojiTrebaDaDobijeVoznju);

        ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom prozor = new ProzorZaDodeljivanjeVoznjiAplikacijaAukcijom(ucitavanje,voznja,vozac);
        prozor.setVisible(true);

    }
}
