package ucitavanje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import osobe.Dispecar;
import osobe.Musterija;
import osobe.Vozac;

public class Ucitavanje {

    private ArrayList<Musterija> musterije;
    private ArrayList<Dispecar> dispecari;
    private ArrayList<Vozac> vozaci;

    public Ucitavanje() {
        this.musterije = new ArrayList<Musterija>();
        this.dispecari = new ArrayList<Dispecar>();
        this.vozaci = new ArrayList<Vozac>();
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
				String jmbgString = split[4];
				double jmbg = Double.parseDouble(jmbgString);
				String adresa = split[5];
				String pol = split[6];
				String brojTelefonaString = split[7];
				int brojTelefona = Integer.parseInt(brojTelefonaString);
				String tipKorisnika = split[11];
				if(tipKorisnika.equals("MUSTERIJA")) {
					String prazanStringMusterija01 = split[8];
					String prazanStringMusterija02 = split[9];
					String prazanStringMusterija03 = split[10];
					Musterija musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, prazanStringMusterija01, prazanStringMusterija02, prazanStringMusterija03, tipKorisnika);
					musterije.add(musterija);
				}else if(tipKorisnika.equals("DISPECAR")) {
					String plataString = split[8];
					int plataDispecara = Integer.parseInt(plataString);
					String brojPozivnogTelefonaString = split[9];
					int brojPozivnogTelefona = Integer.parseInt(brojPozivnogTelefonaString);
					String deoGradaNaKojemRadi = split[10];
					Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataDispecara, brojPozivnogTelefona, deoGradaNaKojemRadi, tipKorisnika);
					dispecari.add(dispecar);
				}else if(tipKorisnika.equals("VOZAC")) {
					String plataString = split[8];
					int plataVozaca = Integer.parseInt(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String automobil = split[10];
					System.out.println(line);
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataVozaca, brojKartice, automobil, tipKorisnika);
					vozaci.add(vozac);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private String tipMusterija = "MUSTERIJA";
    private String tipDispecar = "DISPECAR";
    private String tipVozac = "VOZAC";

    public Musterija loginMusterija(String korisnickoIme, String lozinka, String tipMusterija) {
        for (Musterija musterija : musterije) {
            if (musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
                    && musterija.getLozinka().equals(lozinka) && musterija.getTipKorisnika().equals(tipMusterija)) {
                return musterija;
            }
        }
        return null;
    }

	public Dispecar loginDispecar(String korisnickoIme, String lozinka, String tipDispecar) {
		for (Dispecar dispecar : dispecari) {
			if (dispecar.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& dispecar.getLozinka().equals(lozinka) && dispecar.getTipKorisnika().equals(tipDispecar)) {
				return dispecar;
			}
		}
		return null;
	}
	public Vozac loginVozac(String korisnickoIme, String lozinka, String tipVozac) {
		for (Vozac vozac : vozaci) {
			if (vozac.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& vozac.getLozinka().equals(lozinka) && vozac.getTipKorisnika().equals(tipVozac)) {
				return vozac;
			}
		}
		return null;
	}
}
