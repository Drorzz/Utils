package org.drorzz.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testAll() {
	    String fileName = OuterClassInfoTest.class.getSimpleName().concat(".java");

	    assertEquals("FileName",    fileName,                           OuterClassInfo.getFileName());
	    assertEquals("ClassName",   OuterClassInfoTest.class.getName(), OuterClassInfo.getClassName());
	    assertEquals("ClassName",   "OuterClassInfoTest",               OuterClassInfo.getShortClassName());
	    assertEquals("MethodName",  "testAll",                    OuterClassInfo.getMethodName());
	    assertEquals("Class",       OuterClassInfoTest.class,           OuterClassInfo.getOuterClass());

        TestClass testClass = new TestClass();

        assertEquals("Inner-FileName",    fileName,                       testClass.getFileName());
        assertEquals("Inner-ClassName",   TestClass.class.getName(),      testClass.getClassName());
        assertEquals("Inner-ClassName",   "OuterClassInfoTest$TestClass", testClass.getShortClassName());
	    assertEquals("Inner-MethodName",  "getMethodName",                testClass.getMethodName());
	    assertEquals("Inner-Class",       TestClass.class,                testClass.getOuterClass());
    }
}