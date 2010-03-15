package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.ChessBoard;
import siberia.pieces.ChessPiece;
import siberia.pieces.King;

public class ChessBoardTest {
	
	private ChessBoard subject;

	@Before
	public void setUp() throws Exception {
		subject = new ChessBoard();
		subject.configureBoard();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testChessBoard() {
		assertNotNull(subject);
	}

	@Test
	public final void testConfigureBoard() {
		subject.configureBoard();
		assert(true);
	}

	@Test
	public final void testPrintBoard() {
		subject.printBoard();
		assert(true);
	}

	@Test
	public final void testGetPieceAt() {
		ChessPiece piece = new King("white", "../resources/whiteking.jpg");
		assertEquals(piece.color, subject.getPieceAt(7, 4).color);
		assertEquals(piece.getImage(), subject.getPieceAt(7, 4).getImage());
	}

	@Test
	public final void testApplyMove() {
		int[] current = {6,0};
		int[] next = {5,0};
		subject.applyMove(current, next);
		assert(true);
	}

	@Test
	public final void testValidateMove() {
		//move to same spot
		int[] current = {6,0};
		int[] next = {6,0};
		assertFalse(subject.validateMove(current, next, "white"));
		
		//"moving" empty space
		current = new int[]{5,0};
		next = new int[]{4,0};
		assertFalse(subject.validateMove(current, next, "white"));
		
		//valid move (white pawn forward one space)
		current = new int[]{6,0};
		next = new int[]{5,0};
		assertTrue(subject.validateMove(current, next, "white"));
	}

	@Test
	public final void testIsThereCheck() {
		assertFalse(subject.isThereCheck("white"));
		
		assertFalse(subject.isThereCheck("black"));
	}

	@Test
	public final void testIsThereCheckMate_black() {
		assertFalse("At start of game, black is in Checkmate", subject.isThereCheckMate("black"));
	}
	
	@Test
	public final void testIsThereCheckMate_white() {
		assertFalse("At start of game, white is in Checkmate", subject.isThereCheckMate("white"));
	}

	@Test
	public final void testConvertMove() {
		int[] current = {6,0};
		int[] next = {5,0};
		subject.applyMove(current, next);
		assertEquals("6 0 5 0", subject.convertMove());
	}

	@Test
	public final void testStringToMove() {
		int[][] move = subject.stringToMove("0 0 7 7");
		assertEquals(0, move[0][0]);
		assertEquals(0, move[0][1]);
		assertEquals(7, move[1][0]);
		assertEquals(7, move[1][1]);
	}
	
	@Test
	public final void testDecodeMove() {
		assertFalse(subject.decodeMove("1 1 1 1"));
		assertTrue(subject.decodeMove("6 0 5 0"));
		
	}

	@Test
	public final void testIsGarbled() {
		assertTrue(subject.isGarbled("garbled blah"));
		assertFalse(subject.isGarbled("0 0 0 0"));
		assertTrue(subject.isGarbled("10 -9 28 -1"));
	}

}
