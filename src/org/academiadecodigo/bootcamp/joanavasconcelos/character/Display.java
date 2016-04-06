package org.academiadecodigo.bootcamp.joanavasconcelos.character;

import org.academiadecodigo.bootcamp.joanavasconcelos.game.Field;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;


/**
 * Created by JVasconcelos on 16/02/16
 */

public class Display {
    private Text text;

    public void displayText(Char character, double x, double y) {
        String str = "";
        text = new Text(x, y, str);

        if(character.isInfected()) {
            str = "Fatality!";
        } else {
            str = "You monster!";
        }

        text.setText(str);

        if (x + text.getWidth() > Field.getField().getWidth() - 50) {
            text = new Text(x - text.getWidth(), y, str);
        }

        text.setColor(Color.RED);
        text.grow(10, 10);
        text.draw();

    }


    public void eraseText(Char character) {
        text.delete();
    }

}
