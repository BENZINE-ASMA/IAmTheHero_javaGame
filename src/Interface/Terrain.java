package Interface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Components.Case;
import Components.CaseCLanA;
import Components.CaseClanB;
import Components.CaseIntraversable;
import Components.CaseTraversable;
import Components.Donjon;
import Components.Riviere;
import Components.Village;
import entities.Competence;
import entities.Direction;
import entities.Entite;
import entities.Humain;
import entities.Personnage;


public class Terrain implements Serializable {
    private static final long serialVersionUID = 1L;

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
                        case '/' -> cc = new Village(h, l);
                        case 'G' -> cc = new CaseTraversable(h,l,new Humain(Competence.guerisseuse));
                        case 'A' -> cc = new CaseTraversable(h,l,new Humain(Competence.historien));
                        case 'H' -> cc = new CaseTraversable(h,l,new Humain(Competence.aubergiste));
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
    public Case[][] getCarte(){
    	return this.carte;
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
    
    
    public boolean isPlayerOnCase( Class<?extends Case> caseClass) {
    	
    		return caseClass.isInstance(carte[yjoueur][xjoueur]);
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
				if (destTraversable instanceof CaseTraversable && !( destTraversable.getContenu() instanceof Humain)) {
					destTraversable.setContenu(joueur);
					
					((CaseTraversable)carte[yjoueur][xjoueur]).vide(); 
					xjoueur= newX;
					yjoueur= newY;
				}
				
			//}
			
		}
		
	}

	
	}
}
