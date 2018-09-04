package upb.automata.pushdown;

import java.util.ArrayList;
import java.util.Collections;

public class Simulator {
	private Automata automata;
	private ArrayList<Step> correctSteps;
	private ArrayList<Step> allSteps;
	
	public Simulator(){
		this.automata = null;
		this.correctSteps = new ArrayList<Step>();
		this.allSteps = new ArrayList<Step>();
		
	}
	
	public Simulator(Automata automata){
		this.automata = automata;
		this.correctSteps = new ArrayList<Step>();
		this.allSteps = new ArrayList<Step>();
	}
	
	public ArrayList<Step> getCorrectSteps(){
		ArrayList<Step> reversed = new ArrayList<Step>(this.correctSteps);
		Collections.reverse(reversed);
		return reversed;
	}

	public ArrayList<Step> getAllSteps(){
		return this.allSteps;
	}
	
	public void setAutomata(Automata automata) {
		this.automata = automata;
		if (automata.description != null) {
			System.out.println("Using: '" + automata.description + "'");
		}		
	}
	
	public boolean run(String input) {
		if (this.automata == null) {
			System.out.println("Automata not defined");
			return false;
		}
		this.allSteps.clear();
		this.correctSteps.clear();
		return this.verify(this.automata.startState, input, this.automata.initializeStack());
	}
	
	public boolean verify(State state, String input, ArrayList<ArrayList<Character>> stacks) {
		Character i = input.length() > 0? input.charAt(0) : Automata.EPSILON; 
		Character[] z = this.getLastValues(stacks);
		if (i == Automata.EPSILON) {
			if (this.automata.finalStates.indexOf(state) >= 0) {
				this.allSteps.add(new Step(state, input, stacks, Step.ACCEPTED_STATE_STEP));
				this.correctSteps.add(new Step(state, input, stacks, Step.ACCEPTED_STATE_STEP));
				return true;
			} 
			if (checkIfEmpty(z)) {
				this.allSteps.add(new Step(state, input, stacks, Step.EMPTY_STACK_STEP));
				this.correctSteps.add(new Step(state, input, stacks, Step.EMPTY_STACK_STEP));
				return true;
			}
		}
		boolean found = false;
		for (TransitionFunction transition : this.automata.transitionRelation) {
			if (transition.check(state, Automata.EPSILON, z)){
				found = true;
				this.allSteps.add(new Step(state, input, stacks, transition, Step.RULE_FOUND));
				if (this.verify(transition.step, input, transition.modifyStack(stacks))) {
					this.correctSteps.add(new Step(state, input, stacks, transition, Step.RULE_FOUND));
					return true;
				}
			} 
			if (i != Automata.EPSILON && transition.check(state, i, z)){
				found = true;
				this.allSteps.add(new Step(state, input, stacks, transition, Step.RULE_FOUND));
				if (this.verify(transition.step, input.substring(1), transition.modifyStack(stacks))) {
					this.correctSteps.add(new Step(state, input, stacks, transition, Step.RULE_FOUND));
					return true;
				}
			}			
		}
		if (!found) {
			this.allSteps.add(new Step(state, input, stacks, Step.RULE_NOT_FOUND));			
		}
		return false;
	}
	
	private boolean checkIfEmpty(Character[] z) {
		for (Character c : z) {
			if (c != Automata.LAMBDA) {
				return false;
			}
		}
		return true;
	}
	
	private Character[] getLastValues(ArrayList<ArrayList<Character>> stacks) {
		Character[] z = new Character[stacks.size()]; 
		for (int c = 0; c < z.length; c++){
			ArrayList<Character> stack = stacks.get(c);
			z[c] = stack.size() > 0? stack.get(stack.size()-1) : Automata.LAMBDA;
		};
		return z;
	}
}
