/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package siberia;

import javax.swing.*;

/**
 *
 * @author tradams
 */
public class InstructionsPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public InstructionsPanel(){
		JLabel jl = new JLabel("Instructions: \n\n\nDo X Y Z and shit will work....");
		this.add(jl);
		this.setBounds(544/2-200, 544/2-200, 400, 400);
	}
}
