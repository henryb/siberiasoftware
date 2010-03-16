package siberia;

public interface Player {
	void putMove(String move);
    boolean ready();
	void gameStart();
	void gameOver(boolean win);
}
