package pk.demo.roshambo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Stroke {

	private String uid;
	private Date timestamp;
	private Handsign playerOne;
	private Handsign playerTwo;
	private StrokeResult result;

	public Stroke(String uid, Date timestamp, Handsign playerOne, Handsign playerTwo) {
		this.uid = uid;
		this.timestamp = timestamp;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
}
