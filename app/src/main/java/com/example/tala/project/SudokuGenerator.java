package com.example.tala.project;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tala on 5/22/2016.
 */

public class SudokuGenerator {
    private static SudokuGenerator instance;

    //arraylist of arraylist of integers used as a temp for generating sudoku
    private ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>();

    private Random rand = new Random();

    private SudokuGenerator(){}

    public static SudokuGenerator getInstance(){
        if( instance == null ){
            instance = new SudokuGenerator();
        }
        return instance;
    }

    public int[][] generateGrid(){
        int[][] Sudoku = new int[9][9];

        int currentPos = 0;

        //we have 81 pos 9*9
        while( currentPos < 81 ){
            if( currentPos == 0 ){
                clearGrid(Sudoku);
            }

            //fe 7al kant el size arraylist la had el position mesh 0 y3ni feha arqam
            if( Available.get(currentPos).size() != 0 ){
                int i = rand.nextInt(Available.get(currentPos).size());//ra7 na5ud random number men 7ajmha ala w heya 0-9
                int number = Available.get(currentPos).get(i);//hun a5dna el raqm

                //bedna nshuf el raqm ele 5trna random ma fe zayu bel satr aw 3amud aw fel cell eza ma kan fe conflict
                if( !checkConflict(Sudoku, currentPos , number)){
                    //jebna el raqm ele men 0-81 w 3rfna mwqe3y bel cell men 5lal ele t7t
                    int xPos = currentPos % 9;
                    int yPos = currentPos / 9;

                    //w 7atena fe hay el 2D arrat
                    Sudoku[xPos][yPos] = number;

                    //b3den ma7ena had el position men el array list l2enu 5alas t3aba
                    Available.get(currentPos).remove(i);

                    currentPos++;
                }else{
                    //else fe 7alet kan fe conflict bn3mlu delete men el arraylist ne5ls menu l2enu already mawjud
                    Available.get(currentPos).remove(i);
                }

            }else{
                //ama fe 7alet kan size el arraylist 0 ezn fesh feha arqam keef bedna ne3ml random so bnru7 n3abeha :P
                for( int i = 1 ; i <= 9 ; i++ ){
                    Available.get(currentPos).add(i);
                }
                currentPos--;
            }
        }


        return Sudoku;
    }

    /*
    remove elements ele home el cells el fadyen ele user lazim y3behum
    el mafrud ykun fe easy, mid , hard
    w 7asbhum y3abi eza easy ykun el fr3'at 2lal w hakza
    ana lal testing sawet el far3'at 2lal
     */
    public int[][] removeElements( int[][] Sudoku ){
        int i = 0;

        // bedu yemshe 5 marat = 5 far3'at
        while( i < 5 ){
            //fe shakel randomly y5ud x, y positions
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            //w eza ma kanu 0 y7welhum la 0
            if( Sudoku[x][y] != 0 ){
                Sudoku[x][y] = 0;
                i++;
            }
        }
        return Sudoku;

    }

    /*
    hun 3melna clear lal sudoku w clear lal arraylist w rje3na 3abena el arraylist men awal w jded :D
     */
    private void clearGrid(int [][] Sudoku){
        Available.clear();

        for( int y =  0; y < 9 ; y++ ){
            for( int x = 0 ; x < 9 ; x++ ){
                Sudoku[x][y] = -1;
            }
        }

        for( int x = 0 ; x < 81 ; x++ ){
            Available.add(new ArrayList<Integer>());
            for( int i = 1 ; i <= 9 ; i++){
                Available.get(x).add(i);
            }
        }
    }

    //check conflict easy to understand ;)
    private boolean checkConflict( int[][] Sudoku , int currentPos , final int number){
        int xPos = currentPos % 9;
        int yPos = currentPos / 9;

        if( checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number) ){
            return true;
        }

        return false;
    }
    private boolean checkHorizontalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        for( int x = xPos - 1; x >= 0 ; x-- ){
            if( number == Sudoku[x][yPos]){
                return true;
            }
        }

        return false;
    }

    private boolean checkVerticalConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        for( int y = yPos - 1; y >= 0 ; y-- ){
            if( number == Sudoku[xPos][y] ){
                return true;
            }
        }

        return false;
    }

    private boolean checkRegionConflict( final int[][] Sudoku , final int xPos , final int yPos , final int number ){
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for( int x = xRegion * 3 ; x < xRegion * 3 + 3 ; x++ ){
            for( int y = yRegion * 3 ; y < yRegion * 3 + 3 ; y++ ){
                if( ( x != xPos || y != yPos ) && number == Sudoku[x][y] ){
                    return true;
                }
            }
        }

        return false;
    }
}
