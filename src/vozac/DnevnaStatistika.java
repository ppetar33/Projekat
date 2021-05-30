package vozac;

import liste.Liste;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DnevnaStatistika extends JFrame {

    private JLabel datum = new JLabel("Unesi datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;

    public DnevnaStatistika(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        setTitle("Dnevna statistika");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
        initActions();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(datum);
        add(datumUnos);
        add(new JLabel());
        add(btnOk,"split 2");
        this.getRootPane().setDefaultButton(btnOk);
        add(btnCancel);
    }

    private void initActions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosDatuma = datumUnos.getText().trim();
                    boolean voznje = ucitavanje.nadjiDatum(unosDatuma);

                    if (voznje == false) {
                        JOptionPane.showMessageDialog(null, "Nazalost, za uneti datum, nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        String uneseniDatum = datumUnos.getText().trim();

                        int ukupanBrojVoznjiAplikacija = ucitavanje.uporediDatumIVoznjeAplikacijomZaStatistikuDnevnu(uneseniDatum);
                        int ukupanBrojVoznjiTelefon = ucitavanje.uporediDatumIVoznjeTelefonomZaStatistikuDnevnu(uneseniDatum);
                        int ukupanBrojSvihVoznji = ukupanBrojVoznjiAplikacija + ukupanBrojVoznjiTelefon;

                        ProzorZaPrikazStatistika prozorZaPrikazStatistika = new ProzorZaPrikazStatistika(ukupanBrojSvihVoznji);
                        prozorZaPrikazStatistika.setVisible(true);
                    }
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                DnevnaStatistika.this.setVisible(false);
                DnevnaStatistika.this.dispose();
            }
        });
    }

    public boolean validacija() {

        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if(datumUnos.getText().equals("")){
            obavestenjeZaGresku += "\nMorate uneti datum! \n";
            ok = false;
        }

        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci!", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }
}
