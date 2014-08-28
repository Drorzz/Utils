package org.drorzz.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Drorzz
 * @since 28.08.2014.
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
    }

    @Test
    public void testAll() {
        TestClass testClass = new TestClass();

        assertEquals("FileName","OuterClassInfoTest.java",testClass.getFileName());
        assertEquals("ClassName",TestClass.class.getName(),testClass.getClassName());
        assertEquals("ClassName","OuterClassInfoTest$TestClass",testClass.getShortClassName());
        assertEquals("MethodName","getMethodName",testClass.getMethodName());
    }
}