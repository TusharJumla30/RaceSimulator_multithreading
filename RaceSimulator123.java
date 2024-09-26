
import java.util.Random;
import java.util.Scanner;
public class RaceSimulator123 implements Runnable {

    private static final int TARGET_DISTANCE = 1000;
    private static final int MIN_DISTANCE = 5;
    private static final int MAX_DISTANCE = 10;

    private final String name;
    private int distanceCovered;

    public RaceSimulator123(String name) {
        this.name = name;
        this.distanceCovered = 0;
    }

    public void run() {
        Random random = new Random();

        while (distanceCovered < TARGET_DISTANCE) {
            int distanceThisSecond = MIN_DISTANCE + random.nextInt(MAX_DISTANCE - MIN_DISTANCE + 1);
            distanceCovered += distanceThisSecond;
            System.out.println(name + " has covered " + distanceCovered + " meters.");

            try {
                Thread.sleep(1000); // pause for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " has finished the race!");
    }

    public int getDistanceCovered() {
        return distanceCovered;
    }

    public static void main(String[] args) throws InterruptedException {
        //int numRunners = Integer.parseInt(args[0]);
        System.out.println("Enter number of runners");
        Scanner sc =new Scanner(System.in);
        int numRunners = sc.nextInt();

        RaceSimulator123[] runners = new RaceSimulator123[numRunners];
        Thread[] threads = new Thread[numRunners];

        for (int i = 0; i < numRunners; i++) {
           // System.out.println("Enter Name of "+i+" runner: ");
           // String name  = sc.nextLine();
           // runners[i] = new RaceSimulator(name);
            runners[i] = new RaceSimulator123("Runner " + (i + 1));
            threads[i] = new Thread(runners[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join(); // wait for all threads to finish
        }

        // sort runners by distance covered
        for (int i = 0; i < numRunners - 1; i++) {
            for (int j = i + 1; j < numRunners; j++) {
                if (runners[i].getDistanceCovered() < runners[j].getDistanceCovered()) {
                    RaceSimulator123 temp = runners[i];
                    runners[i] = runners[j];
                    runners[j] = temp;
                }
            }
        }

        System.out.println("Top 3 runners:");
        for (int i = 0; i < 3 && i < numRunners; i++) {
            System.out.println((i + 1) + ". " + runners[i].name + " - " + runners[i].distanceCovered + " meters");
        }
    }
}
