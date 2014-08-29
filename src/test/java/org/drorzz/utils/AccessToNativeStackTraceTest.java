package org.drorzz.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccessToNativeStackTraceTest {

	@Test
	public void testAccessToNativeStackTrace() {
		AccessToNativeStackTrace throwable = new AccessToNativeStackTrace();
		StackTraceElement element = throwable.getStackTraceElement(0);

		assertEquals("Element - Class name",this.getClass().getName(),element.getClassName());
		assertEquals("Element - Method name","testAccessToNativeStackTrace",element.getMethodName());
		assertTrue("Depth",throwable.getStackTraceDepth() > 0);
	}
}