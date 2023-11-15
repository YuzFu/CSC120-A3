/**
 * Import modules 
 */

import java.util.Scanner;
import java.util.Random;

/*
 * Creat conversation class
 */

public class Conversation {
  /*
  * Allocate static array of canned responses
  */ 
  private static String[] cannedR = {
    "That's interesting.",
    "I see what you mean.",
    "Tell me more about that.",
    "Fascinating!",
    "I'm not sure what to say.",
    "Interesting point of view.",
    "Please continue.",
    "I appreciate your input.",
  };
  
  /*
   * Allocate detected words and mirrored words
   */
  private static String[] detectedW = {"I", "me", "am", "you", "my", "your", "m", "i", "we", "our", "My", "You", "Your", "We", "Our", "us"};
  private static String[] mirrorW = {"you", "you", "are", "I", "your", "my", "'re", "you", "you", "your", "Your", "I", "My", "You", "Your", "you"};

  /**
  * Generate response when no specific words detected, Or replace words and print back
  * @param userR  user's input
  * @parma random random object for electing canned responses
  * @return       the generated reponse
  */ 
  public static String response(String userR, Random random) {
    String response;

    String[] userW = userR.split("[\\s']+");
    boolean detect = false;
    
    for (int i = 0; i < userW.length; i++) {
      for (int m = 0; m < detectedW.length; m++) {
        if (userW[i].equals(detectedW[m])) {
          userW[i] = userW[i].replace(userW[i], mirrorW[m]);
          detect = true;
          break;
        }
      }
    } 
    
    if (detect) {
      String mirrorS = String.join(" ", userW);
      response = mirrorS;
    } else {
      int randomIndex = random.nextInt(cannedR.length);
      response = cannedR[randomIndex];
    }
    return response;
  }

  /*
  * Ask for rounds with users, answer user response with detect() method, store and print transcript of reponses
  */ 
  public static void main(String[] arguments) {
    Scanner input = new Scanner (System.in);
    System.out.print("How many rounds of conversation do you want?");
    int rounds = input.nextInt();

    String[] stringA = new String[2+2*rounds];

    // Say hi
    System.out.println("Hi, how are you today?");
    stringA[0] = "Hi, how are you today?";

    for (int i = 0; i < rounds; i++) {
      Scanner sentence = new Scanner (System.in);
      String userR = sentence.nextLine();
      stringA[2*i+1] = userR;
      Random random = new Random();
      String r = response(userR, random); 
      stringA[2*i+2] = r;
      System.out.print(r + System.lineSeparator());
    } 
    
    System.out.print("\nIt is my pleasure to talk with you. Have a nice day!\n");
    stringA[1+2*rounds] = "It is my pleasure to talk with you. Have a nice day!";

    System.out.print("\nHere is the transcript of our conversation:\n");

    for (int i = 0; i < stringA.length; i++) { 
      System.out.println(stringA[i]); 
    } 
  }
}
