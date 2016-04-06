package org.academiadecodigo.bootcamp.joanavasconcelos.game;

/**
 * Created by JVasconcelos on 11/02/16
 */

public class Position {

    private double x;
    private double y;

    public Position() {
        setPos();
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPos() {
        setX(Randomize.getRandomByRange(Field.getField().getWidth() - 50));
        setY(Randomize.getRandomByRange(Field.getField().getHeight() - 100));
    }


}
