/**
 * @author heshan
 **/
public class BusGeneration extends Thread {

    private final SharedData sharedData;
    private final int INTERVAL_MEAN = 1200; // mean value for bus generation
    private long interval;

    @Override
    public void run() {
        try {
            this.generateBuses();
        } catch (InterruptedException e) {
            System.out.format("Error in generateBus method\n", e);
        }
    }

    public BusGeneration(SharedData sharedData) {
        this.sharedData = sharedData;
        this.interval = 0;
    }

    public void generateBuses() throws InterruptedException {
        while(true) {
            this.interval = (long)(-1*(Math.log(Math.random()))*INTERVAL_MEAN); // calculate the interval exponentially
            System.out.format("Next bus will arrive in %d ms\n", this.interval);
            Bus.sleep(interval); // make the bus thread sleep for the amount interval

            System.out.println("A new bus is generated");
            new Bus(sharedData).start();    // new bus generated
        }
    }

}
