package pk.demo.roshambo.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Stroke;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringComponent
@UIScope
public class GameSession {

	private String uid = UUID.randomUUID().toString();
	private GameEngine gameEngine;

	List<Stroke> strokes = new ArrayList<>();

	public GameSession(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public void newStroke(Handsign playerOne, Handsign playerTwo) {
		Stroke stroke = new Stroke(uid, new Date(), playerOne, playerTwo);
		gameEngine.evaluateStroke(stroke);
		strokes.add(stroke);
	}

	public void newStroke(Handsign playerTwo) {
		Handsign playerOne = gameEngine.getRandomSign();
		Stroke stroke = new Stroke(uid, new Date(), playerOne, playerTwo);
		gameEngine.evaluateStroke(stroke);
		strokes.add(stroke);
	}

	public List<Stroke> getStrokes() {
		return strokes;
	}
}
