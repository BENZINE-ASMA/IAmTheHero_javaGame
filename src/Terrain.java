import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Direction;
import entities.Personnage;


public class Terrain {

    private int largeur, hauteur;
    private Case[][] carte;
    public static int xjoueur ;
    public static int yjoueur ;
    public static Personnage joueur;
    

 
    public Terrain(String file,Personnage joueur ) {
    	Terrain.joueur = joueur;
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.largeur = sc.nextInt(); 
            this.hauteur = sc.nextInt();
            sc.nextLine(); 
            this.carte = new Case[hauteur][largeur]; 

        
            for (int h = 0;h < hauteur; h++) {
                String line = sc.nextLine(); 
                for (int l = 0; l < largeur; l++) {
                    Case cc = null;
                    char ch = line.charAt(l); 
                    switch (ch) {
                        case '#' -> cc = new CaseIntraversable(h, l); 
                        case ' ' -> cc = new CaseTraversable(h, l);
                        case '*' -> cc = new CaseCLanA(h, l);
                        case '-' -> cc = new CaseClanB(h, l);
                        case '@' -> cc = new Riviere(h, l);
                        case '|' -> cc = new Donjon(h, l);
                        case 'J' -> {
                        xjoueur = l; yjoueur= h;cc = new CaseTraversable(h, l, joueur);
                        }
                    }
                    carte[h][l] = cc; 
                }
            }
            sc.close();
        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace(); 
        }
    }

    public void affiche() {
        for (Case[] row : carte) {
            for (Case c : row) {
            	System.out.print(c.toString());
            }
            System.out.println(); 
        }
     
    }


    public int getLargeur() {
        return this.largeur;
    }

    public int getHauteur() {
        return this.hauteur;
    }

	public void movePlayer(Direction direction) {
		int newX = xjoueur, newY = yjoueur;
		switch (direction) {
		case nord -> newY -=1;
		case sud -> newY +=1;
		case est -> newX +=1;
		case ouest -> newX -=1;
		}
	        if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
			Case dest = carte[newY][newX];
			
			if (dest instanceof CaseTraversable) {
				CaseTraversable destTraversable = (CaseTraversable) dest;
				if (destTraversable.estLibre()) {
					destTraversable.setContenu(joueur);
					((CaseTraversable)carte[yjoueur][xjoueur]).vide(); //vider la case du joueur
					xjoueur= newX;
					yjoueur= newY;
				}
				
			}
			
		}
		
	}
}
