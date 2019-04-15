package word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class wordCombination {

    public static void main(String[] args) throws IOException {
        Dictionary.loadDictionary();
        new Helper().printOutput();
    }
}

/**
 * Dictionary Class for checking word is valid english dictionary word or not
 */
class Dictionary {

    static String urlEndPoint = "https://raw.githubusercontent.com/dwyl/english-words/master/words.txt";
    static ArrayList<String> localDictionary = new ArrayList<String>();

    /**
     * Load online dictionary to local for checking word is meaningful or not
     */
    static void loadDictionary() throws IOException {
        URL oracle = new URL(urlEndPoint);
        BufferedReader br = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String st;
        while ((st = br.readLine()) != null) {
            localDictionary.add(st);
        }
    }

    /**
     * Checking word is meaningful or not
     */
    static boolean isEnglishWord(String str) {
        return localDictionary.contains(str);
    }
}

/**
 * Helper class for load dictionary and find all word combination word
 */
class Helper {

    public void printOutput() throws IOException {
        while (true) {
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("=> Search english dictionary word from given input string.");
            System.out.println("Please enter string => ");
            // Reading data using readLine
            String str = reader.readLine();
            System.out.println("-------------Output------------");
            System.out.println("");
            ArrayList<String> wordList = new Helper().FindAllCombinationWord(str);
            for (String inputString : wordList) {
                if (Dictionary.isEnglishWord(inputString)) {
                    System.out.println("=> " + inputString);
                }
            }
            System.out.println("");
            System.out.println("-------------EOF------------");
            System.out.println("Do you want to continue...? Y Or N Or other to exit. => ");
            // Reading data using readLine
            str = reader.readLine();

            if (str.toLowerCase().contains("n")) {
                break;
            } else if (str.toLowerCase().contains("y")) {
                System.out.println("Thank you for take interest in search.");
                System.out.println("");
            }

        }
    }

    /**
     * Find All Combination Word function
     *
     * @param str string to calculate permutation for
     */
    public ArrayList<String> FindAllCombinationWord(String str) {

        ArrayList<String> wordList = new ArrayList<String>();

        int n = str.length();
        ArrayList<String> combinationWord = permute(str, 0, n - 1);

        for (String item : combinationWord) {
            int j = 0;
            for (int i = n; i >= 2; i--) {
                String finalString = item.substring(j);

                if (!wordList.contains(finalString)) {
                    wordList.add(finalString);
                }
                j++;
            }
        }
        return wordList;
    }

    /**
     * permutation function
     *
     * @param str string to calculate permutation for
     * @param start starting index
     * @param end   end index
     */
    ArrayList<String> combinationWord = new ArrayList<String>();

    public ArrayList<String> permute(String str, int start, int end) {

        if (start == end)
            combinationWord.add(str);// System.out.println(str);
        else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i);
                permute(str, start + 1, end);
                str = swap(str, start, i);
            }
        }
        return combinationWord;
    }

    /**
     * Swap Characters at position
     *
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
