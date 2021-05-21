package dispecer;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.NarucivanjeVoznjePrekoAplikacije;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IzvestajiDispecera extends JFrame {

    private JLabel datum = new JLabel("Datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;
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
                    dnevniIzvestaj(ucitavanje);
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

    public void dnevniIzvestaj(Liste ucitavanje){
        System.out.println("");
        ukupanBrojAktivnihVozaca(ucitavanje);
        ukupanBrojVoznji(ucitavanje);
        ukupanBrojVoznjiAplikacija(ucitavanje);
        ukupanBrojVoznjiTelefon(ucitavanje);
        prosecnoTrajanjeVoznje(ucitavanje);
        prosecanBrojPredjenihKm(ucitavanje);
        ukupnaZaradaZaSveVoznje(ucitavanje);
        prosecnaZaradaPoVoznji(ucitavanje);
    }

    public void ukupanBrojVoznji(Liste ucitavanje){

        String trenutniDatum = datumUnos.getText().trim();
        int uporediDatum = ucitavanje.uporediDatum(trenutniDatum);
        if (uporediDatum != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupan broj voznji je: " + uporediDatum);
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void ukupanBrojVoznjiAplikacija(Liste ucitavanje){

        String trenutniDatum = datumUnos.getText().trim();
        int uporediDatumIvoznjeAplikacijom = ucitavanje.uporediDatumIvoznjeAplikacijom(trenutniDatum);
        if (uporediDatumIvoznjeAplikacijom != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupan broj voznji kreiranih putem aplikacije je: " + uporediDatumIvoznjeAplikacijom);
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji kreiranih putem aplikacije!");
        }

    }
    public void ukupanBrojVoznjiTelefon(Liste ucitavanje){

        String trenutniDatum = datumUnos.getText().trim();
        int uporediDatumIvoznjeTelefonom = ucitavanje.uporediDatumIvoznjeTelefonom(trenutniDatum);
        if (uporediDatumIvoznjeTelefonom != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupan broj voznji kreiranih putem telefona je: " + uporediDatumIvoznjeTelefonom);
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji kreiranih putem telefona!");
        }

    }
    public void ukupanBrojAktivnihVozaca(Liste ucitavanje){

        int brojAktivnihVozaca = ucitavanje.brojAktivnihVozaca();
        if (brojAktivnihVozaca != 0){
            System.out.println("Ukupan broj aktivnih vozaca je: " + brojAktivnihVozaca);
        }else{
            System.out.println("Nema aktivnih vozaca.");
        }

    }
    public void prosecnoTrajanjeVoznje(Liste ucitavanje){

        String trenutniDatum = datumUnos.getText().trim();
        double uporediDatumItrajanjeVoznje = ucitavanje.uporediDatumItrajanjeVoznje(trenutniDatum);
        if (uporediDatumItrajanjeVoznje != 0){
            System.out.println("Za datum: " + trenutniDatum + ", prosecno trajanje voznji je: " + Math.round(uporediDatumItrajanjeVoznje) + " min");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void prosecanBrojPredjenihKm(Liste ucitavanje){

        String trenutniDatum = datumUnos.getText().trim();
        double uporediDatumIkilometrazu = ucitavanje.uporediDatumIkilometrazu(trenutniDatum);
        if (uporediDatumIkilometrazu != 0){
            System.out.println("Za datum: " + trenutniDatum + ", prosecna kilometraza je: " + Math.round(uporediDatumIkilometrazu) + " km");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void ukupnaZaradaZaSveVoznje(Liste ucitavanje){

        String trenutniDatum = datumUnos.getText().trim();
        double ukupnaZaradaZaSveVoznje = ucitavanje.ukupnaZaradaZaSveVoznje(trenutniDatum);
        if (ukupnaZaradaZaSveVoznje != 0){
            System.out.println("Za datum: " + trenutniDatum + ", ukupna zarada za sve voznje je: " + ukupnaZaradaZaSveVoznje + " din");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }
    public void ukupnaZaradaPoVoznji(Liste ucitavanje){
        // potrebno je obracunati ukupnu zaradu vozaca za sve voznje
    }
    public void prosecnaZaradaPoVoznji(Liste ucitavanje){

        String trenutniDatum = datumUnos.getText().trim();
        double prosecnaZaradaPoVoznji = ucitavanje.prosecnaZaradaZaVoznje(trenutniDatum);
        if (prosecnaZaradaPoVoznji != 0){
            System.out.println("Za datum: " + trenutniDatum + ", prosecna zarada po voznji je: " + prosecnaZaradaPoVoznji + " din");
        }else{
            System.out.println("Za datum: " + trenutniDatum + ", nema voznji!");
        }

    }

}
