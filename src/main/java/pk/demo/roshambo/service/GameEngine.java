package pk.demo.roshambo.service;

import com.vaadin.flow.spring.annotation.SpringComponent;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;
import pk.demo.roshambo.model.RoundResult;
import pk.demo.roshambo.model.Summary;

import java.util.*;

@SpringComponent
public class GameEngine implements IGameEngine {

	private final Random rand = new Random();
	private Map<String, List<Round>> globalRounds = new HashMap<>();


	@Override public Handsign getRandomSign() {
		int sign = rand.nextInt(3);
		switch (sign) {
			case 0: return Handsign.ROCK;
			case 1: return Handsign.PAPER;
			case 2: return Handsign.SCISSORS;
			default:
				throw new IllegalStateException();
		}
	}

	@Override public void resetEngine() {
		globalRounds = new HashMap<>();
	}

	@Override public RoundResult postRound(Round round) {
		RoundResult roundResult = evaluateRound(round.getPlayerOne(), round.getPlayerTwo());
		round.setResult(roundResult);

		//store round for the summary
		if (globalRounds.containsKey(round.getUid())) {
			globalRounds.get(round.getUid()).add(round);
		} else {
			List<Round> strokes = new ArrayList<>();
			strokes.add(round);
			globalRounds.put(round.getUid(), strokes);
		}
		return roundResult;
	}

	// this method may be static
	@Override public RoundResult evaluateRound(Handsign playerOne, Handsign playerTwo) {
		if (playerOne.equals(playerTwo)) {
			return RoundResult.DRAW;
		} else {
			if (playerOne.getValue()+1 == playerTwo.getValue() ||
					(playerOne.equals(Handsign.SCISSORS) &&
							playerTwo.equals(Handsign.ROCK))) {
				return RoundResult.WIN_TWO;
			} else {
				return RoundResult.WIN_ONE;
			}
		}
	}

	@Override public Summary getSummary() {
		Summary summary = new Summary();
		globalRounds.entrySet().forEach(e -> summary.addRoundsTotal(e.getValue().size()));
		globalRounds.entrySet().forEach(e -> summary.addWinsPlayerOne(e.getValue().stream().filter(ee -> ee.getResult().equals(
				RoundResult.WIN_ONE)).count()));
		globalRounds.entrySet().forEach(e -> summary.addWinsPlayerTwo(e.getValue().stream().filter(ee -> ee.getResult().equals(
				RoundResult.WIN_TWO)).count()));
		globalRounds.entrySet().forEach(e -> summary.addDraws(e.getValue().stream().filter(ee -> ee.getResult().equals(
				RoundResult.DRAW)).count()));
		return summary;
	}



}
