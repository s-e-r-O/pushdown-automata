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
	
	public boolean check(State q, Character i, Character[] z) {
		return q.equals(this.start) && i.equals(this.input) && z[0].equals(this.stackValue);
	}
	
	public ArrayList<ArrayList<Character>> modifyStack(ArrayList<ArrayList<Character>> stacks){
		ArrayList<ArrayList<Character>> newStacks = new ArrayList<ArrayList<Character>>();
		ArrayList<Character> newStack = new ArrayList<Character>(stacks.get(0));	
		if (this.stackAction == StackAction.REMOVE) {
			newStack.remove(newStack.size()-1);
			newStacks.add(newStack);
		} else if(this.stackAction == StackAction.DO_NOTHING) {
			newStacks.add(newStack);
		} else {
			newStack.add(this.stackAction);
			newStacks.add(newStack);				
		}
		return newStacks;
	}
}
