package org.drorzz.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Drorzz
 * @since 28.08.2014.
 */
public class OuterClassInfo {
	private static class EmptyStackTraceThrowable extends Throwable{
		public EmptyStackTraceThrowable() {
		}
	}

	private final static int MAX_DEPTH = 3;
	private final static Method nativeStackTraceElementMethod = initNativeStackTraceElementMethod();

	private static Method initNativeStackTraceElementMethod(){
		Method method;
		try {

			method = EmptyStackTraceThrowable.class.getSuperclass().getDeclaredMethod("getStackTraceElement", int.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		method.setAccessible(true);
		return method;
	}

	private static StackTraceElement getStackTraceElement(Throwable exception,int index) {
		try {
			return (StackTraceElement)nativeStackTraceElementMethod.invoke(exception,index);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

    private static StackTraceElement getClassInformer(){
	    EmptyStackTraceThrowable emptyThrowable = new EmptyStackTraceThrowable();
        String className = OuterClassInfo.class.getName();
	    StackTraceElement element;
	    for(int i = 2; i <= MAX_DEPTH; i++){
		    element = getStackTraceElement(emptyThrowable,i);
            if(!className.equals(element.getClassName())){
//	            System.out.println(i);
	            return element;
            }
        }
        throw new RuntimeException("Not found outer class.");
    }

    public static String getFileName() {
        return getClassInformer().getFileName();
    }
    public static String getClassName() {
        return getClassInformer().getClassName();
    }

    public static String getShortClassName() {
        String fullClassName = getClassName();

        int position = fullClassName.lastIndexOf(".");
        if(position == -1){
            return fullClassName;
        }else{
            return fullClassName.substring(position+1,fullClassName.length());
        }
    }

    public static String getMethodName() {
        return getClassInformer().getMethodName();
    }
}

