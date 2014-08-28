package org.drorzz.utils;

/**
 * @author Drorzz
 * @since 28.08.2014.
 */
public class OuterClassInfo {

    private static StackTraceElement getClassInformer(){
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String className = OuterClassInfo.class.getName();
        for(StackTraceElement element:stack){
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
}
