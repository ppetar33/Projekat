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
import java.time.LocalDateTime;

public class DnevniIzvestajVozaca extends JFrame {

    private JLabel datum = new JLabel("Unesi datum");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOK = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;

    public DnevniIzvestajVozaca(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;
        setTitle("Dnevni izvestaj vozaca");
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

                        String uneseniDatum = datumUnos.getText().trim();
                        String[] nizDatum = uneseniDatum.split("-");
                        int uneseniDan = Integer.parseInt(nizDatum[2]);
                        int uneseniMesec = Integer.parseInt(nizDatum[1]);
                        int unesenaGodina = Integer.parseInt(nizDatum[0]);

                        DoublyLinkedList<Vozac> vozaci = ucitavanje.dohvatiVozace();
                        DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> voznjaAplikacije = ucitavanje.zavrsenePutemAplikacije();
                        DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> voznjaTelefon = ucitavanje.zavrsenePutemTelefona();
                        DoublyLinkedList<Izvestaji> tests = new DoublyLinkedList<>();


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
                                    if (dan == uneseniDan && mesec == uneseniMesec && godina == unesenaGodina ){
                                        ukupnoVoznji++;
                                        ukupnoKilometara+= x.getBrojKMpredjenih();
                                        ukupnoTrajanje+= x.getTrajanjVoznje();
                                        ukupnaZarada+= x.getCenaVoznje();
                                    }
                                }
                            }

                            for (NarucivanjeVoznjePrekoTelefona x : voznjaTelefon) {
                                if (x.getVozac().getKorisnickoIme().equals(trenutniVozac)) {
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

                            if (ukupnoVoznji > 0) {
                                prosekKilometara = ukupnoKilometara / ukupnoVoznji;
                                prosekTrajanja = ukupnoTrajanje / ukupnoVoznji;
                                prosecnaZarada = ukupnaZarada / ukupnoVoznji;
                                //todo
                                prosecnoBezVoznje = (480 - ukupnoTrajanje) / 60;
                            }


                            Izvestaji proba = new Izvestaji(trenutniVozac,ukupnoVoznji,ukupnoKilometara,prosekKilometara,ukupnoTrajanje,prosekTrajanja,ukupnaZarada,prosecnaZarada,prosecnoBezVoznje);
                            if(ukupnoVoznji!=0) {
                                tests.add(proba);
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