package de.tbelmega.automata.state;

import de.tbelmega.automata.Transition;
import de.tbelmega.automata.TransitionRepresentation;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thiemo on 03.02.2016.
 */
public class SimpleState implements State {
    private final String id;
    Set<Transition> transitions = new HashSet<>();

    SimpleState(Integer stateName) {
        this(stateName.toString());
    }

    SimpleState(String stateName) {
        this.id = stateName;
    }

    public String getStateId(){
        return this.id;
    }

    @Override
    public boolean hasTransition(TransitionRepresentation rep) {
        for (Transition t: transitions){
            if ( rep.read == t.readCharacter &&
                    rep.pop == t.popCharacter &&
                    rep.push == t.pushCharacter &&
                    rep.toState.equals(t.targetState.getStateId())
                    ) return true;
        }
        return false;
    }

    public void addTransition(char inputCharacter, SimpleState targetState) {
        this.addTransition(inputCharacter, '-', '-', targetState);
    }


    public void addTransition(char inputCharacter, char popCharacter, char pushCharacter, SimpleState targetState) {
        this.transitions.add(new Transition(inputCharacter, popCharacter, pushCharacter, targetState));
    }

    public void addTransition(TransitionRepresentation rep, SimpleState toState) {
        this.transitions.add(new Transition(rep.read, rep.pop, rep.push, toState));
    }


    /**
     * Looks for the transition from this state with the given input character. Returns the target state.
     */
    public SimpleState doTransitionDeterministic(Character inputCharacter) {
        for (Transition t: this.transitions) {
            if (t.readCharacter == inputCharacter) return t.targetState;
        }
        throw new IllegalArgumentException("No such transition defined.");
    }

    /**
     * Looks for all transitions from this state with the given input character. Returns a set of all target states.
     * May be the empty set.
     */
    public Set<SimpleState> doTransition(Character inputCharacter) {
        Set<SimpleState> nextStates = new HashSet<>();

        for (Transition t: this.transitions) {
            if (t.readCharacter == inputCharacter) nextStates.add(t.targetState);
        }

        return nextStates;
    }

    @Override
    public boolean equals(Object anotherObject){
        if (anotherObject instanceof  SimpleState){
            return this.id.equals(((SimpleState) anotherObject).getStateId());
        } else return false;
    }

    @Override
    public int hashCode(){
        return this.id.hashCode();
    }

}
