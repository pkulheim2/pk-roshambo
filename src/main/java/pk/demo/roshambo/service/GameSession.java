package pk.demo.roshambo.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringComponent
@UIScope
public class GameSession implements IGameSession {

	private String uid = UUID.randomUUID().toString();
	private IGameEngine gameEngine;

	List<Round> strokes = new ArrayList<>();

	public GameSession(IGameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	@Override public List<Round> newStroke(Handsign playerOne, Handsign playerTwo) {
		Round stroke = new Round(uid, new Date(), playerOne, playerTwo);
		stroke.setResult(gameEngine.postRound(stroke));
		strokes.add(stroke);
		return strokes;
	}

	@Override public List<Round> newStroke(Handsign playerTwo) {
		Handsign playerOne = gameEngine.getRandomSign();
		Round stroke = new Round(uid, new Date(), playerOne, playerTwo);
		stroke.setResult(gameEngine.postRound(stroke));
		strokes.add(stroke);
		return strokes;
	}

	@Override public List<Round> getStrokes() {
		return strokes;
	}

	@Override public List<Round> resetSession() {
		strokes = new ArrayList<>();
		return strokes;
	}
}
