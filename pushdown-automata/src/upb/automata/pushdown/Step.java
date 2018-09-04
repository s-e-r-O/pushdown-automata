package upb.automata.pushdown;

import java.util.ArrayList;

public class Step {
	
	public static final Integer ACCEPTED_STATE_STEP = 0;
	public static final Integer EMPTY_STACK_STEP = 1;
	public static final Integer RULE_NOT_FOUND = 2;
	public static final Integer RULE_FOUND = 3;
	
	public State state;
	public String input;
	public ArrayList<ArrayList<Character>> stacks;
	public TransitionFunction futureTransitionFunction;
	public Integer status;
	
	public Step(State state, String input, ArrayList<ArrayList<Character>> stacks, 
			TransitionFunction futureTransitionFunction, Integer status) {
		this.state = state;
		this.input = input;
		this.stacks = stacks;
		this.futureTransitionFunction = futureTransitionFunction;
		this.status = status;
	}
	
	public Step(State state, String input, ArrayList<ArrayList<Character>> stacks, Integer status) {
		this.state = state;
		this.input = input;
		this.stacks = stacks;
		this.futureTransitionFunction = null;
		this.status = status;
	}
	
	public String toString() {
		if (this.futureTransitionFunction == null) {
			return "{ " + this.state.id + ", " + this.input + ", " + this.stacks.toString() + " }";
		}
		return "{ " + this.state.id + ", " + this.input + ", " + this.stacks.toString() + ", " + this.futureTransitionFunction.toString() + " }";
	}
}
