package by.epam.javatraining.bialiatskaya.tasks.task03;

public class Realization {

    public Realization() {
        double[] array0 = new double[5];
        double[] array1 = {1};
        double[] array2 = {1.1, 2.2};
        double[] array3 = {1.1, 7.7, 2.2, 5.5, 3.3};
        double[] array4 = {1.1, 1.1, 1.1, 1.1, 1.1};
        double[] array5 = {1.1, 1.2, 1.5, 1.6, 1.9};
        double[] array6 = {10.1, 9.1, 7.1, 3.1, 1.1};
        System.out.println("task1");
        showExtrems(array4);
        showExtrems(array3);
        System.out.println("task2");
        showMeans(array3);
        System.out.println("task3");
        checkForOrderedForm(array1);
        checkForOrderedForm(array4);
        checkForOrderedForm(array5);
        checkForOrderedForm(array6);
        System.out.println("task4");
        defineFirstLocalExtremes(array0);
        defineFirstLocalExtremes(array1);
        defineFirstLocalExtremes(array2);
        defineFirstLocalExtremes(array3);
        System.out.println("task5");
        search(array5, 1.6);
    }

    /* Check whether array is incorrect: doesn't exist or has null lenght */
    boolean isNotZeroArray(double[] array) {
        if (array.length == 0 || array == null) {
            System.out.println("Array is empty or doesn't exist");
            return false;
        } else
            return true;
    }

    /* Method to define max/min values */
    int maxValueIndex(double[] array) {
        int maxIndex = 0;  // the index of maximum value

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }  // int maxValueIndex(double[] array)

    /* Method to define min values */
    int minValueIndex(double[] array) {
        int minIndex = 0;  // the index of minimum value

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }

        return minIndex;
    }  // int minValueIndex(double[] array)

    /**
     * MainTask03.Task1
     * Find out and print indexes of extremals of the array or just -1
     */
    void showExtrems(double[] array) {
        if (isNotZeroArray(array)) {
            int minIndex = minValueIndex(array);
            int maxIndex = maxValueIndex(array);

            if (minIndex == maxIndex) {
                System.out.println("-1");
            } else {
                System.out.println("Min value element index = " + minIndex +
                                   " max value element index = " + maxIndex);
            }
        }
    }

    /* Method to define arithmetic  mean */
    double arithmeticMeans(double[] array) {
        double arithmeticMean = 0;

        for (int i = 0; i < array.length; i++) {
            arithmeticMean += array[i];
        }

        return arithmeticMean / array.length;
    }

    /* Method to define geometric mean */
    double geometricMeans(double[] array) {
        double geometricMean = 1;

        for (int i = 0; i < array.length; i++) {
            geometricMean *= array[i];
        }

        return Math.pow(geometricMean, 1. / array.length);
    }

    /**
     * MainTask03.Task2
     * Calculate and print either arithmetic or geometric means
     */
    void showMeans(double[] array) {
        if (isNotZeroArray(array)) {
            double arithmetic = arithmeticMeans(array);
            double geometric = geometricMeans(array);

            System.out.print("Arithmetic mean = " + arithmetic +
                             " geometric  mean = " + geometric + "\n");
        }
    }

    /* Check whether all elements of the vector are in an ordered ascending form */
    boolean isAscendingForm(double[] array) {
        int i = 1;
        while (i < array.length) {
            if (array[i - 1] > array[i])
                return false;
            i++;
        }

         return true;
    }

    /* Check the elements whether are of the same value*/
    boolean notSameElements(double[] array) {
        if (array[0] == array[array.length - 1]) {
            return false;
        } else {
            return true;
        }
    }

    /* Check whether all elements of the vector are in an ordered descending form */
    boolean isDescendingForm(double[] array) {
        int i = 1;
        while (i < array.length) {
            if (array[i - 1] < array[i])
                return false;
            i++;
        }

        return true;
    }

    /* Define whether array is ordered.
       returns 0 - if ascending order, 1 - if descending order, -1 - otherwise
    * */
    int isOrdered(double[] array) {
        int returnVar = -1;
        if (notSameElements(array)) {
            if (isAscendingForm(array)) {
                returnVar = 0;
            } else {
                if (isDescendingForm(array))
                    returnVar = 1;
            }
        }

        return returnVar;
    }

    /** MainTask03.Task3
     *  Check whether all elements of the vector are in an ordered ascending or descending form
     */
    void checkForOrderedForm(double[] array) {
        if (isNotZeroArray(array)) {
            if (array.length == 1) {
                System.out.println("Array contains only one item, impossible to define any order.");
                return;
            }

            String mess = "";
            switch (isOrdered(array)) {
                case (1):
                    System.out.println("All elements of the vector are in an ordered ascending form.");
                    break;
                case (0):
                    System.out.println("All elements of the vector are in an ordered descending form.");
                    break;
                case (-1):
                    System.out.println("all elements are the same, can't detect order ");
                    break;
            }
        }
    }

//    /* Method to define both max/min local elements for 2-element vector
//     * Returns array of elements: 1st (index=0) - local minimum, 2nd (index = 1) - local maximum
//     * */
//    int[] localMinMaxIndexFor2Elements(double[] array) {
//        int[] returnArray = new int[2];
//
//        returnArray[0] = (array[0] < array[1] ? 0 : 1);
//        returnArray[1] = returnArray[0] | (int) 1;
//
//        return returnArray;
//    }
//
    /* Method to define index of local minimum for vectors (with more than 2 elements)
     * Returns local minimum, or -1 if there's no local minimum
     * */
    int localMINIndex(double[] array) {
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
     int localMAXIndex(double[] array) {
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

    boolean isArrayRight(double[] array) {
        if (isNotZeroArray(array)) {
            if (array.length == 1) {
                System.out.println("Array consists only from one element, " +
                                   "impossible to define the local extreme elements.");
                return false;
            } else {
                return true;
            }
        } else { return false;}
    }

    /** MainTask03.Task4
     *  Define the first local minimum and local maximum
     */
    void defineFirstLocalExtremes(double[] array) {
        if (isArrayRight(array)) {
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
    }

    /* Make the linear search */
    int linearSearch(double[] array, double item) {
        int i = 0;

        while (i < array.length) {
            if (array[i] == item)
                return i;
            i++;
        }
        return -1;
    }

    /*  Make the binary search */
    int binarySearch(double[] array, double item) {
        int lowIndex  = 0;
        int highIndex = array.length - 1;
        int index = -1;

        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;

            if (array[midIndex] < item) {
                lowIndex = midIndex + 1;
            } else
                if (array[midIndex] > item) {
                    highIndex = midIndex - 1;
                } else {
                    index = midIndex;  // index of item in sorted array
                    break;
                }
        }
        return index;
    }

    void showMessTask3(double[] array, int index) {
        if (index == -1) {
            System.out.println("The item hasn't found.");
        } else {
            System.out.println("Here is the item: " + array[index]);
        }
    }
    /** MainTask03.Task5
     *  Make either binary and linear search */
    void search(double[] array, double item) {
        if (isArrayRight(array)) {
            showMessTask3(array, linearSearch(array, item));

            double[] sortedArray = array;
            if (isOrdered(array) == -1) {
                sortedArray = sort(array);
            }
            showMessTask3(sortedArray, binarySearch(sortedArray, item));
        }
    }

    double[] sort(double[] array) {
        return array;
    }
//    /** MainTask03.Task
//     *
//     *
//     */
//    void check(double[] array) {
//        if (isNotZeroArray(array))
//            return;
//
//        System.out.println(".");
//    }
}
