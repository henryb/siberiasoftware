package siberia.tests;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AnonChessTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite();

		//Network
		suite.addTestSuite(ResolverTest.class);
		suite.addTestSuite(ChatClientTest.class);
		suite.addTestSuite(MoveListenerTest.class);
		//siberia.pieces
		suite.addTestSuite(BishopTest.class);
		suite.addTestSuite(KnightTest.class);
		suite.addTestSuite(PawnTest.class);
		suite.addTestSuite(RookTest.class);
		suite.addTestSuite(QueenTest.class);
		suite.addTestSuite(KingTest.class);
		//siberia
		suite.addTestSuite(BoardTest.class);
		suite.addTestSuite(BoardLocationTest.class);
		suite.addTestSuite(ChessBoardTest.class);
		suite.addTestSuite(DisplayBoardTest.class);
		suite.addTestSuite(DragBoardTest.class);
		suite.addTestSuite(PlayerControllerTest.class);
		suite.addTestSuite(NetworkPlayerTest.class);
		suite.addTestSuite(SimpleLayersTest.class);
		suite.addTestSuite(StatusPanelTest.class);

		suite.addTestSuite(ChessAppletTest.class);
		
		return suite;
		
	}

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
	
}
