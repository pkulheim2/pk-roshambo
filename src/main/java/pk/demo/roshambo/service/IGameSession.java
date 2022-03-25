package pk.demo.roshambo.service;

import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;

import java.util.List;

public interface IGameSession {

	/**
	 * Process a new round, signs from both players needs to be provided
	 * @param playerOne
	 * @param playerTwo
	 * @return actual list of Rounds
	 */
	List<Round> newRound(Handsign playerOne, Handsign playerTwo);

	/**
	 * Process a new round, signs from player two needs to be provided, for player one a random one is generated
	 * @param playerTwo
	 * @return actual list of Rounds
	 */
	List<Round> newRound(Handsign playerTwo);

	/**
	 * Returns actual list of Rounds
	 * @return
	 */
	List<Round> getRounds();

	/**
	 * Resets session
	 * @return
	 */
	List<Round> resetSession();
}
