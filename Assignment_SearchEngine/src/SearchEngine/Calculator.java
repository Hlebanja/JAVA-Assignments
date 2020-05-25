package SearchEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private String searchedWord;
    private ArrayList<String[]> documentsList;

    public Calculator(String searchedWord, ArrayList<String[]> documentsList) {
        this.searchedWord = searchedWord;
        this.documentsList = documentsList;
    }

    //STEP 1 - Term frequency calculation
    // count of searched word from each document
    public int countWordOccurrence(String[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(this.searchedWord)) {
                counter++;
            }
        }
        return counter;
    }

    // TF: word count / document length
    public float calculateTermFrequency(String[] arr) {
        return (float) countWordOccurrence(arr) / arr.length;
    }

    //STEP 2 - Inverse document frequency calculation
    // Number of documents where word appear
    public int countDocumentAppearance(ArrayList<String[]> docsList) {
        int counter = 0;
        for (int i = 0; i < docsList.size(); i++) {
            if (countWordOccurrence(docsList.get(i)) > 0) {
                counter++;
            }
        }
        return counter;
    }

    // IDF: log(Total number of documents / number of docs where the word appear)
    public float calculateInverseDocFrequency() {
        int totalNumberOfDocs = this.documentsList.size();
        int documentAppearance = countDocumentAppearance(this.documentsList);
        float appearanceFactor = (float) totalNumberOfDocs / documentAppearance;

        if (documentAppearance == 0) {
            return 0;
        }
        return (float) Math.log10(appearanceFactor);
    }

    // STEP 3 - calculate td-idf for each document and add them to a HashMap
    public Map<String, Float> calculateTFIDF() {
        Map<String, Float> docsMap = new HashMap<>();
        float IDF = calculateInverseDocFrequency();
        float adjustedFrequencyScore; // TF-IDF

        for (int i = 0; i < this.documentsList.size(); i++) {
            adjustedFrequencyScore = calculateTermFrequency(documentsList.get(i)) * IDF;
            if ( adjustedFrequencyScore > 0) {
                docsMap.put("document" + (i + 1), adjustedFrequencyScore);
            }
        }
        return docsMap;
    }
}
