package SearchEngine;

import java.util.Map;

public class Sorter {

    private Map<String, Float> resultMap;
    public String[][] resultsArray;

    public Sorter(Map<String, Float> resultMap) {
        this.resultMap = resultMap;
        this.resultsArray = createResultsArray();
    }

    // STEP 4 - Sort results in a decreasing order utilizing TF-IDF. Because HashMap is not suitable for sorting:
    //  first, pass elements from HashMap to bidirectional array

    private String[][] createResultsArray() {
        String[][] resultArray = new String[this.resultMap.size()][2];
        int counter = 0;
        for (Map.Entry<String, Float> element : this.resultMap.entrySet()) {
            resultArray[counter][0] = element.getKey();
            resultArray[counter][1] = String.valueOf(element.getValue());
            counter++;
        }
        return resultArray;
    }

    //  second, sort (descending) bidirectional array and present results
    public String[][] sortResultsArray() {
        for (int i = 0; i < this.resultsArray.length; i++) {
            for (int j = i + 1; j < this.resultsArray.length; j++) {
                if (Float.parseFloat(this.resultsArray[i][1]) < Float.parseFloat(this.resultsArray[j][1])) {
                    String tempKey = this.resultsArray[i][0];
                    String tempValue = this.resultsArray[i][1];

                    this.resultsArray[i][0] = this.resultsArray[j][0];
                    this.resultsArray[i][1] = this.resultsArray[j][1];

                    this.resultsArray[j][0] = tempKey;
                    this.resultsArray[j][1] = tempValue;
                }
            }
        }
        return this.resultsArray;
    }

}
