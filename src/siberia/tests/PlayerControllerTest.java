package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.ChessBoard;
import siberia.NetworkPlayer;
import siberia.Player;
import siberia.PlayerController;

public class PlayerControllerTest {

	private PlayerController subject;
	private Player player;
	private Player anotherplayer;
	
	@Before
	public void setUp() throws Exception {
		subject = new PlayerController();
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public final void testPlayerController() {
		assertNotNull(subject);
	}

	@Test
	public final void testMakeMove() {
		player = new NetworkPlayer(subject);
		subject.addPlayer(player);
		anotherplayer = new NetworkPlayer(subject);
		subject.addPlayer(anotherplayer);
		subject.makeMove(subject.getBlack(), "blah");
		subject.makeMove(subject.getWhite(), "fsafhjk");
		subject.makeMove(subject.getWhite(), "6 0 5 0");
		subject.makeMove(subject.getWhite(), "6 0 5 0");
		subject.makeMove(subject.getBlack(), "fgsfds");
		subject.makeMove(subject.getBlack(), "1 1 3 1");
		
		subject.over();
		subject.makeMove(null, null);
		assert(true);
	}

	@Test
	public final void testGetBoard() {
		ChessBoard board = new ChessBoard();
		board.configureBoard();
		assertSame(board.getClass(), subject.getBoard().getClass());		
	}

	@Test
	public final void testAddPlayer() {
		player = new NetworkPlayer(subject);
		subject.addPlayer(player);
		anotherplayer = new NetworkPlayer(subject);
		subject.addPlayer(anotherplayer);
		assert(true);
		
	}

	@Test
	public final void testSetWhitePlayer() {
		subject.setWhitePlayer(null);
		assertNull(subject.getWhite());
		
		
		player = new NetworkPlayer(subject);
		subject.setWhitePlayer(player);
		assertNull(subject.getWhite());
		subject.addPlayer(player);
		subject.setWhitePlayer(player);
		assertSame(player, subject.getWhite());
		
		anotherplayer = new NetworkPlayer(subject);
		subject.addPlayer(anotherplayer);
		subject.setWhitePlayer(player);
		assertSame(player, subject.getWhite());
		
		subject.setWhitePlayer(anotherplayer);
		assertSame(anotherplayer, subject.getWhite());
	}

	@Test
	public final void testGetWhite() {
		player = new NetworkPlayer(subject);
		subject.addPlayer(player);
		assertSame(player, subject.getWhite());
		
	}

	@Test
	public final void testGetBlack() {
		player = new NetworkPlayer(subject);
		anotherplayer = new NetworkPlayer(subject);
		subject.addPlayer(player);
		subject.addPlayer(anotherplayer);
		assertSame(anotherplayer, subject.getBlack());
	}
	
	@Test
	public final void testIsOver() {
		assertFalse("PlayerController is over when game starts" ,subject.isOver());
	}

	@Test
	public final void testOver() {
		subject.over();
		assertTrue("PlayerController didn't end game when over() called", subject.isOver());
	}
	
}
