
public class Professor {
	private String name;
	private String vorname;
	private String geschlecht;
	private long tel;
	private String eMail;
	private static int id = 0;

	public Professor(String name, String vorname, String geschlecht, long tel) {
		this.name = name;
		this.vorname = vorname;
		this.geschlecht = geschlecht;
		this.tel = tel;
		eMail = eMail(vorname, name);
		id++;
	}

	public String eMail(String vorname, String name) {
		return "vorname.nachname@hft-stuttgart.de";
	}

	
	public String toStringtoTxt() {
		return name+":"+vorname+":"+geschlecht+":"+ tel+ ":" + eMail+";";
	}

	public int getId() {
		return id;
	}

}
