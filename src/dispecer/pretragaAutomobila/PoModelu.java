package dispecer.pretragaAutomobila;

import automobili.Automobil;
import dispecer.pretragaVozaca.PoAutomobilu;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class PoModelu extends JFrame {

    public JLabel pretragaPoModelu = new JLabel("Unesi model automobila");
    private JTextField tpretragaPoModelu = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Automobil automobil;

    public PoModelu(Liste ucitavanje, Automobil automobil){
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;
        setTitle("Pretraga automobila po modelu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGui();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }

    private void initGui() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(pretragaPoModelu);
        add(tpretragaPoModelu);
        add(new JLabel());
        add(btnOK, "split 2");
        add(cancel);
    }


    public DoublyLinkedList<Automobil> pretragaPoModelu(DoublyLinkedList<Automobil> automobili, String model ){
        DoublyLinkedList<Automobil> pretrazeni = new DoublyLinkedList<Automobil>();
        for (Automobil a: automobili) {
            if (a.getModel().equals(model)){
                pretrazeni.add(a);
            }
        }
        return pretrazeni;
    }

    private void initListeners() {
        String[] zaglavlje = new String[]{"ID", "Model", "Proizvodjac", "Godina proizvodnje", "Broj registarske oznake", "Broj taksi vozila", "Vrsta vozila", "Status automobila", "Pet Friendly"};


        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosModela = tpretragaPoModelu.getText().trim();

                    DoublyLinkedList<Automobil> rezultatPretrage = ucitavanje.nadjiAutomobilPoModelu(unosModela);

                    if (rezultatPretrage.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Automobil modela (" + unosModela + ") ne postoji!","GRESKA",JOptionPane.WARNING_MESSAGE);
                    }else {
                        ProzorZaPretraguPoModelu prozorZaPretraguPoModelu = new ProzorZaPretraguPoModelu();
                        prozorZaPretraguPoModelu.setVisible(true);
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoModelu.this.setVisible(false);
                PoModelu.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        if(tpretragaPoModelu.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Uneli ste pogresne podatke",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
