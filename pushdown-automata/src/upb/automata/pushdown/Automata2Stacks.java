package upb.automata.pushdown;

import java.util.ArrayList;

public class Automata2Stacks extends Automata {
	ArrayList<Character> stack2Alphabet;
	Character startStack2Symbol;

	public ArrayList<ArrayList<Character>> initializeStack(){
		ArrayList<ArrayList<Character>> stacks = super.initializeStack();
		ArrayList<Character> stack = new ArrayList<Character>();
		stack.add(this.startStack2Symbol);
		stacks.add(stack);
		return stacks;
	}
}
