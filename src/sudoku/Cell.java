/*
 * Hee Won Yang 
 * CIS 2168; Lab6 
 * Chess and Sudoku
 */
package sudoku;

/**
 *
 * @author Belle
 */
public class Cell {

    int row;
    int col;

    public Cell(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "Cell [row=" + row + ", col=" + col + "]";
    }

}
