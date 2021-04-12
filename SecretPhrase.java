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

    public static final String[] phrase_list = { "A blessing in disguise", "Better late than never",
            "Cut somebody some slack", "Get out of hand", "Make a long story short", "So far so good",
            "To make matters worse", "We will cross that bridge when we come to it", "To get bent out of shape",
            "That is the last straw", "Speak of the devil", "On the ball", "Miss the boat", "Pull yourself together",
            "No pain no gain", "Hang in there" };
    // showUp is a boolean array, if value in one index is true, it means letter of the riddle on that index has been revealed
    public static boolean[] showUp;
    // remainStars stores how many letters are not revealed yet
    public static int remainStars = 0;

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

    public static void main(String[] args) {
        // pick phrase from list
        String phrase = phrase_list[(int) (Math.random() * phrase_list.length)];
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
}