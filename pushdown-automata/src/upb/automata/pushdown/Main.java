package upb.automata.pushdown;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Automata a = new Automata();
		State p = new State("p");
		State q = new State("q");
		State r = new State("r");
		a.states = new ArrayList<State>();
		a.states.add(p);
		a.states.add(q);
		a.states.add(r);
		a.finalStates = new ArrayList<State>();
		a.finalStates.add(r);
		a.startState = p;
		a.transitionRelation = new ArrayList<TransitionFunction>();
		a.transitionRelation.add(new TransitionFunction(p, '0', 'Z', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, '0', 'A', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, '1', 'A', q, '-'));
		a.transitionRelation.add(new TransitionFunction(p, '-', 'A', q, '/'));
		a.transitionRelation.add(new TransitionFunction(q, '1', 'A', q, '-'));
		a.transitionRelation.add(new TransitionFunction(q, '-', 'Z', r, '/'));
		Simulator s = new Simulator(a);
		s.run("0011");
	}

}
