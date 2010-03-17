package siberia.tests;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.pieces.Bishop;

public class BishopTest extends TestCase{
	
	private Bishop subject;
	private int[][] board_at_start;
	private int[][] board_in_game;
	
	@Before
	public void setUp() throws Exception {
		subject = new Bishop("white", "tester");
		
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
		board_in_game[6] = new int[]{7,0,7,7,7,7,7,7};
		board_in_game[7] = new int[]{3,5,6,2,1,6,5,4};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testVerify_move_invalid_jumping() {
		
		int[] position = {7, 2};
		int[] next = {5, 0};
		
		assertFalse(subject.verify_move(board_at_start, position, next));
	}
	
	@Test
	public final void testVerify_move_invalid_attacking_own_piece() {

		int[] position = {7, 2};
		int[] next = {6, 4};
		
		assertFalse(subject.verify_move(board_at_start, position, next));
	}
	
	@Test
	public final void testVerify_move_valid() {

		int[] position = {7, 2};
		int[] next = {5, 0};
		
		assertTrue(subject.verify_move(board_in_game, position, next));
		
		int[] position2 = {5,2};
		int[] next2 = {4,1};
		
		assertTrue(subject.verify_move(board_in_game, position2, next2));
		
		int[] position3 = {2,3};
		int[] next3 = {4,5};
		
		assertTrue(subject.verify_move(board_in_game, position3, next3));
		
		int[] position4 = {2,3};
		int[] next4 = {4,1};
		
		assertTrue(subject.verify_move(board_in_game, position4, next4));
		
		int[] position5 = {4,5};
		int[] next5 = {4,0};
		
		assertFalse(subject.verify_move(board_in_game, position5, next5));
		
		int[] position6 = {3,5};
		int[] next6 = {4,5};
		
		assertFalse(subject.verify_move(board_in_game, position6, next6));
		
		int[] position7 = {3,1};
		int[] next7 = {4,5};
		
		assertFalse(subject.verify_move(board_in_game, position7, next7));
		
		int[] position8 = {2,3};
		int[] next8 = {5,6};
		
		assertTrue(subject.verify_move(board_in_game, position8, next8));
	}

	
	@Test
	public final void testBishop() {
		assertNotNull(subject);
	}

}
