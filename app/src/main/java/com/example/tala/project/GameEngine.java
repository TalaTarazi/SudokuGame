package com.example.tala.project;

/**
 * Created by Tala on 5/22/2016.
 */


        import com.example.tala.project.GameGrid;

        import android.content.Context;

public class GameEngine {
    private static GameEngine instance;

    private GameGrid grid = null;

    private int selectedPosX = -1, selectedPosY = -1;

    private GameEngine(){}

    public static GameEngine getInstance(){
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    /*
    create he ele btrsum awal el saf7a awal eshe bte3mal generate lal sudoku ele bedha tetl3
    men class SudokuGenerator
    w b3deen btetb3ha men class GameGrid ele bewadi 3a SudokuCell ele brsum
     */
    public void createGrid( Context context ){
        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();
        Sudoku = SudokuGenerator.getInstance().removeElements(Sudoku);
        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
    }

    public GameGrid getGrid(){
        return grid;
    }

    public void setSelectedPosition( int x , int y ){
        selectedPosX = x;
        selectedPosY = y;
    }

    public void setNumber( int number ){
        if( selectedPosX != -1 && selectedPosY != -1 ){
            grid.setItem(selectedPosX,selectedPosY,number);
        }
        grid.checkGame();
    }
}
