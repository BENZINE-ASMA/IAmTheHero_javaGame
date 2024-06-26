package representation;

import java.util.HashMap;

import entities.Personnage;

public interface Event {
	public void display();
	public Event chooseNext();
	public Event chooseNext(String choice);
	public Event chooseNext2(String choice);
	public int getId() ;
	public String getNom();
	public String getDescription();
	public void setDescription(String description);
	public void setNom(String nom);
	public Node getNode(); // récupère le Node sans le decorator
	public Personnage getJoueur() ;
	public void setJoueur(Personnage joueur);
	public void addToNodeSuivant(String replique ,Event toAdd);
	public HashMap<String,Event>  getNodesSuivant();
	public String insertLineBreaks(String text, int maxLength);

}
