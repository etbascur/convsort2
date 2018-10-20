
import java.util.Random;
public class Tester {
    private int [] arr;                  // This is the array to be sorted.
//    private int runSize = 5;
//    protected String populationStrategy;   // How should the array be populated?
//    protected SortingFactory factory;      // This creates different sorting algorithms.
//    protected String [] algorithms = {"insertionsort", "mergesort", "quicksort"}; // Algos to use.


    /**
     * Default Constructor
     */
    public Tester() {
        arr = new int[]{7, 9, 0, 62, 9, 94, 10, 31, 31, 36, 78, 71, 86, 28, 28, 34, 35, 77, 90, 28, 52, 10, 46, 23, 53};
//        createNewArray(5000);
    }


    /**
     * Constructor which sets the size of the array
     * @param arraySize
     */
    public Tester(int arraySize) {
        createNewArray(arraySize);
    }


    /**
     * Sets the array and populates it according to the population strategy.
     * @param size
     */
    private void createNewArray(int size) {
        arr = new int[size];
        populateArrayRandomly();
    }


    private void changeArraySize(int newSize) {
        createNewArray(newSize);
    }

    /**
     * Populates the array with random values.
     */
    private void populateArrayRandomly() {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            // arr[i] = r.nextInt();
            arr[i] = r.nextInt(100);
        }
    }


    /**
     * Checks whether the array is sorted.
     * @return true if sorted; false otherwise.
     */
    private boolean isSorted(int [] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1])
                return false;
        }
        return true;
    }


    /**
     * Prints the array size and whether sorted or not.
     */
    public void printStatus() {
        System.out.print(arr.length + "\t");
        if (isSorted(arr))
            System.out.println("[OK]");
        else
            System.out.println("[XX] -- not sorted");
    }


    /**
     * Prints the array size and whether sorted or not.
     * @param arr to be considered.
     */
    public void printStatus(int [] arr) {
        System.out.print(arr.length + "\t");
        if (isSorted(arr))
            System.out.println("[OK]");
        else
            System.out.println("[XX] -- not sorted");
    }


    /**
     * Makes a copy of the array. This helps to compare sorting algorithms.
     * @return a copy of the internal array.
     */
    public int [] copyArray() {
        int [] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }


    /**
     * Iterates over the array variable "algorithms", instantiates each and determines the timing.
     * Sends that to stdout.
     */
    public void printSortingTiming() {
        ConvSort convoluted = new ConvSort();
        //MergeSort mergeSort = new MergeSort();
        System.out.println("----------------------------------------------------");
        int [] copy = copyArray();
        //int [] copy2= copyArray();
        long start = System.currentTimeMillis();
        convoluted.sort(copy);
        //mergeSort.sort(copy2);
        long total_time = System.currentTimeMillis() - start;
        System.out.print(total_time + " ms.\t");
        if (total_time < 1000) {
            System.out.print("\t");
        }
        printStatus(copy);
    }


    /**
     * main: try 10 different array sizes; make
     * @param args
     */
    public static void main(String[] args) {

        Tester timing = new Tester();
//        int [] sizes = {50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000, 450000, 500000};
        timing.changeArraySize(500000);
           timing.printStatus();
        timing.printSortingTiming();
        System.out.println("----------------------------------------------------");
//        for (int size : sizes) {
//        }
    }

}

