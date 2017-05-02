/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evilhangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;


/**
 *
 * @author Student Lab
 */
public class Word_Temp {
    
    static String word;
    static String lastWord;
    static ArrayList<String> wordList = new ArrayList<String>();
    static ArrayList<String> wordListClone = new ArrayList<String>();
    public static void findWord(){
        String thisLine ;
      try {
         // open input stream test.txt for reading purpose.
         BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
         
         while ((thisLine = br.readLine()) != null  ) {
             if(thisLine.length()==Player.length){
            wordList.add(thisLine);
             }
         }       
         } catch(Exception e) {
             e.printStackTrace();
        }
    }
    
}
