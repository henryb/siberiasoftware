package siberia.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.pieces.Bishop;

public class BishopTest {
	
	private Bishop subject;
	private int[][] board_at_start;
	private int[][] board_in_game;
	
	@Before
	public void setUp() throws Exception {
		subject = new Bishop("white", "tester");
		
		board_at_start = new int[8][8];
		
		board_at_start[0] = new int[]{10,12,13,9,8,13,12,11};
		board_at_start[1] = new int[]{14,14,14,14,14,14,14,14};
		
		// 0 corresponds to empty space
		for(int i = 2; i <= 5; i++){
			for(int j = 0; j < board_at_start[i].length; j++){
				board_at_start[i][j] = 0;			
			}
		}
		board_at_start[6] = new int[]{7,7,7,7,7,7,7,7};
		board_at_start[7] = new int[]{3,5,6,2,1,6,5,4};
		
		board_in_game = new int[8][8];
		
		board_in_game[0] = new int[]{10,12,13,9,8,13,12,11};
		board_in_game[1] = new int[]{14,14,14,14,14,14,14,14};
		
		// 0 corresponds to empty space
		for(int i = 2; i <= 5; i++){
			for(int j = 0; j < board_in_game[i].length; j++){
				board_in_game[i][j] = 0;			
			}
		}
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
	}

	
	@Test
	public final void testBishop() {
		assertNotNull(subject);
	}

}
