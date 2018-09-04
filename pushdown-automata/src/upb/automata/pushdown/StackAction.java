package upb.automata.pushdown;

import upb.automata.pushdown.util.Formatter;

public class StackAction {
	public static final Character REMOVE = '-';
	public static final Character DO_NOTHING = '/';
	
	public Character action;
	private Character value;
	
	public StackAction(Character value, Character action) {
		this.value = value;
		this.action = action;
	}
	
	@Override
	public String toString() {
		return Formatter.actionToString(value, action);
	}
}
