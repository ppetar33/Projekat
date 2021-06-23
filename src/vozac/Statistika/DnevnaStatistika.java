package vozac.Statistika;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class DnevnaStatistika extends JFrame {

    private JLabel datum = new JLabel("Unesi datum");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOK = new JButton("OK");
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
        add(btnOK,"split 2");
        this.getRootPane().setDefaultButton(btnOK);
        add(btnCancel);
    }

    private void initActions(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosDatuma = datumUnos.getText().trim();
                    boolean voznje = ucitavanje.nadjiDatum(unosDatuma);

                    if (voznje == false){
                        JOptionPane.showMessageDialog(null, "Nazalost, za uneti datum, nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        String uneseniDatum = datumUnos.getText().trim();
                        String[] nizDatum = uneseniDatum.split("-");
                        int uneseniDan = Integer.parseInt(nizDatum[2]);
                        int uneseniMesec = Integer.parseInt(nizDatum[1]);
                        int unesenaGodina = Integer.parseInt(nizDatum[0]);

                        DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> voznjaAplikacije = ucitavanje.zavrsenePutemAplikacije();
                        DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> voznjaTelefon = ucitavanje.zavrsenePutemTelefona();

                        int ukupnoVoznji = 0;
                        double ukupnoKilometara = 0;
                        double ukupnoTrajanje = 0;
                        double prosekKilometara = 0;
                        double prosekTrajanja = 0;
                        double prosecnoBezVoznje = 0;
                        double ukupnaZarada = 0;
                        double prosecnaZarada = 0;

                        String ulogovaniVozac = ucitavanje.ulogovanKorisnik();

                        for (NarucivanjeVoznjePrekoAplikacije x : voznjaAplikacije){
                            if (x.getVozac().getKorisnickoIme().equals(ulogovaniVozac)){
                                LocalDateTime datum = x.getDatumIvremePorudzbine();
                                int dan = datum.getDayOfMonth();
                                int mesec = datum.getMonthValue();
                                int godina = datum.getYear();
                                if (dan == uneseniDan && mesec == uneseniMesec && godina == unesenaGodina ){
                                    ukupnoVoznji++;
                                    ukupnoKilometara+= x.getBrojKMpredjenih();
                                    ukupnoTrajanje+= x.getTrajanjVoznje();
                                    ukupnaZarada+= x.getCenaVoznje();
                                }
                            }
                        }

                        for (NarucivanjeVoznjePrekoTelefona x : voznjaTelefon){
                            if (x.getVozac().getKorisnickoIme().equals(ulogovaniVozac)){
                                LocalDateTime datum = x.getDatumIvremePorudzbine();
                                int dan = datum.getDayOfMonth();
                                int mesec = datum.getMonthValue();
                                int godina = datum.getYear();
                                if (dan == uneseniDan && mesec == uneseniMesec && godina == unesenaGodina ){
                                    ukupnoVoznji++;
                                    ukupnoKilometara+= x.getBrojKMpredjenih();
                                    ukupnoTrajanje+= x.getTrajanjVoznje();
                                    ukupnaZarada+= x.getCenaVoznje();
                                }
                            }
                        }


                        if (ukupnoVoznji > 0){
                            prosekKilometara = ukupnoKilometara / ukupnoVoznji;
                            prosekTrajanja = ukupnoTrajanje / ukupnoVoznji;
                            prosecnaZarada = ukupnaZarada / ukupnoVoznji;
                            prosecnoBezVoznje = (480 - ukupnoTrajanje) / 60;
                        }

                        ProzorZaPrikazStatistika prozorZaPrikazStatistika = new ProzorZaPrikazStatistika(ukupnoVoznji, ukupnoKilometara, ukupnoTrajanje, prosekKilometara,prosekTrajanja,prosecnoBezVoznje, ukupnaZarada, prosecnaZarada);
                        prozorZaPrikazStatistika.setVisible(true);
                        DnevnaStatistika.this.setVisible(false);
                        DnevnaStatistika.this.dispose();
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
