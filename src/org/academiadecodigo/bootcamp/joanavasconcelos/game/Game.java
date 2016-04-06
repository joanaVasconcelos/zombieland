package org.academiadecodigo.bootcamp.joanavasconcelos.game;

import org.academiadecodigo.bootcamp.joanavasconcelos.character.Char;
import org.academiadecodigo.bootcamp.joanavasconcelos.character.InfectionDetector;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

import java.util.ArrayList;


/**
 * Created by JVasconcelos on 11/02/16
 */

public class Game implements MouseHandler, KeyboardHandler {

    private ArrayList<Char> chars;
    private double mouseX;
    private double mouseY;
    private int nonInfectedChars;
    private Text text;
    //private int count;


    private boolean mouseClicked;

    public ArrayList<Char> createChars(int charNumber) {
        chars = new ArrayList<>(charNumber);

        for (int i = 0; i < charNumber; i++) {
            chars.add(new Char());
        }

        return chars;
    }


    public void draw(ArrayList<Char> chars) {

        for (Char character : chars) {

            character.setRepresentation(character.getPos().getX(), character.getPos().getY());
            character.setDetector(new InfectionDetector(chars));

        }


    }

    public void start() throws InterruptedException {

        Field field = new Field();
        field.init();

        createChars(50);
        draw(chars);

        mouse();

        chars.get(0).setInfected();


        while (!gameOver()) {

            Thread.sleep(200);

            if (mouseClicked) {
                click();
                mouseClicked = false;
            }


            for (Char character : chars) {

                character.move();

            }

            //count++;


        }


        for (Char character : chars) {

            character.getRepresentation().delete();

        }

        System.out.println(nonInfectedChars);
        if (nonInfectedChars == 0) {
            drawTextInPlace(350, 250, "GAME OVER, LOSER", Color.BLACK);
            keyboard();
            return;
        }


        drawTextInPlace(300, 250, "YOU MANAGED TO STOP THE INFECTION! YOU WIN", Color.BLACK);
        keyboard();


    }

    public boolean gameOver() {


        ArrayList<Char> deadEnemies = new ArrayList<>();
        nonInfectedChars = chars.size() - 1;

        for (Char character : chars) {

            if(character.isDead()) {
                deadEnemies.add(character);
                continue;
            }

            if (character.isInfected()) {
                nonInfectedChars--;
            }
        }

        for (Char deadchar : deadEnemies) {
            //if(count % 5 == 0) {
            //deadchar.getDisplay().eraseText(deadchar);
            chars.remove(deadchar);
            //}
        }


        return nonInfectedChars == 0 || nonInfectedChars == chars.size();

    }


    public void mouse() {

        Mouse mouse = new Mouse(this);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (!mouseClicked) {
            mouseX = e.getX();
            mouseY = e.getY() - 23;
            mouseClicked = true;
        } else {
            System.out.println("Calm down clicking your mouse");
        }

    }

    private boolean targetHit(double x, double y, Char character) {
        return mouseX >= x && mouseX <= x + character.getRepresentation().getWidth()
                && mouseY >= y && mouseY <= y + character.getRepresentation().getHeight();

    }

    public void click() throws InterruptedException {


        for (Char character : chars) {
            double targetX = character.getPos().getX();
            double targetY = character.getPos().getY();

            if (character.isDead()) {
                continue;
            }


            if (!character.isInfected()) {

                if (targetHit(targetX, targetY, character)) {

                    drawTextInPlace(300, 470, "Don't kill innocent people!", Color.RED);

                    character.setDead(true);
                    character.getDisplay().displayText(character, mouseX, mouseY);
                    character.getRepresentation().delete();

                }
                continue;

            }


            if (targetHit(targetX, targetY, character)) {

                if (Math.random() < 0.1) {
                    character.setSpeed(3);
                    return;
                }


                character.setDead(true);
                character.getDisplay().displayText(character, mouseX, mouseY);
                character.getRepresentation().delete();


            }


        }

    }



    private void drawTextInPlace(double x, double y, String str, Color colour) throws InterruptedException {
        text = new Text(x, y, str);
        text.setColor(colour);
        text.grow(100, 30);
        text.draw();

    }


    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public void keyboard() {

        Keyboard k = new Keyboard(this);
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_SPACE);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event);

    }


    @Override
    public void keyPressed(KeyboardEvent e) {

        Runtime.getRuntime().exit(0);

    }

    public void keyReleased(KeyboardEvent e) {

    }


}
