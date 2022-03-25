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
	void testroundEvaluation_rock_paper() {
		RoundResult roundResult = gameEngine.evaluateRound(Handsign.ROCK, Handsign.PAPER);
		assertEquals(WIN_TWO, roundResult);
	}

	@Test
	void testroundEvaluation_rock_scissors() {
		RoundResult roundResult = gameEngine.evaluateRound(Handsign.ROCK, Handsign.SCISSORS);
		assertEquals(WIN_ONE, roundResult);
	}

	@Test
	void testroundEvaluation_paper_scissors() {
		RoundResult roundResult = gameEngine.evaluateRound(Handsign.PAPER, Handsign.SCISSORS);
		assertEquals(WIN_TWO, roundResult);
	}

	@Test
	void testroundEvaluation_paper_rock() {
		RoundResult roundResult = gameEngine.evaluateRound(Handsign.PAPER, Handsign.ROCK);
		assertEquals(WIN_ONE, roundResult);
	}

	@Test
	void testroundEvaluation_rock_rock() {
		RoundResult roundResult = gameEngine.evaluateRound(Handsign.ROCK, Handsign.ROCK);
		assertEquals(DRAW, roundResult);
	}

	@Test
	void testSummary() {
		gameEngine.resetEngine();
		Round round1 = new Round("uid1", null, Handsign.ROCK, Handsign.ROCK);
		Round round2 = new Round("uid2", null, Handsign.PAPER, Handsign.SCISSORS);
		Round round3 = new Round("uid3", null, Handsign.SCISSORS, Handsign.ROCK);
		gameEngine.postRound(round1);
		gameEngine.postRound(round2);
		gameEngine.postRound(round3);
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

		List<Round> rounds = gameSession.newRound(Handsign.ROCK, Handsign.PAPER);

		List<Round> rounds2 = gameSession2.newRound(Handsign.ROCK, Handsign.PAPER);
		assertEquals(1, rounds.size());
		assertEquals(1, rounds2.size());;

		Summary summary = gameEngine.getSummary();
		assertEquals(2, summary.getRoundsTotal());

	}

	@Test
	void testResetSession() {
		gameEngine.resetEngine();
		GameSession gameSession = new GameSession(gameEngine);

		List<Round> rounds = gameSession.newRound(Handsign.ROCK, Handsign.PAPER);
		assertEquals(1, rounds.size());
		gameSession.resetSession();
		List<Round> rounds2 = gameSession.getRounds();
		assertEquals(0, rounds2.size());

		Summary summary = gameEngine.getSummary();
		assertEquals(1, summary.getRoundsTotal());

	}


}
