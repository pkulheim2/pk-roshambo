package pk.demo.roshambo.service;

import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;
import pk.demo.roshambo.model.RoundResult;
import pk.demo.roshambo.model.Summary;

public interface IGameEngine {

	/**
	 * generates random sign
	 * @return
	 */
	Handsign getRandomSign();

	/**
	 * resets game engine globally
	 * intended for unit testing
	 */
	void resetEngine();

	/**
	 * Posts one round into engine.
	 * Round is evaluated and stored for global summary
	 * @param round
	 * @return
	 */
	RoundResult postRound(Round round);

	/**
	 * Evaluates a round
	 * @param playerOne
	 * @param playerTwo
	 * @return
	 */
	RoundResult evaluateRound(Handsign playerOne, Handsign playerTwo);

	/**
	 * Provides global summary of plays from all game sessions
	 * @return
	 */
	Summary getSummary();
}
