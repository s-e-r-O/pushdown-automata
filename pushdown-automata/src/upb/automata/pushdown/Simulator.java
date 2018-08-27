package upb.automata.pushdown;

import java.util.ArrayList;

public class Simulator {
	private Automata automata;
	
	public Simulator(){
		this.automata = null;
	}
	
	public Simulator(Automata automata){
		this.automata = automata;
		if (automata.description != null) {
			System.out.println("Using: '" + automata.description + "'");
		}
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
		System.out.print("Testing '" + input + "': ");
		if (this.verify(this.automata.startState, input, this.automata.initializeStack())) {
			System.out.println("Good");
			return true;
		}
		System.out.println("Bad");
		return false;
	}
	
	public boolean verify(State state, String input, ArrayList<ArrayList<Character>> stacks) {
		Character i = input.length() > 0? input.charAt(0) : Automata.EPSILON; 
		Character[] z = this.getLastValues(stacks);
		if (i == Automata.EPSILON) {
			if (this.automata.finalStates.indexOf(state) >= 0 || checkIfEmpty(z)) {
				return true;
			}
		}
		for (TransitionFunction transition : this.automata.transitionRelation) {
			if ((transition.check(state, Automata.EPSILON, z) 
					&& this.verify(transition.step, input, transition.modifyStack(stacks)))
					|| (i != Automata.EPSILON && transition.check(state, i, z) 
					&& this.verify(transition.step, input.substring(1), transition.modifyStack(stacks)))) {
				return true;
			}			
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
