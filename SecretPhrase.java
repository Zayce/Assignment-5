/**
 ** Program Name: SecretPhrase
 ** Author: Zulhelmi bin Mohamad
 ** Date: April 11th, 2021
 ** Course: CPSC 1150 
 ** Compiler:JDK 15.0.1
 */

import java.io.File;
import javax.swing.JOptionPane;
import java.lang.Math;
import java.lang.String;
import java.util.Scanner;


public class SecretPhrase {

    // showUp is a boolean array, if value in one index is true, it means letter of the riddle on that index has been revealed
    public static boolean[] showUp;
    // remainStars stores how many letters are not revealed yet
    public static int remainStars = 0;

    public static void main(String[] args) throws Exception {
        

        //Gets random phrase from Phrase.txt
        String[] phraseList = textFileToArray();
        String phrase = phraseList[(int) (Math.random() * phraseList.length)];
        initialStars(phrase);
        String message = "Play our game - guess the phrase\n Enter one letter\n" + getRiddle(phrase);
        int counter = 0;
        while (remainStars != 0) {
            String input = JOptionPane.showInputDialog(null, message);
            char guessLetter = Character.toUpperCase(input.charAt(0));
            if (checkLetter(phrase, guessLetter)) {
                message = "Play our game - guess the phrase\n Enter one letter\n" + getRiddle(phrase);
            } else {
                message = "Sorry - not in the phrase: " + guessLetter + "\n" + getRiddle(phrase);
            }
            counter++;
        }
        // calculate the score
        long score = Math.round(phrase.length() * 1.0 / counter *100);
        JOptionPane.showMessageDialog(null,
                "Congratulations!\nThe phrase is \"" + phrase + "\"\nYour Score is " + score + " out of 100.");
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

    // initial showUp and remainStars
    // make sure space character is revealed
    public static void initialStars(String phrase) {
        showUp = new boolean[phrase.length()];
        remainStars = phrase.length();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                showUp[i] = true;
                remainStars--;
            }
        }
    }
    // getRiddle would get what riddle looks like currently, if showUp is true on a index, the letter on that index will be revealed, otherwise, it is a '*'
    public static String getRiddle(String phrase) {
        String riddle = "";
        for (int i = 0; i < phrase.length(); i++) {
            if (showUp[i])
                riddle += phrase.charAt(i);
            else
                riddle += '*';
        }
        return riddle;
    }
    // check if a letter is in the phrase, turn showUp[i] to true if phrase[i] == letter, and update the value of remainStars;
    public static boolean checkLetter(String phrase, char letter) {
        boolean result = false;
        for (int i = 0; i < phrase.length(); i++) {
            if (letter == Character.toUpperCase(phrase.charAt(i))) {
                result = true;
                showUp[i] = true;
                remainStars--;
            }
        }
        return result;
    }

    public static 

}