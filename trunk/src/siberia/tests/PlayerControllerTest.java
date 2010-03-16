package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.ChessBoard;
import siberia.PlayerController;

public class PlayerControllerTest {

	private PlayerController subject;
	
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
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBoard() {
		ChessBoard board = new ChessBoard();
		board.configureBoard();
		assertSame(board.getClass(), subject.getBoard().getClass());		
	}

	@Test
	public final void testAddPlayer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetWhitePlayer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetWhite() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBlack() {
		fail("Not yet implemented"); // TODO
	}

}
