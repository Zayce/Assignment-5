/**
 ** Program Name: Exercise3
 ** Author: Zulhelmi bin Mohamad
 ** Date: April 11th, 2021
 ** Course: CPSC 1150 
 ** Compiler:JDK 15.0.1
 */

import java.io.File;
import java.lang.String;
import java.util.Scanner;


public class Exercise3 {
    public static void main(String[] args) throws Exception {

        //System.out.println(reverseDislay("abcd"));
        
                    
        
        String[] phraseList = textFileToArray();
        for(int i = 0; i < phraseList.length; i++){
            System.out.println(reverseDislay(phraseList[i]));
        }
    }

    public static String[] textFileToArray () throws Exception{
        File file = new File("Phrases.txt");

        //Exits if can't read file
        if (!file.canRead()){ 
            System.out.println("Error: permission to read from file is denied"); 
            System.exit(1);
        }

        //Counts number of lines
        int lines = 0;
        Scanner countLines = new Scanner(file);
        while(countLines.hasNextLine()){
            countLines.nextLine();
            lines++;
        }
        countLines.close();

        //Reads each lines and assign it to the array
        String[] phraseList = new String[lines];
        Scanner readLine = new Scanner(file);
        for (int i = 0; i < lines; ++i){
            phraseList[i] = readLine.nextLine();
        }
        readLine.close();
        
        return phraseList;
    }

    public static String reverseDislay(String value){
        int lastIndex = value.length() - 1;
        String newValue = "";
        String reverse = "";

        //Returns value if it's the only character
        if (value.length() == 1){
            return value;
        }
        else{
            //Creates new string without the last character
            for(int i = 0; i < lastIndex; ++i){
                newValue += value.charAt(i);
            }

            //Adds the last character and calls method for string without last character
            reverse += (value.charAt(lastIndex) + reverseDislay(newValue));
            return reverse;
        }
    }
}