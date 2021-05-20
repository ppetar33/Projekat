package dispecer.pretragaAutomobila;

import automobili.Automobil;
import dispecer.pretragaVozaca.PoAutomobilu;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class PoModelu extends JFrame {

    public JLabel pretragaPoModelu = new JLabel("Unesi model automobila");
    private JTextField tpretragaPoModelu = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Automobil automobil;
    private DoublyLinkedList<Automobil> automobils;
    private DefaultTableModel table_model;
    private JTable automobiliPoModeliTabela;

    public PoModelu(Liste ucitavanje, Automobil automobil){
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;
        this.automobils = new DoublyLinkedList<Automobil>();
        setTitle("Pretraga automobila po modelu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGui();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }

    private void initGui() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(pretragaPoModelu);
        add(tpretragaPoModelu);
        add(new JLabel());
        add(btnOK, "split 2");
        add(cancel);
    }


    public DoublyLinkedList<Automobil> pretragaPoModelu(DoublyLinkedList<Automobil> automobili, String model ){
        DoublyLinkedList<Automobil> pretrazeni = new DoublyLinkedList<Automobil>();
        for (Automobil a: automobili) {
            if (a.getModel().equals(model)){
                pretrazeni.add(a);
            }
        }
        return pretrazeni;
    }

    private void initListeners() {
        String[] zaglavlje = new String[]{"ID", "Model", "Proizvodjac", "Godina proizvodnje", "Broj registarske oznake", "Broj taksi vozila", "Vrsta vozila", "Status automobila", "Pet Friendly"};


        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosModela = tpretragaPoModelu.getText().trim();


                    Object [][] sadrzaj = new Object[automobils.size()][zaglavlje.length];

                    if(pretragaPoModelu != null){
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
                    automobiliPoModeliTabela = new JTable(table_model);

                    automobiliPoModeliTabela.setRowSelectionAllowed(true);
                    automobiliPoModeliTabela.setColumnSelectionAllowed(false);
                    automobiliPoModeliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    automobiliPoModeliTabela.setDefaultEditor(Object.class, null);
                    automobiliPoModeliTabela.getTableHeader().setReorderingAllowed(false);

                    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
                    automobiliPoModeliTabela.setRowSorter(sorter);

                    JScrollPane jsp = new JScrollPane(automobiliPoModeliTabela);
                    add(jsp, BorderLayout.CENTER);

                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoModelu.this.setVisible(false);
                PoModelu.this.dispose();
            }
        });
    }




    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        if(tpretragaPoModelu.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Greska",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
