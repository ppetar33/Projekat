package ucitavanje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import osobe.Dispecar;
import osobe.Musterija;
import osobe.Odeljenje;
import osobe.Osoba;
import osobe.Pol;
import osobe.Vozac;

public class Ucitavanje {

	private ArrayList<Musterija> musterije;
	private ArrayList<Dispecar> dispecari;
	private ArrayList<Vozac> vozaci;


	private ArrayList<Osoba> korisnici;

	public Ucitavanje() {
		this.musterije = new ArrayList<Musterija>();
		this.dispecari = new ArrayList<Dispecar>();
		this.vozaci = new ArrayList<Vozac>();
		this.korisnici = new ArrayList<Osoba>();
	}

	public void ucitajZaposlene(String imeFajla) {
		try {
			File korisniciFajl = new File("src/fajlovi/" + imeFajla);
			BufferedReader br = new BufferedReader(new FileReader(korisniciFajl));
			String line = null;
			while((line = br.readLine()) != null) {
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
				if(tipKorisnika.equals("MUSTERIJA")) {
					Musterija musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona);
//					System.out.println(musterija); formatirani ispis pomocu toString
					musterije.add(musterija);
				}else if(tipKorisnika.equals("DISPECAR")) {
					String plataString = split[8];
					double plataDispecara = Double.parseDouble(plataString);
					String brojPozivnogTelefona = split[9];
					Odeljenje odeljenje = Odeljenje.valueOf(split[10]);
					Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataDispecara, brojPozivnogTelefona, odeljenje);
//					System.out.println(dispecar); formatirani ispis pomocu toString
					dispecari.add(dispecar);
				}else if(tipKorisnika.equals("VOZAC")) {
					String plataString = split[8];
					int plataVozaca = Integer.parseInt(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String automobil = split[10];
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataVozaca, brojKartice, automobil);
//					System.out.println(vozac); formatirani ispis pomocu toString
					vozaci.add(vozac);
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


	public Osoba login(String korisnickoIme, String lozinka)
	{
		for (Osoba o : korisnici)
		{
			if (o.getKorisnickoIme().equals(korisnickoIme) && o.getLozinka().equals(lozinka)) {
				return o;
			}
		}
		return null;
	}

}
