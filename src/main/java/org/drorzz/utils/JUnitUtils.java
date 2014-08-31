package org.drorzz.utils;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Drorzz
 * @since 31.08.14.
 */
public class JUnitUtils {
	private static final String SEPARATOR = " | ";

	private static String getInnerMessage(String message, String addingMessage) {
		if (message != null && !message.trim().isEmpty()) {
			return message.concat(SEPARATOR).concat(addingMessage);
		} else {
			return null;
		}
	}

	static public <K,V> void assertMapEquals(String message, Map<K,V> expected, Map<K,V> actual){
		assertEquals(getInnerMessage(message,"Size"),expected.size(), actual.size());
		for(Map.Entry<K,V> value:expected.entrySet()){
			V actualValue = actual.get(value.getKey());
			assertEquals(getInnerMessage(message,"Value"), value.getValue(), actualValue);
		}
	}

	static public <K,V> void assertMapEquals(Map<K,V> expected, Map<K,V> actual){
		assertMapEquals(null, expected, actual);
	}
}
