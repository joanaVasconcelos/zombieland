package org.academiadecodigo.bootcamp.joanavasconcelos;

import org.academiadecodigo.bootcamp.joanavasconcelos.game.Field;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;


/**
 * Created by JVasconcelos on 22/02/16
 */

public class Screen implements MouseHandler {

    private Rectangle startScreen;
    private boolean mousecliked;


    public void startingScreen() {
        startScreen = new Rectangle(0, 0, Field.getField().getWidth(), Field.getField().getHeight());
        startScreen.setColor(Color.WHITE);
        startScreen.fill();
        startScreen.draw();



    }

    public boolean isMousecliked() {
        return mousecliked;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Mouse mouse = new Mouse(this);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);

        mousecliked = true;


    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
