package dispecer.izvestaj;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzvestajiDispecera extends JFrame {

    private JLabel datum = new JLabel("Datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;

    public IzvestajiDispecera(Liste ucitavanje){
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
        add(btnCancel);
    }

    private void initActions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String unosDatuma = datumUnos.getText().trim();

                boolean voznje = ucitavanje.nadjiDatum(unosDatuma);


                if(voznje == false){
                    JOptionPane.showMessageDialog(null,"Za datum: " + unosDatuma + " nema voznji.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else{

                    String uneseniDatum = datumUnos.getText().trim();

                    int uporediDatum = ucitavanje.uporediDatum(uneseniDatum);
                    int uporediDatumIvoznjeAplikacijom = ucitavanje.uporediDatumIvoznjeAplikacijom(uneseniDatum);
                    int uporediDatumIvoznjeTelefonom = ucitavanje.uporediDatumIvoznjeTelefonom(uneseniDatum);
                    int uporediDatumItrajanjeVoznje = (int) ucitavanje.uporediDatumItrajanjeVoznje(uneseniDatum);
                    int uporediDatumIkilometrazu = (int) ucitavanje.uporediDatumIkilometrazu(uneseniDatum);
                    int ukupnaZaradaZaSveVoznje = (int) ucitavanje.ukupnaZaradaZaSveVoznje(uneseniDatum);
                    int prosecnaZaradaPoVoznji = (int) ucitavanje.prosecnaZaradaZaVoznje(uneseniDatum);

                    ProzorZaPrikazIzvestaja prozorZaPrikazIzvestaja = new ProzorZaPrikazIzvestaja(unosDatuma,uporediDatum,uporediDatumIvoznjeAplikacijom,uporediDatumIvoznjeTelefonom,uporediDatumItrajanjeVoznje,uporediDatumIkilometrazu,ukupnaZaradaZaSveVoznje,prosecnaZaradaPoVoznji);
                    prozorZaPrikazIzvestaja.setVisible(true);
                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                IzvestajiDispecera.this.setVisible(false);
                IzvestajiDispecera.this.dispose();
            }
        });
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
