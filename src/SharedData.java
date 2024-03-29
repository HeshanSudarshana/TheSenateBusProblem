import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedData {
    private final AtomicInteger waiting = new AtomicInteger(0);    // atomic integer to keep the waiting rider count
    private final Semaphore mutex = new Semaphore(1);  // mutex to protect the waiting count variable
    private final Semaphore bus = new Semaphore(0);    // signals when the bus has arrived
    private final Semaphore boarded = new Semaphore(0);    // signals when a rider has boarded


    public AtomicInteger getWaiting() {
        return waiting;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public Semaphore getBus() {
        return bus;
    }

    public Semaphore getBoarded() {
        return boarded;
    }
}
