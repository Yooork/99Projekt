
public class Professor {
	private static int zaehler = 0;
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
		profId=zaehler++;
		
		
	}

	public String eMail(String vorname, String name) {
		return "vorname.nachname@hft-stuttgart.de";
	}

	public String toStringtoTxt() {
		return name + ":" + vorname + ":" + geschlecht + ":" + tel + ":" + eMail + ";";
	}

	@Override
	public String toString() {
		return "Professor [name=" + name + ", vorname=" + vorname + ", geschlecht=" + geschlecht + ", tel=" + tel
				+ ", eMail=" + eMail + ", profId=" + profId + "]";
	}

	public int getId() {
		return profId;
	}

}
