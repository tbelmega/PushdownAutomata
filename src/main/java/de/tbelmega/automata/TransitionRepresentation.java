package de.tbelmega.automata;

/**
 * Created by Thiemo on 18.02.2016.
 */
public class TransitionRepresentation {

    public static final String ELEMENT_SEPERATOR = ",";
    public final char read;
    public final char pop;
    public final char push;
    public final String toState;


    public TransitionRepresentation(String transitionRepresentation) {
        String[] arguments = transitionRepresentation.split(ELEMENT_SEPERATOR);
        assert arguments.length == 4;
        read = arguments[0].trim().charAt(0);
        pop = arguments[1].trim().charAt(0);
        push = arguments[2].trim().charAt(0);
        toState = arguments[3].trim();
    }
}
