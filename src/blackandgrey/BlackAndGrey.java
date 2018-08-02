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
            for (int j=0;j<5;j++) {
                System.out.print(grid.getColor(sc.nextInt()-1,sc.nextInt()-1));
            }
            System.out.println();
        }
    }
    
}

class Grid {
    int size=0;
    ArrayList<Integer> factors=new ArrayList();

    public Grid(int pSize){
        this.size=pSize;
        this.factors=getFactors(size);
    }
    
    
    public char getColor(int x,int y) {
        boolean flip=false;
        for (Integer tileSize:this.factors) {
            int maskTileX=x/tileSize;
            int maskTileY=y/tileSize;
            if ((maskTileX+maskTileY)%2==0)
                flip=!flip;
        }
        return flip?'B':'G';
    }

    private ArrayList<Integer> getFactors(int a) {
        ArrayList<Integer> fa=new ArrayList();
        for (int i=1;i<=a;i++) {
            if (a%i==0) {
                fa.add(i);
            }
        }
        return fa;
    }
}
