
# README

## FindWise - Simple Search Engine

### Launching
This is a simple application with no GUI. For launching, simply run the main task through your IDE.

### How it works
The search engine's logic is divided into the following steps and substeps.
  - Steps 1, 2 and 3 are taken care of by the Calculator class. 
  - Step 4 is taken care of by the Sorter class.

##### Step 1 - Term Frequency (TF) calculations
  - Count the searched term in each document
  - Divide the count by the document length (term frequency adjusted for document length)
  
##### Step 2 - Inverse document frequency (IDF) calculations
  - Count in how many documents the searched term appears
  - Calculate inverse document frequency
  
##### Step 3 - Calculate TF * IDF for each document
  - Results are stored in a HashMap, with documents names as keys and TF-IDF as values  

##### Step 4 - Sort results from high TF-IDF to lowest TF-IDF


###### Final notes:
  - ###### The original idea was to have a JSON file being inputed through the usage of the json-simple library. This was not yet done as to avoid unecessary complexity.
  - ###### The Step 4 implementation can be greatly improved.
