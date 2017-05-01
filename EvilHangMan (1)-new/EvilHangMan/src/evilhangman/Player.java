/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evilhangman;

/**
 *
 * @author Student Lab
 */
public class Player {
    static int length;
    static char letter;
    
    public static void inputLength(int number){
        length = number;
    }
    
    public static char guess(char letter){
        
        Player.letter = letter;
        //System1.guessCount--;
        
        return letter;
    }
}
