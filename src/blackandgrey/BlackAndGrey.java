/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackandgrey;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class BlackAndGrey {

    static Scanner sc=new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (int i=0;i<2;i++) {
            Grid grid=new Grid(sc.nextInt());
            grid.changePattern();
            for (int j=0;j<5;j++) {
                System.out.print(grid.getColor(sc.nextInt()-1,sc.nextInt()-1));
            }
            System.out.println();
        }
    }
    
}

class Grid {
    int size=0;
    char[][] grid;

    public Grid(int pSize){
        this.size=pSize;
        this.grid=new char[size][size];
        initialize(this.grid,size);

        
    }
    
    public void changePattern() {
        ArrayList<Integer> factors=getFactors(this.size);
        for (int edge:factors) {
            char[][] mask=createMask(this.size,edge);
            flipOver(this.grid,mask);
        }
    }
    
    public char getColor(int x,int y) {
        return this.grid[x][y];
    }

    private ArrayList<Integer> getFactors(int a) {
        ArrayList<Integer> factors=new ArrayList();
        for (int i=1;i<=a;i++) {
            if (a%i==0)
                factors.add(i);
        }
        return factors;
    }

    private void initialize(char[][] pGrid, int size) {
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
                pGrid[i][j]='G';
        }
    }
    
    private char toggleColor(char color) {
        return color=='B'?'G':'B';
    }

    private char[][] createMask(int size,int edge) {
        char[][] mask=new char[size][size];
        int steps=size/edge;
        char color='B';
        
        for (int i=0;i<steps;i++) {
            char tileColor=color;
            for (int j=0;j<steps;j++) {
                fillColor(mask,i,j,edge,tileColor);
                tileColor=toggleColor(tileColor);
            }
            color=toggleColor(color);
        }
        return mask;
    }

    private void flipOver(char[][] grid, char[][] mask) {
        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++)
            {
                if (mask[i][j]=='B') {
                    grid[i][j]=toggleColor(grid[i][j]);
                }
            }
        }
    }

    private void fillColor(char[][] mask, int row, int col, int edge, char color) {
        int tileX=row*edge;
        int tileY=col*edge;
        
        for (int i=0;i<edge;i++) {
            for (int j=0;j<edge;j++)
                mask[tileX+i][tileY+j]=color;
        }
    }
}
