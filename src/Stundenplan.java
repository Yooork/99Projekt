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
import java.util.Scanner;

public class Stundenplan {

	// Alle ArrayLists werden mit static angelegt, damit innerhalb der ganzen Klasse
	// auf diese zugegriffen werden kann.

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
		// Main-Methode, hier werden Kurse/Professoren hardcoded hinzugefügt und das
		// SwitchCase ausgeführt, womit alle funktionen des Programmes ausgeführt werden
		// kann.
		wochentage.add(montag);
		wochentage.add(dienstag);
		wochentage.add(mittwoch);
		wochentage.add(donnerstag);
		wochentage.add(freitag);
		wochentage.add(samstag);

		alleProfessoren.add(new Professor("Pado", "Ulrike", "Weiblich", 4971189262811l));

		alleKurse.add(new Kurs("4", 1, 4, true, "Zoom", 1));
		alleKurse.add(new Kurs("4", 1, 2, true, "Moodle", 1));
		alleKurse.add(new Kurs("4", 1, 3, false, "1/111", 1));
		alleKurse.add(new Kurs("5", 1, 6, false, "1/405", 1));
		alleKurse.add(new Kurs("6", 1, 5, false, "4/134", 1));
		alleKurse.add(new Kurs("1.1", 1, 1, false, "1/654", 1));
		alleKurse.add(new Kurs("3.1", 1, 2, false, "2/201", 1));
		alleKurse.add(new Kurs("3.2", 1, 2, false, "8/423", 1));
		alleKurse.add(new Kurs("1.1", 5, 2, false, "3/654", 1));

		professorenVonDateieinlesen();

		kurseVonDateieinlesen();

		kurseEinsortieren();

		sortBlockeinheit();

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

			System.out.println("");

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

				break;

			case "4": // Kurs löschen
				kursloeschen();
				endeVonSwitchCase();
				break;

			case "5": // Alle Professoren ausgeben
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
		// Professoren werden mit einer for-each-Schleife in einer Textdatei, auf dem
		// Desktop abgespeichert, um sie nach einem Programm-Stopp zubehalten
		try (BufferedWriter dateiSchreiber = new BufferedWriter(new FileWriter(
				new File("/Users/" + System.getProperty("user.name") + "/Desktop/alleProfessoren.txt"), false))) {

			for (Professor p : alleProfessoren) {
				dateiSchreiber.write(p.toStringforBackup() + "\n");
			}

		} catch (IOException e) {

		}

	}

	public static void professorenVonDateieinlesen() {
		// Professoren werden von einer Datei eingelesen, die auf dem Desktop liegt.
		// Diese Datei wurde von der Methode 'professorenInDateiausgeben()' erstellt.
		try (BufferedReader dateiLeser = new BufferedReader(new FileReader(
				new File("/Users/" + System.getProperty("user.name") + "/Desktop/alleProfessoren.txt")))) {
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
		// Kurse werden mit einer for-each-Schleife in einer Textdatei, auf dem Desktop
		// abgespeichert, um sie nach einem Programm-Stopp zubehalten

		try (BufferedWriter dateiSchreiber = new BufferedWriter(new FileWriter(
				new File("/Users/" + System.getProperty("user.name") + "/Desktop/alleKurse.txt"), false))) {

			for (int i = 0; i < wochentage.size(); i++) {
				for (int z = 0; z < wochentage.get(i).size(); z++) {
					Kurs k = wochentage.get(i).get(z);
					dateiSchreiber.write(k.toStringforbackup() + ("\n"));
				}
			}

		} catch (IOException e) {

		}

	}

	public static void kurseVonDateieinlesen() {
		// Kurse werden von einer Datei eingelesen, die auf dem Desktop liegt. Diese
		// Datei wurde von der Methode 'professorenInDateiausgeben()' erstellt.
		try (BufferedReader dateiLeser = new BufferedReader(
				new FileReader(new File("/Users/" + System.getProperty("user.name") + "/Desktop/alleKurse.txt")))) {
			String zeile;

			String[] z;

			while ((zeile = dateiLeser.readLine()) != null) {

				z = zeile.split(";");

				boolean kursnameexistiert = false;
				for (Kurs k : alleKurse) {

					if (z[0].equals(k.getKursname())) {
						kursnameexistiert = true;
					}
				}

				if (kursnameexistiert == false) {
					alleKurse.add(new Kurs(z[0], Integer.parseInt(z[1]), Integer.parseInt(z[2]),
							Boolean.parseBoolean(z[3]), (z[4]), Integer.parseInt(z[5])));
				}
			}
		} catch (IOException e) {
			System.out.println("Es wurden keine Kurse eingelesen");
		}
	}

	public static void dateiAusgeben() {
		// Kurse werden mit 2 for-Schleifen in einer Datei ausgegeben. Die erste
		// for-Schleife gibt den Wochentag an, die zweite gibt den Kurs an. Hier
		// arbeiten wir mit der Methode 'get()' um den Index herauszufinden, falls ein
		// Wochentag, keine Kurse besitz wird dies in der Datei entsprechend
		// gekennzeichnet
		Scanner inString = new Scanner(System.in);

		System.out.println("Welcher Dateiname soll der Stundenplan haben?");
		String dateiname = inString.nextLine();

		try (BufferedWriter dateiSchreiber = new BufferedWriter(new FileWriter(
				new File("/Users/" + System.getProperty("user.name") + "/Desktop/" + dateiname + ".txt"), false))) {

			boolean ausgabe = false;

			String[] WochentageinArray = { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag" };

			for (int i = 0; i < wochentage.size(); i++) {

				dateiSchreiber.write("-----" + WochentageinArray[i] + "-----\n");

				for (int z = 0; z < wochentage.get(i).size(); z++) {

					dateiSchreiber.write(wochentage.get(i).get(z).toString());

					ausgabe = true;

				}

				if (ausgabe == false) {
					dateiSchreiber.write("Vorlesungsfreier Tag\n\n");

				}
				ausgabe = false;
			}

		} catch (IOException e) {

		}
		System.out.println("Die Datei mit dem Namen '" + dateiname + "' wurde erfolgreich erstellt.");
	}

	/* Rest */
	public static void sortBlockeinheit() {
		// Die Kurse in der ArrayList 'wochentage' werden nach ihrer Blockeinheit
		// sortiert

		Collections.sort(wochentage, new Comparator<ArrayList<Kurs>>() {

			@Override
			public int compare(ArrayList<Kurs> o1, ArrayList<Kurs> o2) {
				return 0;
			}
		});

	}

	public static void endeVonSwitchCase() {
		// Wird ausgeführt, damit dem User die Entscheidung geben wird nach der Ausgabe
		// das Programm zubeenden oder eine kleine Pause eingelegt wird bis es wieder
		// ins Hauptmenü geht.
		Scanner inString = new Scanner(System.in);

		System.out.println("┌────────────────────────────────────────┐");
		System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
		System.out.println("│q ➞ Programm beenden                    │");
		System.out.println("│andere Taste ➞ Programm fortsetzen      │");
		System.out.println("└────────────────────────────────────────┘");
		System.out.print("Ihre Eingabe ➞ ");

		String eingabe1 = inString.nextLine();

		if (eingabe1.equals("q")) {
			System.exit(0);
		}

	}

	/* Rest -> Kurs */
	public static void kurseEinsortieren() {
		// alle Kurse werden überprüft, ob sie fehlerhaft erstellt wurden. Dann
		// überprüft, ob eine Blockeinheit doppelt belegt ist, falls einer der beiden
		// Fälle eintritt wird der Kurs aus der ArrayList entfernt. Andernfalls wird der
		// Kurs in seine entsprechende ArrayList eingefügt.

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

			}
		}
		alleKurse.clear();
	}

	public static void einKurseinsotrieren() {
		// letzter hinzugefügter Kurse wird überprüft, ob er fehlerhaft erstellt wurden.
		// Dann wird überprüft, ob die Blockeinheit bereits belegt ist, falls einer der
		// beiden Fälle eintritt wird der Kurs aus der ArrayList entfernt. Andernfalls
		// wird der Kurs in seine entsprechende ArrayList eingefügt. Diese Methode wird
		// nach jedem neuerstelten Kurs ausgeführt und überprüft lediglich einen (den
		// letzten) Kurse.

		Kurs neuerKurs = alleKurse.get(0);

		if (neuerKurs.getBlockUhrzeit() == null || neuerKurs.getWochentagZahl() == 0) {
			alleKurse.remove(0);
			System.out.println("Der Kurs " + neuerKurs.getKursname()
					+ " ist fehlerhaft und wird nicht zum Stundenplan hinzugefügt");
		}

		int gefundeneKurse = 0;
		for (int i = 0; i < wochentage.size(); i++) {
			for (int z = 0; z < wochentage.get(i).size(); z++) {
				Kurs k = wochentage.get(i).get(z);

				if (k.getBlockeinheit() == neuerKurs.getBlockeinheit()
						&& k.getWochentagZahl() == neuerKurs.getWochentagZahl()) {
					gefundeneKurse++;

				}
			}
		}

		if (gefundeneKurse > 0) {
			System.out.println("Der Kurs " + neuerKurs.getKursname() + " wurde nicht erstellt, da "
					+ neuerKurs.getWochentag() + " um " + neuerKurs.getBlockUhrzeit() + " bereits belegt ist.");
			alleKurse.remove(neuerKurs);
		} else {
			switch (alleKurse.get(0).getWochentagZahl()) {
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
				// gibts nicht
				break;
			}

		}

	}

	/* Kurse */
	public static void kursAusgeben(ArrayList<Kurs> k) {
		// Methode zum ausgeben von einer 'ArrayList<Kurs>'
		for (Kurs kurs : k) {
			System.out.println(kurs.toStringforKonsole() + "\n");

		}
	}

	public static void kursSuchen() {
		Scanner inString = new Scanner(System.in);

		int x = 0;

		System.out.println("Welcher Kurs soll gesucht werden? (Kursname)");
		String kursname = inString.nextLine();

		for (int i = 0; i < wochentage.size(); i++) {
			for (int z = 0; z < wochentage.get(i).size(); z++) {
				Kurs k = wochentage.get(i).get(z);

				if (k.getKursname().equals(kursname)) {
					x++;
				}
			}
		}

		if (x > 1) {

			System.out.println("┌────────────────────────────────────────┐");
			System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
			System.out.println("│1 ➞ nächsten Termin ausgeben            │");
			System.out.println("│andere Taste ➞ alle Termine ausgeben    │");
			System.out.println("└────────────────────────────────────────┘");
			System.out.print("Ihre Eingabe ➞ ");

			String eingabe2 = inString.nextLine();

			if (eingabe2.equals("1")) {

				Date date = new Date();

				int tag = date.getDay();

				if (tag == 0) {// Wenn Sonntag ist wird wird der Tag auf 7 statt 0 gesetzt
					tag = 7;
				}

				boolean gefunden = false;

				for (int i = tag - 1; i < wochentage.size(); i++) {

					if (gefunden == false) {
						for (int z = 0; z < wochentage.get(i).size(); z++) {

							if (wochentage.get(i).get(z).getKursname().equals(kursname)
									&& wochentage.get(i).get(z).getWochentagZahl() >= tag) {
								System.out.println("Der Nächste '" + wochentage.get(i).get(z).getKursname()
										+ "'-Kurs findet um " + wochentage.get(i).get(z).getBlockUhrzeit() + " am "
										+ wochentage.get(i).get(z).getWochentag() + " statt.");

								gefunden = true;
								break;
							}
						}
					}
				}

				if (gefunden == false) {
					for (int i = 0; i < wochentage.size(); i++) {
						if (gefunden == false) {
							for (int z = 0; z < wochentage.get(i).size(); z++) {
								if (wochentage.get(i).get(z).getKursname().equals(kursname)) {
									System.out.println("Der Nächste '" + wochentage.get(i).get(z).getKursname()
											+ "'-Kurs findet um " + wochentage.get(i).get(z).getBlockUhrzeit() + " am "
											+ wochentage.get(i).get(z).getWochentag() + " statt.");
									gefunden = true;
									break;

								}
							}

						}
					}

				}

			} else {
				for (int i = 0; i < wochentage.size(); i++) {
					for (int z = 0; z < wochentage.get(i).size(); z++) {
						Kurs k = wochentage.get(i).get(z);

						if (k.getKursname().equals(kursname)) {
							System.out.println(k.toStringforKonsole() + "\n");
						}
					}
				}
			}

		}

		if (x == 1) {

			for (int i = 0; i < wochentage.size(); i++) {
				for (int z = 0; z < wochentage.get(i).size(); z++) {
					Kurs k = wochentage.get(i).get(z);

					if (k.getKursname().equals(kursname)) {
						System.out.println("Der Kurs " + k.getKursname() + " findet um " + k.getBlockUhrzeit() + " am "
								+ k.getWochentag() + " statt.");

					}
				}

			}
		}
		if (x == 0) {
			System.out.println("Den Kurs '" + kursname + "' gibt es nicht!");
		}
	}

	public static void kursErstellenSC() {
		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		// probier mald
		System.out.println("Geben Sie den Kursname ein, den Sie erstellen wollen.");
		String kursName = inString.nextLine();

		int wiederholen = 0;
		String uhrzeit;
		String wochentag;
		while (true) {
			while (true) { // Schleife falls die Uhrzeit falsch eingegeben wird, kann man die Uhrzeit
							// nochmals neu eingeben
				System.out.println("Geben Sie die Uhrzeit ein, wann der Kurs statt findet.");
				uhrzeit = inString.nextLine();
				if (uhrzeit.equals("1") || uhrzeit.equals("2") || uhrzeit.equals("3") || uhrzeit.equals("4")
						|| uhrzeit.equals("5") || uhrzeit.equals("6") || uhrzeit.equals("8:00 Uhr - 9:30 Uhr")
						|| uhrzeit.equals("9:45 Uhr - 11:15 Uhr") || uhrzeit.equals("11:30 Uhr - 13:00 Uhr")
						|| uhrzeit.equals("14:00 Uhr - 15:30 Uhr") || uhrzeit.equals("15:45 Uhr - 17:15 Uhr")
						|| uhrzeit.equals("17:30 Uhr - 19:00 Uhr")) {
					break;
				} else {
					System.out.println(uhrzeit + " ist keine richtige Angabe");

					System.out.println("┌────────────────────────────────────────┐");
					System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
					System.out.println("│andere Taste ➞ Eingabe wiederholen      │");
					System.out.println("│q ➞ Vorhergang beenden                  │");

					System.out.println("└────────────────────────────────────────┘");
					System.out.print("Ihre Eingabe ➞ ");
					String uhrzeitWied = inString.nextLine();
					switch (uhrzeitWied) {
					case "q":
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
				break;
			}

			while (true) { // Schleife falls der Wochentag falsch eingegeben wird, kann man den Wochentag
							// nochmals neu eingeben
				System.out.println("An welchem Wochentag ist der Kurs?");
				wochentag = inString.nextLine();
				if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3") || wochentag.equals("4")
						|| wochentag.equals("5") || wochentag.equals("6") || wochentag.equals("Montag")
						|| wochentag.equals("Dienstag") || wochentag.equals("Mittwoch")
						|| wochentag.equals("Donnerstag") || wochentag.equals("Freitag") || wochentag.equals("Samstag")
						|| wochentag.equals("Sonntag")) {
					break;
				} else {
					System.out.println(wochentag + " ist kein Wochentag");
					System.out.println("┌────────────────────────────────────────┐");
					System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");

					System.out.println("│andere Taste ➞ Eingabe wiederholen      │");
					System.out.println("│q ➞ Vorhergang beenden                  │");
					System.out.println("└────────────────────────────────────────┘");
					System.out.print("Ihre Eingabe ➞ ");
					String wochentagWied = inString.nextLine();
					switch (wochentagWied) {
					case "q":
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
				break;
			}
			boolean online = false;
			String onlineString;
			while (true) {
				System.out.println("Ist der Kurs online?");
				onlineString = inString.nextLine();
				if (onlineString.equals("true") || onlineString.equals("false")) {
					online = Boolean.parseBoolean(onlineString);
					break;
				} else {
					System.out.println(onlineString + " ist keine richtige Angabe");
					System.out.println("┌────────────────────────────────────────┐");
					System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
					System.out.println("│andere Taste ➞ Eingabe wiederholen      │");
					System.out.println("│q ➞ Vorhergang beenden                  │");

					System.out.println("└────────────────────────────────────────┘");
					System.out.print("Ihre Eingabe ➞ ");
					String onlineWied = inString.nextLine();
					switch (onlineWied) {
					case "q":
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
				break;
			}

			String ort = null;

			while (true) {
				if (online == false) {
					System.out.println("In welchem Raum findet der Kurs statt?");
					ort = inString.nextLine();
					if (ort.contains("/")) {
						break;
					} else {
						System.out.println(
								"Die Angabe war nicht richtig, das Gebäude muss mit einem '/' von der Raumnummer getrennt sein.");
						System.out.println("┌────────────────────────────────────────┐");
						System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
						System.out.println("│andere Taste ➞ Eingabe wiederholen      │");
						System.out.println("│q ➞ Vorhergang beenden                  │");

						System.out.println("└────────────────────────────────────────┘");
						System.out.print("Ihre Eingabe ➞ ");
						String onlineWied = inString.nextLine();
						switch (onlineWied) {
						case "q":
							wiederholen = 1;
							break;
						default:
							continue;
						}

					}
				}
				if (wiederholen == 1) {
					break;
				}

				else {

					System.out.println("Geben Sie den Link oder Plattform ein wo der Kurs gehalten wird ein.");
					ort = inString.nextLine();
				}

			}

			for (Professor prof : alleProfessoren) {
				System.out.println(prof.toStringsimple());

			}

			System.out.println("");

			System.out.println("┌────────────────────────────────────────┐");
			System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
			System.out.println("│0 ➞ Professor erstellen               	 │");
			System.out.println("│Professor-ID ➞ Professor einteilen    	 │");
			System.out.println("└────────────────────────────────────────┘");
			System.out.print("Ihre Eingabe ➞ ");

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
						if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3")
								|| wochentag.equals("4") || wochentag.equals("5") || wochentag.equals("6")) {
							int wochentagZahl = Integer.parseInt(wochentag);
							alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentagZahl, online, ort, profID));

						} else {
							alleKurse.add(new Kurs(kursName, uhrzeitZahl, wochentag, online, ort, profID));
						}
					} else {
						if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3")
								|| wochentag.equals("4") || wochentag.equals("5") || wochentag.equals("6")) {
							int wochentagZahl = Integer.parseInt(wochentag);
							alleKurse.add(new Kurs(kursName, uhrzeit, wochentagZahl, online, ort, profID));
						} else {
							alleKurse.add(new Kurs(kursName, uhrzeit, wochentag, online, ort, profID));
						}
					}
					erstellt = true;
				}

			}

			if (erstellt == false) {

				System.out
						.println("Der Kurs wurde nicht erstellt, da '" + profID + "' keine gültige Professor-ID ist.");

			}

			if (erstellt == true) {
				System.out.println("Der Kurs mit dem Namen '" + alleKurse.get(0).getKursname() + "' wurde erstellt.");
				einKurseinsotrieren();
				sortBlockeinheit();
			}
			endeVonSwitchCase();
			break;

		}

	}

	public static void kursloeschen() {

		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		System.out.println("Anhand welcher Eigenschaft soll ein Kurs gelöscht werden?");
		System.out.println("┌────────────────────────────────────────┐");
		System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
		System.out.println("│1 ➞ Name                                │");
		System.out.println("│2 ➞ Kurs-ID                             │");
		System.out.println("│andere Taste ➞ abbrechen                │");
		System.out.println("└────────────────────────────────────────┘");
		System.out.print("Ihre Eingabe ➞ ");

		String eingabe1 = inString.nextLine();

		switch (eingabe1) {

		case "1":
			System.out.println("Welcher Kurs soll gelöscht werden (Kursname)");
			eingabe1 = inString.nextLine();

			int p = 0;

			for (int i = 0; i < wochentage.size(); i++) {
				for (int z = 0; z < wochentage.get(i).size(); z++) {
					Kurs k = wochentage.get(i).get(z);
					if (k.getKursname().equals(eingabe1)) {
						p++;
					}
				}
			}

			if (p == 0) {
				System.out.println("Es wurde kein Kurs mit dem Namen " + eingabe1 + " gefunden");
			}
			if (p == 1) {
				for (int i = 0; i < wochentage.size(); i++) {
					for (int z = 0; z < wochentage.get(i).size(); z++) {
						Kurs k = wochentage.get(i).get(z);

						if (k.getKursname().equals(eingabe1)) {

							System.out.println("Der Kurs '" + k.getKursname() + "' wurde entfernt");
							wochentage.get(i).remove(z);
							break;

						}
					}
				}
			}

			if (p > 1) {
				for (int i = 0; i < wochentage.size(); i++) {
					for (int z = 0; z < wochentage.get(i).size(); z++) {
						Kurs k = wochentage.get(i).get(z);

						if (eingabe1.equals(k.getKursname())) {
							System.out.println(k.toStringforKonsole() + "\n");
						}
					}
				}

				System.out.println("Es wurden " + p + " Kurse gefunden bitte gebe die Kurs-ID ein:");
				int eingabe5 = inInt.nextInt();

				int entfernteKurse = 0;

				for (int i = 0; i < wochentage.size(); i++) {
					for (int z = 0; z < wochentage.get(i).size(); z++) {
						Kurs k = wochentage.get(i).get(z);

						if (k.getKursId() == eingabe5) {

							System.out.println("Der Kurs '" + k.getKursname() + "' mit der Kurs-ID '" + k.getKursId()
									+ "' wurde entfernt.");

							wochentage.get(i).remove(z);

							entfernteKurse++;

						}

					}
				}

				if (entfernteKurse == 0) {
					System.out.println("Es wurde kein Kurs mit dieser Kurs-ID gefunden.");
				}

			}

			break;

		case "2":

			for (int i = 0; i < wochentage.size(); i++) {
				for (int z = 0; z < wochentage.get(i).size(); z++) {
					Kurs k = wochentage.get(i).get(z);
					System.out.println(k.toStringforKonsole() + "\n");
				}
			}
			System.out.println("Welche Kurs-ID hat der Kurs den du löschen möchtest?");

			int eingabe2 = inInt.nextInt();

			int entfernteKurse = 0;

			for (int i = 0; i < wochentage.size(); i++) {
				for (int z = 0; z < wochentage.get(i).size(); z++) {
					Kurs k = wochentage.get(i).get(z);

					if (k.getKursId() == eingabe2) {

						wochentage.get(i).remove(z);

						entfernteKurse++;
						System.out.println("Der Kurs '" + k.getKursname() + "' wurde entfernt");

					}

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

		boolean existiert = false;

		for (Professor p : alleProfessoren) {
			if (p.getName().equals(name) && p.getVorname().equals(vorname)) {
				existiert = true;
			}
		}

		if (existiert == false) {
			alleProfessoren.add(new Professor(name, vorname, geschlecht, tel));
			System.out.println("Der Professor mit dem Namen '" + name + "' wurde erfolgreich erstellt.");
		} else {
			System.out.println(
					"Der Professor mit dem Namen '" + name + "' wurde nicht erstellt, da dieser bereits existiert");
		}

	}

	public static void professorloeschen() {
		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		int pIDgeloescht = 0;

		System.out.println("Anhand welcher Eigenschaft soll der Professor gelöscht werden?");

		System.out.println("┌────────────────────────────────────────┐");
		System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
		System.out.println("│1 ➞ Name                                │");
		System.out.println("│2 ➞ Professor-ID                        │");
		System.out.println("│andere Taste ➞ abbrechen                │");
		System.out.println("└────────────────────────────────────────┘");
		System.out.print("Ihre Eingabe ➞ ");

		String eingabe1 = inString.nextLine();

		switch (eingabe1) {

		case "1":
			System.out.println("Geben Sie den Nachnamen des Professors ein:");
			String eingabe2 = inString.nextLine();

			int p = 0;

			for (Professor p1 : alleProfessoren) {
				if (p1.getName().equals(eingabe2)) {

					p++;
				}
			}

			if (p == 0) {
				System.out.println("Es wurde kein Professor mit dem Namen " + eingabe2 + " gefunden");
			}

			if (p == 1) {
				for (Professor p1 : alleProfessoren) {
					if (p1.getName().equals(eingabe2)) {

						pIDgeloescht = p1.getProfId();
						System.out.println(p1.toStringsimple() + " wurde gelöscht");
						alleProfessoren.remove(p1);
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

						System.out.println(p1.toStringsimple() + " wurde gelöscht");
						pIDgeloescht = p1.getProfId();
						alleProfessoren.remove(p1);
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
			boolean gefunden = false;

			for (int x = 0; x < alleProfessoren.size(); x++) {
				// Kurs a = e.next();
				Professor prof = alleProfessoren.get(x);

				if (prof.getId() == eingabe4) {
					// e.remove();
					pIDgeloescht = eingabe4;
					alleProfessoren.remove(x);
					gefunden = true;
					System.out.println("Der Professor wurde erfolgreich gelöscht");
				}

			}
			if (gefunden == false) {
				System.out.println("Der Professor wurde nicht gelöscht, da diese ID nicht hinterlegt ist.");
			}

		}

		int gefundeneKurse = 0;

		for (int i = 0; i < wochentage.size(); i++) {
			for (int z = 0; z < wochentage.get(i).size(); z++) {
				Kurs k = wochentage.get(i).get(z);
				if (k.getPID() == pIDgeloescht) {
					gefundeneKurse++;
				}
			}
		}

		if (gefundeneKurse > 0) {

			System.out.println("Sollen auch die Kurse von dem Professor gelöscht werden?");
			System.out.println("┌────────────────────────────────────────┐");
			System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
			System.out.println("│1 ➞ ja                                  │");
			System.out.println("│andere Taste ➞ nein                     │");
			System.out.println("└────────────────────────────────────────┘");
			System.out.print("Ihre Eingabe ➞ ");

			String eingabe2 = inString.nextLine();

			if (eingabe2.equals("1")) {
				int geloeschteKurse = 0;
				for (int i = 0; i < wochentage.size(); i++) {
					for (int z = 0; z < wochentage.get(i).size(); z++) {
						Kurs k = wochentage.get(i).get(z);
						if (k.getPID() == pIDgeloescht) {
							wochentage.get(i).remove(z);
							geloeschteKurse++;

						}
					}
				}
				System.out.println("Es wurde(n) " + geloeschteKurse + " Kurs(e) gelöscht.");
			}

		}

	}

	public static void professorSuchen() {
		Scanner inString = new Scanner(System.in);
		Scanner inInt = new Scanner(System.in);

		System.out.println("Anhand welcher Eigenschaft soll der Professor gesucht werden?");

		System.out.println("┌────────────────────────────────────────┐");
		System.out.println("│Wählen Sie unter folgenen Möglichkeiten:│");
		System.out.println("│1 ➞ Name                                │");
		System.out.println("│2 ➞ Professor-ID                        │");
		System.out.println("│andere Taste ➞ abbrechen                │");
		System.out.println("└────────────────────────────────────────┘");
		System.out.print("Ihre Eingabe ➞ ");

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
			boolean gefunden = false;

			for (int x = 0; x < alleProfessoren.size(); x++) {
				// Kurs a = e.next();
				Professor prof = alleProfessoren.get(x);

				if (prof.getId() == eingabe4) {

					System.out.println("Name: " + alleProfessoren.get(x).getName() + " "
							+ alleProfessoren.get(x).getVorname() + " Geschlecht: "
							+ alleProfessoren.get(x).getGeschlecht() + " E-Mail: " + alleProfessoren.get(x).geteMail());
					gefunden = true;
					break;
				}

			}
			if (gefunden == false) {
				System.out.println("Es wurde kein Professor gefunden, da diese ID nicht hinterlegt ist.");
			}

		}

	}
}
