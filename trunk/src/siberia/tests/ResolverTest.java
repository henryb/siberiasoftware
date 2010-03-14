package siberia.tests;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

import junit.framework.TestCase;
import Network.Resolver;

public class ResolverTest extends TestCase {
	
	private Resolver subject;
	private Chat chat;
	private Message message;

	protected void setUp() throws Exception {
		super.setUp();
		subject = new Resolver();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testResolver() {
		
		assertNotNull(subject);
		
	}

	public final void testConnected() {
		assertTrue(subject.connected());
	}

	public final void testProcessMessage() {
		chat = null;
		message = new Message("chat message", Message.Type.chat);
		subject.processMessage(chat, message);
		assert(true);
	}

	public final void testChatCreated() {
		chat = null;
		boolean local = true;
		subject.chatCreated(chat, local);
		local = false;
		subject.chatCreated(chat, local);
		assert(true);
	}

}
