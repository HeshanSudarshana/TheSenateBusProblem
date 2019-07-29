import java.util.concurrent.Semaphore;

/**
 * @author heshan
 **/
public class Bus extends Thread {

    private final int MAX_BOARDERS = 50;
    private final SharedData sharedData;
    private int noOfBoarders;
    private int noOfWaiting;

    @Override
    public void run() {
        try {
            this.runBus();
        } catch (InterruptedException e) {
            System.out.format("Error in runBus method %s\n", e);
        }
    }

    public Bus(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void runBus() throws InterruptedException {
        sharedData.getMutex().acquire();    // bus acquires the mutex so the riders cannot enter the bus stop
        System.out.format("The mutex acquired by bus %d\n", this.getId());

        noOfBoarders = Math.min(sharedData.getWaiting().get(), MAX_BOARDERS);   // selecting the number of boarders
        System.out.format("No. of waiting: %d, No. of boarding: %d\n", sharedData.getWaiting().get(), noOfBoarders);

        for(int i=0; i<noOfBoarders; i++) {
            System.out.format("The bus semaphore released by bus %d\n", this.getId());
            sharedData.getBus().release();  // the bus lock is released in order to rider to board

            sharedData.getBoarded().acquire();  // boarded semaphore acquired by bus
            System.out.format("The boarded semaphore acquired by bus %d\n", this.getId());
        }

        noOfWaiting = sharedData.getWaiting().get();    // get the waiting riders count
        sharedData.getWaiting().set(Math.max(noOfWaiting-50, 0));   // set the waiting count after all the riders have boarded
        System.out.format("No of riders left in the halt: %d\n", sharedData.getWaiting().get());

        System.out.format("The mutex released by bus %d and departed\n\n", this.getId());
        sharedData.getMutex().release();    // release the mutex so that the riders can enter the bus halt
    }
}
