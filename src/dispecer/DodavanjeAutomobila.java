package dispecer;

import automobili.Automobil;
import enumi.Obrisan;
import enumi.StatusAutomobila;
import enumi.VrstaVozila;
import podaci.Liste;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DodavanjeAutomobila extends JFrame {
    private Container c;
    private JLabel model;
    private JTextField tmodel;
    private JLabel proizvodjac;
    private JTextField tproizvodjac;
    private JLabel godinaProizvodnje;
    private JTextField tgodinaProizvodnje;
    private JLabel brojRegistarskeOznake;
    private JTextField tbrojRegistarskeOznake;
    private JLabel brojTaksiVozila;
    private JTextField tbrojTaksiVozila;
    private JLabel vrstaVozila;
    private JRadioButton putnickiAutomobil;
    private JRadioButton kombi;
    private ButtonGroup vrstaVozilaDugme;
    private JButton btnOK;

    private Liste ucitavanje;

    public DodavanjeAutomobila(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;

        setTitle("Dodavanje Automobila");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        model = new JLabel("Model:");
        model.setFont(new Font("Arial", Font.PLAIN, 18));
        model.setSize(100, 20);
        model.setLocation(40, 60);
        c.add(model);

        tmodel = new JTextField();
        tmodel.setFont(new Font("Arial", Font.PLAIN, 15));
        tmodel.setSize(190, 35);
        tmodel.setLocation(180, 55);
        c.add(tmodel);

        proizvodjac = new JLabel("Proizvodjac:");
        proizvodjac.setFont(new Font("Arial", Font.PLAIN, 18));
        proizvodjac.setSize(100, 20);
        proizvodjac.setLocation(40, 120);
        c.add(proizvodjac);

        tproizvodjac = new JTextField();
        tproizvodjac.setFont(new Font("Arial", Font.PLAIN, 15));
        tproizvodjac.setSize(190, 35);
        tproizvodjac.setLocation(180, 115);
        c.add(tproizvodjac);

        godinaProizvodnje = new JLabel("Godina proizvodnje: ");
        godinaProizvodnje.setFont(new Font("Arial", Font.PLAIN, 18));
        godinaProizvodnje.setSize(200, 20);
        godinaProizvodnje.setLocation(430, 120);
        c.add(godinaProizvodnje);

        tgodinaProizvodnje = new JTextField();
        tgodinaProizvodnje.setFont(new Font("Arial", Font.PLAIN, 15));
        tgodinaProizvodnje.setSize(190, 35);
        tgodinaProizvodnje.setLocation(650, 115);
        c.add(tgodinaProizvodnje);

        brojRegistarskeOznake = new JLabel("Broj registarske oznake: ");
        brojRegistarskeOznake.setFont(new Font("Arial", Font.PLAIN, 18));
        brojRegistarskeOznake.setSize(200, 20);
        brojRegistarskeOznake.setLocation(430, 60);
        c.add(brojRegistarskeOznake);

        tbrojRegistarskeOznake = new JTextField();
        tbrojRegistarskeOznake.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojRegistarskeOznake.setSize(190, 35);
        tbrojRegistarskeOznake.setLocation(650, 55);
        c.add(tbrojRegistarskeOznake);

        brojTaksiVozila = new JLabel("Broj taksi vozila: ");
        brojTaksiVozila.setFont(new Font("Arial", Font.PLAIN, 18));
        brojTaksiVozila.setSize(200, 20); //100
        brojTaksiVozila.setLocation(430, 180);
        c.add(brojTaksiVozila);

        tbrojTaksiVozila = new JTextField();
        tbrojTaksiVozila.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojTaksiVozila.setSize(190, 35);
        tbrojTaksiVozila.setLocation(650, 175);
        c.add(tbrojTaksiVozila);

        vrstaVozila = new JLabel("Vrsta Vozila: ");
        vrstaVozila.setFont(new Font("Arial", Font.PLAIN, 18));
        vrstaVozila.setSize(130, 20);
        vrstaVozila.setLocation(40, 180);
        c.add(vrstaVozila);

        putnickiAutomobil = new JRadioButton("Putnicki Automobil");
        putnickiAutomobil.setFont(new Font("Arial", Font.PLAIN, 15));
        putnickiAutomobil.setSelected(true);
        putnickiAutomobil.setSize(190, 20);
        putnickiAutomobil.setLocation(180, 180);
        c.add(putnickiAutomobil);

        kombi = new JRadioButton("Kombi");
        kombi.setFont(new Font("Arial", Font.PLAIN, 15));
        kombi.setSelected(false);
        kombi.setSize(195, 20);
        kombi.setLocation(330, 180);
        c.add(kombi);

        vrstaVozilaDugme = new ButtonGroup();
        vrstaVozilaDugme.add(putnickiAutomobil);
        vrstaVozilaDugme.add(kombi);

        btnOK = new JButton("Potvrdi");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 19));
        btnOK.setSize(180, 40);
        btnOK.setLocation(350, 300);
        btnOK.setBackground(Color.BLUE);
        c.add(btnOK);

        initListeners();
    }

    private void initListeners() {

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveraPodataka() == true) {
                    Automobil automobil = new Automobil();

                    String unosModel = tmodel.getText().trim();
                    String unosProizvodjac = tproizvodjac.getText().trim();
                    int unosGodinaProizvodnje = Integer.parseInt(tgodinaProizvodnje.getText().trim());
                    String unosBrojRegistarskeOznake = tbrojRegistarskeOznake.getText().trim();
                    int unosBrojTaksiVozila = Integer.parseInt(tbrojTaksiVozila.getText().trim());


                    automobil.setModel(unosModel);
                    automobil.setProizvodjac(unosProizvodjac);
                    automobil.setGodinaProizvodnje(unosGodinaProizvodnje);
                    automobil.setRegistarskiBroj(unosBrojRegistarskeOznake);
                    automobil.setBrojVozila(unosBrojTaksiVozila);
                    automobil.setObrisan(Obrisan.TRUE);
                    automobil.setStatusAutomobila(StatusAutomobila.SLOBODAN);
                    if (putnickiAutomobil.isSelected()) {
                        automobil.setVrstaVozila(VrstaVozila.PUTNICKI_AUTOMOBIL);
                    } else if (kombi.isSelected()) {
                        automobil.setVrstaVozila(VrstaVozila.KOMBI);
                    }
                    ArrayList<Automobil> automobili = ucitavanje.getAutomobili();
                    int id = generisiNoviId(automobili);
                    automobil.setId(id);
                    automobili.add(automobil);
                    ucitavanje.snimanjeAutomobila("automobil.txt");
                    JOptionPane.showMessageDialog(null, "Automobil je uspesno dodat!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    DodavanjeAutomobila.this.dispose();
                    DodavanjeAutomobila.this.setVisible(false);
                }
            }
        });
    }

    private int generisiNoviId(ArrayList<Automobil> automobili) {
        int maks = -1;
        for (Automobil a : automobili) {
            if (a.getId() > maks) {
                maks = a.getId();
            }
        }
        return maks + 1;
    }

    private boolean proveraPodataka() {
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if (tmodel.getText().trim().equals("")) {
            obavestenjeZaGresku += "Morate uneti model automobila!\n";
            ok = false;
        }
        if (tproizvodjac.getText().trim().equals("")) {
            obavestenjeZaGresku += "Morate uneti proizvodjaca za automobil!\n";
            ok = false;
        }
        try {
            Integer.parseInt(tgodinaProizvodnje.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Godina proizvodnje mora biti broj!\n";
            ok = false;
        }
        if(tbrojRegistarskeOznake.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti broj registarske oznake za automobil!\n";
            ok = false;
        }
        try {
            Integer.parseInt(tbrojTaksiVozila.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Broj taksi vozila mora biti broj!\n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}


