package upb.automata.pushdown;

import java.util.ArrayList;

public class TransitionFunction {
	State start;
	Character input;
	Character stackValue;
	State step;
	Character stackAction;
	
	TransitionFunction(State start, Character input, Character stackValue, State step, Character stackAction){
		this.start = start;
		this.input = input;
		this.stackValue = stackValue;
		this.step = step;
		this.stackAction = stackAction;
	}
	
	public boolean check(State q, Character i, Character z) {
		return q.equals(this.start) && i == this.input && z == this.stackValue;
	}
	
	public ArrayList<Character> modifyStack(ArrayList<Character> stack){
		ArrayList<Character> newStack = new ArrayList<Character>(stack);
		if (this.stackAction == Automata.LAMBDA) {
			newStack.remove(newStack.size()-1);
			return newStack;
		} else if(this.stackAction == Automata.NOTHING) {
			return newStack;
		}
		newStack.add(this.stackAction);
		return newStack;
	}
}
