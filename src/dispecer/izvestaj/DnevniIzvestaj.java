package dispecer.izvestaj;

import liste.Liste;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DnevniIzvestaj extends JFrame {

    private JLabel datum = new JLabel("Datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;

    public DnevniIzvestaj(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        setTitle("Dnevni izvestaj");
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
                if(validacija() == true) {
                    String unosDatuma = datumUnos.getText().trim();

                    boolean voznje = ucitavanje.nadjiDatum(unosDatuma);


                    if (voznje == false) {
                        JOptionPane.showMessageDialog(null, "Za datum: " + unosDatuma + " nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        String uneseniDatum = datumUnos.getText().trim();

                        int uporediDatum = ucitavanje.uporediDatum(uneseniDatum);
                        int uporediDatumIvoznjeAplikacijom = ucitavanje.uporediDatumIvoznjeAplikacijom(uneseniDatum);
                        int uporediDatumIvoznjeTelefonom = ucitavanje.uporediDatumIvoznjeTelefonom(uneseniDatum);
                        int uporediDatumItrajanjeVoznje = (int) ucitavanje.uporediDatumItrajanjeVoznje(uneseniDatum);
                        int uporediDatumIkilometrazu = (int) ucitavanje.uporediDatumIkilometrazu(uneseniDatum);
                        int ukupnaZaradaZaSveVoznje = (int) ucitavanje.ukupnaZaradaZaSveVoznje(uneseniDatum);
                        int prosecnaZaradaPoVoznji = (int) ucitavanje.prosecnaZaradaZaVoznje(uneseniDatum);

                        ProzorZaPrikazDnevnogIzvestaja prozorZaPrikazIzvestaja = new ProzorZaPrikazDnevnogIzvestaja(unosDatuma, uporediDatum, uporediDatumIvoznjeAplikacijom, uporediDatumIvoznjeTelefonom, uporediDatumItrajanjeVoznje, uporediDatumIkilometrazu, ukupnaZaradaZaSveVoznje, prosecnaZaradaPoVoznji);
                        prozorZaPrikazIzvestaja.setVisible(true);
                    }
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                DnevniIzvestaj.this.setVisible(false);
                DnevniIzvestaj.this.dispose();
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

    /*
          Specifikacija:
            Prikaz sumiranog izveštaja na dnevnom, nedeljnom, mesečnom i godišnjem nivou. Izveštaj treba
            da sadrži podatke o ukupnom broju vožnji, broju vožnji poručenim putem aplikacije, broju vožnji
            poručenim putem telefonskog poziva, broju aktivnih vozača, prosečnom trajanju vožnje, prosečnom
            broju pređenih kilometara, ukupnoj zaradi za sve vožnje, i prosečnoj zaradi po vožnji. Zaradu
            vozača po vožnji računati po sledećoj formuli:
                start + brojPredjenihKilometara * cenaPoKilometru.

          Odgovor asistenta:
            Broj aktivnih vozaca se misli na one vozace koji su vozili taj dan/nedelju/mesec itd a ne na
            one koji su neobrisani
            A ono za zaradu kada je po vozacu onda je ukupna zarada za tog vozaca a kada je za sve onda
            je ukupna zarada za sve
            Treba da se odvoje voznje koje su putem telefona i koje su putem aplikacije
            A za interval mozemo da izaberemo dan i hocu nedelju dana unazad ili mesec dana unazad uglavnom
            je bitno da moze tako da se bira da mi dobili sve bodove
    */
