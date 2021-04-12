package vozac;

import gui.LoginProzor;
import osobe.Vozac;
import ucitavanje.Ucitavanje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogucnostiVozaca extends JFrame {

    private JMenuBar vozacMenu = new JMenuBar();

    private JMenu funkcionalnostiVozaca = new JMenu("Voznje");
    private JMenuItem prikazVoznje = new JMenuItem("Prikaz sopstvenih voznji");
    private JMenuItem prikazVoznjeZakazanihPrekoAplikacije = new JMenuItem("Prikaz voznji zakazanih preko aplikacije");
    private JMenuItem prikazDodeljenihVoznji = new JMenuItem("Prikaz dodeljenih voznji");
    private JMenuItem sumiraneStatistikeVoznji = new JMenuItem("Prikaz sumiraze statistike voznji");
    private JMenuItem aukcijeVoznje = new JMenuItem("Aukcije voznje");

    private JMenu odjava = new JMenu("Odjava");
    private JMenuItem potvrdaZaOdjavu = new JMenuItem("Potvrdi");
    private JMenuItem odustaniZaOdjavu = new JMenuItem("Odustani");

    private Ucitavanje ucitavanje;
    private Vozac prijavljeniVozac;

    public MogucnostiVozaca(Ucitavanje ucitavanje,Vozac prijavljeniVozac){
        this.ucitavanje = ucitavanje;
        this.prijavljeniVozac = prijavljeniVozac;
        setTitle("Vozac, ime: " + prijavljeniVozac.getIme().substring(0, 1).toUpperCase() + prijavljeniVozac.getIme().substring(1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        initGUI();
        initListeners();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        setJMenuBar(vozacMenu);

        vozacMenu.add(funkcionalnostiVozaca);
        funkcionalnostiVozaca.add(prikazVoznje);
        funkcionalnostiVozaca.add(prikazVoznjeZakazanihPrekoAplikacije);
        funkcionalnostiVozaca.add(prikazDodeljenihVoznji);
        funkcionalnostiVozaca.add(sumiraneStatistikeVoznji);
        funkcionalnostiVozaca.add(aukcijeVoznje);

        vozacMenu.add(odjava);
        odjava.add(potvrdaZaOdjavu);
        odjava.add(odustaniZaOdjavu);
    }

    private void initListeners(){
        prikazVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });
        prikazVoznjeZakazanihPrekoAplikacije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });
        prikazDodeljenihVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });
        sumiraneStatistikeVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });
        aukcijeVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });
        potvrdaZaOdjavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste se odjavili!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                MogucnostiVozaca.this.dispose();
                MogucnostiVozaca.this.setVisible(false);
                LoginProzor loginProzor = new LoginProzor(ucitavanje);
                loginProzor.setVisible(true);
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
