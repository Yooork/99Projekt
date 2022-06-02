
public class Professor {
	private static int zaehler = 1;
	private String name;
	private String vorname;
	private String geschlecht;
	private long tel;
	private String eMail;
	private int profId;

	public Professor(String name, String vorname, String geschlecht, long tel) {
		this.name = name;
		this.vorname = vorname;
		this.geschlecht = geschlecht;
		this.tel = tel;
		eMail = eMail(vorname, name);
		profId = zaehler++;

	}

	public String eMail(String vorname, String name) {
		return "vorname.nachname@hft-stuttgart.de";
	}

	public String toStringtoTxt() {
		return name + ";" + vorname + ";" + geschlecht + ";" + tel + ";" + eMail + ";" + profId;
	}

	public String toStringsimple() {
		return "Name: " + name + " " + vorname + " Geschlecht: " + geschlecht + " Professor-ID: " + profId;
	}

	@Override
	public String toString() {
		return "Professor [name=" + name + ", vorname=" + vorname + ", geschlecht=" + geschlecht + ", tel=" + tel
				+ ", eMail=" + eMail + ", profId=" + profId + "]";
	}

	public static int getZaehler() {
		return zaehler;
	}

	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public long getTel() {
		return tel;
	}

	public String geteMail() {
		return eMail;
	}

	public int getProfId() {
		return profId;
	}

	public int getId() {
		return profId;
	}

}
