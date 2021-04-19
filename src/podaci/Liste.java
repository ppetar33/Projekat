package podaci;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import automobili.Automobil;
import enumi.StatusVoznje;
import automobili.Voznja;
import enumi.VrstaVozila;
import enumi.Obrisan;
import osobe.Dispecar;
import osobe.Musterija;
import osobe.Odeljenje;
import enumi.Pol;
import osobe.Vozac;
import taksisluzba.TaksiSluzba;

public class Liste {

	private ArrayList<Musterija> musterije;
	private ArrayList<Dispecar> dispecari;
	private ArrayList<Vozac> vozaci;
	private ArrayList<TaksiSluzba> taksiSluzbe;
	private ArrayList<Automobil> automobil;
	private ArrayList<Voznja> voznja;

	public Liste() {
		this.musterije = new ArrayList<Musterija>();
		this.dispecari = new ArrayList<Dispecar>();
		this.vozaci = new ArrayList<Vozac>();
		this.taksiSluzbe = new ArrayList<TaksiSluzba>();
		this.automobil = new ArrayList<Automobil>();
		this.voznja = new ArrayList<Voznja>();
	}

	public ArrayList<Musterija> getMusterije() {
		return musterije;
	}

	public void dodajMusteriju(Musterija musterija) {
		this.musterije.add(musterija);
	}

	public void obirisiMusteriju(Musterija musterija) {
		this.musterije.remove(musterija);
	}

	public ArrayList<Dispecar> getDispecari() {
		return dispecari;
	}

	public void dodajDispecera(Dispecar dispecar) {
		this.dispecari.remove(dispecar);
	}

	public void obrisiDispecera(Dispecar dispecar) {
		this.dispecari.remove(dispecar);
	}

	public ArrayList<Vozac> getVozaci() {
		return vozaci;
	}

	public void dodajVozaca(Vozac vozac) {
		this.vozaci.add(vozac);
	}

	public void obrisiVozaca(Vozac vozac) {
		this.vozaci.remove(vozac);
	}

	public ArrayList<TaksiSluzba> getTaksiSluzbe() {
		return taksiSluzbe;
	}

	public ArrayList<Automobil> getAutomobil(){
		return automobil;
	}

	public ArrayList<Voznja> getVoznja(){
		return voznja;
	}

	public void ucitajKorisnike(String imeFajla) {
		try {
			File korisniciFajl = new File("src/fajlovi/" + imeFajla);
			BufferedReader br = new BufferedReader(new FileReader(korisniciFajl));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				String korisnickoIme = split[0];
				String lozinka = split[1];
				String ime = split[2];
				String prezime = split[3];
				String jmbg = split[4];
				String adresa = split[5];
				Pol pol = Pol.valueOf(split[6].toUpperCase());
				String brojTelefona = split[7];
				String tipKorisnika = split[11];
				if (tipKorisnika.equals("MUSTERIJA")) {
					Obrisan obrisan = Obrisan.valueOf(split[12]);
					Musterija musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
					musterije.add(musterija);
					// System.out.println(musterija); formatirani ispis pomocu toString
				} else if (tipKorisnika.equals("DISPECAR")) {
					String plataString = split[8];
					double plataDispecara = Double.parseDouble(plataString);
					String brojPozivnogTelefona = split[9];
					Odeljenje odeljenje = Odeljenje.valueOf(split[10]);
					Obrisan obrisan = Obrisan.valueOf(split[12]);
					Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataDispecara, brojPozivnogTelefona, odeljenje, obrisan);
					dispecari.add(dispecar);
					// System.out.println(dispecar); formatirani ispis pomocu toString
				} else if (tipKorisnika.equals("VOZAC")) {
					String plataString = split[8];
					double plataVozaca = Double.parseDouble(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String automobil = split[10];
					Obrisan obrisan = Obrisan.valueOf(split[12]);
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataVozaca, brojKartice, automobil, obrisan);
					vozaci.add(vozac);
					// System.out.println(vozac); formatirani ispis pomocu toString
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Musterija loginMusterija(String korisnickoIme, String lozinka) {
		for (Musterija musterija : musterije) {
			if (musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& musterija.getLozinka().equals(lozinka)) {
				return musterija;
			}
		}
		return null;
	}

	public Dispecar loginDispecar(String korisnickoIme, String lozinka) {
		for (Dispecar dispecar : dispecari) {
			if (dispecar.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& dispecar.getLozinka().equals(lozinka)) {
				return dispecar;
			}
		}
		return null;
	}

	public Vozac loginVozac(String korisnickoIme, String lozinka) {
		for (Vozac vozac : vozaci) {
			if (vozac.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& vozac.getLozinka().equals(lozinka)) {
				return vozac;
			}
		}
		return null;
	}

	public void dodavanjeKorisnika() {
		try {
			File korisniciFajl = new File("src/fajlovi/korisnici.txt");
			String content = "";
			/*
			 *
			 * for (korisnik k : korisnici)
			 * {
			 *
			 *
			 *
			 * 	if (k vozac (instaceof))
			 * {
			 *  castuj vozaca
			 *   snimi vozac.pripremiZaUpis();
			 * }
			 * else if (musterija(
			 * {
			 * *  castuj musteriju
			 *   snimi musterija.pripremiZaUpis();
			 * }
			 * else
			 * {
			 * *  castuj dispecer
			 *   snimi dispecer.pripremiZaUpis();
			 *
			 * */

			for (Vozac vozac : vozaci) {
				content += vozac.getKorisnickoIme() + "," +
						vozac.getLozinka() + "," +
						vozac.getIme() + "," +
						vozac.getPrezime() + "," +
						vozac.getJmbg() + "," +
						vozac.getAdresa() + "," +
						vozac.getPol() + "," +
						vozac.getBrojTelefona() + "," +
						vozac.getPlata() + "," +
						vozac.getBrojClanskeKarte() + "," +
						vozac.getAutomobil() + "," + "VOZAC" + "," + vozac.getObrisan() + "\n";
			}
			for (Musterija musterija : musterije) {
				content += musterija.getKorisnickoIme() + "," +
						musterija.getLozinka() + "," +
						musterija.getIme() + "," +
						musterija.getPrezime() + "," +
						musterija.getJmbg() + "," +
						musterija.getAdresa() + "," +
						musterija.getPol() + "," +
						musterija.getBrojTelefona() + "," + "," + "," + "," + "MUSTERIJA" + "," + musterija.getObrisan() + "\n";
			}
			for (Dispecar dispecar : dispecari) {
				content += dispecar.getKorisnickoIme() + "," +
						dispecar.getLozinka() + "," +
						dispecar.getIme() + "," +
						dispecar.getPrezime() + "," +
						dispecar.getJmbg() + "," +
						dispecar.getAdresa() + "," +
						dispecar.getPol() + "," +
						dispecar.getBrojTelefona() + "," +
						dispecar.getPlata() + "," +
						dispecar.getBrojTelefonskeLinije() + "," +
						dispecar.getOdeljenje() + "," + "DISPECAR" + "," + dispecar.getObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(korisniciFajl));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ucitajTaksiSluzbe(String imeFajla) {
		this.taksiSluzbe = new ArrayList<TaksiSluzba>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while ((line = br.readLine()) != null) {
				String[] podaci = line.trim().split(",");
				// System.out.println(Arrays.toString(podaci));
				int id = Integer.parseInt(podaci[0]);
				String pib = podaci[1];
				String naziv = podaci[2];
				String adresa = podaci[3];
				double cenaStartaVoznje = Double.parseDouble(podaci[4]);
				double cenaPoKilometru = Double.parseDouble(podaci[5]);
				TaksiSluzba ts = new TaksiSluzba(id, pib, naziv, adresa, cenaStartaVoznje, cenaPoKilometru);
				taksiSluzbe.add(ts);
				System.out.println(ts);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}

	}

	public void snimiTaksiSluzbe(String imeFajla)
	{

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (TaksiSluzba taksiSluzba : taksiSluzbe) {
				br.write(taksiSluzba.pripremiZaSnimanjeTaksiSluzbu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}

	public void ucitajAutomobila(String imeFajla){
		this.automobil = new ArrayList<Automobil>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while((line = br.readLine()) != null){
				String [] podaci = line.trim().split(",");
				int id = Integer.parseInt(podaci[0]);
				String model = podaci[1];
				String proizvodajc = podaci[2];
				int godinaProizvodnje = Integer.parseInt(podaci[3]);
				String registarskiBroj = podaci[4];
				int brojVozila = Integer.parseInt(podaci[5]);
				Obrisan obrisan = Obrisan.valueOf(podaci[6].toUpperCase());
				VrstaVozila vrstaVozila = VrstaVozila.valueOf(podaci[7].toUpperCase());
				Automobil aut = new Automobil(id,model,proizvodajc,godinaProizvodnje,registarskiBroj,brojVozila,obrisan,vrstaVozila);
				automobil.add(aut);
				System.out.println(aut);
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}

	public void snimanjeAutomobila(String imeFajla){
		try{
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (Automobil automobil : automobil){
				br.write(automobil.pripremiZaSnimanjeAutomobil());
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}

	public void ucitavanjeVoznji(String imeFajla){
		try {
			this.voznja = new ArrayList<Voznja>();
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while ((line = br.readLine()) != null){
				String[] podaci = line.trim().split(",");
				int id = Integer.parseInt(podaci[0]);
				String datumIvremePorudzbine = podaci[1]; //todo: KOJA VRSTA PODATAKA JE OVDE
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime dateTime = LocalDateTime.parse(datumIvremePorudzbine, formatter);
				String adresaPolaska = podaci[2];
				String adresaDestinacije = podaci[3];
//				musterija podaci[5]
//				vozac podaci[5]
				double brojKMpredjenih = Double.parseDouble(podaci[6]);
				double trajanjVoznje = Double.parseDouble(podaci[7]);
				StatusVoznje statusVoznje = StatusVoznje.valueOf(podaci[8].toUpperCase());
//				Voznja voz = new Voznja(id,dateTime,adresaPolaska,adresaDestinacije,musterija,vozac,brojKMpredjenih,trajanjVoznje,statusVoznje);
//				voznja.add(voz);
				System.out.println(automobil);
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}

	public void snimanjeVoznji(String imeFajla){
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (Voznja voznja: voznja){
				br.write(voznja.pripremiZaSnimanjeVoznju());
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}


}