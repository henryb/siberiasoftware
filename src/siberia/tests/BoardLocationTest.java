package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.Board;
import siberia.BoardLocation;
import siberia.pieces.*;

public class BoardLocationTest {
	
	private BoardLocation subject;
	private ChessPiece piece;
	private Board board;
	

	@Before
	public void setUp() throws Exception {
		board = null;
		subject = new BoardLocation(board, 0, 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testBoardLocation() {
		assertNotNull(subject);
	}

	@Test
	public final void testSetLoc() {
		subject.setLoc(5, 2);
		assertEquals(5, subject.getLocationX());
		assertEquals(2, subject.getLocationY());
	}

	@Test
	public final void testGetLocationX() {
		subject = new BoardLocation(board, 5, 2);
		assertEquals(5, subject.getLocationX());
	}

	@Test
	public final void testGetLocationY() {
		subject = new BoardLocation(board, 5, 2);
		assertEquals(2, subject.getLocationY());
	}

	@Test
	public final void testGetPiece() {
		assertNull(subject.getPiece());
		
	}

	@Test
	public final void testSetPiece() {
		piece = new Pawn("white", "../../resources/whitepawn.jpg");
		subject.setPiece(piece);
		assertEquals(piece, subject.getPiece());
	}

	@Test
	public final void testToString() {
		subject = new BoardLocation(board, 5, 2);
		assertEquals("5 2", subject.toString());
	}

}
