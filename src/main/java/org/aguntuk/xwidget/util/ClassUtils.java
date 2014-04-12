/**
The MIT License (MIT)

Copyright (c) <year> <copyright holders>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package org.aguntuk.xwidget.util;

import java.util.HashMap;
import java.util.Map;

public enum ClassUtils {
	INSTANCE;
	private enum ClassType {
		BYTE, SHORT, STRING, INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR, BYTE_ARRAY, SHORT_ARRAY, STRING_ARRAY, INTEGER_ARRAY,
		LONG_ARRAY, FLOAT_ARRAY, DOUBLE_ARRAY, BOOLEAN_ARRAY, CHAR_ARRAY, BYTE_PRIMITIVE_ARRAY, SHORT_PRIMITIVE_ARRAY, INT_PRIMITIVE_ARRAY,
		LONG_PRIMITIVE_ARRAY, FLOAT_PRIMITIVE_ARRAY, DOUBLE_PRIMITIVE_ARRAY, BOOLEAN_PRIMITIVE_ARRAY, CHAR_PRIMITIVE_ARRAY;
	};
	private Map<String, ClassType> wrapperClasses = null;
	private Map<String, ClassType> wrapperClassArray = null;
	ClassUtils() {
		//Wrapper classes and arrays
		wrapperClasses = new HashMap<String, ClassType>();
		wrapperClasses.put("java.lang.Byte", ClassType.BYTE);
		wrapperClasses.put("java.lang.Short", ClassType.SHORT);
		wrapperClasses.put("java.lang.String",ClassType.STRING);
		wrapperClasses.put("java.lang.Integer",ClassType.INTEGER);
		wrapperClasses.put("java.lang.Long",ClassType.LONG);
		wrapperClasses.put("java.lang.Float",ClassType.FLOAT);
		wrapperClasses.put("java.lang.Double",ClassType.DOUBLE);
		wrapperClasses.put("java.lang.Boolean",ClassType.BOOLEAN);
		wrapperClasses.put("java.lang.Character",ClassType.CHAR);
		wrapperClasses.put(Byte.TYPE.getName(),ClassType.BYTE);
		wrapperClasses.put(Short.TYPE.getName(),ClassType.SHORT);
		wrapperClasses.put(Integer.TYPE.getName(),ClassType.INTEGER);
		wrapperClasses.put(Long.TYPE.getName(),ClassType.LONG);
		wrapperClasses.put(Float.TYPE.getName(),ClassType.FLOAT);
		wrapperClasses.put(Double.TYPE.getName(),ClassType.DOUBLE);
		wrapperClasses.put(Boolean.TYPE.getName(),ClassType.BOOLEAN);
		wrapperClasses.put(java.lang.Character.TYPE.getName(),ClassType.CHAR);		
		
		wrapperClassArray = new HashMap<String, ClassType>();
		wrapperClassArray.put("[Ljava.lang.Byte;", ClassType.BYTE_ARRAY);
		wrapperClassArray.put("[Ljava.lang.Short;", ClassType.SHORT_ARRAY);
		wrapperClassArray.put("[Ljava.lang.String;",ClassType.STRING_ARRAY);
		wrapperClassArray.put("[Ljava.lang.Integer;",ClassType.INTEGER_ARRAY);
		wrapperClassArray.put("[Ljava.lang.Long;",ClassType.LONG_ARRAY);
		wrapperClassArray.put("[Ljava.lang.Float;",ClassType.FLOAT_ARRAY);
		wrapperClassArray.put("[Ljava.lang.Double;",ClassType.DOUBLE_ARRAY);
		wrapperClassArray.put("[Ljava.lang.Boolean;",ClassType.BOOLEAN_ARRAY);
		wrapperClassArray.put("[Ljava.lang.Character;",ClassType.CHAR_ARRAY);
		
		wrapperClassArray.put("[B",ClassType.BYTE_PRIMITIVE_ARRAY);
		wrapperClassArray.put("[S",ClassType.SHORT_PRIMITIVE_ARRAY);
		wrapperClassArray.put("[I",ClassType.INT_PRIMITIVE_ARRAY);
		wrapperClassArray.put("[J",ClassType.LONG_PRIMITIVE_ARRAY);
		wrapperClassArray.put("[F",ClassType.FLOAT_PRIMITIVE_ARRAY);
		wrapperClassArray.put("[D",ClassType.DOUBLE_PRIMITIVE_ARRAY);
		wrapperClassArray.put("[Z",ClassType.BOOLEAN_PRIMITIVE_ARRAY);
		wrapperClassArray.put("[C",ClassType.CHAR_PRIMITIVE_ARRAY);		
	}

	public Object fromStringToOtherType(String s, String targetType) {
		Object retVal = null;
		ClassType type = wrapperClasses.get(targetType) ;
		switch(type) {
			case BYTE:
				retVal = Byte.valueOf(s);
				break;
			case SHORT:
				retVal = Short.valueOf(s);
				break;
			case STRING:
				retVal = s;
				break;
			case INTEGER:
				retVal = Integer.valueOf(s);
				break;
			case LONG:
				retVal = Long.valueOf(s);
				break;
			case BOOLEAN:
				retVal = Boolean.valueOf(s);
				break;
			case CHAR:
				retVal = Character.valueOf(s.charAt(0));
				break;
			case FLOAT:
				retVal = Float.valueOf(s);
				break;
			case DOUBLE:
				retVal = Double.valueOf(s);
				break;
		}
		return retVal;
	}
	
	public Object fromStringObjectToOtherArrayType(String[] s, String targetType) {
		Object retVal = null;
		Object[] newArray = null;
		ClassType type = wrapperClassArray.get(targetType);
		switch(type) {
			case BOOLEAN_ARRAY:
				newArray = convert(s, "java.lang.Boolean");
				retVal = makeArray(newArray, new Boolean[newArray.length]);
				break;
			case BOOLEAN_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Boolean");
				boolean[] booleanArray = new boolean[newArray.length];
				for(int i = 0; i < booleanArray.length; ++i) {
					booleanArray[i] = (Boolean)newArray[i];
				}				
				retVal = booleanArray;
				break;
			case BYTE_ARRAY:
				newArray = convert(s, "java.lang.Byte");
				retVal = makeArray(newArray, new Byte[newArray.length]);
				break;
			case BYTE_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Byte");
				byte[] byteArray = new byte[newArray.length];
				for(int i = 0; i < byteArray.length; ++i) {
					byteArray[i] = (Byte)newArray[i];
				}				
				retVal = byteArray;
				break;
			case CHAR_ARRAY:
				newArray = convert(s, "java.lang.Character");
				retVal = makeArray(newArray, new Character[newArray.length]);
				break;
			case CHAR_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Character");
				char[] charArray = new char[newArray.length];
				for(int i = 0; i < charArray.length; ++i) {
					charArray[i] = (Character)newArray[i];
				}				
				retVal = charArray;
				break;
			case DOUBLE_ARRAY:
				newArray = convert(s, "java.lang.Double");
				retVal = makeArray(newArray, new Double[newArray.length]);
				break;
			case DOUBLE_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Double");
				double[] doubleArray = new double[newArray.length];
				for(int i = 0; i < doubleArray.length; ++i) {
					doubleArray[i] = (Double)newArray[i];
				}				
				retVal = doubleArray;
				break;
			case FLOAT_ARRAY:
				newArray = convert(s, "java.lang.Float");
				retVal = makeArray(newArray, new Float[newArray.length]);
				break;
			case FLOAT_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Float");
				float[] floatArray = new float[newArray.length];
				for(int i = 0; i < floatArray.length; ++i) {
					floatArray[i] = (Float)newArray[i];
				}				
				retVal = floatArray;
				break;
			case INTEGER_ARRAY:
				newArray = convert(s, "java.lang.Integer");
				retVal = makeArray(newArray, new Integer[newArray.length]);
				break;
			case INT_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Integer");
				int[] intArray = new int[newArray.length];
				for(int i = 0; i < intArray.length; ++i) {
					intArray[i] = (Integer)newArray[i];
				}				
				retVal = intArray;
				break;
			case LONG_ARRAY:
				newArray = convert(s, "java.lang.Long");
				retVal = makeArray(newArray, new Long[newArray.length]);
				break;
			case LONG_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Long");
				long[] longArray = new long[newArray.length];
				for(int i = 0; i < longArray.length; ++i) {
					longArray[i] = (Long)newArray[i];
				}				
				retVal = longArray;
				break;
			case SHORT_ARRAY:
				newArray = convert(s, "java.lang.Short");
				retVal = makeArray(newArray, new Short[newArray.length]);
				break;
			case SHORT_PRIMITIVE_ARRAY:
				newArray = convert(s, "java.lang.Short");
				short[] shortArray = new short[newArray.length];
				for(int i = 0; i < shortArray.length; ++i) {
					shortArray[i] = (Short)newArray[i];
				}				
				retVal = shortArray;
				break;
			case STRING_ARRAY:
				retVal = s;
				break;
		}
		return retVal;
	}

	private Object makeArray(Object[] newArray, Object object) {
		System.arraycopy(newArray, 0, object, 0, newArray.length);
		return object;
	}
	
	private Object[] convert(String[] s, String className) {
		Object[] retVal = new Object[s.length];
		for(int i = 0; i < s.length; ++i) {
			retVal[i] = fromStringToOtherType(s[i], className);
		}
		return retVal;
	}
	
	
	public boolean isArrayType(String className) {
		return wrapperClassArray.containsKey(className);
	}
	

	
	
	
	

}
