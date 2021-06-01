package musterija.probaZaAlgoritme.aukcijaTelefon;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import javax.swing.*;

public class IzborMusterijeSvejedno extends JFrame {


    public IzborMusterijeSvejedno(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja, DoublyLinkedList<Double> svejednoListaOcena, DoublyLinkedList<Integer> svejednoListaBrojVoznji, DoublyLinkedList<Integer> svejednoListaVreme, DoublyLinkedList<Integer> svejednoListaGodisteAuta, DoublyLinkedList<String> vozaciKorisnickaImena) {
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
        double rezultat = 0;
        DoublyLinkedList<Double> rezultatRacunanja = new DoublyLinkedList<>();
        for(Integer i : svejednoListaVreme){
            rezultat = (min/i) * vrednost;
            rezultatRacunanja.add(rezultat);
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
        double rezultat = 0;
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
        double rezultat = 0;
        DoublyLinkedList<Double> rezultatRacunanja = new DoublyLinkedList<>();
        for(Integer d : svejednoListaBrojVoznji){
            rezultat = (d/najveciBrojVoznji) * vrednost;
            rezultatRacunanja.add(rezultat);
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
        double rezultat = 0;
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

        DoublyLinkedList<Double> rezultat = new DoublyLinkedList<>();

        for(int i = 0; i < vremeDolaska.size(); i++){
            double rez = vremeDolaska.get(i) + ocene.get(i) + brojOdradjenihVoznji.get(i) + godistaAuta.get(i);
            rezultat.add(rez);
        }

        double max = rezultat.getFirst();

        for(Double d : rezultat){
            System.out.println(d);
        }

        for (int j = 0; j < rezultat.size(); j++) {
            if (rezultat.get(j) > max) {
                max = rezultat.get(j);
            }
        }

        System.out.println("-----");
        System.out.println(max);

        int index = rezultat.indexOf(max);

        System.out.println(index);

        String vozacKojiTrebaDaDobijeVoznju = vozaciKorisnickaImena.get(index);

        System.out.println(vozacKojiTrebaDaDobijeVoznju);


        

    }

    // sada je potrebno da ove metode vrate brojeve
    // jer cu ispisati korisnickoImeVozaca + rezultat (rezultat cu izracunati tako sto cu sabrati sve brojeve)
    // sabrao sam sve brojeve i dobio sam konacan rezultat
    // sortirati taj rezultat
    // prikazati dispeceru tabelu vozaca koji su ucestvovali u aukciji i njihov rank, rank odredjujem po sortiranoj listi rezultata
    // to je to


    /*
        vreme dolaska (non-beneficial)
        ocena vozaca (beneficial)
        broj odradjenih voznji (beneficial)
        godista auta (beneficial)
        napraviti tabelu gde je ponuda vise vozaca jer je nemoguce da se sve ispuni
        u zaglavlju ce biti:
            korisnicko ime vozaca, ocena vozaca, vreme dolaska, broj odradjenih voznji, godiste auta, rank (koji cu izracunati u programu)
        postoji nesto sto se zove non-beneficial i beneficial kriterijumi
        non-beneficial su oni gde se gleda najmanji podatak
        beneficial su oni gde se gleda najveci podatak
        non-beneficial se racuna tako sto podelimo najmanju vrednost sa svakom vrednoscu
        beneficial se racuna tako sto podelimo svaki podatak sa najvecom vrednoscu

        ocena vozaca: (preuzeti sve ocene vozaca)
            3.5
            4.0
            3.4
            4.5
            3.0
        najveca vrednost 4.5 (pronaci najvecu vrednost)
        podeliti svaku vrednost sa najvecnom
            3.5/4.5 = 0,78
            4.0/4.5 = 0,88
            3.4/4.5 = 0,75
            4.5/4.5 = 1,0
            3.0/4.5 = 0,67

        vreme dolaska: (preuzeti sva vremena dolaska koje su uneli vozaci)
            10
            5
            12
            8
            7
        najmanja vrednost 5 (pronaci najmanju vrednost)
        podaliti najmanju vrednost sa svakim podatkom
            5/10 = 0,5
            5/5 = 1
            5/12 = 0,42
            5/8 = 0,62
            5/7 = 0,71

        broj odradjenih voznji: (preuzeti sve podatke za broj odradjenih voznji)
            0
            0
            0
            2
            4
        najveca vrednost 4 (pronaci najvecu vrednost)
        podaliti svaku vrednost sa najvecom
            0/4 = 0
            0/4 = 0
            0/4 = 0
            2/4 = 0,5
            4/4 = 1

         godiste auta: (preuzeti sva godista auta)
            2012
            2007
            2009
            2014
            2012
         najveca vrednost 2014 (pronaci najvecu vrednost)
         podeliti svaku vrednost sa najvecom
            2012/2014 = 0,999
            2007/2014 = 0,996
            2009/2014 = 0,997
            2014/2014 = 1
            2012/2014 = 0,999

        stavljam da su sva 4 kriterijuma jednako vazna (25% = 0.25)
        sada je potrebno da svaki rezultat koji sam dobio pomnozim sa 0.25
        na kraju dobijam:

        ocena vozaca:
            0,195
            0,22
            0,185
            0,25
            0,167
        vreme dolaska:
            0,125
            0,25
            0,105
            0,155
            0,177
        broj voznji:
            0
            0
            0
            0,125
            0,25
        godiste auta:
            0,2497
            0,2490
            0,2492
            0,2500
            0,2497

        sada je potrebno da sve vrednosti za odredjenog vozaca saberem i dobijem jedan rezultat
        jer cu po njemu da sortiram
        nemanja123, 0,5697 CETVRTI
        mirko123, 0,7190 TRECI
        jovana123, 0,5392 PETI
        marina123, 0,7800 DRUGI
        stefan123, 0,8437  PRVI

        racunanje uraditi u kodu
        a sortiranje je potrebno napraviti metodu za sortiranje double vrednosti
        nakon sortiranja cu dobiti listu vozaca:
            1. stefan123
            2. marina123
            3. mirko123
            4. nemanja123
            5. jovana123
    */
}
