import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



public class Stundenplan {

	public static void main(String[] args) {
		
		
		
		Professor p1 = new Professor("Pado", "Ulrike", "Weiblich", 4971189262811l);

		ArrayList<Kurs> alleKurse = new ArrayList<Kurs>();
		alleKurse.add(new Kurs("Programmieren 2", "9:45 Uhr - 11:15 Uhr", 4, false, p1));
		alleKurse.add(new Kurs("Programmieren 5", 1, 4, true, p1));
		alleKurse.add(new Kurs("Programmieren 1", 1, 4, false, p1));
		//alleKurse.add(new Kurs("Programmieren 2", 11, "Sonntag", true, p1));

		ArrayList<Kurs> montag = new ArrayList<Kurs>();
		ArrayList<Kurs> dienstag = new ArrayList<Kurs>();
		ArrayList<Kurs> mittwoch = new ArrayList<Kurs>();
		ArrayList<Kurs> donnerstag = new ArrayList<Kurs>();
		ArrayList<Kurs> freitag = new ArrayList<Kurs>();
		ArrayList<Kurs> samstag = new ArrayList<Kurs>();
		
		
		
		

		for (Kurs e : alleKurse) {
			if (e.getBlockUhrzeit() != null && e.getWochentagZahl() != 0) {

				switch (e.getWochentagZahl()) {

				case 1:
					montag.add(e);
					break;
				case 2:
					dienstag.add(e);
					break;
				case 3:
					mittwoch.add(e);
					break;
				case 4:
					donnerstag.add(e);
					break;
				case 5:
					freitag.add(e);
					break;
				case 6:
					samstag.add(e);
					break;
				default:

					break;
				}
			} else {
				System.out.println(
						"Der Kurs " + e.getKursname() + " ist fehlerhaft und wird nicht zum Stundenplan hinzugefügt");
				// alleKurse.remove(e);
			}

		}

		allesotieren(montag, dienstag, mittwoch, donnerstag, freitag, samstag);

		
		
		
		
		
		Scanner in = new Scanner(System.in);
		System.out.print("Drücke 1 um alle Kurse auszugeben und 2 um einen Kurs zu suchen: \n");
		while (true){
			switch(in.nextInt()){
			case 1:
				System.out.println();
				ausgeben(montag);
				ausgeben(dienstag);
				ausgeben(mittwoch);
				ausgeben(donnerstag);
				ausgeben(freitag);
				ausgeben(samstag);
				break;	
			case 2:
				System.out.println();
				System.out.println("Welcher Kurs soll gesucht werden? (Kursname)");
				String eingabe = in.nextLine();
				suchen(alleKurse, eingabe);
				break;
			case 3:
				Scanner in2 = new Scanner(System.in);
				System.out.println("Kursname");
				String kursName = in2.nextLine();
				System.out.println("Welche Uhrzeit");
				String uhrzeit = in2.nextLine();
				System.out.println("Welcher Wochentag");
				String wochentag = in2.nextLine();
				
				
				if (uhrzeit.equals("1") || uhrzeit.equals("2") || uhrzeit.equals("3") || uhrzeit.equals("4") || uhrzeit.equals("5")) {
					System.out.println(uhrzeit);
					int bla = Integer.parseInt(uhrzeit);
					System.out.println(bla+1);
				}
				
				System.out.println("dhjewesk");
				
				break;
//				alleKurse.add()
			}
		break;
		}

	}

	public static void allesotieren(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag, ArrayList<Kurs> mittwoch,
			ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {
		// Nach Blockeinheit sotieren
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
		
		dateiAusgeben(montag, dienstag, mittwoch, donnerstag, freitag, samstag);

	}

	public static void ausgeben(ArrayList<Kurs> k) {
		for (Kurs kurs : k) {
			System.out.println(kurs);
		
		}
	}
	
	public static void dateiAusgeben(ArrayList<Kurs> montag, ArrayList<Kurs> dienstag, ArrayList<Kurs> mittwoch, ArrayList<Kurs> donnerstag, ArrayList<Kurs> freitag, ArrayList<Kurs> samstag) {
		
		try (BufferedWriter dateiSchreiber = new BufferedWriter(new FileWriter (new File("/Users/york/Desktop/text.txt"),false))){
			String online;
			String kursname;
			String blockUhrzeit;
			
			for (Kurs kurs : montag) {
			dateiSchreiber.write("-----Montag-----");
			
			kursname=kurs.getKursname();
			
			
				
				//dateiSchreiber.write(.getKursname());
				
				if(kurs.isOnline())
				{
					 online = "Der Kurs findet online statt";
				}
				else {
					 online = "Der Kurs findet in präsenz statt";
				}
				dateiSchreiber.write(online);
				dateiSchreiber.write("Professor: ");
			
			}
			//dateiSchreiber.write(kursname);
			
			
	}
		catch(IOException e) {
			
		}
}
	
	public static void suchen(ArrayList<Kurs> alleKurse, String eingabe) {

		int i=0;
		for (Kurs e: alleKurse) {
			
			if (e.getKursname().equals(eingabe)) {
				System.out.println(e.getKursname()+" findet um "+e.getBlockUhrzeit()+" am "+e.getWochentag()+" statt.");
				i++;
			}
			
		}
		if (i==0) {
			System.out.println("Kurs gibt es nicht!");
		}
	}
}
