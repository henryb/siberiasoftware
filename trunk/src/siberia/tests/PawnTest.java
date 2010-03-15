package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.pieces.Pawn;

public class PawnTest {
	
	private Pawn subject;
	private int[][] board_at_start;
	private int[][] board_in_game;
	
	@Before
	public void setUp() throws Exception {
		subject = new Pawn("white", "tester");
		
		board_at_start = new int[8][8];
		
		// 0 corresponds to empty space
		board_at_start[0] = new int[]{10,12,13,9,8,13,12,11};
		board_at_start[1] = new int[]{14,14,14,14,14,14,14,14};
		board_at_start[2] = new int[]{0,0,0,0,0,0,0,0};
		board_at_start[3] = new int[]{0,0,0,0,0,0,0,0};
		board_at_start[4] = new int[]{0,0,0,0,0,0,0,0};
		board_at_start[5] = new int[]{0,0,0,0,0,0,0,0};
		board_at_start[6] = new int[]{7,7,7,7,7,7,7,7};
		board_at_start[7] = new int[]{3,5,6,2,1,6,5,4};
		
		board_in_game = new int[8][8];
		
		board_in_game[0] = new int[]{10,12,13,9,8,13,12,11};
		board_in_game[1] = new int[]{14,14,0,14,14,0,14,14};
		board_in_game[2] = new int[]{0,0,0,0,0,0,0,0};
		board_in_game[3] = new int[]{0,7,14,0,0,0,0,0};
		board_in_game[4] = new int[]{0,0,0,0,0,14,0,7};
		board_in_game[5] = new int[]{0,0,0,0,0,0,7,0};
		board_in_game[6] = new int[]{7,0,7,7,7,7,0,0};
		board_in_game[7] = new int[]{3,5,6,2,1,6,5,4};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testVerify_move_valid() {
		int[] position = {6, 6};
		int[] next = {5, 6};
		
		assertTrue(subject.verify_move(board_at_start, position, next));
	}
	
	@Test
	public final void testVerify_move_valid_twospaces() {
		int[] position = {6, 6};
		int[] next = {4, 6};
		
		assertTrue(subject.verify_move(board_at_start, position, next));
	}
	
	@Test
	public final void testVerify_move_valid_attacking_nme() {
		int[] position = {5, 6};
		int[] next = {4, 5};
		
		assertTrue(subject.verify_move(board_in_game, position, next));
	}
	
	@Test
	public final void testVerify_move_invalid_beyond_rules() {
		int[] position = {6, 6};
		int[] next = {4, 5};
		
		assertFalse(subject.verify_move(board_at_start, position, next));
	}

	@Test
	public final void testVerify_move_invalid_attacking_own_piece() {
		int[] position = {5, 6};
		int[] next = {6, 5};
		assertFalse(subject.verify_move(board_in_game, position, next));
	}

	@Test
	public final void testValidate_passant() {
		int[] position = {3, 1};
		int[] next = {2, 2};
		int[][] lastMove = {{1,2},{3,2}};
		
		assertTrue(subject.validate_passant(board_in_game, position, next, lastMove));
	}

	@Test
	public final void testPawn() {
		assertNotNull(subject);
	}

}
