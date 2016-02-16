package de.tbelmega.automata.state;

import java.util.Set;

/**
 * Created by Thiemo on 16.02.2016.
 */
public interface State {

    Set<? extends State> doTransition(Character inputCharacter);

    String getStateId();
}
