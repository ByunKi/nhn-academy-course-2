package exercise.unit_12;

public class Exercise1 {

    private static Counter counter = new Counter();

    public static void main(String[] args) {
        while (true) {
            try {
                Thread countingThread = new Thread(() -> counter.increment());
                Thread countingThread2 = new Thread(() -> counter.increment());

                System.out.println(counter.getCount());
                if (counter.getCount() == 10) {
                    break;
                }

                countingThread.join();
                countingThread2.join();

                countingThread.start();
                countingThread2.start();
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

class Counter {
    private volatile long count = 0;

    synchronized void increment() {
        this.count++;
    }

    synchronized long getCount() {
        return count;
    }

    synchronized void clear() {
        this.count = 0;
    }
}