package de.tbelmega.automata;

import de.tbelmega.automata.state.SimpleState;

/**
 * Created by Thiemo on 03.02.2016.
 */
public class Transition {
    public final Character readCharacter;
    public final SimpleState targetState;
    public final Character pushCharacter;
    public final Character popCharacter;

    public Transition(char inputCharacter, char popCharacter, char pushCharacter, SimpleState targetState) {
        this.readCharacter = inputCharacter;
        this.popCharacter = popCharacter;
        this.pushCharacter = pushCharacter;
        this.targetState = targetState;
    }

    public boolean popCharacterIs(Character thatChar) {
        return this.popCharacter == thatChar;
    }
}
