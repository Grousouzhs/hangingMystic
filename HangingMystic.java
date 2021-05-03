import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangingMystic {
   public static void plays() throws FileNotFoundException {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Welcome to HangingMystic");
    String word;
    //Scanner element is the first special element -- Jonah
    //To implement this system, copy the path of the wordlist text file for easy mode,
    //and for expert mode copy the path of the dictionary text file
    Scanner scanner = new Scanner(new File("C:/Users/techn/Documents/FinalProj/wordlist.txt"));
    List<String> words = new ArrayList<>();
    while (scanner.hasNext()) {
      words.add(scanner.nextLine());
    }
    Random rand = new Random();
    word = words.get(rand.nextInt(words.size()));
    //main list
    List<Character> playerGuesses = new ArrayList<>();
    //wrong letter counter
    Integer wrongCount = 0;
    while (true) {
      printHangedMan(wrongCount);
      if (wrongCount >= 6) {
        System.out.println("ggwp");
        //ggwpp = good game well played
        System.out.println("The word was: " + word);
        break;
      }
      printWordState(word, playerGuesses);
      //calls boolean
      if (!getPlayerGuess(keyboard, word, playerGuesses)) {
        wrongCount++;
      }
      if (printWordState(word, playerGuesses)) {
        System.out.println("You win!");
        break;
      }
      System.out.println("Enter your guess for the whole word:");
      if (keyboard.nextLine().equals(word)) {
        System.out.println("You win!");
        break;
      } else {
        System.out.println("Try again!");
      }
    }
  }
  //printing out the man
  private static void printHangedMan(Integer wrongCount) {
    System.out.println(" *---*");
    System.out.println("   |");
    if (wrongCount >= 1) {
      System.out.println("   O");
    }
    if (wrongCount >= 2) {
      System.out.print("  \\ ");
      if (wrongCount >= 3) {
        System.out.println("/");
      } else {
        System.out.println("");
      }
    }
    if (wrongCount >= 4) {
      System.out.println("   |");
    }
    if (wrongCount >= 5) {
      System.out.print("  /");
      if (wrongCount >= 6) {
        System.out.println(" \\");
      } else {
        System.out.println("");
      }
    }
    System.out.println("");
    System.out.println("");
  }
  //Booleans are another special element, these were hard for me -- Jonah
  //processes player letter guess
  private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
    System.out.println("Letters guessed: " + playerGuesses);
    System.out.println("Enter a letter:");
    String letterGuess = keyboard.nextLine();
    playerGuesses.add(letterGuess.charAt(0));
    return word.contains(letterGuess);
  }
  //boolean that reads and modifies "word"
  private static boolean printWordState(String word, List<Character> playerGuesses) {
    int correctCount = 0;
    for (int i = 0; i < word.length(); i++) {
      if (playerGuesses.contains(word.charAt(i))) {
        System.out.print(word.charAt(i));
        correctCount++;
      } else {
        System.out.print("_");
      }
    }
    System.out.println();

    return (word.length() == correctCount);
  }

  public static void main(String[] args) throws FileNotFoundException{
    //play again feature
    plays();
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Play one more time? y or n: ");
      if (keyboard.nextLine().equals("y")){
        plays();
      } else {
        System.out.println("Thank you for playing HangingMystic!");
      }
    }
}
