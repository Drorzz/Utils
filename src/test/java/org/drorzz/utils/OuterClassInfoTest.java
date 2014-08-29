package org.drorzz.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Drorzz
 * @since 28.08.14.
 */
public class OuterClassInfoTest {
    class TestClass{
        public String getFileName() {
            return OuterClassInfo.getFileName();
        }

        public String getMethodName() {
            return OuterClassInfo.getMethodName();
        }

        public String getShortClassName() {
            return OuterClassInfo.getShortClassName();
        }

        public String getClassName() {
            return OuterClassInfo.getClassName();
        }

	    public Class<?> getOuterClass() {
		    return OuterClassInfo.getOuterClass();
	    }

    }

	private static Class<?> getStaticOuterClass() {
		return OuterClassInfo.getOuterClass();
	}

    @Test
    public void testAll() {
        TestClass testClass = new TestClass();

        assertEquals("FileName","OuterClassInfoTest.java",testClass.getFileName());
        assertEquals("ClassName",TestClass.class.getName(),testClass.getClassName());
        assertEquals("ClassName","OuterClassInfoTest$TestClass",testClass.getShortClassName());
	    assertEquals("MethodName","getMethodName",testClass.getMethodName());
	    assertEquals("Class",TestClass.class,testClass.getOuterClass());
	    assertEquals("StaticClass",OuterClassInfoTest.class, getStaticOuterClass());
    }
}