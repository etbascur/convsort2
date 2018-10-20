import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class ConvSort {
    private static ArrayList<Integer> runIndex = new ArrayList<>();
    private static ArrayList<Integer> notrunIndex = new ArrayList<>();
    private int runSize=7;

    public static void findruns(int arr[], int runSize){
        //ArrayList<Integer> runIndex = new ArrayList<>();
        //ArrayList<Integer> notrunIndex = new ArrayList<>();
        int start = 0;
        int finish = 0;
        int i =0;
        while(i<arr.length-1) {
            if (arr[i] < arr[i + 1]) {
                finish++;
            } else {
                if (finish >= runSize-1) {
                    runIndex.add(start);
                    runIndex.add(i);
                    start = i + 1;
                    finish = 0;
                } else {
                    finish = 0;
                    start = i + 1;
                }
            }
            i++;
        }
        if(finish>=runSize-1){
            runIndex.add(start);
            runIndex.add(i);
        }
        //System.out.println("found runs "+runIndex);
    }

    public static void findnotruns(ArrayList<Integer> sorted,int arr[]){
        if(sorted.get(0)!=0){
            notrunIndex.add(0);
            notrunIndex.add(sorted.get(0)-1);
        }
        for(int i=2; i<sorted.size();i+=2){
            if(sorted.get(i)-sorted.get(i-1)!=1){
                notrunIndex.add(sorted.get(i-1)+1);
                notrunIndex.add(sorted.get(i)-1);
            }
        }
        if(sorted.get(sorted.size()-1)<arr.length-1){
            notrunIndex.add(sorted.get(sorted.size()-1)+1);
            notrunIndex.add(arr.length-1);
        }
        //System.out.println("found not runs "+notrunIndex);
    }
//Modified insertion sort for indexes
    public static void insertionSort(int arr[],int start, int end){
        for (int i = start+1; i < end+1;i++) { // i= 1 because we assume initially that left of i is sorted
            int temp = arr[i]; //copy of index at i to be compared
            int k = i - 1;  //value being compared (previous index) initially it will be a[0] (i-1)
            while (k>=start && arr[k] > temp){ //copies the lesser values towards the front of the array (to a lower index)
                arr[k+1] = arr[k]; //swaps the indexes
                --k;
            }
            arr[k+1] = temp; //moves on to the next index to compare
        }
    }

    public static void DivideNotruns(ArrayList<Integer> unsorted,ArrayList<Integer> sorted, int runSize){
        ArrayList<Integer> temp = new ArrayList<>();// temp arraylist to store divided numbers
        for(int i =0;i<unsorted.size();i+=2) { //goes up by two on the start indexes (i= start index, i+1 = finish)
            int size = (unsorted.get(i + 1) + 1) - unsorted.get(i); //size of the notrun
            int low = unsorted.get(i); //current low
            //splits the arrays into indexes of runSize, if subindex is smaller than run size, it won't enter while loop
            while (size >= runSize) {
                temp.add(low);
                temp.add(low + runSize - 1); //adds the high, plus run size -1 because we count from 0
                size -= runSize; //if theres more than one runsize in the subindex, this finds it
                low += runSize; // and sets this to it's low point, otherwise, it's the start of the <runSize array.
            }
            if (size != 0) {//takes care of the leftovers that were less than runSize (one array of <runSize)
                temp.add(low);
                temp.add(unsorted.get(i + 1));
            }//exits while loop, moves to the next start, end pair.
        }
            unsorted.clear(); //finished copying the arraylist, clear it, and copy the ordered temp back into it.
            for(int k=0;k<temp.size();k++){
                unsorted.add(temp.get(k));
            }
            //System.out.println("run or smaller not runs: "+notrunIndex);

    }

    public static void sortNotruns(ArrayList<Integer> unsorted, int arr[]){
        for(int i =0; i<unsorted.size();i+=2){//goes through the array by the start and end index points
            if (unsorted.get(i)== unsorted.get(i+1)){//doesn't sort through single element arrays
                //Do nothing
            }
            else {//sorts
                insertionSort(arr, unsorted.get(i), unsorted.get(i + 1));
            }
        }

        //System.out.println("run or smaller not runs: "+notrunIndex);
        //System.out.println("array print "+Arrays.toString(arr));
    }

    public static void joinLists(ArrayList<Integer> unsorted, ArrayList<Integer> sorted){
        int currSorted=0;
        int currUnsorted=0;
        while(currUnsorted < unsorted.size()){//go through the unsorted array
            if(unsorted.get(currUnsorted)<sorted.get(currSorted)){//if unsorted is less than sorted add them to the array
                sorted.add(currSorted,unsorted.get(currUnsorted+1));
                sorted.add(currSorted, unsorted.get(currUnsorted));
                currSorted+=2; // since we just added elements in, this keeps me at the same element.
                currUnsorted+=2;
            }
            else{//move to the next sorted item
                if(currSorted+2 == sorted.size()){ //fills out the rest of the unsorted array into the sorted if we reached the last run
                    for(int i=currUnsorted;i <unsorted.size();i++){
                        sorted.add(unsorted.get(i));
                    }
                    currUnsorted = unsorted.size();
                }
                else{//otherwise we move to the next sorted (run)
                    currSorted+=2;
                }

                /*sorted.add(currSorted,unsorted.get(currUnsorted+1));
                sorted.add(currSorted, unsorted.get(currUnsorted));
                currUnsorted+=2;*/
            }
        }
        //System.out.println(runIndex);
    }

    public static void merge(int arr[],int start,int mid,int midag,int end){
        int left[] = new int[((mid - start) + 1)];
        int right[]= new int[(end-midag)+1];
        int k = start;
        for (int i=0;i<((mid - start) + 1);i++){
            left[i]=arr[start+i];
        }
        for(int j = 0;j<(end-midag)+1;j++){
            right[j] = arr[midag+j];
        }
        int i =0;//left arr index
        int j =0;//right arr index
        k = start;
        while (i<left.length && j<right.length){
            if(left[i]<=right[j]){
                arr[k]=left[i];
                i++;
            }
            else{
                arr[k]= right[j];
                j++;
            }
            k++;
        }
        while(i<left.length){
            arr[k]= left[i];
            i++;
            k++;
        }
        while(j<right.length){
            arr[k]= right[j];
            j++;
            k++;
        }
    }

    public static void mergecall(ArrayList<Integer> sorted, int arr[]){
        while(sorted.size()>2){
            int start = 0;//start of first array in Arraylist
            int middle=1;
            int midag=2;
            int end = 3;//3 is the 4th item in the arraylist
            ArrayList<Integer> temp= new ArrayList<>();
            int modfour = sorted.size()%4; //will let me know if I have 2 indexes (1 run) in the end.
            int divfour = sorted.size()/4;//lets me know how many times to increment the start position by 4.
            for(int i = 0; i< divfour;i++){
                merge(arr,sorted.get(start),sorted.get(middle),sorted.get(midag),sorted.get(end));
                sorted.remove(middle);
                sorted.remove(middle);
                start+=2;
                middle+=2;
                midag+=2;
                end+=2;
            }
/*            if(modfour != 0){//take care of the last two and add them to the temp
                temp.add(sorted.get(sorted.size()-2));
                temp.add(sorted.get(sorted.size()-1));
            }*/

        }
        //System.out.println("array print "+Arrays.toString(arr));
        System.out.println(runIndex);
    }

    public void sort(int arr[]){
        findruns(arr,runSize);
        findnotruns(runIndex,arr);
        DivideNotruns(notrunIndex,runIndex,runSize);
        sortNotruns(notrunIndex,arr);
        joinLists(notrunIndex,runIndex);
        mergecall(runIndex,arr);
    }

}
