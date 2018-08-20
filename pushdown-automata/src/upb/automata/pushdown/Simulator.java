package upb.automata.pushdown;

import java.util.ArrayList;

public class Simulator {
	Automata automata;
	
	public Simulator(Automata automata){
		this.automata = automata;		
	}
	
	public void run(String input) {
		ArrayList<Character> stack = new ArrayList<Character>();
		stack.add('Z');
		this.verify(this.automata.startState, input, 0, stack);
	}
	
	public boolean verify(State state, String input, Integer index, ArrayList<Character> stack) {
		Character i;
		String inputLeft;
		if (index >= input.length()) {
			i = '-';
			inputLeft = "-";
			if (this.automata.finalStates.indexOf(state) >= 0) {
				return true;
			}
		} else {
			inputLeft = input.substring(index);
			i = input.charAt(index);
		}
		Character z = stack.get(stack.size()-1);
		System.out.println("Stack: " + stack.toString());
		System.out.println("From: (" + state.id + ", " + inputLeft + ", " + z + ")");
		for (int c = 0; c < this.automata.transitionRelation.size(); c++ ) {
			TransitionFunction transition = this.automata.transitionRelation.get(c);
			if (transition.check(state, i, z)) {
				return this.verify(transition.end, input, index + 1, transition.modifyStack(stack));
			}			
		}
		return false;
	}
	
}
