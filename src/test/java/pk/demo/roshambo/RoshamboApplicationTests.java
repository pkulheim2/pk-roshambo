package pk.demo.roshambo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;
import pk.demo.roshambo.model.RoundResult;
import pk.demo.roshambo.service.GameSession;
import pk.demo.roshambo.service.IGameEngine;
import pk.demo.roshambo.service.IGameSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoshamboApplicationTests {

	@MockBean
	private IGameEngine gameEngineMock;


	@Test
	void testRoundBothSignsProvided() {

		when(gameEngineMock.postRound(any())).thenReturn(RoundResult.DRAW);
		IGameSession gameSession = new GameSession(gameEngineMock);

		gameSession.newRound(Handsign.ROCK, Handsign.PAPER);
		List<Round> strokes = gameSession.newRound(Handsign.PAPER, Handsign.ROCK);

		assertEquals(2, strokes.size());

		assertEquals(RoundResult.DRAW, strokes.get(0).getResult());

	}

	@Test
	void testRoundOneSignsProvided() {

		when(gameEngineMock.postRound(any())).thenReturn(RoundResult.DRAW);

		when(gameEngineMock.getRandomSign()).thenReturn(Handsign.ROCK);
		IGameSession gameSession = new GameSession(gameEngineMock);

		gameSession.newRound(Handsign.PAPER);
		List<Round> strokes = gameSession.newRound(Handsign.ROCK);

		assertEquals(2, strokes.size());

		assertEquals(RoundResult.DRAW, strokes.get(0).getResult());

	}


}
