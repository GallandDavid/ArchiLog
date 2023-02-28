package model.Object;

import model.utils.Couleur;
import model.utils.Position;

public class Rectangle extends Forme{

    private double largeur;
    private double hauteur;
    private double arrondi_bords;

    public Rectangle(Position position, double rotation, Position centre_rotation, Couleur couleur, double largeur, double hauteur, double arrondi_bords) {
        super(position, rotation, centre_rotation, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.arrondi_bords = arrondi_bords;
    }

    public double getLargeur(){
        return largeur;
    }

    public double getHauteur(){
        return hauteur;
    }

    public double getArrondiBords(){
        return arrondi_bords;
    }
    
}
