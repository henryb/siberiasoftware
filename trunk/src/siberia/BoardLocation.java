package siberia;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import siberia.pieces.ChessPiece;

public class BoardLocation extends JButton {
    private ChessPiece piece;
    private int m_x, m_y;
    public BoardLocation(Board brd, int x, int y) {
        this.m_x = x;
        this.m_y = y;

        //this.setBounds(0,0, 10, 10);
        //this.setSize(50,50);

        //this.addActionListener(brd);
    }

	public void setLoc(int x, int y){
		m_x = x;
		m_y = y;
	}

    public int getLocationX() {
        return m_x;
    }

    public int getLocationY() {
        return m_y;
    }
	public ChessPiece getPiece(){
		return piece;
	}
    public void setPiece(ChessPiece cp){
        this.piece = cp;
        if(cp != null){
            this.setIcon(new ImageIcon(this.piece.getImage()));
			this.repaint();
        } else {
            this.setIcon(null);
			this.repaint();
        }
    }

    @Override
    public boolean isBorderPainted() {
        return true;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public boolean isContentAreaFilled() {
        return false;
    }

    public String toString() {
        return getLocationX() + " " + getLocationY();
    }
}
