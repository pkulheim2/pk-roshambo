package pk.demo.roshambo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;
import pk.demo.roshambo.model.RoundResult;
import pk.demo.roshambo.model.Summary;
import pk.demo.roshambo.service.GameSession;
import pk.demo.roshambo.service.IGameEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static pk.demo.roshambo.model.RoundResult.*;

@SpringBootTest
class RoshamboEngineTests {

	@Autowired
	private IGameEngine gameEngine;

	@Test
	void testStrokeEvaluation_rock_paper() {
		RoundResult strokeResult = gameEngine.evaluateRound(Handsign.ROCK, Handsign.PAPER);
		assertEquals(WIN_TWO, strokeResult);
	}

	@Test
	void testStrokeEvaluation_rock_scissors() {
		RoundResult strokeResult = gameEngine.evaluateRound(Handsign.ROCK, Handsign.SCISSORS);
		assertEquals(WIN_ONE, strokeResult);
	}

	@Test
	void testStrokeEvaluation_paper_scissors() {
		RoundResult strokeResult = gameEngine.evaluateRound(Handsign.PAPER, Handsign.SCISSORS);
		assertEquals(WIN_TWO, strokeResult);
	}

	@Test
	void testStrokeEvaluation_paper_rock() {
		RoundResult strokeResult = gameEngine.evaluateRound(Handsign.PAPER, Handsign.ROCK);
		assertEquals(WIN_ONE, strokeResult);
	}

	@Test
	void testStrokeEvaluation_rock_rock() {
		RoundResult strokeResult = gameEngine.evaluateRound(Handsign.ROCK, Handsign.ROCK);
		assertEquals(DRAW, strokeResult);
	}

	@Test
	void testSummary() {
		gameEngine.resetEngine();
		Round stroke1 = new Round("uid1", null, Handsign.ROCK, Handsign.ROCK);
		Round stroke2 = new Round("uid2", null, Handsign.PAPER, Handsign.SCISSORS);
		Round stroke3 = new Round("uid3", null, Handsign.SCISSORS, Handsign.ROCK);
		gameEngine.postRound(stroke1);

		gameEngine.postRound(stroke2);
		gameEngine.postRound(stroke3);
		Summary summary = gameEngine.getSummary();
		assertEquals(3, summary.getRoundsTotal());
		assertEquals(1, summary.getDraws());
		assertEquals(0, summary.getWinsPlayerOne());
		assertEquals(2, summary.getWinsPlayerTwo());


	}

	@Test
	void testSessions() {
		gameEngine.resetEngine();
		GameSession gameSession = new GameSession(gameEngine);

		GameSession gameSession2 = new GameSession(gameEngine);

		List<Round> strokes = gameSession.newStroke(Handsign.ROCK, Handsign.PAPER);

		List<Round> strokes2 = gameSession2.newStroke(Handsign.ROCK, Handsign.PAPER);
		assertEquals(1, strokes.size());
		assertEquals(1, strokes2.size());;

		Summary summary = gameEngine.getSummary();
		assertEquals(2, summary.getRoundsTotal());

	}

	@Test
	void testResetSession() {
		gameEngine.resetEngine();
		GameSession gameSession = new GameSession(gameEngine);

		List<Round> strokes = gameSession.newStroke(Handsign.ROCK, Handsign.PAPER);
		assertEquals(1, strokes.size());
		gameSession.resetSession();
		List<Round> strokes2 = gameSession.getStrokes();
		assertEquals(0, strokes2.size());

		Summary summary = gameEngine.getSummary();
		assertEquals(1, summary.getRoundsTotal());

	}


}
