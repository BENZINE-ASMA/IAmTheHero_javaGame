package entities;

import java.util.Random;

/**
 * Énumération représentant les quatre directions cardinales.
 */
public enum Direction {
    nord, sud, est, ouest;

    /**
     * Convertit un caractère en une direction.
     * 
     * @param c le caractère à convertir ('^', 'v', '<', '>')
     * @return la direction correspondante ou null si le caractère ne correspond à aucune direction
     */
    public static Direction dir(Character c) {
        return switch (c) {
            case '^' -> Direction.nord;
            case 'v' -> Direction.sud;
            case '<' -> Direction.est;
            case '>' -> Direction.ouest;
            default -> null;
        };
    }

    /**
     * Retourne une direction aléatoire.
     * 
     * @return une direction choisie aléatoirement parmi nord, sud, est et ouest
     */
    public static Direction randomDir() {
        Random random = new Random();
        return switch (random.nextInt(4)) {
            case 0 -> Direction.nord;
            case 1 -> Direction.sud;
            case 2 -> Direction.est;
            default -> Direction.ouest;
        };
    }
}
