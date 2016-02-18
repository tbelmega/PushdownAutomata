package de.tbelmega.automata;

import de.tbelmega.automata.state.SimpleState;
import de.tbelmega.automata.state.State;
import de.tbelmega.automata.state.StateManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thiemo on 03.02.2016.
 */
public abstract class Automaton {

    /**
     * In this program, the empty character (epsilon) is symbolized by the dash character.
     */
    public static final char EPSILON = '-';


    /**
     * The state manager ensures that there is only one state with the same name.     *
     * Use stateManager.getState(name) to get the SimpleState object with a given name.
     */
    private StateManager stateManager = new StateManager();

    protected SimpleState startState;
    protected Set<String> acceptStates = new HashSet<>();

    public SimpleState getStartState() {
        return startState;
    }


    public boolean isAcceptingState(String stateId) {
        for (String s: acceptStates) {
            if (s.equals(stateId)) return true;
        }
        return false;
    }

    /**
     * Emulate this automatonRepresentation's computation of the input word,
     * depending on the type of automatonRepresentation.
     * @return true, iff the automatonRepresentation accepts the input word. false otherwise.
     */
    public abstract boolean emulate(String inputWord);

    /**
     * Set the given state as this automatonRepresentation's start state.
     */
    public void setStartState(int stateName) {
        this.startState = this.stateManager.getState(stateName);
    }
    public void setStartState(String stateName) {
        this.startState = this.stateManager.getState(stateName);
    }

    /**
     * Add a state to this automatonRepresentation's list of accepted states.
     */
    public void addAcceptState(Integer stateName) {
        this.acceptStates.add(stateName.toString());
    }
    public void addAcceptState(String stateName) {
        this.acceptStates.add(stateName.toString());
    }

    /**
     * Create a new transition to targetState. Store it in fromState.
     * This transition does not use stack operations.
     */
    public void addTransition(int fromStateName, char inputChar, int targetStateName) {
        this.addTransition(fromStateName, inputChar, EPSILON, EPSILON, targetStateName);
    }

    /**
     * Create a new transition to targetState with input and stack operations. Store it in fromState.
     */
    public void addTransition(int fromStateName, char inputChar, char popChar, char pushChar, int targetStateName) {
        SimpleState fromState = stateManager.getState(fromStateName);
        SimpleState targetState = stateManager.getState(targetStateName);
        fromState.addTransition(inputChar, popChar, pushChar, targetState);
    }

    public void addTransition(String fromStateName, String transitionRepresentation){
        SimpleState fromState = stateManager.getState(fromStateName);
        TransitionRepresentation rep = new TransitionRepresentation(transitionRepresentation);
        fromState.addTransition(rep, stateManager.getState(rep.toState));
    };


    /**
     * Checks if the automaton has a certain transition.
     * @param fromStateId The id of the beginning state of the transition.
     * @param transitionRepresentation A string representation of a transition, which is a comma-separated list of input character, pop character, push character and ending state. E.g. "q1, a, -, a, q2",
     * @return true, iff the state has that transition.
     */
    public boolean hasTransition(String fromStateId, String transitionRepresentation) {
        TransitionRepresentation rep = new TransitionRepresentation(transitionRepresentation);
        State fromState = stateManager.getState(fromStateId);
        return fromState.hasTransition(rep);
    }
}
