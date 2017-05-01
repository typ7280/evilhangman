/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evilhangman;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Student Lab
 */
public class System1 {

    private static Readable in;
    private static Readable out;

    /**
     * @param args the command line arguments
     */
    int length = Player.length;
    char letter;
    static char guessLetter;
    static int guessCount ;
    boolean con;
    boolean match;
    static ArrayList<Character> letterList = new ArrayList<>() ;
    static Scanner sc = new Scanner(System.in);
    static Random r = new Random();

    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("      Hello      to       EvilHangman       ");
        System.out.println("---------------------------------------------");
        System.out.println("Input length");
        Player.inputLength(sc.nextInt());
        System.out.println("-------------------------------------------");
        
        for (int i =0 ;i<Player.length;i++){
            System.out.print("_ ");
        }
        
        System.out.println();
        Word_Temp.findWord("x");/////////////////////////////////// decreate word
        Word_Temp.word = Word_Temp.wordList.get(r.nextInt(Word_Temp.wordList.size()));
        System.out.println(Word_Temp.word);
        guessCount = Word_Temp.word.length()+2;
        
        /*for(int i=0;i<Word_Temp.wordList.size();i++){
        System.out.println(Word_Temp.wordList.get(i));
        }*/
        System.out.println("Guess count : "+guessCount);
        //System.out.println("Input Letter");                       มันนอกลูป
        //guessLetter =  Player.guess(sc.next().charAt(0));         มันนอกลูป
        
        checkDuplicate(guessLetter);
        //decreaseletter(guessLetter);
        //changeWord();
        
       /* if(checkLetterMatch(guessLetter)){
            changeWord();
        }*/
        
        while(guessCount>0){
            System.out.println("Input Letter" + guessCount);
            System.out.println("     **"+Word_Temp.word );
            guessLetter =  Player.guess(sc.next().charAt(0));
            //decreasLetter(guessLetter);
        
            checkDuplicate(guessLetter);
            //if(guessLetter == Word_Temp.wordList.get(i).charAt(i)){
            //}
        
        
            guessCount--;
            
       /* if(checkLetterMatch(guessLetter)){
            changeWord();
        }*/
            if(guessCount == 0){
                quit(false);
            }
        }
        
        
    }

    public static void checkDuplicate(char letter) {//ตัวที่ทายว่าซ้ำไม ยังไม่เสด
        for(int i =0;i<letterList.size();i++){
            if(letter==letterList.get(i)){
                System.out.println("Input New Letter(Already Taken)");
                guessLetter =  Player.guess(sc.next().charAt(0));
                checkDuplicate(guessLetter);
            }
            else{
                letterList.add(letter);//here 
            }
            
            System.out.print(letterList.get(i));
        
        }
    }

    public static boolean checkLetterMatch(char letter) { //
        boolean match = false;
        for (int i = 0; i < Word_Temp.word.length(); i++) {
            if (letter == Word_Temp.word.charAt(i)) {
                match = true;
            }
        }

        return match;

    }

    public static void decreasLetter(char letter) { //ตัดword
        Word_Temp.findWord("x");
        guessLetter =  Player.guess(sc.next().charAt(0));
        letter = guessLetter;
        
       
        for(int i =0;i<Word_Temp.wordList.size();i++){
            for(int j=0;j<Word_Temp.wordList.get(i).charAt(i);j++){
                letter = Word_Temp.wordList.get(i).charAt(j);
                if(letter == guessLetter){
                    Word_Temp.wordList.remove(i);
                    System.out.println("decrease"+Word_Temp.word);
                    break;
                }
            }
        }
    }
    public static String changeWord(){
        for(int i =0;i<Word_Temp.wordList.size();i++){
            for(int j=0;j<Word_Temp.wordList.get(i).length();j++){
                char check = Word_Temp.wordList.get(i).charAt(j);
                if(check == guessLetter){
                    Word_Temp.wordList.remove(i);
                    Word_Temp.wordList.get(i+1);
                    break;
                }
            }
        }
        return Word_Temp.wordList.get(r.nextInt(Word_Temp.wordList.size()));
    }

    public static boolean quit(boolean con) {
            if(con == false){    
                System.out.println();
                System.out.println("---------------END---------------");
                System.out.println();
            }
            else if(guessCount != 0) return true;
        return con;
    }

}
