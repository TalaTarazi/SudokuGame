package com.example.tala.project;

/**
 * Created by Tala on 5/22/2016.
 */

        import com.example.tala.project.SudokuChecker;

        import android.content.Context;
        import android.widget.Toast;

/*
sawena hadd el class 3shan e7na 5ls 3melna generate w fadena kam cell bedna netb3hum 3l shasha
 */
public class GameGrid {
    private SudokuCell[][] Sudoku = new SudokuCell[9][9];

    private Context context;

    public GameGrid( Context context ){
        this.context = context;
        //sudokucell 3ebara 3n class brsumelna el cell w aydan bezabet el line w el borders w el text
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                Sudoku[x][y] = new SudokuCell(context);
            }
        }
    }

    /*
    el aham setGrid bya5ud el sudoku ele e7na 3melnaha generate
    b3tnaha fe class el gameEngine w beru7 zay el shatuur beb3t.ha la class sudoku cell ele btsarf feha w betb3ha
    w sara7tn ana had el class el teba3a laqd t3lmt.hu men google 7abebna :P
     */
    public void setGrid( int[][] grid ){
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++){
                Sudoku[x][y].setInitValue(grid[x][y]);
                if( grid[x][y] != 0 ){
                    Sudoku[x][y].setNotModifiable();
                }
            }
        }
    }

    public SudokuCell[][] getGrid(){
        return Sudoku;
    }

    public SudokuCell getItem(int x , int y ){
        return Sudoku[x][y];
    }

    public SudokuCell getItem( int position ){
        int x = position % 9;
        int y = position / 9;

        return Sudoku[x][y];
    }

    public void setItem( int x , int y , int number ){
        Sudoku[x][y].setValue(number);
    }

    /*
    check game wala as.hal menha beru7 3a class sudokuchecker bef7as enu ma fe arqam men3ada w eza fesh b2olkk
    yaaa kbeeer fuztt
     */
    public void checkGame(){
        int [][] sudGrid = new int[9][9];
        for( int x = 0 ; x < 9 ; x++ ){
            for( int y = 0 ; y < 9 ; y++ ){
                sudGrid[x][y] = getItem(x,y).getValue();
            }
        }

        if( SudokuChecker.getInstance().checkSudoku(sudGrid)){
            Toast.makeText(context, "wohoooo you won ya kbeer", Toast.LENGTH_LONG).show();
        }
    }
}
