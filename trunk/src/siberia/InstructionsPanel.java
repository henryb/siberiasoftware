/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package siberia;

import java.awt.*;
import java.text.BreakIterator;
import javax.swing.*;

/**
 *
 * @author tradams
 */
public class InstructionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public InstructionsPanel() {
		JLabel jl = new JLabel("");
		String text = "Welcome to AnonChess! To get started, simply wait for a connection to occur with the next available player." +
				" Once started, click on a piece to select it and then click on an empty square to move it there... " +
				"two clicks is all you need to make a move! For castling, just move the king and rook will move " +
				"automatically. All the normal rules of chess apply, but then again you know how to play, right? Then " +
				"enjoy the game!\n\n\n\t";
		String by = "AnonChess developed by Siberia Software (http://slaruva.com/siberiasoftware). " +
				"Special thanks to Michael Maggs (http://commons.wikimedia.org/wiki/User:MichaelMaggs) for the original " +
				"chess piece photos.";

		JLabel jl2 = new JLabel("");
		this.add(jl);
		this.add(jl2);

		JLabel img1 = new JLabel();
		img1.setIcon(new ImageIcon("resources/siblogo2.jpg"));
		JLabel img2 = new JLabel();
		img2.setIcon(new ImageIcon("resources/anonlogo1.jpg"));
		this.add(img1);
		this.add(img2);
		this.setBounds(544 / 2 - 200, 544 / 2 - 200, 400, 400);
		wrapLabelText(jl,text);
		wrapLabelText(jl2,by);

	}

	//wrapping code from : http://www.geekyramblings.net/2005/06/30/wrap-jlabel-text/
	private void wrapLabelText(JLabel label, String text) {
		FontMetrics fm = label.getFontMetrics(label.getFont());
		Container container = label.getParent();
		int containerWidth = container.getWidth();

		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(text);

		StringBuffer trial = new StringBuffer();
		StringBuffer real = new StringBuffer("<html>");

		int start = boundary.first();
		for (int end = boundary.next(); end != BreakIterator.DONE;
				start = end, end = boundary.next()) {
			String word = text.substring(start, end);
			trial.append(word);
			int trialWidth = SwingUtilities.computeStringWidth(fm,
					trial.toString());
			if (trialWidth > containerWidth) {
				trial = new StringBuffer(word);
				real.append("<br>");
			}
			real.append(word);
		}

		real.append("</html>");

		label.setText(real.toString());
	}
}
