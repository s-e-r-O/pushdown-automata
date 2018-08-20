package upb.automata.pushdown;

public class State {
	String id;
	State(String id){
		this.id = id;
	}
	public boolean equals(State s) {
		return this.id == s.id;
	}
}
