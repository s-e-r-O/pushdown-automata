package upb.automata.pushdown;

public class State {
	String id;
	State(String id){
		this.id = id;
	}
	public boolean equals(Object o) {
		if(!(o instanceof State))
	        return false;
	    State other = (State)o;
		return other.id.equals(this.id);
	}
}
