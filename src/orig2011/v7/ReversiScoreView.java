/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orig2011.v7;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class ReversiScoreView implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource().getClass().equals(ReversiModel.class)) {
			ReversiModel reversi = (ReversiModel)evt.getSource();
            if (evt.getPropertyName().equals("newTurn")) {
                String scoreOutput = String.format(
                        "Bong! White: %d\tBlack: %d\tIt is now %s's turn", 
                        reversi.getWhiteScore(), reversi.getBlackScore(),
                        reversi.getTurnColor().name().toLowerCase());
                System.out.println(scoreOutput);
            }
            else if (evt.getPropertyName().equals("gameOver")) {
                int white = reversi.getWhiteScore(), black = reversi.getBlackScore();
                if (white > black)
                    System.out.println("White wins!");
                else if (white < black)
                    System.out.println("Black wins!");
                else
                    System.out.println("It's a draw!");
            }
		}
	}
	
}
