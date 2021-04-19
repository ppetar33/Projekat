package dispecer;

//    prilikom dodavanja automobila, automobil je moguće dodeliti nekom od vozača koji nemaju dodeljen automobil

import automobili.Automobil;
import ucitavanje.Ucitavanje_i_Snimanje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private Ucitavanje_i_Snimanje ucitavanje;
    private Automobil automobil;

    public DodavanjeAutomobila(Ucitavanje_i_Snimanje ucitavanje, Automobil automobil) {
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;

        setTitle("Dodavanje Automobila");
        setSize(900,600);
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
        godinaProizvodnje.setSize(130, 20);
        godinaProizvodnje.setLocation(40, 180);
        c.add(godinaProizvodnje);

        tgodinaProizvodnje = new JTextField();
        tgodinaProizvodnje.setFont(new Font("Arial", Font.PLAIN, 15));
        tgodinaProizvodnje.setSize(190, 35);
        tgodinaProizvodnje.setLocation(180, 175);
        c.add(tgodinaProizvodnje);

        brojRegistarskeOznake = new JLabel("Broj registarske oznake: ");
        brojRegistarskeOznake.setFont(new Font("Arial", Font.PLAIN, 18));
        brojRegistarskeOznake.setSize(100, 20);
        brojRegistarskeOznake.setLocation(40, 240);
        c.add(brojRegistarskeOznake);

        tbrojRegistarskeOznake = new JTextField();
        tbrojRegistarskeOznake.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojRegistarskeOznake.setSize(190, 35);
        tbrojRegistarskeOznake.setLocation(180, 235);
        c.add(tbrojRegistarskeOznake);

        brojTaksiVozila = new JLabel("Broj taksi vozila: ");
        brojTaksiVozila.setFont(new Font("Arial", Font.PLAIN, 18));
        brojTaksiVozila.setSize(100, 20);
        brojTaksiVozila.setLocation(40, 300);
        c.add(brojTaksiVozila);

        tbrojTaksiVozila = new JTextField();
        tbrojTaksiVozila.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojTaksiVozila.setSize(190, 35);
        tbrojTaksiVozila.setLocation(180, 295);
        c.add(tbrojTaksiVozila);

        vrstaVozila = new JLabel("Vrsta Vozila: ");
        vrstaVozila.setFont(new Font("Arial", Font.PLAIN, 18));
        vrstaVozila.setSize(100, 20);
        vrstaVozila.setLocation(40, 360);
        c.add(vrstaVozila);

        putnickiAutomobil = new JRadioButton("Putnicki Automobil");
        putnickiAutomobil.setFont(new Font("Arial", Font.PLAIN, 15));
        putnickiAutomobil.setSelected(true);
        putnickiAutomobil.setSize(75, 20);
        putnickiAutomobil.setLocation(180, 360);
        c.add(putnickiAutomobil);

        kombi = new JRadioButton("Kombi");
        kombi.setFont(new Font("Arial", Font.PLAIN, 15));
        kombi.setSelected(false);
        kombi.setSize(80, 20);
        kombi.setLocation(255, 360);
        c.add(kombi);

        vrstaVozilaDugme = new ButtonGroup();
        vrstaVozilaDugme.add(putnickiAutomobil);
        vrstaVozilaDugme.add(kombi);

        btnOK = new JButton("Potvrdi");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 19));
        btnOK.setSize(180, 40);
        btnOK.setLocation(350, 450);
        btnOK.setBackground(Color.BLUE);
        c.add(btnOK);

        initListeners();
    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(proveraPodataka() == true){

                }
            }
        });
    }

    private  boolean proveraPodataka(){
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if (tmodel.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti model automobila!\n";
            ok = false;
        }
        if (tproizvodjac.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti proizvodjaca za automobil!\n";
            ok = false;
        }
        try {
            Integer.parseInt(tgodinaProizvodnje.getText().trim());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Godina proizvodnje mora biti broj!\n";
            ok = false;
        }
        try {
            Integer.parseInt(tbrojRegistarskeOznake.getText().trim());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Broj registarske oznake mora biti broj!\n";
            ok = false;
        }
        try {
            Integer.parseInt(tbrojTaksiVozila.getText().trim());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Broj taksi vozila mora biti broj!\n";
            ok = false;
        }
        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}


