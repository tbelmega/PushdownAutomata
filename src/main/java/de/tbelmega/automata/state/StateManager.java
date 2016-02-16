package de.tbelmega.automata.state;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thiemo on 03.02.2016.
 *
 * A state manager stores the states for an automaton.
 * The state manager ensures that there is only one state with a given name.
 */
public class StateManager {
    private Map<String, SimpleState> states = new HashMap<>();


    /**
     * Returns the state with the given name.
     * If it does not exist yet, it is created.
     * (Convenience method in case integers are used as state names.)
     */
    public SimpleState getState(Integer stateName) {
        return this.getState(stateName.toString());
    }

    /**
     * Returns the state with the given name.
     * If it does not exist yet, it is created.
     */
    public SimpleState getState(String stateName) {
        SimpleState state = this.states.get(stateName);
        if (state != null) return state;
        else return createNewState(stateName);
    }

    private SimpleState createNewState(String s) {
        SimpleState state = new SimpleState(s);
        this.states.put(s, state);
        return state;
    }
}
