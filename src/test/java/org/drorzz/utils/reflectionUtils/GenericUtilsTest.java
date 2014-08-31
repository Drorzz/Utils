package org.drorzz.utils.reflectionUtils;

import org.junit.Test;
import java.util.*;

import static  org.drorzz.utils.reflectionUtils.GenericUtils.*;

import static org.junit.Assert.*;
import static org.drorzz.utils.JUnitUtils.assertMapEquals;



public class GenericUtilsTest {
	private interface SuperInterface1<E>{}
	private interface SuperInterface2<E,T>{}

	private interface TestInterface1 {}
	private interface TestInterface2 extends SuperInterface1<String>{}
	private interface TestInterface3 extends SuperInterface2<Integer,String>{}

	private class SuperClass1<E>{}
	private class SuperClass2<E,T>{}

	private class TestClass1 {}
	private class TestClass2 extends SuperClass1<String>{}
	private class TestClass3 extends SuperClass2<Integer,String>{}

	private class TestALL extends SuperClass2<Integer,String>
							implements SuperInterface1<Boolean>, SuperInterface2<Boolean,Double>{}


	private List<Class<?>> toList(Class<?>... args){
		return Arrays.asList(args);
	}

	@Test
	public void testGetAllGenericsOnSuperclass() {
		Map<Class<?>,List<Class<?>>> mapTestInterface2 = new HashMap<>();
		mapTestInterface2.put(SuperInterface1.class,toList(String.class));

		Map<Class<?>,List<Class<?>>> mapTestInterface21 = new HashMap<>();
		mapTestInterface21.put(SuperInterface1.class,toList(String.class));

		Map<Class<?>,List<Class<?>>> mapTestInterface3 = new HashMap<>();
		mapTestInterface3.put(SuperInterface2.class,toList(Integer.class,String.class));

		Map<Class<?>,List<Class<?>>> mapTestClass2 = new HashMap<>();
		mapTestClass2.put(SuperClass1.class,toList(String.class));

		Map<Class<?>,List<Class<?>>> mapTestClass3 = new HashMap<>();
		mapTestClass3.put(SuperClass2.class,toList(Integer.class,String.class));

		Map<Class<?>,List<Class<?>>> mapTestALL = new HashMap<>();
		mapTestALL.put(SuperClass2.class,toList(Integer.class,String.class));
		mapTestALL.put(SuperInterface1.class,toList(Boolean.class));
		mapTestALL.put(SuperInterface2.class,toList(Boolean.class,Double.class));


		assertMapEquals("Test", mapTestInterface2, mapTestInterface21);

		assertMapEquals("TestInterface2", mapTestInterface2, getAllGenericsOnSuperclass(TestInterface2.class));
		assertMapEquals("TestInterface3", mapTestInterface3, getAllGenericsOnSuperclass(TestInterface3.class));

		assertMapEquals("TestClass2", mapTestClass2, getAllGenericsOnSuperclass(TestClass2.class));
		assertMapEquals("TestClass3", mapTestClass3, getAllGenericsOnSuperclass(TestClass3.class));

		assertMapEquals("TestALL", mapTestALL, getAllGenericsOnSuperclass(TestALL.class));
	}

	@Test
	public void testGetFirstGenericOnSuperclass() {
		assertEquals("TestInterface2",String.class,getFirstGenericOnSuperclass(TestInterface2.class));
		assertEquals("TestInterface3",Integer.class,getFirstGenericOnSuperclass(TestInterface3.class));

		assertEquals("TestClass2",String.class,getFirstGenericOnSuperclass(TestClass2.class));
		assertEquals("TestClass3",Integer.class,getFirstGenericOnSuperclass(TestClass3.class));

		assertEquals("TestALL",Integer.class,getFirstGenericOnSuperclass(TestALL.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFirstGenericOnSuperclassException0() throws Exception {
		ArrayList<String> testList = new ArrayList<>();
		getFirstGenericOnSuperclass(testList.getClass());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFirstGenericOnSuperclassException1() throws Exception {
		getFirstGenericOnSuperclass(TestInterface1.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFirstGenericOnSuperclassException2() throws Exception {
		getFirstGenericOnSuperclass(TestClass1.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAllGenericsOnSuperclass1() throws Exception {
		getAllGenericsOnSuperclass(TestInterface1.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAllGenericsOnSuperclass2() throws Exception {
		getAllGenericsOnSuperclass(TestClass1.class);
	}
}


