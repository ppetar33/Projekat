package vozac;

import liste.Liste;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ProzorZaPrikazStatistika extends JFrame {

    private JLabel ukupanBrojVoznji = new JLabel("Ukupan broj voznji");
    private JTextField tukupanBrojVoznji = new JTextField(12);

    public Liste ucitavanje;

    public ProzorZaPrikazStatistika(int ukupanBrojVoznji){
        setTitle("Prikaz statistika");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        popunjavanjePolja(ukupanBrojVoznji);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);

        add(ukupanBrojVoznji);
        add(tukupanBrojVoznji);

        tukupanBrojVoznji.setEditable(false);
    }

    private void popunjavanjePolja(int ukupanBrojVoznji){
        tukupanBrojVoznji.setText(String.valueOf(ukupanBrojVoznji));
    }
}
