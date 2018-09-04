package upb.automata.pushdown.util;

import upb.automata.pushdown.Automata;
import upb.automata.pushdown.StackAction;

public class Formatter {
	
	public static <T> String arrayToString(T[] arr){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
		    builder.append(arr[i]);
		    if (i < arr.length - 1) {
		    	builder.append(", ");
		    }
		}
		return builder.toString();
	}
	
	public static <T> String arrayToStringLines(T[] arr){
		StringBuilder builder = new StringBuilder();
    	builder.append("  ");
    	for (int i = 0; i < arr.length; i++) {
		    builder.append(arr[i]);
		    if (i < arr.length - 1) {
		    	builder.append("\n  ");
		    }
		}
		return builder.toString();
	}
	
	public static String actionToString(Character value, Character action) {
		if (action.equals(StackAction.REMOVE)) {
			return "\u03BB";
		}
		if (action.equals(StackAction.DO_NOTHING)) {
			return value.toString();
		}
		return action + value.toString();
	}
	
	public static String actionToString(Character[] values, Character[] actions) {
		String[] formattedActions = new String[values.length];
		for (int i = 0; i < formattedActions.length; i++) {
			formattedActions[i] = actionToString(values[i], actions[i]);	    
		}
		return arrayToString(formattedActions); 
	}
	
	public static String inputToString(Character input) {
		if (input == null || input.toString().length() == 0 || input.equals(Automata.EPSILON)) {
			return "\u03B5";
		}
		return input.toString(); 
	}
	public static String inputToString(String input) {
		if (input == null || input.length() == 0) {
			return "\u03B5";
		}
		return input; 
	}
}
