
public class ExceptionUhrzeit extends Exception {
	public ExceptionUhrzeit(String message) {
		super(message);
	}

	public ExceptionUhrzeit() {
		super("Dieser Uhrzeit ist nicht möglich");
	}
}
