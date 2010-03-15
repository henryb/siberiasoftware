package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.ChessApplet;

public class ChessAppletTest {

	private ChessApplet subject;
	private String move;
	
	
	@Before
	public void setUp() throws Exception {
		subject = new ChessApplet();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testInit() {
		assertNotNull(subject);
		subject.init();
		assert(true);
	}

	@Test
	public final void testDestroy() {
		subject.destroy();
		assert(true);
	}

	@Test
	public final void testCheckMove() {
		subject.init();
		move = ""; //garbled move string
		subject.checkMove(move);
		assert(true);
	}

	@Test
	public final void testCheckMove_invalid() {
		subject.init();
		move = "0 0 7 7"; //invalid move
		subject.checkMove(move);
		assert(true);
	}
	
	@Test
	public final void testCheckMove_valid() {
		subject.init();
		move = "7 1 5 2"; //valid move
		subject.checkMove(move);
		assert(true);
	}
	
	@Test
	public final void testPutMove_gameover() {
		subject.init();
		move = "1 1 1 1";
		subject.putMove(move);
		assert(true);
	}
	
	@Test
	public final void testPutMove() {
		subject.init();
		move = "2 2 3 3";
		subject.putMove(move);
		assert(true);
	}

	@Test
	public final void testReady() {
		assertTrue(subject.ready());
	}

}
