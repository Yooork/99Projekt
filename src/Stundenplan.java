import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Stundenplan {

	static ArrayList<Kurs> alleKurse = new ArrayList<Kurs>();

	public static void main(String[] args) {

		Scanner inInt = new Scanner(System.in);
		Scanner inString = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);

		
		
		ArrayList<Professor> alleProfessoren = new ArrayList<Professor>();
		
		
			alleProfessoren.add(new Professor("Pado", "Ulrike", "Weiblich", 4971189262811l));
		
		
		
		
		alleKurse.add(new Kurs("Programmieren 1", "9:45 Uhr - 11:15 Uhr", 1, false, 1));
		alleKurse.add(new Kurs("Programmieren 2", "8:00 Uhr - 9:30 Uhr", 2, true, 1));
		alleKurse.add(new Kurs("Programmieren 3", 1, 3, false, 1));
		alleKurse.add(new Kurs("Programmieren 4 dummi", 1, 4, true, 1));
		alleKurse.add(new Kurs("Programmieren 4", 1, 4, true, 1));

		kurseVonDateieinlesen(alleKurse);

		ArrayList<Kurs> montag = new ArrayList<Kurs>();
		ArrayList<Kurs> dienstag = new ArrayList<Kurs>();
		ArrayList<Kurs> mittwoch = new ArrayList<Kurs>();
		ArrayList<Kurs> donnerstag = new ArrayList<Kurs>();
		ArrayList<Kurs> freitag = new ArrayList<Kurs>();
		ArrayList<Kurs> samstag = new ArrayList<Kurs>();

		kurseEinsotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);

		allesotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);

		while (true) {
			Scanner eingabesc = new Scanner(System.in);

			// https://de.piliapp.com/symbol/line/
			System.out.println("┌────────────────────────────────────────┐");
			System.out.println("│Drücke:                                 │");
			System.out.println("│1 ➞ alle Kurse auszugeben               │");
			System.out.println("│2 ➞ Kurs suchen                         │");
			System.out.println("│3 ➞ Kurs erstellen                      │");
			System.out.println("│4 ➞ Stundenplan als Datei auszugeben    │");
			System.out.println("│5 ➞ Professor erstellen - WIP           │");
			System.out.println("│6 ➞ Kurs löschen - WIP                  │");
			System.out.println("│q ➞ Programm zu beenden                 │");
			System.out.println("└────────────────────────────────────────┘");
			System.out.println("Ihre Eingabe ➞ ");

			String eingabeSwitch = eingabesc.nextLine();

			switch (eingabeSwitch) {
			case "1":
				System.out.println();
				kurseEinsotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);
				ausgeben(montag);
				ausgeben(dienstag);
				ausgeben(mittwoch);
				ausgeben(donnerstag);
				ausgeben(freitag);
				ausgeben(samstag);
				endeVonSwitchCase();
				break;
			case "2":
				System.out.println();
				System.out.println("Welcher Kurs soll gesucht werden? (Kursname)");
				String eingabe = inString.nextLine();
				suchen(alleKurse, eingabe);
				endeVonSwitchCase();
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
				
				System.out.println("Welcher Professor hält den Kurs?");
				
				
				
				for (Professor prof : alleProfessoren) {
					System.out.println(prof.toString());

				}
				
				System.out.println("Geben Sie die richtige Professor-ID ein die Sie für den Kurs einteilen möchten: ");
				
				int profID = inInt.nextInt();
				
				
				

				if (uhrzeit.equals("1") || uhrzeit.equals("2") || uhrzeit.equals("3") || uhrzeit.equals("4")
						|| uhrzeit.equals("5") || uhrzeit.equals("6")) {
					int uhrzeitZahl = Integer.parseInt(uhrzeit);
					if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3") || wochentag.equals("4")
							|| wochentag.equals("5") || wochentag.equals("6")) {
						int wochentagZahl = Integer.parseInt(wochentag);
						alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentagZahl, online, profID));

					} else {
						alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentag, online, profID));
					}
				} else {
					if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3") || wochentag.equals("4")
							|| wochentag.equals("5") || wochentag.equals("6")) {
						int wochentagZahl = Integer.parseInt(wochentag);
						alleKurse.add(new Kurs(kursName, uhrzeit, wochentagZahl, online, profID));
					} else {
						alleKurse.add(new Kurs(kursName, uhrzeit, wochentag, online, profID));
					}
				}

//				kurseEinsotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);
				endeVonSwitchCase();
				break;

			case "q":
				kurseInDateiausgeben(alleKurse);
				System.exit(0);
				break;

			case "4":
				System.out.println("Welcher Dateiname soll der Stundenplan haben?");
				String dateiname = sc3.nextLine();
				dateiAusgeben(montag, dienstag, mittwoch, donnerstag, freitag, samstag, dateiname);
				endeVonSwitchCase();
				break;
			case "5":
				
				Scanner inString3 = new Scanner(System.in);
				
				
				System.out.println("Wie ist der Nachname des Professors?");
				String name = inString3.nextLine();
				
				System.out.println("Wie ist der Vorname des Professors?");
				String vorname = inString3.nextLine();
				
				
				System.out.println("Was für ein Geschlecht hat der Professors?");
				String geschlecht = inString3.nextLine();
				
				System.out.println("Wie ist die Telefonummer des Professors?");
				long tel = inString3.nextLong();
				
				
					alleProfessoren.add(new Professor(name, vorname, geschlecht, tel));
			
				
				break;
				
			case "6":
				System.out.println();
				System.out.println("Anhand welcher Eigenschaft soll ein Kurs gelöscht werden?");
				System.out.println("1 ➞ Name");
				System.out.println("2 ➞ Kurs-ID");
				System.out.println("andere Taste ➞ abbrechen");
				
				
				String eingabe1 = inString.nextLine();
				
				switch(eingabe1) {
				
				case "1":
					System.out.println("Welcher Kurs soll gelöscht werden");
					eingabe1 = inString.nextLine();
					
					int p=0;
					
					for(Kurs k:alleKurse) {
						if(k.getKursname().equals(eingabe1)) {
							p++;
						}
					}
					
					if(p==0) {
						System.out.println("Es Wurde kein Kurs mit dem Namen "+eingabe1+" gefunden");
					}
					if(p==1)
					
					break;
					
				case "2":
					System.out.println("Welche Kurs-ID hat der Kurs den du löschen möchtest?");
					
					
					for(Kurs k: alleKurse) {
						System.out.print(k.toStringfuerKonsole());
					}
					
					int eingabe2 = inInt.nextInt();
					
						
						
						
						for (int x = 0; x < alleKurse.size(); x++) {
							// Kurs a = e.next();
							Kurs k = alleKurse.get(x);

							if (k.getKursId()==eingabe2) {
								// e.remove();
								alleKurse.remove(x);
					}
							
					
					
				
				}
						for(Kurs k: alleKurse) {
							System.out.print(k.toStringfuerKonsole());
						}
				}
				
				
				
				
				
				
				endeVonSwitchCase();
				break;
				

			default:
				System.out.println(eingabeSwitch + " ist nicht definiert");
			}
			System.out.println();
			kurseInDateiausgeben(alleKurse);
		}

	}

	public static void allesotieren(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag, ArrayList<Kurs> mittwoch,
			ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {
		// Nach Blockeinheit sotieren

		// in ein set alle 6 arraylisten reinmachen und dann nur einmal sotieren

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
			System.out.println(kurs.toStringfuerKonsole());

		}
	}

	public static void dateiAusgeben(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag, ArrayList<Kurs> mittwoch,
			ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag, String dateiname) {

		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/" + dateiname + ".txt"), false))) {
			String online;
			boolean ausgabe = false;
			dateiSchreiber.write("-----Montag-----\n");

			for (Kurs kurs : montag) {

				dateiSchreiber.write(kurs.toString());
				ausgabe = true;

			}

			if (ausgabe == false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}

			ausgabe = false;

			dateiSchreiber.write("-----Dienstag-----\n");
			for (Kurs kurs : dienstag) {

				dateiSchreiber.write(kurs.toString());
				ausgabe = true;

			}
			if (ausgabe == false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}

			ausgabe = false;

			dateiSchreiber.write("-----Mittwoch-----\n");

			for (Kurs kurs : mittwoch) {

				dateiSchreiber.write(kurs.toString());
				ausgabe = true;

			}

			if (ausgabe == false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}

			ausgabe = false;

			dateiSchreiber.write("-----Donnerstag-----\n");

			for (Kurs kurs : donnerstag) {

				dateiSchreiber.write(kurs.toString());
				ausgabe = true;

			}

			if (ausgabe == false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}

			ausgabe = false;
			dateiSchreiber.write("-----Freitag-----\n");

			for (Kurs kurs : freitag) {

				dateiSchreiber.write(kurs.toString());
				ausgabe = true;

			}

			if (ausgabe == false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}

			ausgabe = false;

			dateiSchreiber.write("-----Samstag-----\n");

			for (Kurs kurs : samstag) {

				dateiSchreiber.write(kurs.toString());
				ausgabe = true;

			}

			if (ausgabe == false) {
				dateiSchreiber.write("Kurs ist leer\n\n");
			}

			ausgabe = false;

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
			System.out.println("Den Kurs '" + eingabe + "' gibt es nicht!");
		}
	}

	public static void kurseEinsotieren(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag, ArrayList<Kurs> mittwoch,
			ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {

		// Iterator<Kurs> e = alleKurse.iterator(); e.hasNext();
		for (int x = 0; x < alleKurse.size(); x++) {
			// Kurs a = e.next();
			Kurs a = alleKurse.get(x);

			if (a.getBlockUhrzeit() == null || a.getWochentagZahl() == 0) {
				// e.remove();
				alleKurse.remove(x);
				System.out.println(
						"Der Kurs " + a.getKursname() + " ist fehlerhaft und wird nicht zum Stundenplan hinzugefügt");
			} else {
				int i = 0;
				for (Kurs z : alleKurse) {

					if (a.getBlockeinheit() == z.getBlockeinheit() && a.getWochentagZahl() == z.getWochentagZahl()) {
						i++;

					}

				}
				if (i > 1) {
					alleKurse.remove(x);

					// e.remove();
					System.out.println("Der Kurs " + a.getKursname() + " wurde nicht erstellt, da " + a.getWochentag()
							+ " um " + a.getBlockUhrzeit() + " bereits belegt ist.");

				} else {
					if (!existsInSystem(a, montag, dienstag, mittwoch, donnerstag, freitag, samstag) && i == 1) {

						switch (a.getWochentagZahl()) {

						case 1:
							montag.add(a);

							// System.out.println(a.getKursname()+" "+a.getWochentag()+"
							// "+a.getBlockUhrzeit());
							break;
						case 2:
							dienstag.add(a);
							// System.out.println(a.getKursname()+" "+a.getWochentag()+"
							// "+a.getBlockUhrzeit());
							break;
						case 3:
							mittwoch.add(a);
							// System.out.println(a.getKursname()+" "+a.getWochentag()+"
							// "+a.getBlockUhrzeit());
							break;
						case 4:
							donnerstag.add(a);
							// System.out.println(a.getKursname()+" "+a.getWochentag()+"
							// "+a.getBlockUhrzeit());
							break;
						case 5:
							freitag.add(a);
							// System.out.println(a.getKursname()+" "+a.getWochentag()+"
							// "+a.getBlockUhrzeit());
							break;
						case 6:
							samstag.add(a);
							// System.out.println(a.getKursname()+" "+a.getWochentag()+"
							// "+a.getBlockUhrzeit());
							break;
						default:
							System.out.println("2");
							break;
						}
						// System.out.println(alleKurse.get(alleKurse.size() - 1));
					} // i == 1 || i == 0

				}

			}

		}

	}

	private static boolean existsInSystem(Kurs kurs, ArrayList<Kurs> montag, ArrayList<Kurs> dienstag,
			ArrayList<Kurs> mittwoch, ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {
		for (int i = 0; i < montag.size(); i++) {
			if (montag.get(i).getKursId() == kurs.getKursId()) {
				return true;
			}
		}
		for (int i = 0; i < dienstag.size(); i++) {
			if (dienstag.get(i).getKursId() == kurs.getKursId()) {
				return true;
			}
		}
		for (int i = 0; i < mittwoch.size(); i++) {
			if (mittwoch.get(i).getKursId() == kurs.getKursId()) {
				return true;
			}
		}
		for (int i = 0; i < donnerstag.size(); i++) {
			if (donnerstag.get(i).getKursId() == kurs.getKursId()) {
				return true;
			}
		}
		for (int i = 0; i < freitag.size(); i++) {
			if (freitag.get(i).getKursId() == kurs.getKursId()) {
				return true;
			}
		}
		for (int i = 0; i < samstag.size(); i++) {
			if (samstag.get(i).getKursId() == kurs.getKursId()) {
				return true;
			}
		}
		return false;
	}

	public static void kurseVonDateieinlesen(ArrayList<Kurs> alleKurse) {

		try (BufferedReader dateiLeser = new BufferedReader(
				new FileReader(new File("/Users/york/Desktop/alleKurse.txt")))) {
			String zeile;

			String[] z;

			while ((zeile = dateiLeser.readLine()) != null) {

				z = zeile.split(";");

				boolean kursnameexistiert = false;
				for (Kurs i : alleKurse) {

					if (z[0].equals(i.getKursname())) {
						kursnameexistiert = true;
					}
				}

				if (kursnameexistiert == false) {
					alleKurse.add(new Kurs(z[0], Integer.parseInt(z[1]), Integer.parseInt(z[2]),
							Boolean.parseBoolean(z[3]), Integer.parseInt(z[4])));
				}
			}
		} catch (IOException e) {
			System.out.println("Es wurden keine Kurse eingelesen");
		}
	}

	public static void kurseInDateiausgeben(ArrayList<Kurs> alleKurse) {
		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/alleKurse.txt"), false))) {

			for (Kurs k : alleKurse) {
				dateiSchreiber.write(k.toStringtoTxt() + "\n");
			}

		} catch (IOException e) {

		}

	}

	public static void endeVonSwitchCase() {
		Scanner eingabesc2 = new Scanner(System.in);
		System.out.println("\nUm das Programm zu beenden 'q' eingaben andernfalls andere Eingabe tätigen");
		String eingabeSwitch = eingabesc2.nextLine();

		if (eingabeSwitch.equals("q")) {
			System.exit(0);
		}

	}

	public static void ghj() {

	}
}
