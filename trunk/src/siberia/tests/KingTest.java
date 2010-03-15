package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.pieces.King;

public class KingTest {

	private King subject;
	private int[][] board_at_start;
	private int[][] board_in_game;
	private int[][] board_in_check;
	private int[][] board_in_checkmate;
	
	@Before
	public void setUp() throws Exception {
		subject = new King("white", "tester");
		
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
		board_in_game[1] = new int[]{14,14,14,14,14,14,14,14};
		board_in_game[2] = new int[]{0,0,0,0,0,0,0,0};
		board_in_game[3] = new int[]{0,0,0,0,0,0,0,0};
		board_in_game[4] = new int[]{0,0,0,0,0,0,0,0};
		board_in_game[5] = new int[]{0,0,0,0,0,0,0,0};
		board_in_game[6] = new int[]{7,7,7,7,7,7,7,7};
		board_in_game[7] = new int[]{3,5,6,2,1,0,5,4};
		
		board_in_check = new int[8][8];
		
		board_in_check[0] = new int[]{10,12,13,8,9,13,12,11};
		board_in_check[1] = new int[]{14,14,14,14,0,14,14,14};
		board_in_check[2] = new int[]{0,0,0,0,0,0,0,0};
		board_in_check[3] = new int[]{0,0,0,0,0,0,0,0};
		board_in_check[4] = new int[]{0,0,0,0,0,0,0,0};
		board_in_check[5] = new int[]{0,0,0,0,0,0,0,0};
		board_in_check[6] = new int[]{7,7,7,7,0,7,7,7};
		board_in_check[7] = new int[]{3,5,6,2,1,0,5,4};
		
		board_in_checkmate = new int[8][8];
		
		board_in_checkmate[0] = new int[]{10,12,13,9,8,13,12,0};
		board_in_checkmate[1] = new int[]{14,14,14,14,14,11,14,14};
		board_in_checkmate[2] = new int[]{0,0,0,0,0,0,0,0};
		board_in_checkmate[3] = new int[]{0,0,0,0,0,0,0,0};
		board_in_checkmate[4] = new int[]{0,0,0,0,0,0,0,0};
		board_in_checkmate[5] = new int[]{0,0,0,0,0,0,0,0};
		board_in_checkmate[6] = new int[]{7,7,7,7,7,9,7,7};
		board_in_checkmate[7] = new int[]{3,5,6,2,1,0,0,4};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testVerify_move_valid() {
		int[] position = {7,4};
		int[] next = {7,5};
		
		assertTrue(subject.verify_move(board_in_game, position, next));
	}
	
	@Test
	public final void testVerify_move_invalid_beyond_rules() {
		int[] position = {7,4};
		int[] next = {4,5};
		
		assertFalse(subject.verify_move(board_at_start, position, next));
	}

	@Test
	public final void testVerify_move_invalid_attacking_own_piece() {
		int[] position = {7,4};
		int[] next = {6,4};
		
		assertFalse(subject.verify_move(board_at_start, position, next));
	}


	@Test
	public final void testIsThereCheck() {
		int[] position = {7,4};
		assertFalse(subject.isThereCheck(board_at_start, position));
		assertTrue(subject.isThereCheck(board_in_check, position));
	}

	@Test
	public final void testIsThereCheckMate() {
		int[] position = {7,4};
		assertFalse(subject.isThereCheckMate(board_in_check, position));
		assertTrue(subject.isThereCheckMate(board_in_checkmate, position));
	}

	@Test
	public final void testKing() {
		assertNotNull(subject);
	}

}
