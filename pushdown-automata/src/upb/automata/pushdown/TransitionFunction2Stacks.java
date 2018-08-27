package upb.automata.pushdown;

import java.util.ArrayList;

public class TransitionFunction2Stacks extends TransitionFunction {
	Character stack2Value;
	Character stack2Action;
	
	TransitionFunction2Stacks(State start, Character input, Character stackValue, 
								Character stack2Value, State step, Character stackAction, Character stack2Action) {
		super(start, input, stackValue, step, stackAction);
		this.stack2Value = stack2Value;
		this.stack2Action = stack2Action;
	}
	
	public boolean check(State q, Character i, Character[] z) {
		return super.check(q, i, z) && z[1].equals(this.stack2Value);
	}
	
	public ArrayList<ArrayList<Character>> modifyStack(ArrayList<ArrayList<Character>> stacks){
		ArrayList<ArrayList<Character>> newStacks = super.modifyStack(stacks);
		ArrayList<Character> newStack = new ArrayList<Character>(stacks.get(1));	
		if (this.stack2Action == StackAction.REMOVE) {
			newStack.remove(newStack.size()-1);
			newStacks.add(newStack);
		} else if(this.stack2Action == StackAction.DO_NOTHING) {
			newStacks.add(newStack);
		} else {
			newStack.add(this.stack2Action);
			newStacks.add(newStack);				
		}
		return newStacks;
	}
}
