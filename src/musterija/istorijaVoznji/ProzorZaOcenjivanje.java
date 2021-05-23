package musterija.istorijaVoznji;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaOcenjivanje extends JFrame {

    private JLabel oceniVozaca = new JLabel("Oceni vozaca ");
    private JComboBox<Double> ocene;
    private JButton btnOK = new JButton("Potvrdi");
    private Liste ucitavanje;
    private Vozac vozac;

    public ProzorZaOcenjivanje(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Oceni vozaca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI(){

        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(oceniVozaca);
        Double[] array = new Double[]{1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0};
        ocene = new JComboBox(array);
        add(ocene);
        add(new JLabel());
        add(btnOK,"split 2");

    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double selektovanaOcena = (double) ocene.getSelectedItem();
                double ocenaVozaca = vozac.getOcena();
                double novaOcena = (selektovanaOcena + ocenaVozaca) / 2.0;
                vozac.setOcena(novaOcena);
                ucitavanje.dodavanjeKorisnika();
                JOptionPane.showMessageDialog(null,"Uspesno ste ocenili vozaca!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
