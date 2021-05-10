package dispecer.pretragaVozaca;

import automobili.Automobil;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PoAutomobilu extends JFrame {

    public JLabel pretragaPoAutomobilu = new JLabel("Unesi model automobila");
    private JTextField tpretragaPoAutomobilu = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Vozac vozac;
    private ArrayList<Automobil> automobils;
    private DefaultTableModel table_model;
    private JTable automobiliTabela;

    public PoAutomobilu(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        this.automobils = new ArrayList<Automobil>();
        setTitle("Pretraga Vozaca Po Automobilu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGui();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }
    private void initGui(){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(pretragaPoAutomobilu);
        add(tpretragaPoAutomobilu);
        add(new JLabel());
        add(btnOK, "split 2");
        add(cancel);
    }

    private void initListeners(){

        String[] zaglavlje = new String[]{"ID", "Model", "Proizvodjac", "Godina proizvodnje", "Broj registarske oznake", "Broj taksi vozila", "Vrsta automobila", "Status Automobila", "Pet Friendly"};

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                    String unosModela = tpretragaPoAutomobilu.getText().trim();

                    /*
                        podaci su u rezultatPretrage (tipa Automobil) i izgleda ovako:

                        Automobil{id=6, model='G63', proizvodjac='Mercedes', godinaProizvodnje=2021, registarskiBroj='VA-147-BG', brojVozila=630, vrstaVozila=PUTNICKI_AUTOMOBIL, obrisan=true, statusAutomobila=SLOBODAN, petFriendly=true}

                        Validacija je tacna znaci da klikom na potvrdi prikazuje novi prozor i u tom prozoru se prikazuju podaci
                    */


                    Automobil rezultatPretrage = ucitavanje.nadjiAutomobilPoModeluAutomobila(unosModela);
                    automobils.add(rezultatPretrage);
                    Object[][] sadrzaj = new Object[automobils.size()][zaglavlje.length];


                    if(rezultatPretrage != null){
                        for(int i = 0; i < automobils.size(); i++){
                            Automobil automobil = automobils.get(i);
                            sadrzaj[i][0] = automobil.getId();
                            sadrzaj[i][1] = automobil.getModel();
                            sadrzaj[i][2] = automobil.getProizvodjac();
                            sadrzaj[i][3] = automobil.getGodinaProizvodnje();
                            sadrzaj[i][4] = automobil.getRegistarskiBroj();
                            sadrzaj[i][5] = automobil.getBrojVozila();
                            sadrzaj[i][6] = automobil.getVrstaVozila().toString().toLowerCase().replace("_"," ");
                            sadrzaj[i][7] = automobil.getStatusAutomobila().toString().toLowerCase();
                            if(automobil.isPetFriendly()){
                                sadrzaj[i][8] = "da";
                            }else{
                                sadrzaj[i][8] = "ne";
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Model automobila ne postoji!","Greska",JOptionPane.INFORMATION_MESSAGE);
                    }

                    table_model = new DefaultTableModel(sadrzaj, zaglavlje);
                    automobiliTabela = new JTable(table_model);

                    automobiliTabela.setRowSelectionAllowed(true);
                    automobiliTabela.setColumnSelectionAllowed(false);
                    automobiliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    automobiliTabela.setDefaultEditor(Object.class, null);
                    automobiliTabela.getTableHeader().setReorderingAllowed(false);

                    JScrollPane jsp = new JScrollPane(automobiliTabela);
                    add(jsp, BorderLayout.CENTER);

                }

            }

        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoAutomobilu.this.setVisible(false);
                PoAutomobilu.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        if(tpretragaPoAutomobilu.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Greska",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
