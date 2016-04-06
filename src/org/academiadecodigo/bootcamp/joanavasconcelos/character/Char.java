package org.academiadecodigo.bootcamp.joanavasconcelos.character;


import org.academiadecodigo.bootcamp.joanavasconcelos.game.Field;
import org.academiadecodigo.bootcamp.joanavasconcelos.game.Position;
import org.academiadecodigo.bootcamp.joanavasconcelos.game.Randomize;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by JVasconcelos on 11/02/16
 */

public class Char {
    private Picture representation;
    private Position pos;
    private boolean infected;
    private InfectionDetector detector;
    private Direction direction;
    private int speed;
    private boolean dead;
    private Display display;


    public Char() {
        pos = new Position();
        setRandomDir();
        speed = 2;
        this.display = new Display();

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Picture getRepresentation() {
        return representation;
    }

    public void setRepresentation(double x, double y) {
        representation = new Picture(x, y, "run.png");
        representation.draw();

    }

    public void setDetector(InfectionDetector detector) {
        this.detector = detector;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isInfected() {
        return infected;
    }


    public void setInfected() {
        infected = true;
        speed = 1;
        representation.load("zombie.png");
    }

    public Display getDisplay() {
        return display;
    }


    public void setRandomDir() {

        int randomDir = Randomize.getRandomByRange(3);

        switch (randomDir) {

            case 0:
                direction = Direction.RIGHT;
                break;

            case 1:
                direction = Direction.LEFT;
                break;

            case 2:
                direction = Direction.DOWN;
                break;

            case 3:
                direction = Direction.UP;
                break;
        }


    }

    public void changeDir() {
        int turn = Randomize.getRandomByRange(1);

        switch(direction) {
            case UP:
            case DOWN:
                if(turn < 1) {
                    direction = Direction.LEFT;
                    return;
                }

                direction = Direction.RIGHT;
                break;

            case LEFT:
            case RIGHT:
                if(turn < 1) {
                    direction = Direction.UP;
                    return;
                }

                direction = Direction.DOWN;
                break;
        }


    }



    public void followDir() {
        double x = pos.getX();
        double y = pos.getY();
        double xTranslation = 0;
        double yTranslation = 0;

        switch (direction) {

            case RIGHT:
                xTranslation = Math.random() * 10;
                if(x + xTranslation > Field.getField().getWidth() - 50) {
                    xTranslation *= -1;
                    direction = Direction.LEFT;
                }
                break;

            case LEFT:
                xTranslation = Math.random() * -10;
                if(x + xTranslation < 20) {
                    xTranslation *= -1;
                    direction = Direction.RIGHT;
                }

                break;

            case DOWN:
                yTranslation = Math.random() * 10;
                if(y + yTranslation > Field.getField().getHeight() - 100) {
                    yTranslation *= -1;
                    direction = Direction.UP;
                }
                break;

            case UP:
                yTranslation = Math.random() * -10;
                if(y + xTranslation < 20) {
                    yTranslation *= -1;
                    direction = Direction.DOWN;
                }
                break;

        }

        pos.setY(y + yTranslation);
        pos.setX(x + xTranslation);
        representation.translate(xTranslation, yTranslation);


    }


    public void move() throws InterruptedException {


        if(Math.random() < 0.15) {
            changeDir();
            return;
        }


        for (int i = 0; i < speed ; i++) {
            followDir();
            detector.detectInfection(this);

        }


    }



}
