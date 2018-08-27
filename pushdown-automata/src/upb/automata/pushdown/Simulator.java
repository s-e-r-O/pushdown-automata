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
		ArrayList<Character> stack = new ArrayList<Character>();
		stack.add(this.automata.startStackSymbol);
		System.out.print("Testing '" + input + "': ");
		if (this.verify(this.automata.startState, input, stack)) {
			System.out.println("Good");
			return true;
		}
		System.out.println("Bad");
		return false;
	}
	
	public boolean verify(State state, String input, ArrayList<Character> stack) {
		Character i = input.length() > 0? input.charAt(0) : Automata.EPSILON; 
		Character z = stack.size() > 0? stack.get(stack.size()-1) : Automata.LAMBDA;
		if (i == Automata.EPSILON) {
			if (this.automata.finalStates.indexOf(state) >= 0 || z == Automata.LAMBDA) {
				return true;
			}
		}
		for (TransitionFunction transition : this.automata.transitionRelation) {
			if ((transition.check(state, Automata.EPSILON, z) 
					&& this.verify(transition.step, input, transition.modifyStack(stack)))
					|| (i != Automata.EPSILON && transition.check(state, i, z) 
					&& this.verify(transition.step, input.substring(1), transition.modifyStack(stack)))) {
				return true;
			}			
		}
		return false;
	}
	
}
