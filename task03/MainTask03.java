/**
 * EPAM JavaTraining-Intro. Task 3.
 *
 * @author Natallia Bialiatskaya
 *
 * Version 1.0
 *
 * Date 07.25.2019
 */
package by.epam.javatraining.bialiatskaya.tasks.task03;

/**
 *
 */
public class MainTask03 {

    public static void main(String[] args) {
        double[] array0 = new double[5];
        double[] array1 = {1};
        double[] array2 = {1.1, 2.2};
        double[] array3 = {1.1, 2.2, 5.5, 3.3, 7.7};
        DefineFirstLocalExtremes(array0);
        DefineFirstLocalExtremes(array1);
        DefineFirstLocalExtremes(array2);
        DefineFirstLocalExtremes(array3);
    }

    /* Check whether array is incorrect: doesn't exist or has null lenght */
    static boolean IsZeroArray(double[] array) {
        if (array.length == 0 || array == null) {
            System.out.println("Array is empty or doesn't exist");
            return true;
        } else
            return false;
    }

    /* Method to define both max/min values */
    int[] MinMaxValueIndex(double[] array) {
        int minIndex = 0;  // the index of minimum value
        int maxIndex = 0;  // the index of maximum value

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }

            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }

        int[] returnArray = {minIndex, maxIndex};
        return returnArray;
    }  // int[] MinMaxValueIndex(double[] array)

    /**
     * MainTask03.Task1
     * Find out and print indexes of extremals of the array or just -1
     */
    void ShowExtrems(double[] array) {
        if (IsZeroArray(array))
            return;

        int[] minMaxIndexes = new int[2];
        minMaxIndexes = MinMaxValueIndex(array);

        if (minMaxIndexes[0] == minMaxIndexes[1]) {
            System.out.println(-1);
            return;
        } else {
            System.out.println("Min value element index = " + minMaxIndexes[0]);
            System.out.println("Max value element index = " + minMaxIndexes[1]);
            return;
        }
    }

    /**
     * Method to define arithmetic and geometric means
     * Returns array of elements: 1st (index=0) - arithmetic, 2nd (index = 1) - geometric mean
     */
    double[] ArithmeticGeometricMeans(double[] array) {
        double arithmeticMean = 0;
        double geometricMean = 1;

        for (int i = 0; i < array.length; i++) {
            arithmeticMean += array[i];
            geometricMean *= array[i];
        }

        arithmeticMean = arithmeticMean / array.length;
        geometricMean = Math.pow(geometricMean, 1. / array.length);

        double[] returnArray = {arithmeticMean, geometricMean};
        return returnArray;
    }

    /**
     * MainTask03.Task2
     * Calculate and print either arithmetic or geometric means
     */
    void showMeans(double[] array) {
        if (IsZeroArray(array))
            return;

        double[] result = new double[2];
        result = ArithmeticGeometricMeans(array);

        System.out.println("Arithmetic mean = " + result[0]);
        System.out.println("Geometric  mean = " + result[1]);
    }

    /* Check whether all elements of the vector are in an ordered ascending form */
    boolean isAscendingForm(double[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i])
                return false;
        }
        return true;
    }

    /* Check whether all elements of the vector are in an ordered descending form */
    boolean isDescendingForm(double[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] < array[i])
                return false;
        }
        return true;
    }

    /** MainTask03.Task3
     *  Check whether all elements of the vector are in an ordered ascending or descending form
     *
     */
    void checkForOrderedForm(double[] array) {
        if (IsZeroArray(array))
            return;

        if (array.length == 1) {
            System.out.println("Array consists only from one element, impossible to define any order.");
            return;
        }

        String mess = (isAscendingForm(array)? "ascending": "descending");
        System.out.println("All elements of the vector are in an ordered " + mess + " form.");
    }

    /* Method to define both max/min local elements for 2-element vector
     * Returns array of elements: 1st (index=0) - local minimum, 2nd (index = 1) - local maximum
     * */
    public int[] localMinMaxIndexFor2Elements(double[] array) {
        int[] returnArray = new int[2];

        returnArray[0] = (array[0] < array[1] ? 0 : 1);
        returnArray[1] = returnArray[0] | (int) 1;

        return returnArray;
    }

    /* Method to define index of local minimum for vectors (with more than 2 elements)
     * Returns local minimum, or -1 if there's no local minimum
     * */
    static int localMINIndex(double[] array) {
        if (array[0] < array[1])
            return 0;

        int i = 0;
        int lastButOne = array.length - 2;

        do {
            i++;
            if ((array[i] < array[i - 1]) && (array[i] < array[i + 1]))
                return i;
        } while (i < lastButOne);

        if (array[i + 1] < array[i])
            return i + 1;

        return -1;
    }

    /* Method to define index of local maximum for vectors (with more than 2 elements)
     * Returns local MAXimum, or -1 if there's no local maximum
     * */
    static int localMAXIndex(double[] array) {
        if (array[0] > array[1])
            return 0;

        int i = 0;
        int lastButOne = array.length - 2;

        do {
            i++;
            if ((array[i] > array[i - 1]) && (array[i] > array[i + 1]))
                return i;
        } while (i < lastButOne);

        if (array[i + 1] > array[i])
            return i + 1;

        return -1;
    }

    /** MainTask03.Task4
     *  Define the first local minimum and local maximum
     *
     */
    public static void DefineFirstLocalExtremes(double[] array) {
        if (IsZeroArray(array))
            return;

        if (array.length == 1) {
            System.out.println("Array consists only from one element, impossible to define the local extermal elements.");
            return;
        }

        int maxIndex;
        int minIndex;
        if (array.length == 2) {
            minIndex = (array[0] < array[1] ? 0 : 1);
            maxIndex = minIndex | (int) 1;
        } else {
            minIndex = localMINIndex(array);
            maxIndex = localMAXIndex(array);
        }

        if (maxIndex == minIndex) {
            System.out.println(-1);
            return;
        } else {
            System.out.println("local minimum index = " + minIndex + " ");
            System.out.println("local maximum index = " + maxIndex + "");
        }
    }

//    /** MainTask03.Task
//     *
//     *
//     */
//    void check(double[] array) {
//        if (IsZeroArray(array))
//            return;
//
//        System.out.println(".");
//    }
//
//    /** MainTask03.Task
//     *
//     *
//     */
//    void check(double[] array) {
//        if (IsZeroArray(array))
//            return;
//
//        System.out.println(".");
//    }
}