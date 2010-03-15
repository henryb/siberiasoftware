package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.pieces.Pawn;

public class PawnTest {
	
	private Pawn subject;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testVerify_move_valid() {
		int[][] board = {};
		int[] position = {};
		int[] next = {};
		
		assertTrue(subject.verify_move(board, position, next));
	}

	@Test
	public final void testVerify_move_invalid() {
		int[][] board = {};
		int[] position = {};
		int[] next = {};
		
		assertFalse(subject.verify_move(board, position, next));
	}


	@Test
	public final void testValidate_passant() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPawn() {
		fail("Not yet implemented"); // TODO
	}

}
