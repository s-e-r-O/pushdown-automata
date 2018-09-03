package upb.automata.pushdown;

import java.util.ArrayList;

public class Step {
	public State state;
	public String input;
	public ArrayList<ArrayList<Character>> stacks;
	public TransitionFunction futureTransitionFunction;
	
	public Step(State state, String input, ArrayList<ArrayList<Character>> stacks, 
			TransitionFunction futureTransitionFunction) {
		this.state = state;
		if (input.length() > 0) {
			this.input = input;			
		} else {
			this.input = ""+Automata.EPSILON;
		}
		this.stacks = stacks;
		this.futureTransitionFunction = futureTransitionFunction;
	}
	
	public Step(State state, String input, ArrayList<ArrayList<Character>> stacks) {
		this.state = state;
		this.input = input;
		this.stacks = stacks;
		this.futureTransitionFunction = null;
	}
	
	public String toString() {
		if (this.futureTransitionFunction == null) {
			return "{ " + this.state.id + ", " + this.input + ", " + this.stacks.toString() + " }";
		}
		return "{ " + this.state.id + ", " + this.input + ", " + this.stacks.toString() + ", " + this.futureTransitionFunction.toString() + " }";
	}
}
