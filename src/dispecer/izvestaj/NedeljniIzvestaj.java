package dispecer.izvestaj;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class NedeljniIzvestaj extends JFrame {

    private JLabel datum = new JLabel("Datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;

    public NedeljniIzvestaj(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;
        setTitle("Nedeljeni izvestaj");
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

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate parsiranjeUnesenogDatuma = LocalDate.parse(unosDatuma, formatter);
                    LocalDate sedamDana = parsiranjeUnesenogDatuma.minusDays(7);


                    String[] days = new String[7];
                    for (int i = 0; i < days.length; i++) {
                        days[i] = parsiranjeUnesenogDatuma.minusDays(days.length - i - 1).toString();
                    }
                    DoublyLinkedList<String> dani = new DoublyLinkedList<>();
                    for (String x : days) {
                        dani.add(x);
                        System.out.println(x); // 7 dana unazad od unesenog datuma
                        // potrebno je proveriti uneseni datum i 7 dana unazad sa datumima iz fajla
                        // metoda ce da primi ovaj uneseni datum i sedam dana unazad
                        // posto ne znam koji su to datumi bice String datum, datum.minusDays(7)
                        // ako ne postoji datum ni jedan potrebno je da ispise da u intervalu od tog do tog datume ne postoje voznje
                        // ako postoje uzeti voznje koje postoje i pozvati novi prozor za racunanje
                    }


                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                NedeljniIzvestaj.this.setVisible(false);
                NedeljniIzvestaj.this.dispose();
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
