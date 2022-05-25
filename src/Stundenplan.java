import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.KeyStroke;

public class Stundenplan {

	public static void main(String[] args) {

		Scanner inInt = new Scanner(System.in);
		Scanner inString = new Scanner(System.in);

		Professor p1 = new Professor("Pado", "Ulrike", "Weiblich", 4971189262811l);

		ArrayList<Kurs> alleKurse = new ArrayList<Kurs>();
		alleKurse.add(new Kurs("Programmieren 2", "9:45 Uhr - 11:15 Uhr", 4, false, p1));
		alleKurse.add(new Kurs("Programmieren 5", "8:00 Uhr - 9:30 Uhr", "Montag", true, p1));
		alleKurse.add(new Kurs("Programmieren 1", 1, 4, false, p1));
		//alleKurse.add(new Kurs("Programmieren 2", 11, "Sonntag", true, p1));

		ArrayList<Kurs> montag = new ArrayList<Kurs>();
		ArrayList<Kurs> dienstag = new ArrayList<Kurs>();
		ArrayList<Kurs> mittwoch = new ArrayList<Kurs>();
		ArrayList<Kurs> donnerstag = new ArrayList<Kurs>();
		ArrayList<Kurs> freitag = new ArrayList<Kurs>();
		ArrayList<Kurs> samstag = new ArrayList<Kurs>();

		

		Scanner in = new Scanner(System.in);
	
		
		while (true) {
			
			kurseEinsotieren(alleKurse, montag, dienstag, mittwoch, donnerstag, freitag, samstag);
			allesotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);
			
			
			
			System.out.print(
					"Drücke \n1 um alle Kurse auszugeben \n2 um einen Kurs zu suchen \n3 um einen Kurs zu erstellen \nq zum beenden \nIhre Eingabe: ");
			String eingabeSwitch=in.nextLine();
			
			
			switch (eingabeSwitch) {
			case "1":
				System.out.println();
				ausgeben(montag);
				ausgeben(dienstag);
				ausgeben(mittwoch);
				ausgeben(donnerstag);
				ausgeben(freitag);
				ausgeben(samstag);
				break;
			case "2":
				System.out.println();
				System.out.println("Welcher Kurs soll gesucht werden? (Kursname)");
				String eingabe = in.nextLine();
				suchen(alleKurse, eingabe);
				break;
			case "3":
				System.out.println("Kursname");
				String kursName = inString.nextLine();
				System.out.println("Welche Uhrzeit");
				String uhrzeit = inString.nextLine();
				System.out.println("Welcher Wochentag");
				String wochentag = inString.nextLine();
				System.out.println("Online?");
				boolean online = inInt.nextBoolean();
				
				
				if (uhrzeit.equals("1")||uhrzeit.equals("2")||uhrzeit.equals("3")||uhrzeit.equals("4")||uhrzeit.equals("5")||uhrzeit.equals("6")) {
					int uhrzeitZahl = Integer.parseInt(uhrzeit);
					if (wochentag.equals("1")||wochentag.equals("2")||wochentag.equals("3")||wochentag.equals("4")||wochentag.equals("5")||wochentag.equals("6")) {
						int wochentagZahl = Integer.parseInt(wochentag);
						alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentagZahl, online, p1));
					} else {
						alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentag, online, p1));
					}
				} else {
					if (wochentag.equals("1")||wochentag.equals("2")||wochentag.equals("3")||wochentag.equals("4")||wochentag.equals("5")||wochentag.equals("6")) {
						int wochentagZahl = Integer.parseInt(wochentag);
						alleKurse.add(new Kurs(kursName, uhrzeit, wochentagZahl, online, p1));
					} else {
						alleKurse.add(new Kurs(kursName, uhrzeit, wochentag, online, p1));
					}
				}
				
				
				
			
				break;
				
			case "q":
				System.exit(0);
				break;
			
			
			case "4":
				dateiAusgeben(montag, dienstag, mittwoch, donnerstag, freitag, samstag);
				break;
				
			default:
				System.out.println(eingabeSwitch+" ist nicht definiert");
			}
			System.out.println();
		}

	}

	public static void allesotieren(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag, ArrayList<Kurs> mittwoch,
			ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {
		// Nach Blockeinheit sotieren
		
		//in ein set alle 6 arraylisten reinmachen und dann nur einmal sotieren
		
		Collections.sort(montag, new Comparator<Kurs>() {

			@Override
			public int compare(Kurs o1, Kurs o2) {
				return o1.getBlockeinheit().compareTo(o2.getBlockeinheit());
			}
		});

		Collections.sort(dienstag, new Comparator<Kurs>() {

			@Override
			public int compare(Kurs o1, Kurs o2) {
				return o1.getBlockeinheit().compareTo(o2.getBlockeinheit());
			}
		});

		Collections.sort(mittwoch, new Comparator<Kurs>() {

			@Override
			public int compare(Kurs o1, Kurs o2) {
				return o1.getBlockeinheit().compareTo(o2.getBlockeinheit());
			}
		});

		Collections.sort(donnerstag, new Comparator<Kurs>() {

			@Override
			public int compare(Kurs o1, Kurs o2) {
				return o1.getBlockeinheit().compareTo(o2.getBlockeinheit());
			}
		});

		Collections.sort(freitag, new Comparator<Kurs>() {

			@Override
			public int compare(Kurs o1, Kurs o2) {
				return o1.getBlockeinheit().compareTo(o2.getBlockeinheit());
			}
		});

		Collections.sort(samstag, new Comparator<Kurs>() {

			@Override
			public int compare(Kurs o1, Kurs o2) {
				return o1.getBlockeinheit().compareTo(o2.getBlockeinheit());
			}
		});

		// Nach Namen sotieren
		/*
		 * Collections.sort(montag, new Comparator<Kurs>() {
		 * 
		 * @Override public int compare(Kurs o1, Kurs o2) { return
		 * o1.getKursname().compareTo(o2.getKursname()); } });
		 * 
		 * for(Kurs kurs:montag) { System.out.println(kurs); }
		 */

	}

	public static void ausgeben(ArrayList<Kurs> k) {
		for (Kurs kurs : k) {
			System.out.println(kurs);

		}
	}

	public static void dateiAusgeben(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag, ArrayList<Kurs> mittwoch,
			ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {

		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/text.txt"), false))) {
			String online;
			boolean ausgabe = false;
			dateiSchreiber.write("-----Montag-----\n");

			for (Kurs kurs : montag) {

				

				dateiSchreiber.write(kurs.getKursname() + "\n");
				dateiSchreiber.write("Um " + kurs.getBlockUhrzeit() + "\n");

				if (kurs.isOnline()) {
					online = "Der Kurs findet online statt\n";
				} else {
					online = "Der Kurs findet in Präsenz statt\n";
				}
				dateiSchreiber.write(online);
				dateiSchreiber.write("Professor-ID: "+kurs.getId()+"\n\n");

				
				ausgabe=true;
				
				

			}
			
			if(ausgabe==false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}
			
			ausgabe=false;
			
			
			
			dateiSchreiber.write("-----Dienstag-----\n");
			for (Kurs kurs : dienstag) {

				

				dateiSchreiber.write(kurs.getKursname() + "\n");
				dateiSchreiber.write("Um " + kurs.getBlockUhrzeit() + "\n");

				if (kurs.isOnline()) {
					online = "Der Kurs findet online statt\n";
				} else {
					online = "Der Kurs findet in Präsenz statt\n";
				}
				dateiSchreiber.write(online);
				dateiSchreiber.write("Professor-ID: "+kurs.getId()+"\n\n");
				ausgabe=true;

			}
			if(ausgabe==false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}
			
			ausgabe=false;
			
			dateiSchreiber.write("-----Mittwoch-----\n");
			
			
			
			
			
			for (Kurs kurs : mittwoch) {

				

				dateiSchreiber.write(kurs.getKursname() + "\n");
				dateiSchreiber.write("Um " + kurs.getBlockUhrzeit() + "\n");

				if (kurs.isOnline()) {
					online = "Der Kurs findet online statt\n";
				} else {
					online = "Der Kurs findet in Präsenz statt\n";
				}
				dateiSchreiber.write(online);
				dateiSchreiber.write("Professor-ID: "+kurs.getId()+"\n\n");
				ausgabe=true;

			}
			
			if(ausgabe==false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}
			
			ausgabe=false;
			
			
			dateiSchreiber.write("-----Donnerstag-----\n");
			
			
			
			for (Kurs kurs : donnerstag) {

				

				dateiSchreiber.write(kurs.getKursname() + "\n");
				dateiSchreiber.write("Um " + kurs.getBlockUhrzeit() + "\n");

				if (kurs.isOnline()) {
					online = "Der Kurs findet online statt\n";
				} else {
					online = "Der Kurs findet in Präsenz statt\n";
				}
				dateiSchreiber.write(online);
				dateiSchreiber.write("Professor-ID: "+kurs.getId()+"\n\n");
				ausgabe=true;

			}
			
			if(ausgabe==false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}
			
			ausgabe=false;
			dateiSchreiber.write("-----Freitag-----\n");
			
			
			
			
			for (Kurs kurs : freitag) {

				

				dateiSchreiber.write(kurs.getKursname() + "\n");
				dateiSchreiber.write("Um " + kurs.getBlockUhrzeit() + "\n");

				if (kurs.isOnline()) {
					online = "Der Kurs findet online statt\n";
				} else {
					online = "Der Kurs findet in Präsenz statt\n";
				}
				dateiSchreiber.write(online);
				dateiSchreiber.write("Professor-ID: "+kurs.getId()+"\n\n");
				ausgabe=true;

			}
			
			if(ausgabe==false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}
			
			ausgabe=false;
			
			dateiSchreiber.write("-----Samstag-----\n");
			
			
				for (Kurs kurs : samstag) {
			

				

				dateiSchreiber.write(kurs.getKursname() + "\n");
				dateiSchreiber.write("Um " + kurs.getBlockUhrzeit() + "\n");

				if (kurs.isOnline()) {
					online = "Der Kurs findet online statt\n";
				} else {
					online = "Der Kurs findet in Präsenz statt\n";
				}
				dateiSchreiber.write(online);
				dateiSchreiber.write("Professor-ID: "+kurs.getId()+"\n\n");
				ausgabe=true;

			
			
				}
				
				if(ausgabe==false) {
					dateiSchreiber.write("Kurs ist leer\n\n");
				}
				
				ausgabe=false;
				
				System.out.println("Datei wurde erfolgreich erstellt");
			
				
			

		} catch (IOException e) {

		}
	}

	public static void suchen(ArrayList<Kurs> alleKurse, String eingabe) {

		int i = 0;
		for (Kurs e : alleKurse) {

			if (e.getKursname().equals(eingabe)) {
				System.out.println(
						e.getKursname() + " findet um " + e.getBlockUhrzeit() + " am " + e.getWochentag() + " statt.");
				i++;
			}

		}
		if (i == 0) {
			System.out.println("Kurs gibt es nicht!");
		}
	}

	public static void kursAbspeichern(ArrayList<Kurs> alleKurse) {

	}

	public static void kurseEinsotieren(ArrayList<Kurs> alleKurse, ArrayList<Kurs> montag, ArrayList<Kurs> dienstag,
			ArrayList<Kurs> mittwoch, ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {

		for (Iterator<Kurs> e = alleKurse.iterator(); e.hasNext();) {
			Kurs a = e.next();

			if (a.getBlockUhrzeit() == null) {
				e.remove();
				System.out.println(
						"Der Kurs " + a.getKursname() + " ist fehlerhaft und wird nicht zum Stundenplan hinzugefügt");
			}

			else {

				switch (a.getWochentagZahl()) {

				case 1:
					montag.add(a);
					break;
				case 2:
					dienstag.add(a);
					break;
				case 3:
					mittwoch.add(a);
					break;
				case 4:
					donnerstag.add(a);
					break;
				case 5:
					freitag.add(a);
					break;
				case 6:
					samstag.add(a);
					break;
				default:

					break;
				}
			}

		}
	}
	
	public static void kurseVonDateieinlesen() {
		
	}
}


