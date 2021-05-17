package liste;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import automobili.Automobil;
import enumi.*;
import automobili.Voznja;
import musterija.NarucivanjeVoznjePrekoAplikacije;
import musterija.NarucivanjeVoznjePrekoTelefona;
import musterija.OstaleVoznje;
import osobe.Dispecar;
import osobe.Musterija;
import enumi.Odeljenje;
import osobe.Vozac;
import taksiSluzba.TaksiSluzba;

public class Liste {

	private ArrayList<Musterija> musterije;
	private ArrayList<Dispecar> dispecari;
	private ArrayList<Vozac> vozaci;
	private ArrayList<TaksiSluzba> taksiSluzbe;
	private ArrayList<Automobil> automobili;
	private ArrayList<OstaleVoznje> voznja;
	private ArrayList<NarucivanjeVoznjePrekoTelefona> voznjaTelefoni;
	private ArrayList<NarucivanjeVoznjePrekoAplikacije> voznjaAplikacije;

	public Liste() {
		this.musterije = new ArrayList<Musterija>();
		this.dispecari = new ArrayList<Dispecar>();
		this.vozaci = new ArrayList<Vozac>();
		this.taksiSluzbe = new ArrayList<TaksiSluzba>();
		this.automobili = new ArrayList<Automobil>();
		this.voznja = new ArrayList<OstaleVoznje>();
		this.voznjaTelefoni = new ArrayList<NarucivanjeVoznjePrekoTelefona>();
		this.voznjaAplikacije = new ArrayList<NarucivanjeVoznjePrekoAplikacije>();
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

	public ArrayList<OstaleVoznje> getVoznja() {
		return voznja;
	}

	public void setVoznja(ArrayList<OstaleVoznje> voznja) {
		this.voznja = voznja;
	}

	public ArrayList<NarucivanjeVoznjePrekoTelefona> getVoznjaTelefoni() {
		return voznjaTelefoni;
	}

	public void setVoznjaTelefoni(ArrayList<NarucivanjeVoznjePrekoTelefona> voznjaTelefoni) {
		this.voznjaTelefoni = voznjaTelefoni;
	}

	public ArrayList<NarucivanjeVoznjePrekoAplikacije> getVoznjaAplikacije() {
		return voznjaAplikacije;
	}

	public void setVoznjaAplikacije(ArrayList<NarucivanjeVoznjePrekoAplikacije> voznjaAplikacije) {
		this.voznjaAplikacije = voznjaAplikacije;
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
				String obrisanString = split[12];
				boolean obrisan = Boolean.parseBoolean(obrisanString);
				if (tipKorisnika.equals("MUSTERIJA")) {
					Musterija musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
					musterije.add(musterija);
				} else if (tipKorisnika.equals("DISPECAR")) {
					String plataString = split[8];
					double plataDispecara = Double.parseDouble(plataString);
					String brojPozivnogTelefona = split[9];
					Odeljenje odeljenje = Odeljenje.valueOf(split[10]);
					Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan, plataDispecara, brojPozivnogTelefona, odeljenje);
					dispecari.add(dispecar);
				} else if (tipKorisnika.equals("VOZAC")) {
					String plataString = split[8];
					double plata = Double.parseDouble(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String ocenaString = split[13];
					double ocena = Double.parseDouble(ocenaString);
					String idAutomobilaString = split[10];
					int idAutomobila = Integer.parseInt(idAutomobilaString);
					Automobil automobil = new Automobil();
					for(Automobil automobil1 : automobili){
						if(idAutomobila == automobil1.getId()){
							automobil = automobil1;
						}
					}
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan, plata, brojKartice, automobil, ocena, StatusVozaca.SLOBODAN);
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
				String obrisanString = podaci[7];
				boolean obrisan = Boolean.parseBoolean(obrisanString);
				StatusAutomobila statusAutomobila = StatusAutomobila.valueOf(podaci[8].toUpperCase());
				String petFriendlyString = podaci[9];
				boolean petFriendly = Boolean.parseBoolean(petFriendlyString);
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
				StatusVoznje statusVoznje = StatusVoznje.valueOf(podaci[8]);
				String napomena = podaci[9];
				String obrisanString = podaci[10];
				boolean obrisan = Boolean.parseBoolean(obrisanString);
				StatusNaruceneVoznje cimeJeNarucenaVoznja = StatusNaruceneVoznje.valueOf(podaci[11]);
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
				if(cimeJeNarucenaVoznja.equals(StatusNaruceneVoznje.TELEFON)){
					NarucivanjeVoznjePrekoTelefona narucivanjeVoznjePrekoTelefona = new NarucivanjeVoznjePrekoTelefona(id,dateTime,adresaPolaska,adresaDestinacije,musterija,vozac,brojKMpredjenih,trajanjVoznje,statusVoznje,napomena,obrisan,cimeJeNarucenaVoznja);
					voznjaTelefoni.add(narucivanjeVoznjePrekoTelefona);

				}else if(cimeJeNarucenaVoznja.equals(StatusNaruceneVoznje.APLIKACIJA)){
					NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije = new NarucivanjeVoznjePrekoAplikacije(id,dateTime,adresaPolaska,adresaDestinacije,musterija,vozac,brojKMpredjenih,trajanjVoznje,statusVoznje,napomena,obrisan,cimeJeNarucenaVoznja);
					voznjaAplikacije.add(narucivanjeVoznjePrekoAplikacije);
				}else{
					OstaleVoznje ostaleVoznje = new OstaleVoznje(id,dateTime,adresaPolaska,adresaDestinacije,musterija,vozac,brojKMpredjenih,trajanjVoznje,statusVoznje,napomena,obrisan,cimeJeNarucenaVoznja);
					voznja.add(ostaleVoznje);
				}

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
						vozac.getAutomobili().getId() + "," + "VOZAC" + "," +
						vozac.isObrisan() + "," +
						vozac.getOcena() +  "," +
						vozac.getStatusVozaca() + "\n";
			}
			for (Musterija musterija : musterije) {
				content += musterija.getKorisnickoIme() + "," +
						musterija.getLozinka() + "," +
						musterija.getIme() + "," +
						musterija.getPrezime() + "," +
						musterija.getJmbg() + "," +
						musterija.getAdresa() + "," +
						musterija.getPol() + "," +
						musterija.getBrojTelefona() + "," + "," + "," + "," + "MUSTERIJA" + "," + musterija.isObrisan() + "\n";
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
						dispecar.getOdeljenje() + "," + "DISPECAR" + "," + dispecar.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(korisniciFajl));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom dodavanja korisnika");
		}
	}

	public void snimiTaksiSluzbe(String imeFajla) {

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
			for(OstaleVoznje voznja : voznja){
				br.write(voznja.pripremiZaSnimanje());
			}
			for(NarucivanjeVoznjePrekoTelefona voznjaTelefoni : voznjaTelefoni){
				br.write(voznjaTelefoni.pripremiZaSnimanje());
			}
			for(NarucivanjeVoznjePrekoAplikacije voznjaAplikacija : voznjaAplikacije){
				br.write(voznjaAplikacija.pripremiZaSnimanjePrekoAplikacije());
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}


	/*
			PRETRAGA


		BINARNA PRETRAGA

			Prvo je potrebno da imamo sortiranu kolekciju, jer kada uzmemo element u sredini znamo da su levo
			elementi koji su manji ili jednaki, a desno elementi koji su veci ili jednaki, ako je element koji trazim
			jednak sredisnjem ja sam zavrsio pretragu, ako nije onda gledam da li je veci ili manji (manji levo, veci desno).
			Znam na kojoj je strani i drugu stranu odbacujem. Opet dodjem na sredinu leve ili desne strane u zavisnosti
			koja mi treba, u sustini ponavljam postupak sve dok ne dobijem da mi je low=mid=high.

		hash mapa

	 */
//	public Automobil nadjiAutomobilPoId(int[] array,int target){
//
//		return binarySearch(array,0,array.length -1);
//	}
//
//	public int binarySearch(int[] array, int target, int low, int high){
//
//		for(Automobil automobil : automobili){
//			if(low > high){
//				return -1;
//			}
//			int mid = (low+high)/2;
//
//			if(array[mid] == target){
//				return mid;
//			}
//			else if(array[mid] > target){
//				return binarySearch(array,target,low,mid-1);
//			}else{
//				return binarySearch(array,target,mid+1,high);
//			}
//		}
//		return -1;
//	}


	public Vozac nadjiVozaca(String korisnickoIme){
		for(Vozac vozac : vozaci){
			if(vozac.getKorisnickoIme().equals(korisnickoIme) && vozac.isObrisan()){
				return vozac;
			}
		}
		return null;
	}
	public Vozac nadjiVozacaZaMenjanjeStatusa(String korisnickoIme){
		for(Vozac vozac : vozaci){
			if(vozac.getKorisnickoIme().equals(korisnickoIme) && vozac.isObrisan() && vozac.getStatusVozaca().equals(StatusVozaca.ZAUZET)){
				return vozac;
			}
		}
		return null;
	}
	public Vozac nadjiVozacaKojiNemaAutomobil(){
		for(Vozac vozac : vozaci){
			if(vozac.getAutomobili().getId() == 0 && vozac.isObrisan()){
				return vozac;
			}
		}
		return null;
	}
	public ArrayList<Vozac> nadjiVozacaPoPlati(double plataUnos){
		ArrayList<Vozac> sviVozaci = new ArrayList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getPlata() == plataUnos && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public ArrayList<Vozac> nadjiVozacaPoImenu(String unosIme){
		ArrayList<Vozac> sviVozaci = new ArrayList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getIme().equalsIgnoreCase(unosIme) && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public ArrayList<Vozac> nadjiVozacaPoPrezimenu(String unosPrezime){
		ArrayList<Vozac> sviVozaci = new ArrayList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getPrezime().equalsIgnoreCase(unosPrezime) && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public ArrayList<Vozac> nadjiVozacaPoAutomobilu(String model){
		ArrayList<Vozac> sviVozaci = new ArrayList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getAutomobili().getModel().equalsIgnoreCase(model) && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public Vozac nadjiVozacaKojiJeSlobodan(){
		for(Vozac vozac : vozaci){
			if(vozac.isObrisan() && vozac.getStatusVozaca().equals(StatusVozaca.SLOBODAN)){
				return vozac;
			}
		}
		return null;
	}

	public Musterija nadjiMusteriju(String korisnickoIme){
		for(Musterija musterija : musterije){
			if(musterija.getKorisnickoIme().equals(korisnickoIme) && musterija.isObrisan()){
				return musterija;
			}
		}
		return null;
	}

	public Dispecar nadjiDispecera(String korisnickoIme){
		for(Dispecar dispecar : dispecari){
			if(dispecar.getKorisnickoIme().equals(korisnickoIme) && dispecar.isObrisan()){
				return dispecar;
			}
		}
		return null;
	}

	public Automobil nadjiAutomobil(int id){
		for(Automobil automobil : automobili){
			if(automobil.getId() == id && automobil.isObrisan()){
				return automobil;
			}
		}
		return null;
	}
	public Automobil nadjiAutomobilPoStatusuAutomobila(){
		for(Automobil automobil : automobili){
			if(automobil.getStatusAutomobila() == StatusAutomobila.SLOBODAN && automobil.isObrisan()){
				return automobil;
			}
		}
		return null;
	}
	public Automobil nadjiAutomobilPoModeluAutomobila(String modelAutomobila){
		for(Automobil automobil : automobili){
			if(automobil.getModel().equalsIgnoreCase(modelAutomobila) && automobil.isObrisan()){
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

	public NarucivanjeVoznjePrekoAplikacije nadjiVoznju(){
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			if(voznja.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA) && voznja.isObrisan()){
				return voznja;
			}
		}
		return null;
	}

	public NarucivanjeVoznjePrekoTelefona nadjiVoznjuNarucenuPrekoTelefonaPoId(int id){
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			if(voznja.getId() == id){
				return voznja;
			}
		}
		return null;
	}

	/*

		IZVESTAJI

	*/
	public int uporediDatum(String datum){
		int counter = 0;
		for(Voznja voznja : voznja){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				counter++;
			}
		}
		return counter;
	}
	public int uporediDatumIvoznjeAplikacijom(String datum){
		int counter = 0;
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && voznja.getStatusNaruceneVoznje() == StatusNaruceneVoznje.APLIKACIJA){
				counter++;
			}
		}
		return counter;
	}
	public int uporediDatumIvoznjeTelefonom(String datum){
		int counter = 0;
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && voznja.getStatusNaruceneVoznje() == StatusNaruceneVoznje.TELEFON){
				counter++;
			}
		}
		return counter;
	}
	public int brojAktivnihVozaca(){
		int counter = 0;
		for(Vozac vozac : vozaci){
			if(vozac.isObrisan()){
				counter++;
			}
		}
		return counter;
	}
	public double uporediDatumItrajanjeVoznje(String datum){
		double rezultat = 0;
		double counter = 0;
		double average;
		for(Voznja voznja : voznja){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getTrajanjVoznje();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getTrajanjVoznje();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getTrajanjVoznje();
				counter++;
			}
		}
		average = rezultat/counter;
		return average;
	}
	public double uporediDatumIkilometrazu(String datum){
		double rezultat = 0;
		double counter = 0;
		double average;
		for(Voznja voznja : voznja){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		average = rezultat/counter;
		return average;
	}
	public double ukupnaZaradaZaSveVoznje(String datum){
		double ukupnaZarada;
		double start = 150;
		double cenaPoKilometru = 30;
		double rezultat = 0;
		for(Voznja voznja : voznja){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
			}
		}
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
			}
		}
		ukupnaZarada = (start + rezultat) * cenaPoKilometru;
		return ukupnaZarada;
	}
	public double prosecnaZaradaZaVoznje(String datum){
		double ukupnaZarada = 0;
		double average;
		double rezultat = 0;
		double counter = 0;
		for(Voznja voznja : voznja){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum)){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		for(TaksiSluzba taksiSluzba : taksiSluzbe) {
			double start = taksiSluzba.getCenaStartaVoznje();
			double cenaPoKilometru = taksiSluzba.getCenaPoKilometru();

			ukupnaZarada = (start + rezultat) * cenaPoKilometru;
		}
		average = ukupnaZarada / counter;
		return average;
	}


	/*
		GENERISI NOVI ID ZA VOZNJE
	*/

	public int generisiNoviIdZaVoznjePutemTelefona(){
		int maks = -1;
		for (NarucivanjeVoznjePrekoTelefona prekoTelefona : voznjaTelefoni) {
			if (prekoTelefona.getId() > maks) {
				maks = prekoTelefona.getId();
			}
		}
		return maks + 1;
	}
	public int generisiNoviIdZaVoznjePutemAplikacije() {
		int maks = -1;
		for (NarucivanjeVoznjePrekoAplikacije prekoAplikacije : voznjaAplikacije){
			if (prekoAplikacije.getId() > maks){
				maks = prekoAplikacije.getId();
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

	/*
		LISTE NEOBRISANIH ZA PRIKAZ
	*/

	public ArrayList<Vozac> neobrisaniVozaci(){
		ArrayList<Vozac> neobrisaniVozaci = new ArrayList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.isObrisan()){
				neobrisaniVozaci.add(vozac);
			}
		}
		return neobrisaniVozaci;
	}
	public ArrayList<Musterija> neobrisaneMusterije(){
		ArrayList<Musterija> neobrisaneMusterije = new ArrayList<Musterija>();
		for(Musterija musterija : musterije){
			if(musterija.isObrisan()){
				neobrisaneMusterije.add(musterija);
			}
		}
		return neobrisaneMusterije;
	}
	public ArrayList<Dispecar> neobrisaniDispeceri(){
		ArrayList<Dispecar> neobrisaniDispeceri = new ArrayList<Dispecar>();
		for(Dispecar dispecar : dispecari){
			if(dispecar.isObrisan()){
				neobrisaniDispeceri.add(dispecar);
			}
		}
		return neobrisaniDispeceri;
	}
	public ArrayList<Automobil> neobrisaniAutomobili(){
		ArrayList<Automobil> neobrisaniAutomobili = new ArrayList<Automobil>();
		for(Automobil automobil : automobili){
			if(automobil.isObrisan()){
				neobrisaniAutomobili.add(automobil);
			}
		}
		return neobrisaniAutomobili;
	}
	public ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznjeKreiranePutemTelefona(){
		ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznje = new ArrayList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			if(voznja.isObrisan()){
				neobrisaneVoznje.add(voznja);
			}
		}
		return neobrisaneVoznje;
	}
	public ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznjeKreiranePutemTelefona2(){
		ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznje = new ArrayList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			if(voznja.isObrisan() && voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA)){
				neobrisaneVoznje.add(voznja);
			}
		}
		return neobrisaneVoznje;
	}
	public ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznjeKreiranePutemTelefona3(){
		ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznje = new ArrayList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			Vozac ulogovanVozac = null;
			try {
				File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
				Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
				while (citanjeUlogovanogKorisnika.hasNextLine()) {
					String data = citanjeUlogovanogKorisnika.nextLine();
					ulogovanVozac = new Vozac();
					ulogovanVozac.setKorisnickoIme(data);
					if(voznja.isObrisan() && voznja.getStatusVoznje().equals(StatusVoznje.DODELJENA) && voznja.getVozac().getKorisnickoIme().equals(ulogovanVozac.getKorisnickoIme())){
						neobrisaneVoznje.add(voznja);
					}
				}
				citanjeUlogovanogKorisnika.close();
			}  catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Greska");
			}
		}
		return neobrisaneVoznje;
	}
	public ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznjeKreiranePutemTelefona4(){
		ArrayList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznje = new ArrayList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			Vozac ulogovanVozac = null;
			try {
				File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
				Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
				while (citanjeUlogovanogKorisnika.hasNextLine()) {
					String data = citanjeUlogovanogKorisnika.nextLine();
					ulogovanVozac = new Vozac();
					ulogovanVozac.setKorisnickoIme(data);
					if(voznja.isObrisan() && voznja.getStatusVoznje().equals(StatusVoznje.PRIHVACENA) && voznja.getVozac().getKorisnickoIme().equals(ulogovanVozac.getKorisnickoIme())){
						neobrisaneVoznje.add(voznja);
					}
				}
				citanjeUlogovanogKorisnika.close();
			}  catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Greska");
			}
		}
		return neobrisaneVoznje;
	}
	public ArrayList<NarucivanjeVoznjePrekoAplikacije> neobrisaneVoznjeKreiranePutemAplikacije(){
		ArrayList<NarucivanjeVoznjePrekoAplikacije> neobrisaneVoznje = new ArrayList<NarucivanjeVoznjePrekoAplikacije>();
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			if(voznja.isObrisan()){
				neobrisaneVoznje.add(voznja);
			}
		}
		return neobrisaneVoznje;
	}
	public ArrayList<OstaleVoznje> neobrisaneVoznje(){
		ArrayList<OstaleVoznje> neobrisaneVoznje = new ArrayList<OstaleVoznje>();
		for(OstaleVoznje voznja : voznja){
			if(voznja.isObrisan()){
				neobrisaneVoznje.add(voznja);
			}
		}
		return neobrisaneVoznje;
	}
}