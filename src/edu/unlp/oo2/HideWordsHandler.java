package edu.unlp.oo2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class HideWordsHandler extends Handler {

	Set<String> words;
	Handler next;
	
	public HideWordsHandler(Handler next, String... wordsList) {
		words = new HashSet<>(Arrays.asList(wordsList));
		this.next = next;
	}
	
	@Override
	public void publish(LogRecord record) {
		String msg = record.getMessage();
		for (String word : words) {
			msg = msg.replace(word, "***");
		}
		record.setMessage(msg);
		this.next.publish(record);
	}

	@Override
	public void flush() {
		this.next.flush();
	}

	@Override
	public void close() throws SecurityException {
		this.next.close();
	}

}
