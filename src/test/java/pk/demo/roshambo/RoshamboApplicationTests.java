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
	void testStroke() {

		when(gameEngineMock.postRound(any())).thenReturn(RoundResult.DRAW);
		GameSession gameSession = new GameSession(gameEngineMock);

		gameSession.newStroke(Handsign.ROCK, Handsign.PAPER);
		List<Round> strokes = gameSession.newStroke(Handsign.PAPER, Handsign.ROCK);

		assertEquals(2, strokes.size());

		assertEquals(RoundResult.DRAW, strokes.get(0).getResult());

	}


}
