package de.tbelmega.automata.state;

import de.tbelmega.automata.TransitionRepresentation;

import java.util.Set;

/**
 * Created by Thiemo on 16.02.2016.
 */
public interface State {

    Set<? extends State> doTransition(Character inputCharacter);

    String getStateId();

    /**
     * Checks if the state has a transition with the passed parameters.
     * @return true, iff the state has a transition with the passed parameters.
     */
    boolean hasTransition(TransitionRepresentation rep);

}
