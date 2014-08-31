package org.drorzz.utils.reflectionUtils;

import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Drorzz
 * @since 30.08.14.
 */
public class GenericUtils {
	private static abstract class Executed<E> {
		protected E resultValue;

		public E getResultValue() {
			return resultValue;
		}

		public abstract void doExecute(Class<?> clazz, ParameterizedType parameterizedType, Type[] params);

		public abstract boolean hasResultValue();

		public abstract boolean needAll();
	}

	private static class TypeExecuted extends Executed<Type> {
		@Override
		public void doExecute(Class<?> clazz, ParameterizedType parameterizedType, Type[] params) {
			resultValue = params[0];
		}

		@Override
		public boolean hasResultValue() {
			return resultValue != null;
		}

		@Override
		public boolean needAll() {
			return !hasResultValue();
		}
	}

	private static class MapExecuted extends Executed<Map<Class<?>, List<Class<?>>>> {


		public MapExecuted(Map<Class<?>, List<Class<?>>> result) {
			this.resultValue = result;
		}

		@Override
		public void doExecute(Class<?> clazz, ParameterizedType parameterizedType, Type[] params) {
			List<Class<?>> paramsList = new ArrayList<>(params.length);
			for (Type param : params) {
				paramsList.add(getClassFromType(clazz,param));
			}
			resultValue.put((Class<?>) parameterizedType.getRawType(), paramsList);
		}

		@Override
		public boolean hasResultValue() {
			return !resultValue.isEmpty();
		}

		@Override
		public boolean needAll() {
			return true;
		}
	}

	private static Class<?> getClassFromType(Class<?> clazz, Type param){
		if (param instanceof LazyReflectiveObjectGenerator) { // when have generic in created object, like ArrayList<String>
			throw new IllegalArgumentException("No generic defined on ".concat(clazz.getSimpleName()));
		}
		return (Class<?>) param;
	}

	private static boolean extractGenerics(Class<?> clazz, Type candidate, Executed e) {
		if (candidate instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) candidate;
			Type[] params = parameterizedType.getActualTypeArguments();

			if ((params != null) && (params.length >= 1)) {
				e.doExecute(clazz, parameterizedType, params);
				if (!e.needAll()) {
					return true;
				}
			}
		}
		return false;
	}

	private static void abstractExtractGenericsOnSuperclass(Class<?> clazz, Executed e) {
		if (clazz == null)
			throw new IllegalArgumentException("Clazz may not be null");

		Type classGenType = clazz.getGenericSuperclass();

		if (extractGenerics(clazz, classGenType, e)) return;

		for (Type ifGenType : clazz.getGenericInterfaces()) {
			if (extractGenerics(clazz, ifGenType, e)) return;
		}

		if (!e.hasResultValue()) {
			throw new IllegalArgumentException("No generic found on ".concat(clazz.getSimpleName()));
		}
	}

	public static Map<Class<?>, List<Class<?>>> getAllGenericsOnSuperclass(Class<?> clazz) {
		MapExecuted executed = new MapExecuted(new HashMap<Class<?>, List<Class<?>>>());
		abstractExtractGenericsOnSuperclass(clazz, executed);
		return executed.getResultValue();
	}

	public static Class<?> getFirstGenericOnSuperclass(Class<?> clazz) {
		TypeExecuted executed = new TypeExecuted();
		abstractExtractGenericsOnSuperclass(clazz, executed);
		return getClassFromType(clazz,executed.getResultValue());
	}
}
