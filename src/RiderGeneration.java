/**
 * @author heshan
 **/
public class RiderGeneration extends Thread {

    private final SharedData sharedData;
    private final int INTERVAL_MEAN = 300; // mean value for rider generation
    private long interval;

    @Override
    public void run() {
        try {
            this.generateRiders();
        } catch (InterruptedException e) {
            System.out.format("Error in generateBus method\n", e);
        }
    }

    public RiderGeneration(SharedData sharedData) {
        this.sharedData = sharedData;
        this.interval = 0;
    }

    public void generateRiders() throws InterruptedException {
        while(true) {
            this.interval = (long)(-1*(Math.log(Math.random()))*INTERVAL_MEAN); // calculate the interval exponentially
            System.out.format("Next rider will come in %d ms\n", this.interval);
            Rider.sleep(interval); // make the rider thread sleep for the amount interval

            System.out.println("A new rider is generated");
            new Rider(sharedData).start();    // new rider generated
        }
    }
}
