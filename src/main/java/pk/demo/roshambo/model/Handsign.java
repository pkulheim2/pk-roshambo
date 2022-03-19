package pk.demo.roshambo.model;

public enum Handsign {
	ROCK(0), PAPER(1), SCISSORS(2);

	private final int value;

	Handsign(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
