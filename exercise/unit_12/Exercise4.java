package exercise.unit_12;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise4 {
    public static void main(String[] args) {
        int threadsNum = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadsNum);
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(threadsNum);
        for (int i = 1; i <= threadsNum; i++) {
            try {
                String result = executor.submit(new NewCalculateThread(10000 * (i - 1) + 1, 10000 * i)).get();
                blockingQueue.add(result);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }

        executor.shutdown();
        blockingQueue.iterator().forEachRemaining(System.out::println);
    }

}

class NewCalculateThread implements Callable<String> {
    private static final long LIMIT = 100_000L;

    private long maxDivisorCount;
    private long maxDivisor;
    private Counter counter;
    private int start;
    private int end;

    public NewCalculateThread(int start, int end) {
        this.maxDivisor = Long.MIN_VALUE;
        this.maxDivisorCount = 0L;
        this.counter = new Counter();
        this.start = start;
        this.end = end;
    }

    public long getMaxDivisorCount() {
        return maxDivisorCount;
    }

    public long getMaxDivisor() {
        return maxDivisor;
    }

    @Override
    public String call() throws Exception {
        long startTime = System.currentTimeMillis();

        for (int i = start; i <= end; i++) {
            counter.clear();
            for (int j = 1; j <= LIMIT; j++) {
                if (i % j == 0) {
                    counter.increment();
                }
            }

            if (maxDivisorCount < counter.getCount()) {
                maxDivisorCount = counter.getCount();
                maxDivisor = i;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Max Divisor: " + getMaxDivisor());
        builder.append(", Max Divisor Count: " + getMaxDivisorCount());
        builder.append(", Seconds: " + (System.currentTimeMillis() - startTime) / 1000D + " sec");
        return builder.toString();
    }

}
