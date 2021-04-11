package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import osobe.Dispecar;
import osobe.Musterija;
import osobe.Osoba;
import osobe.Vozac;
import ucitavanje.Ucitavanje;

public class LoginProzorTEST extends JFrame{

    private JLabel lblPoruka;
    private JLabel lblKorisnickoIme;
    private JTextField txtKorisnickoIme;
    private JLabel lblSifra;
    private JPasswordField pfSifra;
    private JButton btnOK;
    private JButton btnCancel;
    private Ucitavanje ucitavanje;

    public LoginProzorTEST(Ucitavanje ucitavanje) {
        this.ucitavanje = ucitavanje;
        setTitle("Prijava");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGUI();
        initActions();
        pack();
    }
    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
        setLayout(layout);

        this.lblPoruka = new JLabel("Dobrodošli. Molimo da se prijavite.");
        this.lblKorisnickoIme = new JLabel("Korisničko ime");
        this.txtKorisnickoIme = new JTextField(20);
        this.lblSifra = new JLabel("Šifra");
        this.pfSifra = new JPasswordField(20);
        this.btnOK = new JButton("OK");
        this.btnCancel = new JButton("Cancel");

        this.getRootPane().setDefaultButton(btnOK);

        add(lblPoruka, "span 2");
        add(lblKorisnickoIme);
        add(txtKorisnickoIme);
        add(lblSifra);
        add(pfSifra);
        add(new JLabel());
        add(btnOK, "split 2");
        add(btnCancel);

    }
    private void initActions() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String korisnickoIme = txtKorisnickoIme.getText().trim();
                String sifra = new String(pfSifra.getPassword()).trim();
                String tipDispecar = "DISPECAR";
                String tipMusterija = "MUSTERIJA";
                String tipVozac = "VOZAC";

                Vozac prijavljenVozac = ucitavanje.loginVozac(korisnickoIme, sifra, tipVozac);
                Musterija prijavljenMusterija = ucitavanje.loginMusterija(korisnickoIme, sifra, tipMusterija);
                Dispecar prijavljenDispecar = ucitavanje.loginDispecar(korisnickoIme, sifra, tipDispecar);

                Osoba osoba = ucitavanje.login(korisnickoIme, sifra);

                if (osoba == null)
                {
                    // los login
                }
                else // dobri su podaci
                {
                    if (osoba instanceof Dispecar)
                    {
                        // osoba je dispecer
                        Dispecar dispecar = (Dispecar) osoba;
                        //dispecar.getPlata();
                    }
                    if (osoba instanceof Vozac)
                    {
                        // osoba je vozac
                        Vozac vozac = (Vozac) osoba;
                    }
                    else
                    {
                        // osoba je musterija
                        Musterija musterija = (Musterija)osoba;
                    }
                }




                if (prijavljenMusterija == null && prijavljenVozac == null && prijavljenDispecar == null) {
                    JOptionPane.showMessageDialog(null, "Neispravni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Uspesno ste se prijavili!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }

                if (tipDispecar.equals("DISPECAR")){

                    JOptionPane.showMessageDialog(null, "Ulogovani ste kao dispecar!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }else if(tipMusterija.equals("MUSTERIJA")){
                    JOptionPane.showMessageDialog(null, "Ulogovani ste kao musterija", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }else if(tipVozac.equals("VOZAC")){
                    JOptionPane.showMessageDialog(null, "Ulogovani ste kao vozac!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste izasli iz aplikacije", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                LoginProzorTEST.this.setVisible(false);
                LoginProzorTEST.this.dispose();
            }
        });
    }
}
