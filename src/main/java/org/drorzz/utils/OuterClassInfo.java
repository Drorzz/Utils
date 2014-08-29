package org.drorzz.utils;

/**
 * @author Drorzz
 * @since 28.08.14.
 */
public class OuterClassInfo {

	private final static int MIN_DEPTH = 2;
	private final static int MAX_DEPTH = 3;

	private static StackTraceElement getClassInformer(){
	    AccessToNativeStackTrace throwable = new AccessToNativeStackTrace();
        String className = OuterClassInfo.class.getName();
	    StackTraceElement element;
	    for(int i = MIN_DEPTH; i <= MAX_DEPTH; i++){
		    element = throwable.getStackTraceElement(i);
            if(!className.equals(element.getClassName())){
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

	public static Class<?> getOuterClass(){
		try {
			return Class.forName(getClassName());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}

