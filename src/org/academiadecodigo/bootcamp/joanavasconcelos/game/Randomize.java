package org.academiadecodigo.bootcamp.joanavasconcelos.game;

/**
 * Created by JVasconcelos on 11/02/16
 */

public class Randomize {

    public static int getRandomByRange(int max) {
        return (int) (Math.random() * (max + 1));
    }

}
