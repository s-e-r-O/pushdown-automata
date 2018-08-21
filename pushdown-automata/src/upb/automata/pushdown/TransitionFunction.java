package upb.automata.pushdown;

import java.util.ArrayList;

public class TransitionFunction {
	State start;
	Character input;
	Character stackValue;
	State end;
	Character stackAction;
	
	TransitionFunction(State start, Character input, Character stackValue, State end, Character stackAction){
		this.start = start;
		this.input = input;
		this.stackValue = stackValue;
		this.end = end;
		this.stackAction = stackAction;
	}
	
	public boolean check(State q, Character i, Character z) {
		return q.equals(this.start) && i == this.input && z == this.stackValue;
	}
	
	public ArrayList<Character> modifyStack(ArrayList<Character> stack){
		if (this.stackAction == Automata.LAMBDA) {
			stack.remove(stack.size()-1);
			return stack;
		} else if(this.stackAction == Automata.NOTHING) {
			return stack;
		}
		stack.add(this.stackAction);
		return stack;
	}
}
