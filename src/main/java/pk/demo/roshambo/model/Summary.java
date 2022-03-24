package pk.demo.roshambo.model;

import lombok.Data;

@Data
public class Summary {

	private int roundsTotal;
	private int winsPlayerOne;
	private int winsPlayerTwo;
	private int draws;

	public void addRoundsTotal(int toAdd) {
		this.roundsTotal += toAdd;
	}

	public void addWinsPlayerOne(long toAdd) {
		this.winsPlayerOne += toAdd;
	}

	public void addWinsPlayerTwo(long toAdd) {
		this.winsPlayerTwo += toAdd;
	}

	public void addDraws(long toAdd) {
		this.draws += toAdd;
	}
}
