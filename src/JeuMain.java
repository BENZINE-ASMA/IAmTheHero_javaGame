import java.util.Scanner;

public class JeuMain {
	public static void main(String[] args) {
		
		Terrain terrain = new Terrain("C:\\Users\\marie\\eclipse-workspace\\JavaGame_IAmTheHero\\src\\terrain1.txt");
		Scanner sc = new Scanner(System.in);
		while (true) {
			terrain.affiche();
			String input = sc.nextLine().toLowerCase();
			Direction direction = switch (input) {
			case "n" -> Direction.nord;
			case "s" -> Direction.sud;
			case "e" -> Direction.est;
			case "o" -> Direction.ouest;
			default -> null;
			};
			System.out.println(direction);
			
			if (direction != null) { terrain.movePlayer(direction);}
	
	
	
	}

}
}
