package liste;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import automobili.Automobil;
import enumi.*;
import automobili.Voznja;
import osobe.Dispecar;
import osobe.Musterija;
import osobe.Odeljenje;
import osobe.Vozac;
import taksiSluzba.TaksiSluzba;

public class Liste {

	private ArrayList<Musterija> musterije;
	private ArrayList<Dispecar> dispecari;
	private ArrayList<Vozac> vozaci;
	private ArrayList<TaksiSluzba> taksiSluzbe;
	private ArrayList<Automobil> automobili;
	private ArrayList<Voznja> voznja;

	public Liste() {
		this.musterije = new ArrayList<Musterija>();
		this.dispecari = new ArrayList<Dispecar>();
		this.vozaci = new ArrayList<Vozac>();
		this.taksiSluzbe = new ArrayList<TaksiSluzba>();
		this.automobili = new ArrayList<Automobil>();
		this.voznja = new ArrayList<Voznja>();
	}

	public ArrayList<Musterija> getMusterije() {
		return musterije;
	}

	public void dodajMusteriju(Musterija musterija) {
		this.musterije.add(musterija);
	}


	public ArrayList<Dispecar> getDispecari() {
		return dispecari;
	}

	public void dodajDispecera(Dispecar dispecar) {
		this.dispecari.add(dispecar);
	}


	public ArrayList<Vozac> getVozaci() {
		return vozaci;
	}

	public void setVozaci(ArrayList<Vozac> vozaci) {
		this.vozaci = vozaci;
	}

	public ArrayList<TaksiSluzba> getTaksiSluzbe() {
		return taksiSluzbe;
	}

	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(ArrayList<Automobil> automobili) {
		this.automobili = automobili;
	}

	public ArrayList<Voznja> getVoznja(){
		return voznja;
	}

	public void setVoznja(ArrayList<Voznja> voznja) {
		this.voznja = voznja;
	}


	/*
			UCITAVANJE
	 */

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
				} else if (tipKorisnika.equals("DISPECAR")) {
					String plataString = split[8];
					double plataDispecara = Double.parseDouble(plataString);
					String brojPozivnogTelefona = split[9];
					Odeljenje odeljenje = Odeljenje.valueOf(split[10]);
					Obrisan obrisan = Obrisan.valueOf(split[12]);
					Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataDispecara, brojPozivnogTelefona, odeljenje, obrisan);
					dispecari.add(dispecar);
				} else if (tipKorisnika.equals("VOZAC")) {
					String plataString = split[8];
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					Automobil automobil = new Automobil();
					Obrisan obrisan = Obrisan.valueOf(split[12]);
					String ocenaString = split[13];
					double ocena = Double.parseDouble(ocenaString);
					for(Automobil automobil1 : automobili){
						if(split[10].equalsIgnoreCase(automobil1.getModel())){
							automobil = automobil1;
						}
					}
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, plataString, brojKartice, automobil, obrisan, ocena);
					vozaci.add(vozac);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ucitajTaksiSluzbe(String imeFajla) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while ((line = br.readLine()) != null) {
				String[] podaci = line.trim().split(",");
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

	public void ucitajAutomobila(String imeFajla){
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while((line = br.readLine()) != null){
				String[] podaci = line.trim().split(",");
				int id = Integer.parseInt(podaci[0]);
				String model = podaci[1];
				String proizvodjac = podaci[2];
				int godinaProizvodnje = Integer.parseInt(podaci[3]);
				String registarskiBroj = podaci[4];
				int brojVozila = Integer.parseInt(podaci[5]);
				VrstaVozila vrstaVozila = VrstaVozila.valueOf(podaci[6].toUpperCase());
				Obrisan obrisan = Obrisan.valueOf(podaci[7].toUpperCase());
				StatusAutomobila statusAutomobila = StatusAutomobila.valueOf(podaci[8].toUpperCase());
				PetFriendly petFriendly = PetFriendly.valueOf(podaci[9].toUpperCase());
				Automobil aut = new Automobil(id,model,proizvodjac,godinaProizvodnje,registarskiBroj,brojVozila,vrstaVozila,obrisan,statusAutomobila,petFriendly);
				automobili.add(aut);
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}

	public void ucitavanjeVoznji(String imeFajla){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while ((line = br.readLine()) != null){
				String[] podaci = line.trim().split(",");
				int id = Integer.parseInt(podaci[0]);
				String datumIvremePorudzbine = podaci[1];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime dateTime = LocalDateTime.parse(datumIvremePorudzbine,formatter);
				String adresaPolaska = podaci[2];
				String adresaDestinacije = podaci[3];
				double brojKMpredjenih = Double.parseDouble(podaci[6]);
				double trajanjVoznje = Double.parseDouble(podaci[7]);
				StatusVoznje statusVoznje = StatusVoznje.valueOf(podaci[8].toUpperCase());
				Musterija musterija = new Musterija();
				Vozac vozac = new Vozac();
				for(Musterija musterija1 : musterije){
					if(podaci[4].equalsIgnoreCase(musterija1.getKorisnickoIme())){
						musterija = musterija1;
					}
				}
				for(Vozac vozac1 : vozaci){
					if(podaci[5].equalsIgnoreCase(vozac1.getKorisnickoIme())){
						vozac = vozac1;
					}
				}
				Voznja voz = new Voznja(id,dateTime,adresaPolaska,adresaDestinacije,musterija,vozac,brojKMpredjenih,trajanjVoznje,statusVoznje);
				voznja.add(voz);
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}


	/*
			DODAVANJE
	 */

	public void dodavanjeKorisnika() {
		try {
			File korisniciFajl = new File("src/fajlovi/korisnici.txt");
			String content = "";

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
						vozac.getAutomobili().getModel() + "," + "VOZAC" + "," +
						vozac.getObrisan() + "," +
						vozac.getOcena() + "\n";
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
			System.out.println("Greska prilikom dodavanja korisnika");
		}
	}

	public void snimiTaksiSluzbe(String imeFajla)
	{

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (TaksiSluzba taksiSluzba : taksiSluzbe) {
				br.write(taksiSluzba.pripremiZaSnimanjeTaksiSluzbu());
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}

	public void snimanjeAutomobila(String imeFajla){
		try{
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (Automobil automobil : automobili){
				br.write(automobil.pripremiZaSnimanjeAutomobil());
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}

	public void snimanjeVoznji(String imeFajla){
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (Voznja voznja: voznja){
				br.write(voznja.pripremiZaSnimanjeVoznju());
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}


	/*
			PRETRAGA
	*/

	/*
		BINARNA PRETRAGA

			Prvo je potrebno da imamo sortiranu kolekciju, jer kada uzmemo element u sredini znamo da su levo
			elementi koji su manji ili jednaki, a desno elementi koji su veci ili jednaki, ako je element koji trazim
			jednak sredisnjem ja sam zavrsio pretragu, ako nije onda gledam da li je veci ili manji (manji levo, veci desno).
			Znam na kojoj je strani i drugu stranu odbacujem. Opet dodjem na sredinu leve ili desne strane u zavisnosti
			koja mi treba, u sustini ponavljam postupak sve dok ne dobijem da mi je low=mid=high.

		hash mapa
	public Automobil nadjiAutomobilPoId(int[] array,int target){
		return binarySearch(array,0,array.length -1);
	}

	public Automobil binarySearch(int[] array, int target, int low, int high){

		for(Automobil automobil : automobili){
			if(low > high){
				return null;
			}
			int mid = (low+high)/2;

			if(array[mid] == target){
				return automobil;
			}
			else if(array[mid] > target){
				return binarySearch(array,target,low,mid-1);
			}else{
				return binarySearch(array,target,mid+1,high);
			}
		}
		return null;

	}
	*/

	public Vozac nadjiVozaca(String korisnickoIme){
		for(Vozac vozac : vozaci){
			if(vozac.getKorisnickoIme().equals(korisnickoIme)){
				return vozac;
			}
		}
		return null;
	}

	public Musterija nadjiMusteriju(String korisnickoIme){
		for(Musterija musterija : musterije){
			if(musterija.getKorisnickoIme().equals(korisnickoIme)){
				return musterija;
			}
		}
		return null;
	}

	public Dispecar nadjiDispecera(String korisnickoIme){
		for(Dispecar dispecar : dispecari){
			if(dispecar.getKorisnickoIme().equals(korisnickoIme)){
				return dispecar;
			}
		}
		return null;
	}

	public Automobil nadjiAutomobil(int id){
		for(Automobil automobil : automobili){
			if(automobil.getId() == id){
				return automobil;
			}
		}
		return null;
	}

	public Automobil nadjiAutomobilPoStatusuAutomobila(){
		for(Automobil automobil : automobili){
			if(automobil.getStatusAutomobila() == StatusAutomobila.SLOBODAN && automobil.getObrisan() == Obrisan.TRUE){
				return automobil;
			}
		}
		return null;
	}

	public Automobil nadjiAutomobilPoModeluAutomobila(String modelAutomobila){
		for(Automobil automobil : automobili){
			if(automobil.getModel() == modelAutomobila){
				return automobil;
			}
		}
		return null;
	}

	public TaksiSluzba nadjiTaksiSluzbu(int id){
		for(TaksiSluzba taksiSluzba : taksiSluzbe){
			if(taksiSluzba.getId() == id){
				return taksiSluzba;
			}
		}
		return null;
	}

	public Voznja nadjiVoznju(int id){
		for(Voznja voznja : voznja){
			if(voznja.getId() == id){
				return voznja;
			}
		}
		return null;
	}

	/*
		GENERISI NOVI ID ZA VOZNJE
	 */

	public int generisiNoviIdZaVoznje() {
		int maks = -1;
		for (Voznja voznja : voznja) {
			if (voznja.getId() > maks) {
				maks = voznja.getId();
			}
		}
		return maks + 1;
	}


	/*
			PROVERA ZA LOGIN
	 */

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
}