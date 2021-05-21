package dispecer.izvestaj;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class ProzorZaPrikazIzvestaja extends JFrame {

    private JLabel ukupanBrojVoznji = new JLabel("Ukupan broj voznji ");
    private JTextField tukupanBrojVoznji = new JTextField(12);
    private JLabel ukupanBrojVoznjiTelefon = new JLabel("Ukupan broj voznji narucenih putem telefona");
    private JTextField tukupanBrojVoznjiTelefon = new JTextField(12);
    private JLabel ukupanBrojVoznjiAplikacija = new JLabel("Ukupan broj voznji narucenih putem aplikacije");
    private JTextField tukupanBrojVoznjiAplikacija = new JTextField(12);
    private JLabel prosecnoTrajanjeVoznje = new JLabel("Prosecno trajanje voznje ");
    private JTextField tprosecnoTrajanjeVoznje = new JTextField(12);
    private JLabel prosecanBrojPredjenihKm = new JLabel("Prosecna kilometraza ");
    private JTextField tprosecanBrojPredjenihKm = new JTextField(12);
    private JLabel ukupnaZaradaZaSveVoznje = new JLabel("Ukupna zarada za sve voznje ");
    private JTextField tukupnaZaradaZaSveVoznje = new JTextField(12);
    private JLabel prosecnaZaradaPoVoznji = new JLabel("Prosecna zarada po voznji ");
    private JTextField tprosecnaZaradaPoVoznji = new JTextField(12);

    public Liste ucitavanje;


    public ProzorZaPrikazIzvestaja(String unosDatuma, int uporediDatum, int uporediDatumIvoznjeAplikacijom, int uporediDatumIvoznjeTelefonom, int uporediDatumItrajanjeVoznje, int uporediDatumIkilometrazu, int ukupnaZaradaZaSveVoznje, int prosecnaZaradaPoVoznji) {
        setTitle("Izvestaj za datum: " + unosDatuma);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        popunjavanjePolja(uporediDatum,uporediDatumIvoznjeAplikacijom,uporediDatumIvoznjeTelefonom,uporediDatumItrajanjeVoznje,uporediDatumIkilometrazu,ukupnaZaradaZaSveVoznje,prosecnaZaradaPoVoznji);
    }


    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(ukupanBrojVoznji);
        add(tukupanBrojVoznji);
        add(ukupanBrojVoznjiTelefon);
        add(tukupanBrojVoznjiTelefon);
        add(ukupanBrojVoznjiAplikacija);
        add(tukupanBrojVoznjiAplikacija);
        add(prosecnoTrajanjeVoznje);
        add(tprosecnoTrajanjeVoznje);
        add(prosecanBrojPredjenihKm);
        add(tprosecanBrojPredjenihKm);
        add(ukupnaZaradaZaSveVoznje);
        add(tukupnaZaradaZaSveVoznje);
        add(prosecnaZaradaPoVoznji);
        add(tprosecnaZaradaPoVoznji);

        tukupanBrojVoznji.setEditable(false);
        tukupanBrojVoznjiAplikacija.setEditable(false);
        tukupanBrojVoznjiTelefon.setEditable(false);
        tprosecanBrojPredjenihKm.setEditable(false);
        tprosecnoTrajanjeVoznje.setEditable(false);
        tukupnaZaradaZaSveVoznje.setEditable(false);
        tprosecnaZaradaPoVoznji.setEditable(false);

    }

    private void popunjavanjePolja(int uporediDatum, int uporediDatumIvoznjeAplikacijom, int uporediDatumIvoznjeTelefonom, int uporediDatumItrajanjeVoznje, int uporediDatumIkilometrazu, int ukupnaZaradaZaSveVoznje, int prosecnaZaradaPoVoznji) {

        tukupanBrojVoznji.setText(String.valueOf(uporediDatum));
        tukupanBrojVoznjiAplikacija.setText(String.valueOf(uporediDatumIvoznjeAplikacijom));
        tukupanBrojVoznjiTelefon.setText(String.valueOf(uporediDatumIvoznjeTelefonom));
        tprosecnoTrajanjeVoznje.setText(String.valueOf(uporediDatumItrajanjeVoznje) + " min");
        tprosecanBrojPredjenihKm.setText(String.valueOf(uporediDatumIkilometrazu) + " km");
        tukupnaZaradaZaSveVoznje.setText(String.valueOf(ukupnaZaradaZaSveVoznje) + " din");
        tprosecnaZaradaPoVoznji.setText(String.valueOf(prosecnaZaradaPoVoznji) + " din");

    }


}
