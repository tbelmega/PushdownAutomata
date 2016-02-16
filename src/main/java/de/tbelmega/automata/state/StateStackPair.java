package de.tbelmega.automata.state;

import de.tbelmega.automata.Transition;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static de.tbelmega.automata.Automaton.EPSILON;

/**
 * Created by Thiemo on 16.02.2016.
 */
public class StateStackPair implements State {
    private final Stack<Character> stack;
    public final SimpleState state;

    public StateStackPair(SimpleState state, Stack<Character> characters) {
        this.state = state;
        this.stack = characters;
    }


    public String getStateId(){
        return this.state.getStateId();
    }

    public Set<StateStackPair> doTransition(Character inputCharacter) {
        Set<StateStackPair> nextStates = new HashSet<>();

        for (Transition t: state.transitions) {
            if (transitionCanFire(inputCharacter, t)) {
                Stack<Character> newStack = (Stack<Character>) stack.clone();
                if (t.popCharacter != EPSILON) newStack.pop();
                if (t.pushCharacter != EPSILON) newStack.push(t.pushCharacter);
                nextStates.add(new StateStackPair(t.targetState, newStack));
            }
        }

        return nextStates;
    }

    private boolean transitionCanFire(Character inputCharacter, Transition t) {
        boolean correctInputCharacter = t.readCharacter == inputCharacter;
        boolean canPop = t.popCharacterIs(EPSILON) || t.popCharacterIs(stack.peek());
        return  correctInputCharacter && canPop;
    }

    public char peek() {
        return stack.peek();
    }
}