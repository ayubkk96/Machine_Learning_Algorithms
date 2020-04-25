package ayubkaoukaou.eu.lab;

import java.util.ArrayList;

public class EuclideanDistance {

    //Globalised variables used in the program
    static double totalLine = 0;
    static double result;
    static double smallestLineChecker = 5000000;
    static double correctComparisonCounter = 0;
    static double wrongComparisonCounter = 0;
    static double rowDatasetTwoTracker = 0;
    static double rowDatasetOneTracker = 0;

    //A list to hold the values that are squared
    static ArrayList<Double> listOfPointsSquared = new ArrayList<>();

    static void euclideanDistance(int[][] dataSetElementTwo, int[][] dataSetElementOne) {
        for (int rowDataSetTwo = 0; rowDataSetTwo < dataSetElementTwo.length; rowDataSetTwo++) {
            for (int row = 0; row < dataSetElementOne.length; row++) {
                for (int column = 0; column < dataSetElementOne[row].length; column++) {
                    //Minus each element with each other and then add the result to the variable called result.
                    result = (dataSetElementTwo[rowDataSetTwo][column] - dataSetElementOne[row][column]);
                    //Square each element and add it to a list.
                    listOfPointsSquared.add(Math.pow((result), 2));
                    //Once the column reaches the 64th column in the first dataset (the one before the comparison) it sums the list of squared numbers.
                    if (column == 63) {
                        sumList();
                    }
                }
                //Check if the total line of the row is smaller than a definite bigger number at first
                //It will then add that number to the smallest line checker variable.
                //Once a smaller total line comes along, the smaller total line becomes the smallest line checker variable
                //Keep track of the rows
                checkForSmallestLine(rowDataSetTwo, row);
                totalLine = 0;
            }
            categoriser(dataSetElementTwo, dataSetElementOne);
        }
        System.out.println("Depending on the order of the categorisaition (dataset one to two or two to one) " +
                "the amount of times the comparison was correct using euclidean distance was: " + correctComparisonCounter + " and the amount of times it was wrong was: " + wrongComparisonCounter);
    }

    //Sum the list
    public static void sumList() {
        for (Double aDouble : listOfPointsSquared) {
            totalLine += aDouble;
        }
        //The list is then cleared so that the new numbers from the next row can fill it.
        listOfPointsSquared.clear();
    }

    //Categorise the data
    public static void categoriser(int[][] dataSetElementTwo, int[][] dataSetElementOne) {
        if (dataSetElementTwo[(int) rowDatasetTwoTracker][64] == dataSetElementOne[(int) rowDatasetOneTracker][64]) {
            ++correctComparisonCounter;
        } else {
            ++wrongComparisonCounter;
        }

        smallestLineChecker = 5000000;
    }


    //Check for the smallest line and then set the trackers to equal the exact rows that the smallest line is on
    public static void checkForSmallestLine(int rowDataSetTwo, int row) {
        if (totalLine <= smallestLineChecker) {
            smallestLineChecker = totalLine;
            rowDatasetTwoTracker = rowDataSetTwo;
            rowDatasetOneTracker = row;
        }
    }

}