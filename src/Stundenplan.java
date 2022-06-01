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

	static ArrayList<Kurs> alleKurse = new ArrayList<Kurs>();

	public static void main(String[] args) {

		Scanner inInt = new Scanner(System.in);
		Scanner inString = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		
		

		
		
		ArrayList<Professor> alleProfessoren = new ArrayList<Professor>();
		
		
			alleProfessoren.add(new Professor("Pado", "Ulrike", "Weiblich", 4971189262811l));
			
			professorenVonDateieinlesen(alleProfessoren);
		
		
		
		
		alleKurse.add(new Kurs("Programmieren 1", "9:45 Uhr - 11:15 Uhr", 1, false, 1));
		alleKurse.add(new Kurs("Programmieren 2", "8:00 Uhr - 9:30 Uhr", 2, true, 1));
		alleKurse.add(new Kurs("Programmieren 3", 1, 3, false, 1));
		alleKurse.add(new Kurs("Programmieren 4 dummi", 1, 4, true, 1));
		alleKurse.add(new Kurs("Programmieren 1", 1, 4, true, 1));

		kurseVonDateieinlesen(alleKurse);

		ArrayList<Kurs> montag = new ArrayList<Kurs>();
		ArrayList<Kurs> dienstag = new ArrayList<Kurs>();
		ArrayList<Kurs> mittwoch = new ArrayList<Kurs>();
		ArrayList<Kurs> donnerstag = new ArrayList<Kurs>();
		ArrayList<Kurs> freitag = new ArrayList<Kurs>();
		ArrayList<Kurs> samstag = new ArrayList<Kurs>();
		
		ArrayList<ArrayList<Kurs> > wochentage = new ArrayList<ArrayList<Kurs>>();

		kurseEinsotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);

		allesotieren(wochentage);

		while (true) {
			Scanner eingabesc = new Scanner(System.in);

			// https://de.piliapp.com/symbol/line/
			System.out.println("┌────────────────────────────────────────┐");
			System.out.println("│Drücke:                                 │");
			System.out.println("│1 ➞ alle Kurse ausgeben                 │");
			System.out.println("│2 ➞ Kurs suchen - WIP                   │");
			System.out.println("│3 ➞ Kurs erstellen                      │");
			System.out.println("│4 ➞ Stundenplan als Datei ausgeben      │");
			System.out.println("│5 ➞ Professor erstellen                 │");
			System.out.println("│6 ➞ alle Professoren ausgeben           │");
			System.out.println("│7 ➞ Kurs löschen - WIP                  │");
			System.out.println("│8 ➞ Professor löschen - WIP             │");
			System.out.println("│q ➞ Programm beenden                    │");
			System.out.println("└────────────────────────────────────────┘");
			System.out.print("Ihre Eingabe ➞ ");

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

				int wiederholen = 0;
				String uhrzeit;
				while (true) { // Schleife falls die Uhrzeit falsch eingegeben wird, kann man die Uhrzeit nochmals neu eingeben
					System.out.println("Welche Uhrzeit");	
					uhrzeit = inString.nextLine();
					if (uhrzeit.equals("1") || uhrzeit.equals("2") || uhrzeit.equals("3") || uhrzeit.equals("4")|| uhrzeit.equals("5") || uhrzeit.equals("6") ||
						uhrzeit.equals("8:00 Uhr - 9:30 Uhr")||uhrzeit.equals("9:45 Uhr - 11:15 Uhr")||uhrzeit.equals("11:30 Uhr - 13:00 Uhr")||uhrzeit.equals("14:00 Uhr - 15:30 Uhr")||uhrzeit.equals("15:45 Uhr - 17:15 Uhr")||uhrzeit.equals("17:30 Uhr - 19:00 Uhr")) {
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
					break;
				}
				
				String wochentag;
				while (true) { // Schleife falls der Wochentag falsch eingegeben wird, kann man den Wochentag nochmals neu eingeben
					System.out.println("Welcher Wochentag");
					wochentag = inString.nextLine();
					if (wochentag.equals("1") || wochentag.equals("2") || wochentag.equals("3") || wochentag.equals("4")|| wochentag.equals("5") || wochentag.equals("6")
						|| wochentag.equals("Montag")|| wochentag.equals("Dienstag")|| wochentag.equals("Mittwoch")|| wochentag.equals("Donnerstag")|| wochentag.equals("Freitag")|| wochentag.equals("Samstag")|| wochentag.equals("Sonntag")) {
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
					if (wiederholen == 1){} {
						break;
					}
				}
				if (wiederholen == 1) {
					break;
				}
				
				System.out.println("Online?");
				boolean online = inInt.nextBoolean();
				
				System.out.println("Welcher Professor hält den Kurs?\n");
				
				
				
				for (Professor prof : alleProfessoren) {
					System.out.println(prof.toStringsimple());

				}
				
				System.out.println("Geben Sie die richtige Professor-ID ein die Sie für den Kurs einteilen möchten oder geben Sie '0' ein um einen Professor zu erstellen: ");
				
				int profID = inInt.nextInt();
				
		
				
				if (profID==0) {
					professorErstellen(alleProfessoren);
					
					System.out.println("Welcher Professor hält den Kurs?\n");
					
					
					
					for (Professor prof : alleProfessoren) {
						System.out.println(prof.toStringsimple());
						
						

					}
					profID = inInt.nextInt();
				}
				
			
					boolean erstellt =false;
				
				for (Professor prof: alleProfessoren) {
					if(prof.getId()==profID) {
						
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
						erstellt=true;
					}
					
					
				}
				
				if (erstellt==false) {
					
					System.out.println("Der Kurs wurde nicht erstellt, da '"+profID+ "' keine gÃ¼ltige Professor-ID ist.");
				
			}
				
				
				
				

				kurseinWochentage(montag, dienstag, mittwoch, donnerstag, freitag, samstag, wochentage);
				kurseEinsotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);
				endeVonSwitchCase();
				break;

			case "q":
				kurseInDateiausgeben(alleKurse);
				professorenInDateiausgeben(alleProfessoren);
				System.exit(0);
				break;

			case "4":
				System.out.println("Welcher Dateiname soll der Stundenplan haben?");
				String dateiname = sc3.nextLine();
				kurseinWochentage(montag, dienstag, mittwoch, donnerstag, freitag, samstag, wochentage);
				dateiAusgeben(wochentage, dateiname);
				endeVonSwitchCase();
				break;
			case "5":
				professorErstellen(alleProfessoren);
				endeVonSwitchCase();
				break;
				
			case "6":
				
				for(Professor p: alleProfessoren) {
					System.out.println(p.toStringsimple());
				}
				endeVonSwitchCase();
				break;
				
			case "7":
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
						endeVonSwitchCase();
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
			professorenInDateiausgeben(alleProfessoren);
			kurseinWochentage(montag, dienstag, mittwoch, donnerstag, freitag, samstag, wochentage);
			
			
			
			
			
			
			
			
		}

	}

	public static void allesotieren(ArrayList<ArrayList<Kurs>> wochentage) {
		//Nach Blockeinheit sortieren

		Collections.sort(wochentage, new Comparator<ArrayList<Kurs>>() {



			@Override
			public int compare(ArrayList<Kurs> o1, ArrayList<Kurs> o2) {
				return 0;
			}
		});


	}

	public static void ausgeben(ArrayList<Kurs> k) {
		for (Kurs kurs : k) {
			System.out.println(kurs.toStringfuerKonsole());

		}
	}

	public static void dateiAusgeben(ArrayList<ArrayList<Kurs>> wochentage, String dateiname) {

		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/" + dateiname + ".txt"), false))) {
			String online;
			boolean ausgabe = false;
			

			for (ArrayList<Kurs> kurs : wochentage) {
				
				dateiSchreiber.write("-----"+"-----\n");

				dateiSchreiber.write(kurs.toString());
				
				
				ausgabe = true;

			}

			if (ausgabe == false) {
				dateiSchreiber.write("Vorlesungsfreier Tag\n\n");
			}

			ausgabe = false;

//			dateiSchreiber.write("-----Dienstag-----\n");
//			for (Kurs kurs : dienstag) {
//
//				dateiSchreiber.write(kurs.toString());
//				ausgabe = true;
//
//			}
//			if (ausgabe == false) {
//				dateiSchreiber.write("Vorlesungsfreier Tag\n\n");
//			}
//
//			ausgabe = false;
//
//			dateiSchreiber.write("-----Mittwoch-----\n");
//
//			for (Kurs kurs : mittwoch) {
//
//				dateiSchreiber.write(kurs.toString());
//				ausgabe = true;
//
//			}
//
//			if (ausgabe == false) {
//				dateiSchreiber.write("Vorlesungsfreier Tag\n\n");
//			}
//
//			ausgabe = false;
//
//			dateiSchreiber.write("-----Donnerstag-----\n");
//
//			for (Kurs kurs : donnerstag) {
//
//				dateiSchreiber.write(kurs.toString());
//				ausgabe = true;
//
//			}
//
//			if (ausgabe == false) {
//				dateiSchreiber.write("Vorlesungsfreier Tag\n\n");
//			}
//
//			ausgabe = false;
//			dateiSchreiber.write("-----Freitag-----\n");
//
//			for (Kurs kurs : freitag) {
//
//				dateiSchreiber.write(kurs.toString());
//				ausgabe = true;
//
//			}
//
//			if (ausgabe == false) {
//				dateiSchreiber.write("Vorlesungsfreier Tag\n\n");
//			}
//
//			ausgabe = false;
//
//			dateiSchreiber.write("-----Samstag-----\n");
//
//			for (Kurs kurs : samstag) {
//
//				dateiSchreiber.write(kurs.toString());
//				ausgabe = true;
//
//			}
//
//			if (ausgabe == false) {
//				dateiSchreiber.write("Vorlesungsfreier Tag\n\n");
//			}
//
//			ausgabe = false;
//
//			System.out.println("Datei wurde erfolgreich erstellt");

		} catch (IOException e) {

		}
	}

	public static void suchen(ArrayList<Kurs> alleKurse, String eingabe) {

		int i = 0;
		int x=0;
		
		
		for (Kurs k : alleKurse) {

			if (k.getKursname().equals(eingabe)) {
				x++;
			}
		}
			
			
			if(x>1) {

		
		Scanner eingabe7 = new Scanner(System.in);
		
		System.out.println("Gebe 1 ein um den nächsten Termin anzuzeigen oder eine andere Taste um alle auszugeben");
		
		int in1 = eingabe7.nextInt();
		
		if(in1==1) {
			
		
			
			Date date = new Date();
		      System.out.println(date.getDay());
			
			
			switch(date.getDate()){
			
		    case 1:
		        System.out.println("Montag");
		        break;

		    case 2:
		        
		        //usw
			
			
			case 3:
				System.out.println("dsf");
			}	
			
		}
			
			
			
			
		}
		else {
			
		}
			
			
			if(x==1) {
		
		
		for (Kurs e : alleKurse) {

			if (e.getKursname().equals(eingabe)) {
				System.out.println(
						e.getKursname() + " findet um " + e.getBlockUhrzeit() + " am " + e.getWochentag() + " statt.");
				i++;
			}

		}
			}
		if (x == 0) {
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
					System.out.println("Der Kurs " + alleKurse.get(x).getKursname() + " wurde nicht erstellt, da " + alleKurse.get(x).getWochentag()
							+ " um " + alleKurse.get(x).getBlockUhrzeit() + " bereits belegt ist.");

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
				dateiSchreiber.write(k.toStringtoTxt());
			}

		} catch (IOException e) {

		}

	}

	public static void endeVonSwitchCase() {
		Scanner eingabesc2 = new Scanner(System.in);
		System.out.println("\nUm das Programm zubeenden 'q' eingeben andernfalls andere Eingabe tätigen");
		String eingabeSwitch = eingabesc2.nextLine();

		if (eingabeSwitch.equals("q")) {
			System.exit(0);
		}

	}

	public static void professorErstellen(ArrayList<Professor> alleProfessoren) {
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
	
	
	public static void professorenInDateiausgeben(ArrayList<Professor> alleProfessoren) {
		try (BufferedWriter dateiSchreiber = new BufferedWriter(
				new FileWriter(new File("/Users/york/Desktop/alleProfessoren.txt"), false))) {

			for (Professor p : alleProfessoren) {
				dateiSchreiber.write(p.toStringtoTxt() + "\n");
			}

		} catch (IOException e) {

		}

	}
	
	public static void professorenVonDateieinlesen(ArrayList<Professor> alleProfessoren) {

		try (BufferedReader dateiLeser = new BufferedReader(
				new FileReader(new File("/Users/york/Desktop/alleProfessoren.txt")))) {
			String zeile;

			String[] z;

			while ((zeile = dateiLeser.readLine()) != null) {

				z = zeile.split(";");

				boolean professornameexistiert = false;
				for (Professor i : alleProfessoren) {

					if (Integer.parseInt(z[5])==(i.getId())) {
						professornameexistiert = true;
					}
				}

				if (professornameexistiert == false) {
					alleProfessoren.add(new Professor(z[0],z[1],z[2],Long.parseLong(z[3])));
				}
			}
		} catch (IOException e) {
			System.out.println("Es wurden keine Professoren eingelesen");
		}
	}
	
	public static void kurseinWochentage(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag,
			ArrayList<Kurs> mittwoch, ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag, ArrayList<ArrayList<Kurs>>wochentage) {
		wochentage.add(montag);
		wochentage.add(dienstag);
		wochentage.add(mittwoch);
		wochentage.add(donnerstag);
		wochentage.add(freitag);
		wochentage.add(samstag);
	}
	
	
	
	
	

}
