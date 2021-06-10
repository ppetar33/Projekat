package dispecer.izvestajVozaca;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NedeljniIzvestajVozaca extends JFrame {
    private JLabel datum = new JLabel("Unesi datum");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOK = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;

    public NedeljniIzvestajVozaca(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;
        setTitle("Nedeljni izvestaj vozaca");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
        initActions();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(datum);
        add(datumUnos);
        add(new JLabel());
        add(btnOK, "split 2");
        this.getRootPane().setDefaultButton(btnOK);
        add(btnCancel);
    }

    private void initActions() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosDatuma = datumUnos.getText().trim();
                    boolean voznje = ucitavanje.nadjiDatum(unosDatuma);

                    if (voznje == false) {
                        JOptionPane.showMessageDialog(null, "Nazalost, za uneti datum, nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        DoublyLinkedList<Izvestaji> tests = new DoublyLinkedList<>();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate uneseniDatum1 = LocalDate.parse(unosDatuma, formatter);

                        for (int d = 0; d < 7; d++) {

                            LocalDate trenutniDatum = uneseniDatum1.minusDays(d);
                            int uneseniDan = trenutniDatum.getDayOfMonth();
                            int uneseniMesec = trenutniDatum.getMonthValue();
                            int unesenaGodina = trenutniDatum.getYear();

                            DoublyLinkedList<Vozac> vozaci = ucitavanje.dohvatiVozace();
                            DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> voznjaAplikacije = ucitavanje.zavrsenePutemAplikacije();
                            DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> voznjaTelefon = ucitavanje.zavrsenePutemTelefona();


                            for (Vozac v : vozaci) {
                                String trenutniVozac = v.getKorisnickoIme();
                                int ukupnoVoznji = 0;
                                double ukupnoKilometara = 0;
                                double ukupnoTrajanje = 0;
                                double prosekKilometara = 0;
                                double prosekTrajanja = 0;
                                double prosecnoBezVoznje = 0;
                                double ukupnaZarada = 0;
                                double prosecnaZarada = 0;

                                for (NarucivanjeVoznjePrekoAplikacije x : voznjaAplikacije) {
                                    if (x.getVozac().getKorisnickoIme().equals(trenutniVozac)) {
                                        LocalDateTime datum = x.getDatumIvremePorudzbine();
                                        int dan = datum.getDayOfMonth();
                                        int mesec = datum.getMonthValue();
                                        int godina = datum.getYear();
                                        if (dan == uneseniDan && mesec == uneseniMesec && godina == unesenaGodina) {
                                            ukupnoVoznji++;
                                            ukupnoKilometara += x.getBrojKMpredjenih();
                                            ukupnoTrajanje += x.getTrajanjVoznje();
                                            ukupnaZarada += x.getCenaVoznje();
                                        }
                                    }
                                }

                                for (NarucivanjeVoznjePrekoTelefona x : voznjaTelefon) {
                                    if (x.getVozac().getKorisnickoIme().equals(trenutniVozac)) {
                                        LocalDateTime datum = x.getDatumIvremePorudzbine();
                                        int dan = datum.getDayOfMonth();
                                        int mesec = datum.getMonthValue();
                                        int godina = datum.getYear();
                                        if (dan == uneseniDan && mesec == uneseniMesec && godina == unesenaGodina) {
                                            ukupnoVoznji++;
                                            ukupnoKilometara += x.getBrojKMpredjenih();
                                            ukupnoTrajanje += x.getTrajanjVoznje();
                                            ukupnaZarada += x.getCenaVoznje();
                                        }
                                    }
                                }

                                if (ukupnoVoznji > 0) {
                                    prosekKilometara = ukupnoKilometara / ukupnoVoznji;
                                    prosekTrajanja = ukupnoTrajanje / ukupnoVoznji;
                                    prosecnaZarada = ukupnaZarada / ukupnoVoznji;
                                    //todo
                                    prosecnoBezVoznje = (480 - ukupnoTrajanje) / 60;
                                }


                                Izvestaji proba = new Izvestaji(trenutniVozac, ukupnoVoznji, ukupnoKilometara, prosekKilometara, ukupnoTrajanje, prosekTrajanja, ukupnaZarada, prosecnaZarada, prosecnoBezVoznje);
                                if (ukupnoVoznji != 0) {
                                    tests.add(proba);
                                }

                            }
                        }
                        TabelaPrikaz tabelaPrikaz = new TabelaPrikaz(tests);
                        tabelaPrikaz.setVisible(true);
                    }
                }
            }
        });
    }

    private boolean validacija() {
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";
        if (datumUnos.getText().equals("")) {
            obavestenjeZaGresku += "\nMorate uneti datum! \n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
