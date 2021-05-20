package vozac;

import main.TaxiSluzbaMain;
import osobe.Musterija;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogucnostiVozaca extends JFrame {

    private JMenuBar vozacMenu = new JMenuBar();

    private JMenu funkcionalnostiVozaca = new JMenu("Voznje");
    private JMenuItem prikazIstorijeSopstvenihVoznjiVoznje = new JMenuItem("Prikaz istorije sopstvenih voznji");
    private JMenuItem prikazVoznjeZakazanihPrekoAplikacije = new JMenuItem("Prikaz voznji zakazanih preko aplikacije");
    private JMenuItem prikazDodeljenihVoznji = new JMenuItem("Prikaz dodeljenih voznji kreiranih putem telefona");
    private JMenuItem sumiraneStatistikeVoznji = new JMenuItem("Prikaz sumiraze statistike voznji");
    private JMenuItem aukcijeVoznje = new JMenuItem("Aukcije voznje");
    private JMenuItem zavrsavanjeVoznje = new JMenuItem("Zavrsavanje voznje kreirane putem telefona");
    private JMenuItem zavrsavanjeVoznjePutemAplikacije = new JMenuItem("Zavrsavanje voznje kreirane putem aplikacije");


    private JMenu odjava = new JMenu("Odjava");
    private JMenuItem potvrdaZaOdjavu = new JMenuItem("Potvrdi");
    private JMenuItem odustaniZaOdjavu = new JMenuItem("Odustani");

    private Liste ucitavanje;
    private Vozac prijavljeniVozac;
    private Musterija musterija;

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
        funkcionalnostiVozaca.add(prikazIstorijeSopstvenihVoznjiVoznje);
        funkcionalnostiVozaca.add(prikazVoznjeZakazanihPrekoAplikacije);
        funkcionalnostiVozaca.add(prikazDodeljenihVoznji);
        funkcionalnostiVozaca.add(sumiraneStatistikeVoznji);
        funkcionalnostiVozaca.add(aukcijeVoznje);
        funkcionalnostiVozaca.add(zavrsavanjeVoznje);
        funkcionalnostiVozaca.add(zavrsavanjeVoznjePutemAplikacije);

        vozacMenu.add(odjava);
        odjava.add(potvrdaZaOdjavu);
        odjava.add(odustaniZaOdjavu);
    }

    private void initListeners(){
        prikazIstorijeSopstvenihVoznjiVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IstorijaVoznjeVozac prozorZaPrikazIstorijeVoznji = new IstorijaVoznjeVozac(ucitavanje, prijavljeniVozac);
                prozorZaPrikazIstorijeVoznji.setVisible(true);
            }
        });
        prikazVoznjeZakazanihPrekoAplikacije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikazVoznjiZakazanihPrekoAplikacije prikazVoznjiZakazanihPrekoAplikacije = new PrikazVoznjiZakazanihPrekoAplikacije(ucitavanje, prijavljeniVozac);
                prikazVoznjiZakazanihPrekoAplikacije.setVisible(true);
            }
        });
        prikazDodeljenihVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazDodeljenihVoznji().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nema dodeljenih voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    PrikazDodeljenihVoznji prikazDodeljenihVoznji = new PrikazDodeljenihVoznji(ucitavanje);
                    prikazDodeljenihVoznji.setVisible(true);
                }
            }
        });
        sumiraneStatistikeVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        aukcijeVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        zavrsavanjeVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVoznjeZaZavrsavanjeVoznje().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nema prihvacenih voznji!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    ZavrsavanjeVoznje zavrsavanjeVoznje = new ZavrsavanjeVoznje(ucitavanje);
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
