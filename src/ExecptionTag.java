
public class ExecptionTag extends Exception {
	public ExecptionTag(String message) {
		super(message);
	}

	public ExecptionTag() {
		super("Dieser Tag ist nicht möglich");
	}
}
