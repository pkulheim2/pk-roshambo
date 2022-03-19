package pk.demo.roshambo.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Stroke;
import pk.demo.roshambo.model.StrokeResult;

import java.util.*;

@SpringComponent
public class GameEngine {

	private final Random rand = new Random();
	private final Map<String, List<Stroke>> globalStrokes = new HashMap<>();


	public Handsign getRandomSign() {
		int sign = rand.nextInt(3);
		switch (sign) {
			case 0: return Handsign.ROCK;
			case 1: return Handsign.PAPER;
			case 2: return Handsign.SCISSORS;
			default:
				throw new IllegalStateException();
		}
	}

	public void evaluateStroke(Stroke stroke) {
		stroke.setResult(evaluateResult(stroke.getPlayerOne(), stroke.getPlayerTwo()));
		if (globalStrokes.containsKey(stroke.getUid())) {
			globalStrokes.get(stroke.getUid()).add(stroke);
		} else {
			List<Stroke> strokes = new ArrayList();
			strokes.add(stroke);
			globalStrokes.put(stroke.getUid(), strokes);
		}
	}

	private StrokeResult evaluateResult(Handsign playerOne, Handsign playerTwo) {
		if (playerOne.equals(playerTwo)) {
			return StrokeResult.DRAW;
		} else {
			if (playerOne.getValue()+1 == playerTwo.getValue() ||
					(playerOne.equals(Handsign.SCISSORS) &&
							playerTwo.equals(Handsign.ROCK))) {
				return StrokeResult.WIN_TWO;
			} else {
				return StrokeResult.WIN_ONE;
			}
		}
	}

}
