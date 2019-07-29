/**
 * @author heshan
 **/
public class Rider extends Thread {

    private final SharedData sharedData;

    public Rider(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        try {
            this.runRider();
        } catch (InterruptedException e) {
            System.out.format("Error in runRider method %s\n", e);
        }
    }

    public void runRider() throws InterruptedException {
        sharedData.getMutex().acquire();    // mutex acquired before entering the bus halt
        System.out.format("The mutex acquired by rider %d\n", this.getId());

        sharedData.getWaiting().incrementAndGet();  // increment the waiting no of riders as the rider has entered the bus halt
        System.out.format("The mutex is released and entered to the bus halt by rider %d\n", this.getId());
        sharedData.getMutex().release();    // release the mutex by the rider

        sharedData.getBus().acquire();  // the bus semaphore acquired by the rider
        System.out.format("The rider has boarded the bus and bus semaphore acquired by rider %d\n", this.getId());

        System.out.format("The boarded semaphore released by rider %d\n", this.getId());
        sharedData.getBoarded().release();  // the boarded semaphore is released by the rider
    }
}
