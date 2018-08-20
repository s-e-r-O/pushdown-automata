package upb.automata.pushdown;

import java.util.ArrayList;

public class Automata {
	ArrayList<State> states;
	ArrayList<Character> inputAlphabet;
	ArrayList<Character> stackAlphabet;
	ArrayList<TransitionFunction> transitionRelation;
	State startState;
	Character startStackSymbol;
	ArrayList<State> finalStates;
}
