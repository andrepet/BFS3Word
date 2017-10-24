import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Node{
    String word;
    Node parent;
}

class Dictionary{

    private List<String> dictionary = new LinkedList<>();

    public Dictionary(){}

    public Dictionary(String filePath){
        this.readAndFillFromFile(filePath);
    }

    private void readAndFillFromFile(String filePath){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                this.dictionary.add(line);
            }
            bufferedReader.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean exist(String word){
        return dictionary.contains(word);
    }

    public void add(String word){
        dictionary.add(word);

    }
}

public class BFS3Word {
    public static void main(String[] args) {
        Dictionary all3Words = new Dictionary("word3.txt");

        /*
            Do we need other containers to store our data?
            Do we need other containers to store data we have already generated?
        */

        /*
            How should the logic be for the "brute-force" search?
            We probably need a while-loop, what should the loop condition be?
        */
    }

    /*
        We need to generate 3-word-children for the word of interest...so let's make a method!
        What should we send into public void create3WordChildren?
    */
    //public void create3WordChildren(...?)

}
