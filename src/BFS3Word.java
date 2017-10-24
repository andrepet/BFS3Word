import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Node{
    private String word;
    private Node parent;

    public Node(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean equals(Node node) {
        return word.equals(node.word);
    }
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

        // Example code using Kattio to read data from System.in
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            String startWord = io.getWord();
            String endWord = io.getWord();

            start(startWord, endWord);
       }
       io.close();

    }
    public static void start(String startWord, String endWord){
        Dictionary all3Words = new Dictionary("word3.txt");

        /*
            Do we need other containers to store our data?
            Do we need other containers to store data we have already generated?
        */

        Node startNode = new Node(startWord);
        Node endNode = new Node(endWord);

        /*
            How should the logic be for the "brute-force" search?
            We probably need a while-loop, what should the loop condition be?
        */

        // Here should the code or method be called to print out the found path
        System.out.println(startNode.getWord() + " -> " + endNode.getWord());
    }

    /*
        We need to generate 3-word-children for the word of interest...so let's make a method!
        What should we send into public void create3WordChildren?
    */
    //public void create3WordChildren(...?)

}
