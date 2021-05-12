package dispecer;

import dispecer.podaciAutomobila.BrisanjeAutomobila;
import dispecer.podaciAutomobila.DodavanjeAutomobila;
import dispecer.podaciAutomobila.IzmenaAutomobila;
import dispecer.podaciAutomobila.PrikazAutomobila;
import dispecer.podaciTaksiSluzbe.IzmenaPodatakaTaksiSluzbe;
import dispecer.podaciTaksiSluzbe.PrikazPodatakaTaksiSluzbe;
import dispecer.podaciVozaca.BrisanjeVozaca;
import dispecer.podaciVozaca.DodavanjeVozaca;
import dispecer.podaciVozaca.IzmenaVozaca;
import dispecer.podaciVozaca.PrikazVozaca;
import dispecer.podaciVoznjePrekoAplikacije.PrikazVoznjiPutemAplikacije;
import dispecer.podaciVoznjePrekoTelefona.PrikazVoznjiPutemTelefona;
import dispecer.pretragaVozaca.*;
import loginProzor.LoginProzor;
import osobe.Dispecar;
import osobe.Vozac;
import liste.Liste;
import taksiSluzba.TaksiSluzba;

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

	private JMenu funkcionalnostPrikazVoznji = new JMenu("Prikaz voznji");
	private JMenuItem putemTelefona = new JMenuItem("Putem telefona");
	private JMenuItem putemAplikacije = new JMenuItem("Putem aplikacije");

	private JMenu funkcionalnostPretragaVozaca = new JMenu("Pretraga vozaca");
	private JMenuItem poImenu = new JMenuItem("Po imenu");
	private JMenuItem poPrezimenu = new JMenuItem("Po prezimenu");
	private JMenuItem poPlati = new JMenuItem("Po plati");
	private JMenuItem poAutomobilu = new JMenuItem("Po automobilu");
	private JMenuItem kombinovana = new JMenuItem("Kombinovana");

	private JMenu funkcionalnostPretragaAutomobila = new JMenu("Pretraga automobila");
	private JMenuItem poModelu = new JMenuItem("Po modelu");
	private JMenuItem poProizvodjacu = new JMenuItem("Po proizvodjacu");
	private JMenuItem poGodiniProizvodnje = new JMenuItem("Po godini poizvodnje");
	private JMenuItem poBrojuRegistarskeOznake = new JMenuItem("Po broju registarske oznake");
	private JMenuItem poBrojuTaksiVozila = new JMenuItem("Po broju taksi vozila");

	private JMenu funkcionalnostIzvestaj = new JMenu("Izvestaj");
	private JMenuItem dnevni = new JMenuItem("Dnevni");
	private JMenuItem nedeljni = new JMenuItem("Nedeljeni");
	private JMenuItem mesecni = new JMenuItem("Mesecni");
	private JMenuItem godisnji = new JMenuItem("Godisnji");

	private JMenu odjava = new JMenu("Odjava");
	private JMenuItem potvrdaZaOdjavu = new JMenuItem("Potvrdi");
	private JMenuItem odustaniZaOdjavu = new JMenuItem("Odustani");

	private Liste ucitavanje;
	private Dispecar prijavljeniDispecar;
	private Vozac vozac;
	private TaksiSluzba taksiSluzba;

	public MogucnostiDispecera(Liste ucitavanje, Dispecar prijavljeniDispecar){
		this.ucitavanje = ucitavanje;
		this.prijavljeniDispecar = prijavljeniDispecar;
		setTitle("Dobrodosli " + prijavljeniDispecar.getIme().substring(0, 1).toUpperCase() + prijavljeniDispecar.getIme().substring(1) + " (Dispecer)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(790, 300);
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
		funkcionalnostPretragaVozaca.add(kombinovana);

		dispecerMenu.add(funkcionalnostPretragaAutomobila);
		funkcionalnostPretragaAutomobila.add(poModelu);
		funkcionalnostPretragaAutomobila.add(poProizvodjacu);
		funkcionalnostPretragaAutomobila.add(poGodiniProizvodnje);
		funkcionalnostPretragaAutomobila.add(poBrojuRegistarskeOznake);
		funkcionalnostPretragaAutomobila.add(poBrojuTaksiVozila);

		dispecerMenu.add(funkcionalnostIzvestaj);
		funkcionalnostIzvestaj.add(dnevni);
		funkcionalnostIzvestaj.add(nedeljni);
		funkcionalnostIzvestaj.add(mesecni);
		funkcionalnostIzvestaj.add(godisnji);

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
				PrikazVozaca prozorZaPrikazVozaca = new PrikazVozaca(ucitavanje,vozac);
				prozorZaPrikazVozaca.setVisible(true);
			}
		});
		brisanjeVozaca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BrisanjeVozaca prozorZaBrisanjeVozaca = new BrisanjeVozaca(ucitavanje,vozac);
				prozorZaBrisanjeVozaca.setVisible(true);
			}
		});
		izmenaVozaca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzmenaVozaca prozorZaIzmenuVozaca = new IzmenaVozaca(ucitavanje,vozac);
				prozorZaIzmenuVozaca.setVisible(true);
			}
		});
		// TAKSI SLUZBA
		prikazPodataka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPodatakaTaksiSluzbe prozorZaPrikazPodatakaTaksiSluzbe = new PrikazPodatakaTaksiSluzbe(ucitavanje,taksiSluzba);
				prozorZaPrikazPodatakaTaksiSluzbe.setVisible(true);
			}
		});
		izmenaPodataka.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzmenaPodatakaTaksiSluzbe izmenaPodatakaTaksiSluzbe = new IzmenaPodatakaTaksiSluzbe(ucitavanje, taksiSluzba);
				izmenaPodatakaTaksiSluzbe.setVisible(true);
			}
		});
		dodavanjeAutomobila.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeAutomobila prozorZaDodavanjeAutomobila = new DodavanjeAutomobila(ucitavanje);
				prozorZaDodavanjeAutomobila.setVisible(true);
			}
		});
		// PRIKAZ AUTOMOBILA
		prikazAutomobila.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazAutomobila prozorZaPrikazAutomobila = new PrikazAutomobila(ucitavanje);
				prozorZaPrikazAutomobila.setVisible(true);
			}
		});
		//BRISANJE AUTOMOBILA
		brisanjeAutomobila.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BrisanjeAutomobila prozorZaBrisanjeAutomobila = new BrisanjeAutomobila(ucitavanje);
				prozorZaBrisanjeAutomobila.setVisible(true);
			}
		});
		//IZMENA AUTOMOBILA
		izmenaAutomobila.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzmenaAutomobila prozorZaIzmenuAutomobila = new IzmenaAutomobila(ucitavanje);
				prozorZaIzmenuAutomobila.setVisible(true);
			}
		});

		// PRIKAZ VOZNJI
		putemTelefona.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazVoznjiPutemTelefona prozorZaPrikazVoznjiPutemTelefona = new PrikazVoznjiPutemTelefona(ucitavanje);
				prozorZaPrikazVoznjiPutemTelefona.setVisible(true);
			}
		});
		putemAplikacije.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazVoznjiPutemAplikacije prozorZaPrikazVoznjiPutemAplikacije = new PrikazVoznjiPutemAplikacije(ucitavanje);
				prozorZaPrikazVoznjiPutemAplikacije.setVisible(true);
			}
		});
		// PRETRAGA VOZACA
		poImenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PoImenu poImenu = new PoImenu(ucitavanje,vozac);
				poImenu.setVisible(true);
			}
		});
		poPrezimenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PoPrezimenu poPrezimenu = new PoPrezimenu(ucitavanje,vozac);
				poPrezimenu.setVisible(true);
			}
		});
		poAutomobilu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PoAutomobilu poAutomobilu = new PoAutomobilu(ucitavanje,vozac);
				poAutomobilu.setVisible(true);
			}
		});
		poPlati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PoPlati poPlati = new PoPlati(ucitavanje,vozac);
				poPlati.setVisible(true);
			}
		});
		kombinovana.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Kombinovana kombinovana = new Kombinovana(ucitavanje,vozac);
				kombinovana.setVisible(true);
			}
		});
		// PRETRAGA AUTOMOBILA TODO 



		// IZVESTAJ

		dnevni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajiDispecera izvestajiDispecera = new IzvestajiDispecera();
				izvestajiDispecera.dnevniIzvestaj(ucitavanje);
			}
		});

		//ODJAVLJIVANJE
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
