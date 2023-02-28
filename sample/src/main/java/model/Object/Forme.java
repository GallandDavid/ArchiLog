package model.Object;

import model.utils.Couleur;
import model.utils.Position;

public abstract class Forme {
    private Position position;
    private double rotation;
    private Position centre_rotation;
    private Couleur couleur;
    //private Position translation

    public Forme(Position position, double rotation, Position centre_rotation, Couleur couleur){
        this.position = position;
        this.rotation = rotation;
        this.centre_rotation = centre_rotation;
        this.couleur = couleur;
    }

    public Position getPosition(){
        return position;
    }

    public double getRotation(){
        return rotation;
    }

    public Position getCentreRotation(){
        return centre_rotation;
    }

    public Couleur getCouleur(){
        return couleur;
    }
}
