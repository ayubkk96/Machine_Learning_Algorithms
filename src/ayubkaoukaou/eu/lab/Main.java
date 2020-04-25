package ayubkaoukaou.eu.lab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    /* Please place the correct file path below. */
    static final String getFileName = "C:\\\\Users\\\\kaouk\\\\OneDrive\\\\Documents\\\\courseworktwo\\\\cw2DataSet1.csv";
    static final String getFileNameTwo = "C:\\\\Users\\\\kaouk\\\\OneDrive\\\\Documents\\\\courseworktwo\\\\cw2DataSet2.csv";
    static final File fileOne = new File(getFileName);
    static final File fileTwo = new File(getFileNameTwo);
    static final int rowData = 2810;
    static final int columnData = 65;

    public static void main(String[] args) throws IOException {
//Calling the readData method and storing the data from the data sets into my two dimensional arrays.
        String[][] dataSetOneStrings = readData(fileOne);
        String[][] dataSetTwoStrings = readData(fileTwo);

        //Parsing each of the data sets to integers.
        int[][] dataSetOneIntegers = parseToIntArray(dataSetOneStrings);
        int[][] dataSetTwoIntegers = parseToIntArray(dataSetTwoStrings);

        //Start the neural network
        //NeuralNetwork.mlpAlgorithm(dataSetTwoIntegers);
        // NeuralNetwork.mlpAlgorithm(dataSetOneIntegers);

        //Euclidean distance algorithm
        //EuclideanDistance.euclideanDistance(dataSetTwoIntegers, dataSetOneIntegers);
        // EuclideanDistance.euclideanDistance(dataSetOneIntegers, dataSetTwoIntegers);
    }

    //A method that reads the file and inputs the values into a two dimensional array
    static String[][] readData(File file) throws IOException {

        return Files.lines(file.toPath())
                .map((line) -> line.trim().split(","))
                .toArray(String[][]::new);

    }

    //A method that parses the string two dimensional arrays into integer two dimensional arrays
    static int[][] parseToIntArray(String[][] originalStringArray) {
        int[][] parsedArray = new int[rowData][columnData];
        for (int row = 0; row < originalStringArray.length; row++) {
            for (int column = 0; column < originalStringArray[row].length; column++) {
                parsedArray[row][column] = Integer.parseInt(originalStringArray[row][column]);
            }
        }
        return parsedArray;
    }
}
