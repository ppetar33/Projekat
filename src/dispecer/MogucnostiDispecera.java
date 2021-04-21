package dispecer;

import gui.LoginProzor;
import osobe.Dispecar;
import osobe.Vozac;
import podaci.Liste;
import automobili.Automobil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogucnostiDispecera extends JFrame {

	private JMenuBar dispecerMenu = new JMenuBar();

	private JMenu funkcionalnostiVozac = new JMenu("Vozac");
	private JMenuItem dodavanjeVozaca = new JMenuItem("Dodavanje vozaca");
	private JMenuItem prikazVozaca = new JMenuItem("Prikaz vozaca");
	private JMenuItem izmenaVozaca = new JMenuItem("Izmena vozaca");
	private JMenuItem brisanjeVozaca = new JMenuItem("Brisanje vozaca");

	private JMenu funkcionalnostiTaksiSluzba = new JMenu("Taksi Sluzba");
	private JMenuItem prikazPodataka = new JMenuItem("Prikaz podataka");
	private JMenuItem izmenaPodataka = new JMenuItem("Izmena podataka");

	private JMenu funkcionalnostiAutomobila = new JMenu("Automobil");
	private JMenuItem dodavanjeAutomobila = new JMenuItem("Dodavanje automobila");
	private JMenuItem prikazAutomobila = new JMenuItem("Prikaz automobila");
	private JMenuItem izmenaAutomobila = new JMenuItem("Izmena automobila");
	private JMenuItem brisanjeAutomobila = new JMenuItem("Brisanje automobila");

	private  JMenu funkcionalnostPrikazVoznji = new JMenu("Prikaz voznji");
	private JMenuItem putemTelefona = new JMenuItem("Putem telefona");
	private JMenuItem putemAplikacije = new JMenuItem("Putem aplikacije");

	private JMenuItem funkcionalnostPretragaVozaca = new JMenu("Pretraga vozaca");
	private JMenuItem poImenu = new JMenuItem("Po imenu");
	private JMenuItem poPrezimenu = new JMenuItem("Po prezimenu");
	private JMenuItem poPlati = new JMenuItem("Po plati");
	private JMenuItem poAutomobilu = new JMenuItem("Po automobilu");

	private JMenuItem funkcionalnostPretragaAutomobila = new JMenu("Pretraga automobila");
	private JMenuItem poModelu = new JMenuItem("Po modelu");
	private JMenuItem poProizvodjacu = new JMenuItem("Po proizvodjacu");
	private JMenuItem poGodiniProizvodnje = new JMenuItem("Po godini poizvodnje");
	private JMenuItem poBrojuRegistarskeOznake = new JMenuItem("Po broju registarske oznake");
	private JMenuItem poBrojuTaksiVozila = new JMenuItem("Po broju taksi vozila");

	private JMenu odjava = new JMenu("Odjava");
	private JMenuItem potvrdaZaOdjavu = new JMenuItem("Potvrdi");
	private JMenuItem odustaniZaOdjavu = new JMenuItem("Odustani");


	private Liste ucitavanje;
	private Dispecar prijavljeniDispecar;
	private Vozac vozac;
	private Dispecar dispecar;

	public MogucnostiDispecera(Liste ucitavanje, Dispecar prijavljeniDispecar){
		this.ucitavanje = ucitavanje;
		this.prijavljeniDispecar = prijavljeniDispecar;
		setTitle("Dispecer, ime: " + prijavljeniDispecar.getIme().substring(0, 1).toUpperCase() + prijavljeniDispecar.getIme().substring(1));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(720, 300);
		initGUI();
		initListeners();
		setLocationRelativeTo(null);
	}

	private void initGUI(){
		setJMenuBar(dispecerMenu);

		dispecerMenu.add(funkcionalnostiVozac);
		funkcionalnostiVozac.add(dodavanjeVozaca);
		funkcionalnostiVozac.add(prikazVozaca);
		funkcionalnostiVozac.add(izmenaVozaca);
		funkcionalnostiVozac.add(brisanjeVozaca);

		dispecerMenu.add(funkcionalnostiTaksiSluzba);
		funkcionalnostiTaksiSluzba.add(prikazPodataka);
		funkcionalnostiTaksiSluzba.add(izmenaPodataka);

		dispecerMenu.add(funkcionalnostiAutomobila);
		funkcionalnostiAutomobila.add(dodavanjeAutomobila);
		funkcionalnostiAutomobila.add(prikazAutomobila);
		funkcionalnostiAutomobila.add(izmenaAutomobila);
		funkcionalnostiAutomobila.add(brisanjeAutomobila);

		dispecerMenu.add(funkcionalnostPrikazVoznji);
		funkcionalnostPrikazVoznji.add(putemTelefona);
		funkcionalnostPrikazVoznji.add(putemAplikacije);

		dispecerMenu.add(funkcionalnostPretragaVozaca);
		funkcionalnostPretragaVozaca.add(poImenu);
		funkcionalnostPretragaVozaca.add(poPrezimenu);
		funkcionalnostPretragaVozaca.add(poPlati);
		funkcionalnostPretragaVozaca.add(poAutomobilu);

		dispecerMenu.add(funkcionalnostPretragaAutomobila);
		funkcionalnostPretragaAutomobila.add(poModelu);
		funkcionalnostPretragaAutomobila.add(poProizvodjacu);
		funkcionalnostPretragaAutomobila.add(poGodiniProizvodnje);
		funkcionalnostPretragaAutomobila.add(poBrojuRegistarskeOznake);
		funkcionalnostPretragaAutomobila.add(poBrojuTaksiVozila);

		dispecerMenu.add(odjava);
		odjava.add(potvrdaZaOdjavu);
		odjava.add(odustaniZaOdjavu);
	}

	private void initListeners(){
		dodavanjeVozaca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeVozaca prozorZaDodavanjeVozaca = new DodavanjeVozaca(ucitavanje, vozac);
				prozorZaDodavanjeVozaca.setVisible(true);
			}
		});
		prikazVozaca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazVozaca prozorZaPrikazVozaca = new PrikazVozaca(ucitavanje);
				prozorZaPrikazVozaca.setVisible(true);
			}
		});
		brisanjeVozaca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BrisanjeVozaca prozorZaBrisanjeVozaca = new BrisanjeVozaca(ucitavanje);
				prozorZaBrisanjeVozaca.setVisible(true);
			}
		});
		/*
			TODO
			izmenaVozaca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzmenaVozaca prozorZaIzmenuVozaca = new IzmenaVozaca(ucitavanje);
				prozorZaIzmenuVozaca.setVisible(true);
			}
		});
		*/
		dodavanjeAutomobila.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeAutomobila prozorZaDodavanjeAutomobila = new DodavanjeAutomobila(ucitavanje);
				prozorZaDodavanjeAutomobila.setVisible(true);
			}
		});
		putemTelefona.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazVoznji prozorZaPrikazVoznji = new PrikazVoznji(ucitavanje);
				prozorZaPrikazVoznji.setVisible(true);
			}
		});
//		putemAplikacije.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		poImenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PretragaVozaca pretragaVozaca = new PretragaVozaca(ucitavanje,vozac);
				pretragaVozaca.setVisible(true);
			}
		});
		potvrdaZaOdjavu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Uspesno ste se odjavili!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
				MogucnostiDispecera.this.dispose();
				MogucnostiDispecera.this.setVisible(false);
				LoginProzor loginProzor = new LoginProzor(ucitavanje);
				loginProzor.setVisible(true);
			}
		});
		odustaniZaOdjavu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Uspesno ste odustali od odjave!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
