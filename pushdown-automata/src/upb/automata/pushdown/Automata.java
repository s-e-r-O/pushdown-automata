package upb.automata.pushdown;

import java.util.ArrayList;

public class Automata {
	public static final Character LAMBDA = '-';
	public static final Character EPSILON = '-';
	
	String description;
	ArrayList<State> states;
	ArrayList<Character> inputAlphabet;
	ArrayList<ArrayList<Character>> stackAlphabet;
	ArrayList<TransitionFunction> transitionRelation;
	State startState;
	ArrayList<Character> startStackSymbols;
	ArrayList<State> finalStates;
	
	public ArrayList<ArrayList<Character>> initializeStack(){
		ArrayList<ArrayList<Character>> stacks = new ArrayList<ArrayList<Character>>();
		for (Character startStackSymbol : this.startStackSymbols) {
			ArrayList<Character> stack = new ArrayList<Character>();
			stack.add(startStackSymbol);
			stacks.add(stack);
		}
		return stacks;			
	}
}
