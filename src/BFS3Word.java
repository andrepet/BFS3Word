import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Node{
    private String word;
    private Node parent;

    public Node(String word){
        this.word = word;
        this.parent = null;
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

    public boolean isSameWord(String word) {
        return this.word.equals(word);
    }
}

class Dictionary{

    private Set<String> dictionary = new HashSet<>();

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
    public final static Dictionary swedishDictionary = new Dictionary("word3.txt");
    public final static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','å','ä','ö'};
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

        Dictionary visitedChildren = new Dictionary();
        Queue<Node> remainingChildren = new LinkedList<>();

        Node startNode = new Node(startWord);
        Node endNode = new Node(endWord);

        remainingChildren.add(startNode);
        while(!remainingChildren.isEmpty()){
            Node currentNode = remainingChildren.remove();
            create3WordChildren(currentNode, endNode, remainingChildren, visitedChildren);
            if (endNode.getParent() != null) break;
        }

        if (endNode.getParent() != null){
            System.out.printf("A path exist between '%s' to '%s'! \n", startNode.getWord(), endNode.getWord());
            System.out.print("\nReverse order: ");
            writeChainBackwards(endNode);
            System.out.print("\nNormal order:  ");
            writeChain(endNode);
            System.out.println();
        }
        else{
            System.out.printf("No path exist!\n");
        }
    }

    public static void create3WordChildren( Node currentNode, Node endNode, Queue<Node> remainingChildren, Dictionary visitedChildren ){
        String currentWord = currentNode.getWord();
        for (int i = 0; i<currentWord.length(); i++){
            for (char letter : alphabet) {
                char[] charWord = currentWord.toCharArray();
                charWord[i] = letter;
                String newWord = new String(charWord);

                if (endNode.isSameWord(newWord)){
                    endNode.setParent(currentNode);
                    return;
                }

                if (!swedishDictionary.exist(newWord)) continue;
                if (visitedChildren.exist(newWord)) continue;

                Node newChild = new Node(newWord);
                newChild.setParent(currentNode);
                remainingChildren.add(newChild);
                visitedChildren.add(newWord);
            }
        }
    }
    

    public static void writeChainBackwards(Node node){
        Node currentNode = node;
        while(currentNode != null){
            System.out.print(currentNode.getWord());
            if (currentNode.getParent() != null){
                System.out.print(" -> ");
            }
            currentNode = currentNode.getParent();
        }
    }

    public static void writeChain(Node node){
        if (node == null) return;
        writeChain(node.getParent());
        if (node.getParent() != null){
            System.out.print(" -> ");
        }
        System.out.print(node.getWord());
    }
}
