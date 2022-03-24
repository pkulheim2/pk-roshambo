package pk.demo.roshambo.service;

import pk.demo.roshambo.model.Handsign;
import pk.demo.roshambo.model.Round;

import java.util.List;

public interface IGameSession {

	List<Round> newStroke(Handsign playerOne, Handsign playerTwo);

	List<Round> newStroke(Handsign playerTwo);

	List<Round> getStrokes();

	List<Round> resetSession();
}
