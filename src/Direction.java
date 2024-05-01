import java.util.Random;

public enum Direction{
	nord,sud,est,ouest;


public static Direction dir (Character c) {
	return switch (c) {
	case '^' -> Direction.nord;
	case 'v' -> Direction.sud;
	case '<' -> Direction.est;
	case '>' -> Direction.ouest;
	default -> null;
	};
}

public static Direction randomDir() {
	Random random  = new Random();
	return switch (random.nextInt(4)) {
		case 0 -> Direction.nord;
		case 1 -> Direction.sud;
		case 2 -> Direction.est;
		default -> Direction.ouest;
	};
	
}
}