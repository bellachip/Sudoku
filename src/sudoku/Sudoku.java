/*
 * Hee Won Yang 
 * CIS 2168; Lab6 
 * Chess and Sudoku
 */
package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Belle
 */
public class Sudoku {

    static int sudoku[][] = {
        {0, 9, 5, 0, 0, 6, 0, 0, 8},
        {0, 0, 6, 0, 5, 7, 9, 0, 4},
        {0, 8, 2, 0, 3, 0, 0, 0, 7},
        {0, 7, 1, 0, 8, 0, 0, 4, 0},
        {0, 0, 0, 4, 0, 0, 2, 7, 6},
        {9, 0, 0, 6, 0, 0, 0, 8, 0},
        {5, 4, 0, 7, 1, 3, 0, 0, 0},
        {2, 0, 0, 9, 0, 0, 4, 3, 0},
        {3, 0, 8, 0, 0, 4, 7, 9, 0}
    };

    public static void main(String[] args) throws IOException {

        //Buffered Reader Code to Input the File
        File file = new File("/Users/Bella/NetBeansProjects/Sudoku/sudoku.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        //While loop to read the file, and distribute the numbers in it to the Sudoku Board
        int sum = 0;
        String s;
        while ((br.readLine()) != null) {
            for (int i = 0; i < 9; i++) {
                s = br.readLine();
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = s.charAt(j) - '0';
                }
            }
            System.out.println("\n Grid");
            Sudoku sudokuProb = new Sudoku();
            sudokuProb.solve();
            sum += 100 * sudoku[0][0] + 10 * sudoku[0][1] + sudoku[0][2];

        }
        System.out.println("\n Sum of the 3-digit numbers: " + sum);
    }

    //Method to check if the Sudoku Number placement is safe
    public static boolean checkIfSafe(Cell cell, int value) {

        if (sudoku[cell.row][cell.col] != 0) {
            throw new RuntimeException();
        }

        //If it is in row
        for (int c = 0; c < 9; c++) {
            if (sudoku[cell.row][c] == value) {
                return false;
            }
        }

        //If it is in column
        for (int r = 0; r < 9; r++) {
            if (sudoku[r][cell.col] == value) {
                return false;
            }
        }

        //If it is in grid, and to get grid
        int x1 = 3 * (cell.row / 3);
        int y1 = 3 * (cell.col / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                if (sudoku[x][y] == value) {
                    return false;
                }
            }
        }

        //If value not present in row, col and bounding box, return true
        return true;
    }

    boolean solve() {

        boolean isSolved = solveSudoku(new Cell(0, 0));

        if (!isSolved) {
            System.out.println("This Sudoku Problem Does Not Have Solution!");
            return false;
        }
        printSudoku(sudoku);
        return true;
    }

    static boolean solveSudoku(Cell cur) {

        if (cur == null) {
            return true;
        }

        //check if value in the Sudoku Problem is already assigned
        if (sudoku[cur.row][cur.col] != 0) {
            return solveSudoku(getNextCell(cur));
        }

        //All the values are assigned to the cell and checked
        for (int i = 1; i <= 9; i++) {

            boolean valid = checkIfSafe(cur, i);

            if (!valid) {
                continue;
            }
            sudoku[cur.row][cur.col] = i;

            boolean solved = solveSudoku(getNextCell(cur));

            //If the Sudoku Problem is solved
            if (solved) {
                return true;
            } else {
                sudoku[cur.row][cur.col] = 0; //Backtracking
            }
        }

        //Sudoku does not solve
        return false;
    }

    //Function implemented to get the next following cell
    static Cell getNextCell(Cell cur) {

        int row = cur.row;
        int col = cur.col;

        col++;

        if (col > 8) {
            col = 0;
            row++;
        }
        if (row > 8) {
            return null; //Reached end
        }
        Cell nextCell = new Cell(row, col);
        return nextCell;
    }

    //Prints the Sudoku Board
    void printSudoku(int sudoku[][]) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {
                System.out.print(" " + sudoku[row][col] + " ");
            }
            System.out.println();
        }
    }
}
