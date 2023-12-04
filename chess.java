import java.util.Scanner;

class Cell {
    int x, y;
    boolean isEmpty;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isEmpty = true;
    }
}

class Board {
    Cell[][] cells;
    public Board() {
        cells = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(cells[i][j].isEmpty ? "." : "Q");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int srcX, int srcY, int destX, int destY) {
        int xDistance = Math.abs(srcX - destX);
        int yDistance = Math.abs(srcY - destY);

        // move should be in a straight line vertically or horizontally
        if (!((xDistance == 0 && yDistance == 1) || (xDistance == 1 && yDistance == 0))) {
            return false;
        }

        // check for any piece in the path of the move
        int xStep = srcX < destX ? 1 : -1;
        int yStep = srcY < destY ? 1 : -1;
        for (int i = srcX + xStep, j = srcY + yStep; i != destX || j != destY; i += xStep, j += yStep) {
            if (!cells[i][j].isEmpty) {
                return false;
            }
        }

        return true;
    }

    public void makeMove(int srcX, int srcY, int destX, int destY) {
        if (isValidMove(srcX, srcY, destX, destY)) {
            cells[destX][destY].isEmpty = false;
            cells[srcX][srcY].isEmpty = true;
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }
}

public class Chess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        while (true) {
            System.out.println("Enter the source coordinates (x y): ");
            int srcX = scanner.nextInt();
            int srcY = scanner.nextInt();

            System.out.println("Enter the destination coordinates (x y): ");
            int destX = scanner.nextInt();
            int destY = scanner.nextInt();

            board.makeMove(srcX, srcY, destX, destY);
            board.printBoard();
        }
    }
}