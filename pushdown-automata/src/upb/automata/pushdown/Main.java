package upb.automata.pushdown;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Automata a = new Automata();
		State p = new State("p");
		State q = new State("q");
		State r = new State("r");
		a.startStackSymbol = 'Z';
		a.states = new ArrayList<State>();
		a.states.add(p);
		a.states.add(q);
		a.states.add(r);
		a.finalStates = new ArrayList<State>();
		a.finalStates.add(r);
		a.startState = p;
		a.transitionRelation = new ArrayList<TransitionFunction>();
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'Z', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'A', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, 'b', 'A', q, Automata.LAMBDA));
		a.transitionRelation.add(new TransitionFunction(p, Automata.EPSILON, 'A', q, Automata.NOTHING));
		a.transitionRelation.add(new TransitionFunction(q, 'b', 'A', q, Automata.LAMBDA));
		a.transitionRelation.add(new TransitionFunction(q, Automata.EPSILON, 'Z', r, Automata.NOTHING));
		Simulator s = new Simulator(a);
		s.run("aaaabbbb");
	}

}
