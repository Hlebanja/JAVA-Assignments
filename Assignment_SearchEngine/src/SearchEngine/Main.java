package SearchEngine;

import java.util.ArrayList;
import java.util.Map;

import static SearchEngine.InputReader.*;

public class Main {
    public static void main(String[] args) {

        // Documents input - in the future, JSON Reader with json-simple library
        String document1 = "the brown fox jumped over the brown dog";
        String document2 = "the lazy brown dog sat in the corner";
        String document3 = "the red fox bit the lazy dog";

        //Documents will be named according to this array's index.
        //[0] = document1, [1] = document2, [2] = document 3 and so on
        String[] docsArray = {document1, document2, document3};

        // Using ArrayList for dynamic size if there was a variable amount of docs
        ArrayList<String[]> documentsList = new ArrayList<>();
        for (int i = 0; i < docsArray.length; i++) {
            String[] stringArray = docsArray[i].split(" "); //Tokenization
            documentsList.add(stringArray);
        }
        String userInput = scanLine();
        Calculator calculator = new Calculator(userInput, documentsList);

        // Creates a HashMap. keys: documents names (doc1, doc2, ...) values: TF multiplied by IDF
        Map<String, Float> resultMap = calculator.calculateTFIDF();

        // Sorts results into a decreasing order (high TF-IDF to low TF-IDF)
        Sorter sorter = new Sorter(resultMap);
        String[][] sortedResults = sorter.sortResultsArray();

        // -------------- For validation purposes --------------
        System.out.println("-----------");
        System.out.println("Word count:");
        for (int i = 0; i < documentsList.size(); i++) {
            System.out.println("Document" + (i + 1) + ": " + calculator.countWordOccurrence(documentsList.get(i)));
        }
        System.out.println("-----------");
        System.out.println("Word count / document length");
        for (int i = 0; i < documentsList.size(); i++) {
            System.out.println("Document" + (i + 1) + ": " + calculator.calculateTermFrequency(documentsList.get(i)));
        }
        System.out.println("-----------");
        System.out.println("IDF:");
        System.out.println(calculator.calculateInverseDocFrequency());

        System.out.println("-----------");
        System.out.println("Ordered results:");
        for (int i = 0; i < sortedResults.length; i++) {
            System.out.println(sortedResults[i][0] + " (TF-IDF = " + sortedResults[i][1] + ")");
        }
    }
}