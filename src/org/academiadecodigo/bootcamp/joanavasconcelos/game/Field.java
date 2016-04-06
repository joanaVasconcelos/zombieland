package org.academiadecodigo.bootcamp.joanavasconcelos.game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by JVasconcelos on 11/02/16
 */

public class Field {
    private static Picture field;

    public void init(){
        field = new Picture(0, 0, "zombies.jpg");
        field.draw();
    }

    public static Picture getField() {
        return field;
    }




}
