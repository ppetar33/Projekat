package vozac.Statistika;

import liste.Liste;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ProzorZaPrikazStatistika extends JFrame {

    private JLabel ukupanBrojVoznji = new JLabel("Ukupan broj voznji");
    private JTextField tukupanBrojVoznji = new JTextField(12);
    private JLabel ukupanBrojPredjenihKilometara = new JLabel("Ukupan broj predjenih kilometara");
    private JTextField tukupanBrojPredjenihKilometara = new JTextField(12);
    private JLabel ukupnoTrajanjeVoznji = new JLabel("Ukupno trajanje voznji");
    private JTextField tukupnoTrajanjeVoznji = new JTextField(12);
    private JLabel prosecanBrojKilometara = new JLabel("Prosecan broj predjenih kilometara");
    private JTextField tprosecanBrojKilometara = new JTextField(12);
    private JLabel prosecnoTrajanjeVoznji = new JLabel("Prosecno trajanje voznji");
    private JTextField tprosecnoTrajanjeVoznji = new JTextField(12);
    private JLabel prosecnoBezVoznje = new JLabel("Prosecno bez voznje");
    private JTextField tprosecnoBezVoznje = new JTextField(12);
    private JLabel ukupnaZarada = new JLabel("Ukupna zarada");
    private JTextField tukupnaZarada = new JTextField(12);
    private JLabel prosecnaZarada = new JLabel("Prosecna zarada");
    private JTextField tprosecnaZarada = new JTextField(12);

    public Liste ucitavanje;

    public ProzorZaPrikazStatistika(int ukupnoVoznji, double ukupnoKilometara, double ukupnoTrajanje, double prosekKilometara, double prosekTrajanja, double prosecnoBezVoznje, double ukupnaZarada, double prosecnaZarada){
        setTitle("Prikaz statistika");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        popunjavanjePolja(ukupnoVoznji, ukupnoKilometara, ukupnoTrajanje, prosekKilometara, prosekTrajanja, prosecnoBezVoznje, ukupnaZarada, prosecnaZarada);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);

        add(ukupanBrojVoznji);
        add(tukupanBrojVoznji);
        add(ukupanBrojPredjenihKilometara);
        add(tukupanBrojPredjenihKilometara);
        add(ukupnoTrajanjeVoznji);
        add(tukupnoTrajanjeVoznji);
        add(prosecanBrojKilometara);
        add(tprosecanBrojKilometara);
        add(prosecnoTrajanjeVoznji);
        add(tprosecnoTrajanjeVoznji);
        add(prosecnoBezVoznje);
        add(tprosecnoBezVoznje);
        add(ukupnaZarada);
        add(tukupnaZarada);
        add(prosecnaZarada);
        add(tprosecnaZarada);

        tukupanBrojVoznji.setEditable(false);
        tukupanBrojPredjenihKilometara.setEditable(false);
        tukupnoTrajanjeVoznji.setEditable(false);
        tprosecanBrojKilometara.setEditable(false);
        tprosecnoTrajanjeVoznji.setEditable(false);
        tprosecnoBezVoznje.setEditable(false);
        tukupnaZarada.setEditable(false);
        tprosecnaZarada.setEditable(false);
    }

    private void popunjavanjePolja(int ukupnoVoznji, double ukupnoKilometara, double ukupnoTrajanje, double prosekKilometara, double prosekTrajanja, double prosecnoBezVoznje, double ukupnaZarada, double prosecnaZarada){
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        tukupanBrojVoznji.setText(String.valueOf(ukupnoVoznji));
        tukupanBrojPredjenihKilometara.setText(String.valueOf(ukupnoKilometara + " km"));
        tukupnoTrajanjeVoznji.setText(String.valueOf(ukupnoTrajanje + " min"));
        tprosecanBrojKilometara.setText(String.valueOf(prosekKilometara));
        tprosecnoTrajanjeVoznji.setText(String.valueOf(prosekTrajanja + " min"));
        tprosecnoBezVoznje.setText(String.valueOf(df.format(prosecnoBezVoznje) + " h"));
        tukupnaZarada.setText(String.valueOf(ukupnaZarada + " din"));
        tprosecnaZarada.setText(String.valueOf(prosecnaZarada + " din"));
    }
}
