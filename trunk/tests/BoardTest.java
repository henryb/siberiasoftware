

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.Board;
import siberia.BoardLocation;
import siberia.ChessApplet;
import siberia.DragBoard;
import siberia.PlayerController;

public class BoardTest {

	private Board subject;
	private PlayerController pc;
	private BoardLocation location;
	private boolean white;
	
	@Before
	public void setUp() throws Exception {
		pc = new PlayerController();
		white = true;
		subject = new Board(pc, white);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testBoard() {
		assertNotNull(subject);
		subject = new Board(pc, !white);
		assertNotNull(subject);
	}

	@Test
	public final void testGetLocationIntInt() {
		location = subject.getLocation(-1, 0);
		assertNull(location);
		location = subject.getLocation(8, 0);
		assertNull(location);
		location = subject.getLocation(0, -1);
		assertNull(location);
		location = subject.getLocation(0, 8);
		assertNull(location);
		location = subject.getLocation(3, 3);
		assertNotNull(location);
	}

	@Test
	public final void testSetDragBoard() {
		ChessApplet applet = new ChessApplet();
		DragBoard db = new DragBoard(subject, applet, pc);
		subject.setDragBoard(db);
		assert(true);
	}

	@Test
	public final void testUpdateBoolean() {
		subject.update(white);
		subject.update(!white);
		assert(true);
	}

}
