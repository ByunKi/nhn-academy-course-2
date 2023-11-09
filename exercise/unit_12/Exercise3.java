package exercise.unit_12;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Exercise3 {
    public static void main(String[] args) {
        int threadsNum = 10;
        ConcurrentLinkedQueue<Runnable> threadPool = new ConcurrentLinkedQueue<>();
        ArrayBlockingQueue<Thread> blockingQueue = new ArrayBlockingQueue<>(threadsNum);
        for (int i = 1; i <= threadsNum; i++) {
            int begin = 10000 * (i - 1) + 1;
            int end = 10000 * i;

            threadPool.add(() -> {
                Counter counter = new Counter();
                TimeThread time = new TimeThread();
                long maxDivisor = Long.MIN_VALUE;
                long maxDivisorCount = 0;

                for (int num = begin; num <= end; num++) {
                    counter.clear();
                    for (int j = 1; j <= num; j++) {
                        if (num % j == 0) {
                            counter.increment();
                        }
                    }

                    if (maxDivisorCount < counter.getCount()) {
                        maxDivisorCount = counter.getCount();
                        maxDivisor = num;
                    }
                }

                System.out.print("MaxDivisor: " + maxDivisor + ", MaxDivisorCount: " + maxDivisorCount + ", ");
                time.start();
            });
            blockingQueue.add(new Thread(threadPool.poll()));
        }

        blockingQueue.iterator().forEachRemaining(Thread::start);
    }
}
