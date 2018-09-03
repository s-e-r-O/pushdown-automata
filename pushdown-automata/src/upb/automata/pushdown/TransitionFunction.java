package upb.automata.pushdown;

import java.util.ArrayList;
import java.util.Arrays;

public class TransitionFunction {	
	State start;
	Character input;
	Character[] stackValues;
	State step;
	Character[] stackActions;
	
	TransitionFunction(State start, Character input, Character[] stackValues, 
			State step, Character[] stackActions){
		this.start = start;
		this.input = input;
		this.stackValues = stackValues;
		this.step = step;
		this.stackActions = stackActions;
	}
	
	public String toString() {
		return "<" + this.start.id + ", " + this.input + ", " + Arrays.toString(this.stackValues) + ", " + this.step.id + ", " + Arrays.toString(this.stackActions) + ">";
	}
	
	public boolean check(State q, Character i, Character[] z) {
		return q.equals(this.start) && i.equals(this.input) && Arrays.equals(this.stackValues, z);
	}
	
	public ArrayList<ArrayList<Character>> modifyStack(ArrayList<ArrayList<Character>> stacks){
		ArrayList<ArrayList<Character>> newStacks = new ArrayList<ArrayList<Character>>();
		for (int i = 0; i < this.stackActions.length; i++) {
			ArrayList<Character> newStack = new ArrayList<Character>(stacks.get(i));	
			if (this.stackActions[i] == StackAction.REMOVE) {
				newStack.remove(newStack.size()-1);
				newStacks.add(newStack);
			} else if(this.stackActions[i] == StackAction.DO_NOTHING) {
				newStacks.add(newStack);
			} else {
				newStack.add(this.stackActions[i]);
				newStacks.add(newStack);				
			}			
		}
		return newStacks;
	}
}
