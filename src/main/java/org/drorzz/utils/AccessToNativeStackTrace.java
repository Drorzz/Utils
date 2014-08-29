package org.drorzz.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Drorzz
 * @since  29.08.14.
 */
public class AccessToNativeStackTrace extends Throwable{
	private static Method nativeGetStackTraceElementMethod;
	private static Method nativeGetStackTraceDepthMethod;

	public AccessToNativeStackTrace() {
	}

	private static Method getSuperclassDeclaredMethod(String name,  Class<?>... parameterTypes){
		Method method;
		try {
			method = Throwable.class.getDeclaredMethod(name,parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		method.setAccessible(true);
		return method;
	}

	private static Method initNativeStackTraceElementMethod(){
		return getSuperclassDeclaredMethod("getStackTraceElement", int.class);
	}

	private static Method initNativeGetStackTraceDepthMethod(){
		return getSuperclassDeclaredMethod("getStackTraceDepth");
	}


	private static Method getNativeGetStackTraceElementMethod(){
		if(nativeGetStackTraceElementMethod == null){
			nativeGetStackTraceElementMethod = initNativeStackTraceElementMethod();
		}
		return nativeGetStackTraceElementMethod;
	}

	private static Method getNativeGetStackTraceDepthMethod(){
		if(nativeGetStackTraceDepthMethod == null){
			nativeGetStackTraceDepthMethod = initNativeGetStackTraceDepthMethod();
		}
		return nativeGetStackTraceDepthMethod;
	}

	private <E> E invokeNativeMethod(Method method, Object... args){
		try {
			return (E) method.invoke(this,args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public StackTraceElement getStackTraceElement(int index) {
		return invokeNativeMethod(getNativeGetStackTraceElementMethod(),index);
	}

	public int getStackTraceDepth() {
		return invokeNativeMethod(getNativeGetStackTraceDepthMethod());
	}
}

