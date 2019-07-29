import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heshan
 **/
public class Main extends Thread{
    private  static final SharedData sharedData = new SharedData();

    public static void main(String[] args) {
        new RiderGeneration(sharedData).start();
        new BusGeneration(sharedData).start();
    }

}
