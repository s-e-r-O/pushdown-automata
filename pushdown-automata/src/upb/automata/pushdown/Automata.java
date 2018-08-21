package upb.automata.pushdown;

import java.util.ArrayList;

public class Automata {
	
	public static final Character LAMBDA = '-';
	public static final Character EPSILON = '-';
	public static final Character NOTHING = '/';
	
	ArrayList<State> states;
	ArrayList<Character> inputAlphabet;
	ArrayList<Character> stackAlphabet;
	ArrayList<TransitionFunction> transitionRelation;
	State startState;
	Character startStackSymbol;
	ArrayList<State> finalStates;
}
