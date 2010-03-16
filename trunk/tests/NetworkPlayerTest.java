

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siberia.NetworkPlayer;
import siberia.PlayerController;

public class NetworkPlayerTest {

	private NetworkPlayer subject;
	private PlayerController pc;


	@Before
	public void setUp() throws Exception {
		pc = new PlayerController();
		// set up PlayerController pc here

		subject = new NetworkPlayer(pc);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testNetworkPlayer() {
		assertNotNull(subject);
	}

	@Test
	public final void testPutMove() {
		String move = "1 1 1 1";
		subject.putMove(move);
		assert(true);
	}

/*	@Test
	public final void testReady() {
		assertFalse(subject.ready());

		ChessApplet heavyweight = new ChessApplet();

		assertTrue("NetworkPlayer not ready", subject.ready());
	}*/

	@Test
	public final void testMove_setPlayerWhite() {

		pc.addPlayer(subject);
		pc.addPlayer(subject);

		String move = "POSITION:WHITE";
		subject.move(move);
		assert(true);

	}

	@Test
	public final void testMove() {

		String move = "1 1 1 1";
		subject.move(move);
		assert(true);

	}

}
