package org.academiadecodigo.bootcamp.joanavasconcelos.character;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

/**
 * Created by JVasconcelos on 11/02/16
 */

public class InfectionDetector {

    private ArrayList<Char> chars;
    private static double diameterToCentre = 50;

    public InfectionDetector(ArrayList<Char> chars) {
        this.chars = chars;
    }


    public void detectInfection(Char character) {
        Picture rep = character.getRepresentation();
        double repX = rep.getX();
        double repY = rep.getY();


        for (int i = 0; i < chars.size(); i++) {
            Picture charRep = chars.get(i).getRepresentation();
            double charRepX = charRep.getX();
            double charRepY = charRep.getY();

            if (character.equals(chars.get(i)) ||
                    character.isDead() ||
                    chars.get(i).isDead()) {
                continue;
            }



            if (character.isInfected() || chars.get(i).isInfected()) {


                if (Math.abs(repX - charRepX) < diameterToCentre  && Math.abs(repY - charRepY) < diameterToCentre) {

                    character.setInfected();
                    chars.get(i).setInfected();
                }
            }

        }

    }



}
