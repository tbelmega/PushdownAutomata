package de.tbelmega.automata;

import de.tbelmega.automata.state.State;
import de.tbelmega.automata.state.StateStackPair;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Thiemo on 16.02.2016.
 */
public class PushdownAutomaton extends NondeterministicFiniteAutomaton {

    private Stack<Character> stack = new Stack<>();

    @Override
    public boolean emulate(String inputWord){
        Set<State> startStates = evaluateStartStates();

        Set<State> endStates = processInputWord(inputWord, startStates);

        boolean accept = endStatesContainsAnAcceptingState(endStates) && stack.isEmpty();

        return accept;
    }

    /**
     * Returns a set with the start state and an empty stack,
     * and all state/stack combinations that can be reached with epsilon transitions from the start state.
     */
    private Set<State> evaluateStartStates() {
        Set<State> startStates = new HashSet<>();
        startStates.add(new StateStackPair(startState, stack));
        startStates.addAll(getAllTargetStates(startStates, EPSILON));
        return startStates;
    }

}
