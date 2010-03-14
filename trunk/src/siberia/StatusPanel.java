package siberia;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private PlayerController controller;
    private JLabel checkWhite;
    private JLabel checkBlack;
    public StatusPanel(PlayerController pc) {
        this.setLayout(new GridLayout(2, 1));
        this.controller = pc;
        checkWhite = new JLabel("");
        checkBlack = new JLabel("");
        this.add(checkWhite);
        this.add(checkBlack);
        update();
        this.setBounds(544/2-200, 544/2-200, 400, 400);

    }

    public void update() {
        checkWhite.setText("White in check? " +Boolean.toString(controller.getBoard().isThereCheck("white")));

        checkBlack.setText("Black in check? "+Boolean.toString(controller.getBoard().isThereCheck("black")));
    }


}
