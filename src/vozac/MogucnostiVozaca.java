package vozac;

import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import musterija.probaZaAlgoritme.aukcijaAplikacija.UcestvujUaukcijiVoznjeAplikacija;
import osobe.Musterija;
import osobe.Vozac;
import liste.Liste;
import musterija.probaZaAlgoritme.aukcijaTelefon.UcestvujUaukcijiVoznjeTelefon;
import vozac.Statistika.DnevnaStatistika;
import vozac.prikazVoznji.PrikazDodeljenihVoznjiKreiranihTelefonom;
import vozac.prikazVoznji.PrikazDodeljenihVoznjiKreiranihAplikacijom;
import vozac.zavrsavanjeVoznje.ZavrsavanjeVoznjePutemAplikacije;
import vozac.zavrsavanjeVoznje.ZavrsavanjeVoznjePutemTelefona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogucnostiVozaca extends JFrame {

    private JMenuBar vozacMenu = new JMenuBar();

    private JMenu funkcionalnostiVozaca = new JMenu("Voznje");
    private JMenuItem prikazIstorijeSopstvenihVoznjiVoznjeTelefon = new JMenuItem("Prikaz istorije sopstvenih voznji kreirane putem telefona");
    private JMenuItem prikazIstorijeSopstvenihVoznjiVoznjeAplikacija = new JMenuItem("Prikaz istorije sopstvenih voznji kreirane putem aplikacije");
    private JMenuItem prikazVoznjeZakazanihPrekoAplikacije = new JMenuItem("Prikaz voznji zakazanih preko aplikacije");
    private JMenuItem prikazDodeljenihVoznji = new JMenuItem("Prikaz dodeljenih voznji kreiranih putem telefona");
    private JMenuItem sumiraneStatistikeVoznji = new JMenuItem("Prikaz sumiraze statistike voznji");
    private JMenuItem aukcijeVoznjeTelefoni = new JMenuItem("Ucestvuj u aukciji za voznju kreiranu putem telefona");
    private JMenuItem aukcijeVoznjeAplikacija = new JMenuItem("Ucestvuj u aukciji za voznju kreiranu putem aplikacije");
    private JMenuItem zavrsavanjeVoznje = new JMenuItem("Zavrsavanje voznje kreirane putem telefona");
    private JMenuItem zavrsavanjeVoznjePutemAplikacije = new JMenuItem("Zavrsavanje voznje kreirane putem aplikacije");

    private JMenu funkcionalnostStatistika = new JMenu("Statistika voznji");
    private JMenuItem dnevna = new JMenuItem("Dnevna");
    private JMenuItem nedeljna = new JMenuItem("Nedeljena");
    private JMenuItem mesecna = new JMenuItem("Mesecna");
    private JMenuItem godisnja = new JMenuItem("Godisnja");


    private JMenu odjava = new JMenu("Odjava");
    private JMenuItem potvrdaZaOdjavu = new JMenuItem("Potvrdi");
    private JMenuItem odustaniZaOdjavu = new JMenuItem("Odustani");

    private Liste ucitavanje;
    private Vozac prijavljeniVozac;
    private Musterija musterija;
    private NarucivanjeVoznjePrekoTelefona voznjaTelefon;
    private NarucivanjeVoznjePrekoAplikacije voznjaAplikacija;

    public MogucnostiVozaca(Liste ucitavanje, Vozac prijavljeniVozac){
        this.ucitavanje = ucitavanje;
        this.prijavljeniVozac = prijavljeniVozac;
        setTitle("Dobrodosli " + prijavljeniVozac.getIme().substring(0, 1).toUpperCase() + prijavljeniVozac.getIme().substring(1) + " (Vozac)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        initGUI();
        initListeners();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        setJMenuBar(vozacMenu);

        vozacMenu.add(funkcionalnostiVozaca);
        funkcionalnostiVozaca.add(prikazIstorijeSopstvenihVoznjiVoznjeTelefon);
        funkcionalnostiVozaca.add(prikazIstorijeSopstvenihVoznjiVoznjeAplikacija);
        funkcionalnostiVozaca.add(prikazVoznjeZakazanihPrekoAplikacije);
        funkcionalnostiVozaca.add(prikazDodeljenihVoznji);
        funkcionalnostiVozaca.add(sumiraneStatistikeVoznji);
        funkcionalnostiVozaca.add(aukcijeVoznjeTelefoni);
        funkcionalnostiVozaca.add(aukcijeVoznjeAplikacija);
        funkcionalnostiVozaca.add(zavrsavanjeVoznje);
        funkcionalnostiVozaca.add(zavrsavanjeVoznjePutemAplikacije);

        vozacMenu.add(funkcionalnostStatistika);
        funkcionalnostStatistika.add(dnevna);
        funkcionalnostStatistika.add(nedeljna);
        funkcionalnostStatistika.add(mesecna);
        funkcionalnostStatistika.add(godisnja);

        vozacMenu.add(odjava);
        odjava.add(potvrdaZaOdjavu);
        odjava.add(odustaniZaOdjavu);
    }

    private void initListeners(){
        prikazIstorijeSopstvenihVoznjiVoznjeTelefon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVoznjeZaIstorijuVoznjePrekoTelefona().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Trenutno nema voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    IstorijaVoznjeVozacTelefon prozorZaPrikazIstorijeVoznji = new IstorijaVoznjeVozacTelefon(ucitavanje, prijavljeniVozac);
                    prozorZaPrikazIstorijeVoznji.setVisible(true);
                }
            }
        });
        prikazIstorijeSopstvenihVoznjiVoznjeAplikacija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVoznjeZaIstorijuVoznjePrekoAplikacije().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Trenutno nema voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    IstorijaVoznjeVozacAplikacija prozorZaPrikazIstorijeVoznji = new IstorijaVoznjeVozacAplikacija(ucitavanje, prijavljeniVozac);
                    prozorZaPrikazIstorijeVoznji.setVisible(true);
                }
            }
        });
        prikazVoznjeZakazanihPrekoAplikacije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVozacuSvihVoznjiNarucenihPrekoAplikacije().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Trenutno nema voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    PrikazDodeljenihVoznjiKreiranihAplikacijom prikazVoznjiZakazanihPrekoAplikacije = new PrikazDodeljenihVoznjiKreiranihAplikacijom(ucitavanje, prijavljeniVozac);
                    prikazVoznjiZakazanihPrekoAplikacije.setVisible(true);
                }
            }
        });
        prikazDodeljenihVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazDodeljenihVoznji().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nema dodeljenih voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    PrikazDodeljenihVoznjiKreiranihTelefonom prikazDodeljenihVoznji = new PrikazDodeljenihVoznjiKreiranihTelefonom(ucitavanje);
                    prikazDodeljenihVoznji.setVisible(true);
                }
            }
        });
        sumiraneStatistikeVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        aukcijeVoznjeTelefoni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nazalost, nema kreiranih voznji.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    UcestvujUaukcijiVoznjeTelefon aukcijeVoznje = new UcestvujUaukcijiVoznjeTelefon(ucitavanje, voznjaTelefon);
                    aukcijeVoznje.setVisible(true);
                }
            }
        });
        aukcijeVoznjeAplikacija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemAplikacije().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nazalost, nema kreiranih voznji.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    UcestvujUaukcijiVoznjeAplikacija aukcijeVoznje = new UcestvujUaukcijiVoznjeAplikacija(ucitavanje, voznjaAplikacija);
                    aukcijeVoznje.setVisible(true);
                }
            }
        });
        zavrsavanjeVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVoznjeZaZavrsavanjeVoznje().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nema prihvacenih voznji!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    ZavrsavanjeVoznjePutemTelefona zavrsavanjeVoznje = new ZavrsavanjeVoznjePutemTelefona(ucitavanje);
                    zavrsavanjeVoznje.setVisible(true);
                }
            }
        });
        zavrsavanjeVoznjePutemAplikacije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ucitavanje.prikazVoznjeZaZavrsavanjeVoznjePutemAplikacije().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nema prihvacenih voznji!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    ZavrsavanjeVoznjePutemAplikacije zavrsavanjeVoznjePutemAplikacije = new ZavrsavanjeVoznjePutemAplikacije(ucitavanje);
                    zavrsavanjeVoznjePutemAplikacije.setVisible(true);
                }
            }
        });

        //STATISTIKA VOZNJI
        dnevna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DnevnaStatistika dnevnaStatistika = new DnevnaStatistika(ucitavanje);
                dnevnaStatistika.setVisible(true);
            }
        });


        potvrdaZaOdjavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste se odjavili!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                MogucnostiVozaca.this.dispose();
                MogucnostiVozaca.this.setVisible(false);
                TaxiSluzbaMain.main(null);
            }
        });
        odustaniZaOdjavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste odustali od odjave!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
