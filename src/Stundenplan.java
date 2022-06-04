import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;

public class Stundenplan {
	/*
	 * Alle ArrayLists werden mit static angelegt, damit innerhalb der ganzen Klasse
	 * auf diese zugegriffen werden kann
	 */
	static ArrayList<Kurs> alleKurse = new ArrayList<Kurs>();
	static ArrayList<Professor> alleProfessoren = new ArrayList<Professor>();
	static ArrayList<Kurs> montag = new ArrayList<Kurs>();
	static ArrayList<Kurs> dienstag = new ArrayList<Kurs>();
	static ArrayList<Kurs> mittwoch = new ArrayList<Kurs>();
	static ArrayList<Kurs> donnerstag = new ArrayList<Kurs>();
	static ArrayList<Kurs> freitag = new ArrayList<Kurs>();
	static ArrayList<Kurs> samstag = new ArrayList<Kurs>();
	static ArrayList<ArrayList<Kurs>> wochentage = new ArrayList<ArrayList<Kurs>>();

	public static void main(String[] args) {
		/*
		 * Jede ArrayList für die einzelnen Wochentage werden zu einer großen ArrayList
		 * namens 'wochentage' hinzugefügt, um den Code übersichtlicher zugestallten
		 */
		wochentage.add(montag);
		wochentage.add(dienstag);
		wochentage.add(mittwoch);
		wochentage.add(donnerstag);
		wochentage.add(freitag);
		wochentage.add(samstag);

		/* Professoren können hardcoded hinzugefügt werden */
		alleProfessoren.add(new Professor("Pado", "Ulrike", "Weiblich", 4971189262811l));

		/* Kurse können hardcodes hinzugefügt werden */
		/* Wenn bereits eine Blockeinheit belegt ist wird dieser nicht hinzugefügt */
		/* 4x der selbe Kurs, nur auf 4 unterschiedliche Arten angelegt */
//		alleKurse.add(new Kurs("Programmieren 1", 1, 1, false, 1, 1));
//		alleKurse.add(new Kurs("Programmieren 1", 8:00 Uhr - 9:30 Uhr, 1, false, 1, 1));
//		alleKurse.add(new Kurs("Programmieren 1", "8:00 Uhr - 9:30 Uhr", "Montag", false, 1, 1));
//		alleKurse.add(new Kurs("Programmieren 1", 1, "Montag", false, 1, 1));

//		alleKurse.add(new Kurs("1", 1, 1, false, 1, 1));
//		alleKurse.add(new Kurs("4", 1, 4, true, 000,1));
//		alleKurse.add(new Kurs("4", 1, 2, true, 000,1));
//		alleKurse.add(new Kurs("4", 1, 3, false, 1,1));
//		alleKurse.add(new Kurs("5", 1, 6, false, 1,1));
//		alleKurse.add(new Kurs("6", 1, 5, false, 1,10));
//		alleKurse.add(new Kurs("1.1", 1, 1, false, 1,10));
//		alleKurse.add(new Kurs("3.1", 1, 2, false, 1,10));
//		alleKurse.add(new Kurs("3.2", 1, 2, false, 1,10));
//		alleKurse.add(new Kurs("1.1", 5, 2, false, 1, 10));

//		alleKurse.add(new Kurs("sdfjksd", 1, "Mittwoch", false, 1));

		/* Wenn Fehler beim erstellen entstehen werden sie aussotiert */
//		alleKurse.add(new Kurs("Programmieren 4 dummi", -1, 4, true, 1));

		/*
		 * alle Professoren werden von einer Datei eingelesen, die bei dem letzten
		 * ausführen des Programmes abgespeichert wurden
		 */
		professorenVonDateieinlesen();
		/*
		 * alle Kurse werden von einer Datei eingelesen, die bei dem letzten ausführen
		 * des Programmes abgespeichert wurden
		 */
		kurseVonDateieinlesen();
		/*
		 * alle hinzugefügen Kurse werden überprüft, ob doppelte Kurseinheiten oder
		 * fehlerhafte Kurse vorhanden sind
		 */
		kurseEinsotieren();
		/*
		 * die ArrayList 'wochentage' (somit alle korrekt erstellen Kurse) nach ihrer
		 * Blockeinheit sotiert
		 */
		sortBlockeinheit();

		/*
		 * Endlos-Schleife damit das Interface nach jeder ausgeführten Methode angezeigt
		 * wird
		 */
		while (true) {
			Scanner eingabesc = new Scanner(System.in);

			// Website von den folgenden Sonderzeichen: https://de.piliapp.com/symbol/line/
			System.out.println("┌────────────────────────────────────────┐");
			System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
			System.out.println("│▶ Kurs-Abteilung ◀                      │");
			System.out.println("│ 1 ➞ alle ausgeben                      │");
			System.out.println("│ 2 ➞ suchen                             │");
			System.out.println("│ 3 ➞ erstellen                          │");
			System.out.println("│ 4 ➞ löschen                            │");
			System.out.println("│▶ Professor-Abteilung ◀                 │");
			System.out.println("│ 5 ➞ alle ausgeben                      │");
			System.out.println("│ 6 ➞ suchen                             │");
			System.out.println("│ 7 ➞ erstellen                          │");
			System.out.println("│ 8 ➞ löschen                            │");
			System.out.println("│▶ Sonstiges-Abteilung ◀                 │");
			System.out.println("│ 9 ➞ Stundenplan als Datei ausgeben     │");
			System.out.println("│ q ➞ Programm beenden                   │");
			System.out.println("└────────────────────────────────────────┘");
			System.out.print("Ihre Eingabe ➞ ");

			String eingabeSwitch = eingabesc.nextLine();

			switch (eingabeSwitch) {
			case "1":// alle Kurse ausgaben
				System.out.println();
				kursAusgeben(montag);
				kursAusgeben(dienstag);
				kursAusgeben(mittwoch);
				kursAusgeben(donnerstag);
				kursAusgeben(freitag);
				kursAusgeben(samstag);
				endeVonSwitchCase();
				break;
			case "2":// Kurs suchen
				kursSuchen();
				endeVonSwitchCase();
				break;
			case "3":// Kurs Erstellen
				kursErstellenSC();
				endeVonSwitchCase();
				break;

			case "4": // Kurs löschen
				kursloeschen();
				endeVonSwitchCase();
				break;

			case "5":
				for (Professor p : alleProfessoren) {
					System.out.println(p.toStringsimple());
				}
				endeVonSwitchCase();
				break;

			case "6": // Professor suchen
				professorSuchen();
				endeVonSwitchCase();
				break;
			case "7":// Professor erstellen
				professorErstellen();
				endeVonSwitchCase();
				break;

			case "8": // Professor löschen
				professorloeschen();
				endeVonSwitchCase();
				break;

			case "9":// Stundenplan als Dateiausgeben
				dateiAusgeben();
				endeVonSwitchCase();
				break;

			case "q":// Programm beenden
				kurseInDateiausgeben(); 
				professorenInDateiausgeben(); 
				System.exit(0);
				break;

			default:
				System.out.println(eingabeSwitch + " ist nicht definiert");
			}
			System.out.println();
			kurseInDateiausgeben(); 
			professorenInDateiausgeben(); 
		}

	}

	/* Datei Ein- und Ausgabe */

	public static void professorenInDateiausgeben() {
		// Professoren werden mit einer for-each-Schleife in einer Textdatei, auf dem Desktop abgespeichert, um sie nach einem Programm-Stopp zubehalten
		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/alleProfessoren.txt"), false))) {

			
			for (Professor p : alleProfessoren) {
				dateiSchreiber.write(p.toStringtoTxt() + "\n");
			}

		} catch (IOException e) {

		}

	}

	public static void professorenVonDateieinlesen() {
//		 Professoren werden von einer Datei eingelesen, die auf dem Desktop liegt. Diese Datei wurde von der Methode 'professorenInDateiausgeben()' erstellt.
		try (BufferedReader dateiLeser = new BufferedReader(
				new FileReader(new File("/Users/york/Desktop/alleProfessoren.txt")))) {
			String zeile;

			String[] z;

			while ((zeile = dateiLeser.readLine()) != null) {

				z = zeile.split(";");

				boolean professornameexistiert = false;
				for (Professor p : alleProfessoren) {

					if (Integer.parseInt(z[5]) == (p.getId())) {
						professornameexistiert = true;
					}
				}

				if (professornameexistiert == false) {
					alleProfessoren.add(new Professor(z[0], z[1], z[2], Long.parseLong(z[3])));
				}
			}
		} catch (IOException e) {
			System.out.println("Es wurden keine Professoren eingelesen");
		}
	}

	public static void kurseInDateiausgeben() {
		// Kurse werden mit einer for-each-Schleife in einer Textdatei, auf dem Desktop abgespeichert, um sie nach einem Programm-Stopp zubehalten
		
		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/alleKurse.txt"), false))) {

			
			for (Kurs k : alleKurse) {
				dateiSchreiber.write(k.toStringforbackup() + ("\n"));
			}

		} catch (IOException e) {

		}

	}

	public static void kurseVonDateieinlesen() {
//		 Kurse werden von einer Datei eingelesen, die auf dem Desktop liegt. Diese Datei wurde von der Methode 'professorenInDateiausgeben()' erstellt.
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
							Boolean.parseBoolean(z[3]), Integer.parseInt(z[4]), Integer.parseInt(z[5])));
				}
			}
		} catch (IOException e) {
			System.out.println("Es wurden keine Kurse eingelesen");
		}
	}

	public static void dateiAusgeben() {
		// Kurse werden mit 2 for-Schleifen in einer Datei ausgegeben. Die erste for-Schleife gibt den Wochentag an, die zweite gibt den Kurs an. Hier arbeiten wir mit der Methode 'get()' um den Index herauszufinfen, falls ein Wochentag, keine Kurse besitz wird dies in der Datei entsprechend gekennzeichnet
		Scanner inString = new Scanner(System.in);

		System.out.println("Welcher Dateiname soll der Stundenplan haben?");
		String dateiname = inString.nextLine();

		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/" + dateiname + ".txt"), false))) {

			boolean ausgabe = false;

			String[] WochentageinArray = { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag" };

			for (int i = 0; i < wochentage.size(); i++) {

				dateiSchreiber.write("-----" + WochentageinArray[i] + "-----\n");

				for (int z = 0; z < wochentage.get(i).size(); z++) {

					dateiSchreiber.write(wochentage.get(i).get(z).toString());

					ausgabe = true;

				}
			}

			if (ausgabe == false) {
				dateiSchreiber.write("Vorlesungsfreier Tag\n\n");
			}

			ausgabe = false;

		} catch (IOException e) {

		}
	}

	/* Rest */
	public static void sortBlockeinheit() {
		// Die Kurse in der ArrayList 'wochentage' werden nach ihrer Blockeinheit sortiert

		Collections.sort(wochentage, new Comparator<ArrayList<Kurs>>() {

			@Override
			public int compare(ArrayList<Kurs> o1, ArrayList<Kurs> o2) {
				return 0;
			}
		});

	}

	public static void endeVonSwitchCase() {
		Scanner eingabesc2 = new Scanner(System.in);
		System.out.println("\nUm das Programm zubeenden 'q' eingeben andernfalls andere Eingabe tätigen");
		String eingabeSwitch = eingabesc2.nextLine();

		if (eingabeSwitch.equals("q")) {
			System.exit(0);
		}

	}

	/* Rest -> Kurs */
	public static void kurseEinsotieren() {
		//alle Kurse werden überprüft, ob sie fehlerhaft erstellt wurden. Dann überprüft, ob eine Blockeinheit doppelt belegt ist, falls einer der beiden Fälle eintritt wird der Kurs aus der ArrayList entfernt. Andernfalls wird der Kurs in seine entsprechende ArrayList eingefügt. Diese Methode wird nur 1x ausgeführt und überprüft alle Kurse.
		for (int x = 0; x < alleKurse.size(); x++) {
			Kurs k = alleKurse.get(x);

			
			if (k.getBlockUhrzeit() == null || k.getWochentagZahl() == 0) {

				System.out.println(
						"Der Kurs " + k.getKursname() + " ist fehlerhaft und wird nicht zum Stundenplan hinzugefügt");
				alleKurse.remove(k);
			} else {
				int i = 0;
				for (Kurs z : alleKurse) {

					if (k.getBlockeinheit() == z.getBlockeinheit() && k.getWochentagZahl() == z.getWochentagZahl()) {
						i++;

					}

				}

				if (i > 1) {
					System.out.println("Der Kurs " + k.getKursname() + " wurde nicht erstellt, da " + k.getWochentag()
							+ " um " + k.getBlockUhrzeit() + " bereits belegt ist.");
					alleKurse.remove(x);
				}
			}
		}

		for (Kurs k : alleKurse) {

			switch (k.getWochentagZahl()) {
			case 1:
				montag.add(k);

				break;
			case 2:
				dienstag.add(k);

				break;
			case 3:
				mittwoch.add(k);

				break;
			case 4:
				donnerstag.add(k);

				break;
			case 5:
				freitag.add(k);

				break;
			case 6:
				samstag.add(k);

				break;
			default:
				// gibts nicht
				break;
			}
		}
	}

	public static void einKurseinsotrieren() {
		//letzter hinzugefügter Kurse wird überprüft, ob er fehlerhaft erstellt wurden. Dann wird überprüft, ob die Blockeinheit bereits belegt ist, falls einer der beiden Fälle eintritt wird der Kurs aus der ArrayList entfernt. Andernfalls wird der Kurs in seine entsprechende ArrayList eingefügt. Diese Methode wird nach jedem neuerstelten Kurs ausgeführt und überprüft lediglich einen (den letzten) Kurse.
		int x = alleKurse.size() - 1;

		Kurs neuerKurs = alleKurse.get(x);
		if (neuerKurs.getBlockUhrzeit() == null || neuerKurs.getWochentagZahl() == 0) {
			alleKurse.remove(x);
			System.out.println("Der Kurs " + neuerKurs.getKursname()
					+ " ist fehlerhaft und wird nicht zum Stundenplan hinzugefügt");
		}

		int i = 0;
		for (Kurs k : alleKurse) {

			if (k.getBlockeinheit() == neuerKurs.getBlockeinheit()
					&& k.getWochentagZahl() == neuerKurs.getWochentagZahl()) {
				i++;

			}
		}

		if (i > 1) {
			System.out.println("Der Kurs " + neuerKurs.getKursname() + " wurde nicht erstellt, da "
					+ neuerKurs.getWochentag() + " um " + neuerKurs.getBlockUhrzeit() + " bereits belegt ist.");
			alleKurse.remove(neuerKurs);
		} else {
			switch (neuerKurs.getWochentagZahl()) {
			case 1:
				montag.add(neuerKurs);

				break;
			case 2:
				dienstag.add(neuerKurs);

				break;
			case 3:
				mittwoch.add(neuerKurs);

				break;
			case 4:
				donnerstag.add(neuerKurs);

				break;
			case 5:
				freitag.add(neuerKurs);

				break;
			case 6:
				samstag.add(neuerKurs);

				break;
			default:
				//gibts nicht
				break;
			}

		}

	}

	/* Kurse */
	public static void kursAusgeben(ArrayList<Kurs> k) {
		for (Kurs kurs : k) {
			System.out.println(kurs.toStringforKonsole());
			if (kurs.isOnline()) {
				System.out.println("Kurs findet online statt");
			} else {
				System.out.println("Raum: " + kurs.getRaumnummer());
			}
			System.out.println("\n");

		}
	}

	public static void kursSuchen() {
		Scanner inString = new Scanner(System.in);

		int i = 0;
		int x = 0;

		System.out.println("Welcher Kurs soll gesucht werden? (Kursname)");
		String eingabe1 = inString.nextLine();

		for (Kurs k : alleKurse) {

			if (k.getKursname().equals(eingabe1)) {
				x++;
			}
		}

		if (x > 1) {

			System.out
					.println("Gebe 1 ein um den nächsten Termin anzuzeigen oder eine andere Taste um alle auszugeben");

			String eingabe2 = inString.nextLine();

			if (eingabe2.equals("1")) {

				Date date = new Date();

				int tag = date.getDay();

				boolean gefunden = false;

				for (int i1 = tag - 1; i1 < wochentage.size(); i1++) {

					if (gefunden == false) {
						for (int z = 0; z < wochentage.get(i1).size(); z++) {

							if (wochentage.get(i1).get(z).getKursname().equals(eingabe1)
									&& wochentage.get(i1).get(z).getWochentagZahl() >= tag) {
								System.out.println("Der Nächste '" + wochentage.get(i1).get(z).getKursname()
										+ "'-Kurs findet um " + wochentage.get(i1).get(z).getBlockUhrzeit() + " am "
										+ wochentage.get(i1).get(z).getWochentag() + " statt.");

								gefunden = true;
								break;
							}
						}
					}
				}

				if (gefunden == false) {
					for (int i2 = 0; i2 < wochentage.size(); i2++) {
						if (gefunden == false) {
							for (int z = 0; z < wochentage.get(i2).size(); z++) {
								if (wochentage.get(i2).get(z).getKursname().equals(eingabe1)) {
									System.out.println("Der Nächste '" + wochentage.get(i2).get(z).getKursname()
											+ "'-Kurs findet um " + wochentage.get(i2).get(z).getBlockUhrzeit() + " am "
											+ wochentage.get(i2).get(z).getWochentag() + " statt.");
									gefunden = true;
									break;

								}
							}

						}
					}

				}

			}

		}

		if (x == 1) {

			for (Kurs e : alleKurse) {

				if (e.getKursname().equals(eingabe1)) {
					System.out.println(e.getKursname() + " findet um " + e.getBlockUhrzeit() + " am " + e.getWochentag()
							+ " statt.");
					i++;
				}

			}
		}
		if (x == 0) {
			System.out.println("Den Kurs '" + eingabe1 + "' gibt es nicht!");
		}
	}

	public static void kursErstellenSC() {
		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		System.out.println("Kursname");
		String kursName = inString.nextLine();

		int wiederholen = 0;
		String uhrzeit;
		while (true) { // Schleife falls die Uhrzeit falsch eingegeben wird, kann man die Uhrzeit
						// nochmals neu eingeben
			System.out.println("Welche Uhrzeit");
			uhrzeit = inString.nextLine();
			if (uhrzeit.equals("1") || uhrzeit.equals("2") || uhrzeit.equals("3") || uhrzeit.equals("4")
					|| uhrzeit.equals("5") || uhrzeit.equals("6") || uhrzeit.equals("8:00 Uhr - 9:30 Uhr")
					|| uhrzeit.equals("9:45 Uhr - 11:15 Uhr") || uhrzeit.equals("11:30 Uhr - 13:00 Uhr")
					|| uhrzeit.equals("14:00 Uhr - 15:30 Uhr") || uhrzeit.equals("15:45 Uhr - 17:15 Uhr")
					|| uhrzeit.equals("17:30 Uhr - 19:00 Uhr")) {
				break;
			} else {
				System.out.println(uhrzeit + " ist keine richtige Angabe");
				System.out.println("Um den Vorhergang zubeenden '1' eingeben andernfalls andere Eingabe tätigen");
				String uhrzeitWied = inString.nextLine();
				switch (uhrzeitWied) {
				case "1":
					wiederholen = 1;
					break;
				default:
					continue;
				}
			}
			if (wiederholen == 1) {
				break;
			}
		}
		if (wiederholen == 1) {
			// break; ssssrrrrryyy
		}

		String wochentag;
		while (true) { // Schleife falls der Wochentag falsch eingegeben wird, kann man den Wochentag
						// nochmals neu eingeben
			System.out.println("Welcher Wochentag");
			wochentag = inString.nextLine();
			if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3") || wochentag.equals("4")
					|| wochentag.equals("5") || wochentag.equals("6") || wochentag.equals("Montag")
					|| wochentag.equals("Dienstag") || wochentag.equals("Mittwoch") || wochentag.equals("Donnerstag")
					|| wochentag.equals("Freitag") || wochentag.equals("Samstag") || wochentag.equals("Sonntag")) {
				break;
			} else {
				System.out.println(wochentag + " ist kein Wochentag");
				System.out.println("Um den Vorhergang zubeenden '1' eingeben andernfalls andere Eingabe tätigen");
				String wochentagWied = inString.nextLine();
				switch (wochentagWied) {
				case "1":
					wiederholen = 1;
					break;
				default:
					continue;
				}
			}
			if (wiederholen == 1) {
			}
			{
				break;
			}
		}
		if (wiederholen == 1) {
			// break; ssssrrrrryyy
		}

		System.out.println("Ist der Kurs online?");
		boolean online = inInt.nextBoolean();

		int raumnummer = 000;

		if (online == false) {
			System.out.println("In welchem Raum ist der Kurs?");
			raumnummer = inInt.nextInt();
		} else {
			raumnummer = 000;
		}

		System.out.println("Welcher Professor hält den Kurs?\n");

		for (Professor prof : alleProfessoren) {
			System.out.println(prof.toStringsimple());

		}

		System.out.println(
				"Geben Sie die richtige Professor-ID ein die Sie für den Kurs einteilen möchten oder geben Sie '0' ein um einen Professor zu erstellen: ");

		int profID = inInt.nextInt();

		if (profID == 0) {
			professorErstellen();

			for (Professor prof : alleProfessoren) {
				System.out.println(prof.toStringsimple());

			}
			System.out.println("Welcher Professor hält den Kurs?\n");
			profID = inInt.nextInt();
		}

		boolean erstellt = false;

		for (Professor prof : alleProfessoren) {
			if (prof.getId() == profID) {

				if (uhrzeit.equals("1") || uhrzeit.equals("2") || uhrzeit.equals("3") || uhrzeit.equals("4")
						|| uhrzeit.equals("5") || uhrzeit.equals("6")) {
					int uhrzeitZahl = Integer.parseInt(uhrzeit);
					if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3") || wochentag.equals("4")
							|| wochentag.equals("5") || wochentag.equals("6")) {
						int wochentagZahl = Integer.parseInt(wochentag);
						alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentagZahl, online, raumnummer, profID));

					} else {
						alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentag, online, raumnummer, profID));
					}
				} else {
					if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3") || wochentag.equals("4")
							|| wochentag.equals("5") || wochentag.equals("6")) {
						int wochentagZahl = Integer.parseInt(wochentag);
						alleKurse.add(new Kurs(kursName, uhrzeit, wochentagZahl, online, raumnummer, profID));
					} else {
						alleKurse.add(new Kurs(kursName, uhrzeit, wochentag, online, raumnummer, profID));
					}
				}
				erstellt = true;
			}
			einKurseinsotrieren();

		}

		if (erstellt == false) {

			System.out.println("Der Kurs wurde nicht erstellt, da '" + profID + "' keine gültige Professor-ID ist.");

		}

	}

	public static void kursloeschen() {

		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		System.out.println("Anhand welcher Eigenschaft soll ein Kurs gelöscht werden?");
		System.out.println("1 ➞ Name");
		System.out.println("2 ➞ Kurs-ID");
		System.out.println("andere Taste ➞ abbrechen");

		String eingabe1 = inString.nextLine();

		switch (eingabe1) {

		case "1":
			System.out.println("Welcher Kurs soll gelöscht werden");
			eingabe1 = inString.nextLine();

			int p = 0;

			for (Kurs k : alleKurse) {
				if (k.getKursname().equals(eingabe1)) {
					p++;
				}
			}

			if (p == 0) {
				System.out.println("Es wurde kein Kurs mit dem Namen " + eingabe1 + " gefunden");
			}
			if (p == 1) {
				for (Kurs k : alleKurse) {
					if (k.getKursname().equals(eingabe1)) {

						alleKurse.remove(k);
						System.out.println("Der Kurs '" + k.getKursname() + "' wurde entfernt");
						break;

					}
				}
			}

			if (p > 1) {
				for (Kurs k : alleKurse) {

					if (eingabe1.equals(k.getKursname())) {
						System.out.print(k.toStringforKonsole());
					}
				}

				System.out.println("Es wurden " + p + " Kurse gefunden bitte gebe die Kurs ID ein:");
				int eingabe5 = inInt.nextInt();

				int entfernteKurse = 0;

				for (int x = 0; x < alleKurse.size(); x++) {
					// Kurs a = e.next();
					Kurs k = alleKurse.get(x);

					if (k.getKursId() == eingabe5) {
						// e.remove();

						System.out.println("Der Kurs '" + alleKurse.get(x).getKursname() + "' mit der Kurs-ID '"
								+ alleKurse.get(x).getKursId() + "' wurde entfernt.");
						alleKurse.remove(x);
						entfernteKurse++;

					}

				}

				if (entfernteKurse == 0) {
					System.out.println("Es wurde kein Kurs mit dieser Kurs-ID gefunden.");
				}

			}

			break;

		case "2":

			for (Kurs k : alleKurse) {
				System.out.print(k.toString()+" "+k.getKursId());
			}
			System.out.println("Welche Kurs-ID hat der Kurs den du löschen möchtest?");

			int eingabe2 = inInt.nextInt();

			int entfernteKurse = 0;

			for (int x = 0; x < alleKurse.size(); x++) {
				// Kurs a = e.next();
				Kurs k = alleKurse.get(x);

				if (k.getKursId() == eingabe2) {
					// e.remove();
					alleKurse.remove(x);
					entfernteKurse++;
					System.out.println("Der Kurs '" + alleKurse.get(x).getKursname() + "' wurde entfernt");

				}

			}

			if (entfernteKurse == 0) {
				System.out.println("Es wurde kein Kurs mit dieser Kurs-ID gefunden.");
			}

		}

	}

	/* Professor */

	public static void professorErstellen() {
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
	}

	public static void professorloeschen() {
		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		System.out.println("Anhand welcher Eigenschaft soll der Professor gelöscht werden?");
		System.out.println("1 ➞ Name");
		System.out.println("2 ➞ Professor-ID");
		System.out.println("andere Taste ➞ abbrechen");

		String eingabe1 = inString.nextLine();

		switch (eingabe1) {

		case "1":
			System.out.println("Geben Sie den Nachnamen des Professors ein:");
			String eingabe2 = inString.nextLine();

			int p = 0;

			for (Professor p1 : alleProfessoren) {
				if (p1.getName().equals(eingabe2)) {
//					
					p++;
				}
			}

			if (p == 0) {
				System.out.println("Es wurde kein Professor mit dem Namen " + eingabe2 + " gefunden");
			}

			if (p == 1) {
				for (Professor p1 : alleProfessoren) {
					if (p1.getName().equals(eingabe2)) {

						alleProfessoren.remove(p1);
						System.out.println(p1.toStringsimple() + " wurde gelöscht");
						break;

					}
				}
			}

			if (p > 1) {
				System.out.println("Es wurden " + p + " Professoren gefunden");
				System.out.println("Geben Sie den Vorname des Professors ein:");
				String eingabe3 = inString.nextLine();
				for (Professor p1 : alleProfessoren) {
					if (p1.getName().equals(eingabe2) && p1.getVorname().equals(eingabe3)) {
						alleProfessoren.remove(p1);
						System.out.println(p1.toStringsimple() + " wurde gelöscht");
						break;
					}

				}
			}

			break;

		case "2":

			for (Professor prof : alleProfessoren) {
				System.out.println(prof.toStringsimple());
			}

			System.out.println("\nWelche Professor-ID hat der Professor den du löschen möchtest?");

			int eingabe4 = inInt.nextInt();

			for (int x = 0; x < alleProfessoren.size(); x++) {
				// Kurs a = e.next();
				Professor prof = alleProfessoren.get(x);

				if (prof.getId() == eingabe4) {
					// e.remove();
					alleProfessoren.remove(x);
					System.out.println("Der Professor wurde erfolgreich gelöscht");
				} else {
					System.out.println("Der Professor wurde nicht gelöscht, da diese ID nicht hinterlegt ist.");
				}

			}

		}

	}

	public static void professorSuchen() {
		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		System.out.println("Anhand welcher Eigenschaft soll der Professor gesucht werden?");
		System.out.println("1 ➞ Name");
		System.out.println("2 ➞ Professor-ID");
		System.out.println("andere Taste ➞ abbrechen");

		String eingabe1 = inString.nextLine();

		switch (eingabe1) {

		case "1":
			System.out.println("Geben Sie den Nachnamen des Professors ein:");
			String eingabe2 = inString.nextLine();

			int p = 0;

			for (Professor p1 : alleProfessoren) {
				if (p1.getName().equals(eingabe2)) {
//					
					p++;
				}
			}

			if (p == 0) {
				System.out.println("Es wurde kein Professor mit dem Namen " + eingabe2 + " gefunden");
			}

			if (p == 1) {
				for (Professor p1 : alleProfessoren) {
					if (p1.getName().equals(eingabe2)) {

						System.out.println("Name: " + p1.getName() + " " + p1.getVorname() + " Geschlecht: "
								+ p1.getGeschlecht() + " E-Mail: " + p1.geteMail());
						break;

					}
				}
			}

			if (p > 1) {
				System.out.println("Es wurden " + p + " Professoren gefunden");
				System.out.println("Geben Sie den Vorname des Professors ein:");
				String eingabe3 = inString.nextLine();
				for (Professor p1 : alleProfessoren) {
					if (p1.getName().equals(eingabe2) && p1.getVorname().equals(eingabe3)) {
						alleProfessoren.remove(p1);
						System.out.println("Name: " + p1.getName() + " " + p1.getVorname() + " Geschlecht: "
								+ p1.getGeschlecht() + " E-Mail: " + p1.geteMail());
						break;
					}

				}
			}

			break;

		case "2":

			for (Professor prof : alleProfessoren) {
				System.out.println(prof.toStringsimple());
			}

			System.out.println("\nWelche Professor-ID hat der Professor den du suchst?");

			int eingabe4 = inInt.nextInt();

			for (int x = 0; x < alleProfessoren.size(); x++) {
				// Kurs a = e.next();
				Professor prof = alleProfessoren.get(x);

				if (prof.getId() == eingabe4) {
					// e.remove();
					System.out.println("Name: " + alleProfessoren.get(x).getName() + " "
							+ alleProfessoren.get(x).getVorname() + " Geschlecht: "
							+ alleProfessoren.get(x).getGeschlecht() + " E-Mail: " + alleProfessoren.get(x).geteMail());
				} else {
					System.out.println("Es wurde kein Professor gefunden, da diese ID nicht hinterlegt ist.");
				}

			}

		}

	}
}
