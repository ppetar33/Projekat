package ucitavanje;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import osobe.Dispecar;
import osobe.Musterija;
import osobe.Odeljenje;
import osobe.Pol;
import osobe.Vozac;
import taksisluzba.TaksiSluzba;

public class Ucitavanje_i_Snimanje {

	private ArrayList<Musterija> musterije;
	private ArrayList<Dispecar> dispecari;
	private ArrayList<Vozac> vozaci;
	private ArrayList<TaksiSluzba> taksiSluzbe;

	public Ucitavanje_i_Snimanje() {
		this.musterije = new ArrayList<Musterija>();
		this.dispecari = new ArrayList<Dispecar>();
		this.vozaci = new ArrayList<Vozac>();
		this.taksiSluzbe = new ArrayList<TaksiSluzba>();
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

	public void ucitajKorisnike(String imeFajla) {
		try {
			File korisniciFajl = new File("../src/fajlovi/" + imeFajla);
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
					Musterija musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
					musterije.add(musterija);
					// System.out.println(musterija); formatirani ispis pomocu toString
				} else if (tipKorisnika.equals("DISPECAR")) {
					String plataString = split[8];
					double plataDispecara = Double.parseDouble(plataString);
					String brojPozivnogTelefona = split[9];
					Odeljenje odeljenje = Odeljenje.valueOf(split[10]);
					Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataDispecara, brojPozivnogTelefona, odeljenje);
					dispecari.add(dispecar);
					// System.out.println(dispecar); formatirani ispis pomocu toString
				} else if (tipKorisnika.equals("VOZAC")) {
					String plataString = split[8];
					double plataVozaca = Double.parseDouble(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String automobil = split[10];
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataVozaca, brojKartice, automobil);
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
			File korisniciFajl = new File("../src/fajlovi/korisnici.txt");
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
						vozac.getAutomobil() + "," + "VOZAC" + "," + "true" + "\n";
			}
			for (Musterija musterija : musterije) {
				content += musterija.getKorisnickoIme() + "," +
						musterija.getLozinka() + "," +
						musterija.getIme() + "," +
						musterija.getPrezime() + "," +
						musterija.getJmbg() + "," +
						musterija.getAdresa() + "," +
						musterija.getPol() + "," +
						musterija.getBrojTelefona() + "," + "," + "," + "," + "MUSTERIJA" + "," + "true" + "\n";
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
						dispecar.getOdeljenje() + "," + "DISPECAR" + "," + "true" + "\n";
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
			BufferedReader br = new BufferedReader(new FileReader(new File("../src/fajlovi/" + imeFajla)));
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
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("../src/fajlovi/" + imeFajla)));
			for (TaksiSluzba taksiSluzba : taksiSluzbe) {
				br.write(taksiSluzba.pripremiZaSnimanje());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}


	}
}