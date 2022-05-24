
public class Kurs {
	private String kursname;
	private Integer blockeinheit;
	private String blockUhrzeit;
	private String wochentag;
	private int wochentagZahl;
	private boolean online;

	public Kurs(String kursname, int blockeinheit, String wochentag, boolean online, Professor professor) {
		this.kursname = kursname;
		this.blockeinheit = blockeinheit;
		this.wochentag = wochentag;
		this.online = online;

		try {
			blockUhrzeit = blockeinheitInBlockUhrzeit(blockeinheit);
		} catch (ExceptionUhrzeit e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wochentagZahl = WochentagInwochentagZahl(wochentag);
		} catch (ExecptionTag e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Kurs(String kursname, int blockeinheit, int wochentagZahl, boolean online, Professor professor) {
		this.kursname = kursname;
		this.blockeinheit = blockeinheit;
		this.wochentagZahl = wochentagZahl;
		this.online = online;

		try {
			blockUhrzeit = blockeinheitInBlockUhrzeit(blockeinheit);
		} catch (ExceptionUhrzeit e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wochentag = wochentagZahlInWochentag(wochentagZahl);
		} catch (ExecptionTag e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Kurs(String kursname, String blockUhrzeit, String wochentag, boolean online, Professor professor) {
		this.kursname = kursname;
		this.blockUhrzeit = blockUhrzeit;
		this.wochentag = wochentag;
		this.online = online;

		try {
			wochentagZahl = WochentagInwochentagZahl(wochentag);
		} catch (ExecptionTag e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			blockeinheit = blockUhrzeitInBlockeinheit(blockUhrzeit);
		} catch (ExceptionUhrzeit e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Kurs(String kursname, String blockUhrzeit, int wochentagZahl, boolean online, Professor professor) {
		this.kursname = kursname;
		this.blockUhrzeit = blockUhrzeit;
		this.wochentagZahl = wochentagZahl;
		this.online = online;

		try {
			wochentag = wochentagZahlInWochentag(wochentagZahl);
		} catch (ExecptionTag e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blockeinheit = blockUhrzeitInBlockeinheit(blockUhrzeit);
		} catch (ExceptionUhrzeit e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getKursname() {
		return kursname;
	}

	public Integer getBlockeinheit() {
		return blockeinheit;
	}

	public int getWochentagZahl() {
		return wochentagZahl;
	}

	public void setBlockeinheit(int blockeinheit) {
		this.blockeinheit = blockeinheit;
	}

	@Override
	public String toString() {
		return "Kurs [kursname=" + kursname + ", blockeinheit=" + blockeinheit + ", blockUhrzeit=" + blockUhrzeit
				+ ", wochentag=" + wochentag + ", wochentagZahl=" + wochentagZahl + ", online=" + online + "]";
	}

	private String blockeinheitInBlockUhrzeit(int blockeinheit) throws ExceptionUhrzeit {
		if (blockeinheit <= 6 && blockeinheit > 0) {
			switch (blockeinheit) {
			case 1:
				blockUhrzeit = ("8:00 Uhr - 9:30 Uhr");
				break;
			case 2:
				blockUhrzeit = ("9:45 Uhr - 11:15 Uhr");
				break;
			case 3:
				blockUhrzeit = ("11:30 Uhr - 13:00 Uhr");
				break;
			case 4:
				blockUhrzeit = ("14:00 Uhr - 15:30 Uhr");
				break;
			case 5:
				blockUhrzeit = ("15:45 Uhr - 17:15 Uhr");
				break;
			case 6:
				blockUhrzeit = ("17:30 Uhr - 19:00 Uhr");
				break;
			default:
				// geht nicht
				break;
			}
		}

		else {
			throw new ExceptionUhrzeit("Die Blockeinheit/Blockuhrzeit existiert nicht");
		}
		return blockUhrzeit;
	}

	private String wochentagZahlInWochentag(int wochentagZahl) throws ExecptionTag {

		if (wochentagZahl > 0 && wochentagZahl <= 7) {

			switch (wochentagZahl) {
			case 1:
				wochentag = ("Montag");
				break;
			case 2:
				wochentag = ("Dienstag");
				break;
			case 3:
				wochentag = ("Mittwoch");
				break;
			case 4:
				wochentag = ("Donnerstag");
				break;
			case 5:
				wochentag = ("Freitag");
				break;
			case 6:
				wochentag = ("Samstag");
				break;
			case 7:
				throw new ExecptionTag("Sonntags haben wir keine Vorlesung.");
			default:
				// geht nicht
				break;
			}

		} else {
			if (wochentagZahl < 0) {
				throw new ExecptionTag("Es können nur positive Zahlen verwendet werden.");
			}
			if (wochentagZahl > 7) {
				throw new ExecptionTag("Die Woche hat nicht mehr als sieben Wochentage.");
			}
		}

		return wochentag;
	}

	public String getBlockUhrzeit() {
		return blockUhrzeit;
	}

	public String getWochentag() {
		return wochentag;
	}

	public boolean isOnline() {
		return online;
	}

	private int WochentagInwochentagZahl(String wochentag) throws ExecptionTag {
		switch (wochentag) {
		case "Montag":
			wochentagZahl = 1;
			break;
		case "Dienstag":
			wochentagZahl = 2;
			break;
		case "Mittwoch":
			wochentagZahl = 3;
			break;
		case "Donnerstag":
			wochentagZahl = 4;
			break;
		case "Freitag":
			wochentagZahl = 5;
			break;
		case "Samstag":
			wochentagZahl = 6;
			break;
		case "Sonntag":
			throw new ExecptionTag("Sonntags ist keine Vorlesung möglich.");
		default:
			throw new ExecptionTag("Diesen Wochentag gibt es nicht");
		}
		return wochentagZahl;
	}

	public int blockUhrzeitInBlockeinheit(String blockUhrzeit) throws ExceptionUhrzeit {
		switch (blockUhrzeit) {
		case "8:00 Uhr - 9:30":
			blockeinheit = 1;
			break;

		case "9:45 Uhr - 11:15 Uhr":
			blockeinheit = 2;
			break;

		case "11:30 Uhr - 13:00 Uhr":
			blockeinheit = 3;
			break;

		case "14:00 Uhr - 15:30 Uhr":
			blockeinheit = 4;
			break;

		case "15:45 Uhr - 17:15 Uhr":
			blockeinheit = 5;
			break;

		case "17:30 Uhr - 19:00 Uhr":
			blockeinheit = 6;
			break;
		default:
			throw new ExceptionUhrzeit(
					"Die Uhrzeit ist entweder kein Block oder im falschen Format. Formatbeispiel: 8:00 Uhr - 9:30 Uhr");
		}

		return blockeinheit;

	}
}
