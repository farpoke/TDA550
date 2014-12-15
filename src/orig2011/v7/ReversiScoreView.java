/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orig2011.v7;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author maanton
 */
class ReversiScoreView implements PropertyChangeListener {

	private String lastScoreOutput = "";
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource().getClass().equals(ReversiModel.class)) {
			ReversiModel reversi = (ReversiModel)evt.getSource();
			String scoreOutput = String.format(
					"Bong! White: %d\tBlack: %d\tIt is now %s's turn", 
					reversi.getWhiteScore(), reversi.getBlackScore(),
					reversi.getTurnColor().name().toLowerCase());
			if (!scoreOutput.equals(lastScoreOutput)) {
				System.out.println(scoreOutput);
				lastScoreOutput = scoreOutput;
			}
		}
	}
	
}
