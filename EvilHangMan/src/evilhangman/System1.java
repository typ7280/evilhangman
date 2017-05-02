/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evilhangman;

import java.util.ArrayList;
import java.lang.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student Lab
 */
public class System1 {

    /**
     * @param args the command line arguments
     */
    int length = Player.length;
    char letter;
    static char guessLetter;
    static int guessCount;
    boolean con;
    boolean match;
    static ArrayList<Character> letterList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static Random r = new Random();
    static char[] playing;
    static boolean last = false;
    static boolean wins;

    public static void main(String[] args) {
        // TODO code application logic here

        intro();
        play();

    }

    public static void checkDuplicate(char letter) {
        if (letterList.indexOf(letter) != -1) {
            System.out.println("Already taken.(please input new Letter)");
            guessLetter = sc.next().charAt(0);
            checkDuplicate(guessLetter);
        } else {
            letterList.add(letter);
        }
    }

    public static boolean checkLetterMatch(char letter) {
        boolean match = false;
        for (int i = 0; i < Word_Temp.word.length(); i++) {
            if (letter == Word_Temp.word.charAt(i)) {

                match = true;
            }
        }
        if (last) {
            for (int i = 0; i < Word_Temp.word.length(); i++) {
                if (letter == Word_Temp.word.charAt(i)) {
                    playing[i] = letter;

                }

            }

        }
        return match;

    }

    public static void checkInput() {
        System.out.println("Please input new letter(a-z)");
        guessLetter = sc.next().toLowerCase().charAt(0);
        if (guessLetter < 97 || guessLetter > 122) {
            checkInput();
        }
    }

    public static void checkLength() {
        if (Player.length < 2 || Player.length > 22) {
            System.out.println("Please input new length(2-22)");
            Player.inputLength(sc.nextInt());

            checkLength();
        }

    }

    public void decreaseletter(char letter) {

    }

    public static String changeWord() {

        //ArrayList<String> WordListCopy = new ArrayList<>(Word_Temp.wordList); //clone list
        if (Word_Temp.wordList.size() > 10) {
            ArrayList<String> toRemove = new ArrayList<>();
            for (String wordList : Word_Temp.wordList) {
                if (wordList.contains(guessLetter + "")) {
                    toRemove.add(wordList);
                }
            }
            Word_Temp.wordList.removeAll(toRemove);
        }

        if (Word_Temp.wordList.size() <= 10) {
            //Word_Temp.wordList = WordListCopy;
            //get the lastly Word 
            Word_Temp.word = Word_Temp.wordList.get(0);

            last = true;
        } else {
            Word_Temp.word = Word_Temp.wordList.get(r.nextInt(Word_Temp.wordList.size()));
        }
        //System.out.print(Word_Temp.word);
        return Word_Temp.word;
    }

    public static void intro() {
        System.out.println("      Welcome      to       EvilHangman       ");
        System.out.println("---------------------------------------------");
        System.out.println("Input length");
        
        Player.inputLength(sc.nextInt());
        checkLength();
        System.out.println("-------------------------------------------");
        playing = new char[Player.length];
        for (int i = 0; i < playing.length; i++) {
            playing[i] = '_';
        }
        //showPlaying();

        //System.out.println();
        Word_Temp.findWord();// decreate word
        Word_Temp.word = Word_Temp.wordList.get(r.nextInt(Word_Temp.wordList.size()));
        /* for(int i=0;i<Word_Temp.wordList.size();i++){
            System.out.println(Word_Temp.wordList.get(i));
        }*/

        guessCount = (Word_Temp.word.length());
        if(guessCount<10){
            guessCount+=10;
        }

    }

    public static void play() {
        if (guessCount > 0 && wins) {
                win(true);
                System.out.println("---Do you want to play again?----");
                System.out.println("Y/N");
                switch (sc.next().toLowerCase().charAt(0)) {
                    case 'y':
                        quit(true);
                    case 'n':
                        quit(false);
                }
            }
        if(!wins){
        while (guessCount > 0) {
            showPlaying();
            System.out.println("\nGuess count : " + guessCount);
            System.out.print("Guess Letter : ");
            for(int i = 0;i<letterList.size();i++){
                System.out.print(letterList.get(i)+" ");
            }
            System.out.println();
            System.out.println("Input Letter");
            guessLetter = Player.guess(sc.next().toLowerCase().charAt(0));
            // if(guessLetter <97 || guessLetter > 122){
            //   checkInput();
            //}
            checkDuplicate(guessLetter);
            if (checkLetterMatch(guessLetter)) {
                Word_Temp.word = changeWord();
                guessCount--;
                //System.out.print(Word_Temp.word);
            } else if (!checkLetterMatch(guessLetter)) {
                Word_Temp.word = changeWord();
                //System.out.println(Word_Temp.word);
                guessCount--;
            }
        }
        
        if (guessCount <= 0) {
            win(false);
            System.out.println("---Do you want to play again?----");
            System.out.println("Y/N");
            switch (sc.next().toLowerCase().charAt(0)) {
                case 'y':
                    quit(true);
                case 'n':
                    quit(false);
            }
            
            }
        }
    
    }

    public static void showPlaying() {
        wins = true;
        for (int i = 0; i < Player.length; i++) {
            if (playing[i] == '_') {
                wins = false;
            }
            System.out.print(playing[i] + " ");

        }
        System.out.println();
        if (wins) {
            win(wins);
        }

    }

    public static boolean quit(boolean con) {
        if (!con) {
            System.out.println();
            System.out.println("---------------END---------------");
            System.out.println();
        } else {
            letterList.clear();
            intro();
            play();

        }
        return con;
    }

    public static void win(boolean w) {
        if (w) {
            System.out.println("---------------WIN---------------");
            System.out.println(Word_Temp.word);
            guessCount=0;
            System.out.println("---Do you want to play again?----");
            System.out.println("Y/N");
            switch (sc.next().toLowerCase().charAt(0)) {
                case 'y':
                    quit(true);
                case 'n':
                    quit(false);
            }
            

        } else {
            System.out.println("--------------LOSE---------------");
            System.out.println(Word_Temp.word);
        }
        
        }

    }
