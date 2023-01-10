package threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MergeSortWithMultiThreads {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Integer> list = Arrays.asList(5, 2, 1, 3, 9, 6, 4, 8, 7);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Sorter sorter = new Sorter(list, executorService);

        Future<List<Integer>> sortedListFuture = executorService.submit(sorter);
        List<Integer> sortedList = sortedListFuture.get();

        for(int i: sortedList) {
            System.out.print(i+" ");
        }

        executorService.shutdown();
    }
}

class Sorter implements Callable<List<Integer>> {

    private List<Integer> arrayToSort;
    private ExecutorService executorService;

    public Sorter(List<Integer> arrayToSort, ExecutorService executorService) {
        this.arrayToSort = arrayToSort;
        this.executorService = executorService;
    }

    @Override
    public List<Integer> call() throws Exception {
        if(arrayToSort.size() <= 1) {
            return arrayToSort;
        }

        int mid = arrayToSort.size()/2;

        List<Integer> leftArray = new ArrayList<>();
        List<Integer> rightArray = new ArrayList<>();

        for(int i=0; i<mid; i++) {
            leftArray.add(arrayToSort.get(i));
        }

        for(int i=mid; i<arrayToSort.size(); i++) {
            rightArray.add(arrayToSort.get(i));
        }

        Sorter leftSorter = new Sorter(leftArray, executorService);
        Sorter rightSorter = new Sorter(rightArray, executorService);

        Future<List<Integer>> leftArrayFuture = executorService.submit(leftSorter);
        Future<List<Integer>> rightArrayFuture = executorService.submit(rightSorter);

        List<Integer> sortedArray = new ArrayList<>();
        int i=0, j=0;

        List<Integer> leftSortedArray = leftArrayFuture.get();
        List<Integer> rightSortedArray = rightArrayFuture.get();

        while(i < leftSortedArray.size() && j < rightSortedArray.size()) {
            if(leftSortedArray.get(i) < rightSortedArray.get(j)) {
                sortedArray.add(leftSortedArray.get(i));
                i++;
            } else {
                sortedArray.add(rightSortedArray.get(j));
                j++;
            }
        }

        while(i < leftSortedArray.size()) {
            sortedArray.add(leftSortedArray.get(i));
            i++;
        }

        while(j < rightSortedArray.size()) {
            sortedArray.add(rightSortedArray.get(j));
            j++;
        }

        return sortedArray;
    }
}
