package model;

public class PolygoneRegulier extends Forme{
    private int nb_cote;
    private double longueur_cote;


    public PolygoneRegulier(Position position, double rotation, Position centre_rotation, Couleur couleur, int nb_cote, double longueur_cote) {
        super(position, rotation, centre_rotation, couleur);
        this.longueur_cote = longueur_cote;
        this.nb_cote = nb_cote;
    }

    public int getNbCote(){
        return nb_cote;
    }

    public double getLongueurCote(){
        return longueur_cote;
    }
    
}
