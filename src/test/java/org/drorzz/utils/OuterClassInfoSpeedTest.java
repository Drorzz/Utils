package org.drorzz.utils;

import org.junit.*;

/**
 * @author Drorzz
 * @since 28.08.14.
 */
@Ignore
public class OuterClassInfoSpeedTest {
	private String testName = "";
	private long time = 0;
	private final int cyclesNumber = 1000_000;

	@Before
	public void startTimer() {
		time = System.currentTimeMillis();
	}

	@After
	public void timerResult() {
		System.out.printf("%-20s %6.3f\n",
				testName.concat(":"), (System.currentTimeMillis() - time) / 1000.0);
	}


	@Test
	public void getFileNameTest() {
		testName = "File name";
		for (int i = 0; i < cyclesNumber; i++) {
			OuterClassInfo.getFileName();
		}
	}

	@Test
	public void getMethodNameTest() {
		testName = "Method name";
		for (int i = 0; i < cyclesNumber; i++) {
			OuterClassInfo.getMethodName();
		}
	}

	@Test
	public void getShortClassNameTest() {
		testName = "Short class name";
		for (int i = 0; i < cyclesNumber; i++) {
			OuterClassInfo.getShortClassName();
		}
	}

	@Test
	public void getClassNameTest() {
		testName = "Class name";
		for (int i = 0; i < cyclesNumber; i++) {
			OuterClassInfo.getClassName();
		}
	}

	@Test
	public void getOuterClassTest() {
		testName = "Class";
		for (int i = 0; i < cyclesNumber; i++) {
			OuterClassInfo.getOuterClass();
		}
	}

}
