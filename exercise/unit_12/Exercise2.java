package exercise.unit_12;

public class Exercise2 {

    public static void main(String[] args) {
        CalculateThread[] threads = new CalculateThread[10];
        for (int i = 0; i < threads.length; i++) {
            if (i > 0) {
                try {
                    threads[i - 1].join();
                } catch (InterruptedException e) {
                }
            }
            threads[i] = new CalculateThread(i * 10000 + 1, (i + 1) * 10000);
            threads[i].start();
        }

    }
}

class CalculateThread extends Thread {

    private long maxDivisorCount;
    private long maxDivisor;
    private Counter counter;
    private int start;
    private int end;

    public CalculateThread(int start, int end) {
        this.maxDivisor = Integer.MIN_VALUE;
        this.maxDivisorCount = 0;
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

    public void report() {
        System.out.print("MaxDivisor: " + getMaxDivisor() + ", MaxDivisorCount: " + getMaxDivisorCount() + ", ");
    }

    @Override
    public void run() {
        TimeThread time = new TimeThread();

        for (int i = start; i <= end; i++) {
            counter.clear();
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    counter.increment();
                }
            }

            if (maxDivisorCount < counter.getCount()) {
                maxDivisorCount = counter.getCount();
                maxDivisor = i;
            }
        }

        report();
        time.start();
    }

}

class TimeThread extends Thread {
    private long startTime;
    private long endTime;

    public TimeThread() {
        startTime = System.currentTimeMillis();
        endTime = -1;
    }

    @Override
    public void run() {
        this.endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000D + " Seconds");
    }

}