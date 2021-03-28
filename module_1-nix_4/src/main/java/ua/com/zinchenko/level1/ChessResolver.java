package ua.com.zinchenko.level1;

public class ChessResolver {
    public boolean isMoveHorse(int x1, int y1, int x2, int y2) {
        return ((Math.abs(x1 - x2) == 1) && (Math.abs(y1 - y2) == 2)) ||
        ((Math.abs(x1 - x2) == 2) && (Math.abs(y1 - y2) == 1));
    }
}
