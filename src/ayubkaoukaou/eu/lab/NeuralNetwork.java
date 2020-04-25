package ayubkaoukaou.eu.lab;

import java.util.ArrayList;
import java.util.Collections;

//A class containing the multi layered perceptron algorithm.
//This MLP does not contain back propagation as I could not complete it due to family and health issues.
public class NeuralNetwork {

    //Global variables for the algorithm
    static float sum = 0;
    static float weight = 1;


    static float largestNumberInOutputLayer;
    //Specify your perceptrons count
    static final int numberOfHiddenLayerPerceptrons = 50;
    static final int numberOfOutputLayerPerceptrons = 100;

    //A transfer function result variable
    static float transferFunctionResult = 0;

    //lists to store transfer function results and weights
    static ArrayList<Float> hiddenLayerPerceptrons = new ArrayList<>();
    static ArrayList<Float> outputLayerPerceptrons = new ArrayList<>();
    static ArrayList<Float> inputs = new ArrayList<>();
    static ArrayList<Float> tempWeights = new ArrayList<>();

    //to create the random function
    static final int max = 2;
    static final int min = -2;
    static float correctComparison = 0;
    static float wrongComparison = 0;

    //A multi layered perceptron algorithm
    static void mlpAlgorithm(int[][] dataSet) {
        //Loop through the data
        for (int row = 0; row < dataSet.length; row++) {
            for (int iteratorHiddenLayer = 0; iteratorHiddenLayer < numberOfHiddenLayerPerceptrons; iteratorHiddenLayer++) {
                for (int column = 0; column < dataSet[row].length; column++) {
                    //store the weight in a variable
                    weight = (weight * (min + (float) (Math.random() * ((max - min) + 1))));
                    //Multiply inputs by random weights
                    float result = dataSet[row][column] * weight;
                    tempWeights.add(result);

                    //If the column count reaches 63 (64) in the dataset, it will clone the tempweights list
                    // into another list and then sum the weights,
                    // sigmoid the sum and add it to a result.
                    if (column == 63) {
                        sumWeights(sum);
                    }
                }
                //Add the transfer function result to a list
                //Clear temp weights list to be used again
                hiddenLayerPerceptrons.add(transferFunctionResult);
                tempWeights.clear();
            }
            //Loop through the amount of output layer perceptrons wanted to be created and initialise them
            outputLayerPerceptronsInit(hiddenLayerPerceptrons, tempWeights, outputLayerPerceptrons);

            //Check the largest number that comes out the output layer perceptron's transfer function
            //If it is the same as the dataset categorisation, then it is correct, otherwise wrong.
            categoriser(dataSet, row);

            //Reset the variables so that the next row can use it to correctly categorise.
            resetVariables(largestNumberInOutputLayer);
        }
        System.out.println("The amount of times it was correct was: " + correctComparison + " and the amount of times it was wrong was: " + wrongComparison);
    }

    //Init output layer perceptrons, loop through amount of perceptrons,
    // multiply them by a random weight, add them to a list to sum them,
    // take the sum and put it into a transfer function,
    // take the results and put them in a list
    public static void outputLayerPerceptronsInit(ArrayList<Float> listOfHiddenPerceptrons, ArrayList<Float> temporaryListOfWeights, ArrayList<Float> listOfOutputPerceptrons) {
        float sum = 0;
        for (int iteratorOutputLayer = 0; iteratorOutputLayer < numberOfOutputLayerPerceptrons; iteratorOutputLayer++) {
            float multipliedWeight = 0;
            for (float number : listOfHiddenPerceptrons) {
                multipliedWeight = number * (min + (float) (Math.random() * ((max - min) + 1)));
                temporaryListOfWeights.add(multipliedWeight);
            }
            for (float number : temporaryListOfWeights) {
                sum += number;
            }
            float transferFunctionResultTwo = sigmoidFunction(sum);

            listOfOutputPerceptrons.add(transferFunctionResultTwo);

        }
    }

    public static void sumWeights(float sum) {
        inputs = (ArrayList<Float>) tempWeights.clone();
        for (float number : inputs) {
            sum += number;
        }
        transferFunctionResult = sigmoidFunction(sum);
        inputs.clear();
        sum = 0;
    }

    public static void resetVariables(float anyNumberUsed) {
        outputLayerPerceptrons.clear();
        hiddenLayerPerceptrons.clear();
        tempWeights.clear();
        anyNumberUsed = 0;
    }

    //Sigmoid function
    public static float sigmoidFunction(float sum) {
        sum = (float) (1 / (1 + Math.exp(-sum)));
        return sum;
    }

    public static void categoriser(int[][] dataSet, int row) {
        largestNumberInOutputLayer = Collections.max(outputLayerPerceptrons);
        if (largestNumberInOutputLayer == dataSet[row][64]) {
            correctComparison++;
        } else {
            wrongComparison++;
        }
    }
}
