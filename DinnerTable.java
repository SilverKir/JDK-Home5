import java.util.ArrayList;
import java.util.List;

public class DinnerTable {
    List<Philosopher> philosophers;
    List<Spoon> spoons;
    int guestNumber;

    public DinnerTable(int numberOfGuests) {
        this.guestNumber = numberOfGuests;
        this.philosophers = new ArrayList<>(guestNumber);
        this.spoons = new ArrayList<>(guestNumber);
        for (int i = 0; i < guestNumber; i++) {
            philosophers.add(new Philosopher("Philosopher " + (i + 1), 3, this));
            spoons.add(new Spoon());
        }
    }

    public synchronized boolean spareSpoon(Philosopher philosopher) {
        int i = philosophers.indexOf(philosopher);
        int nextI = (i - 1) < 0 ? guestNumber - 1 : i - 1;
                if (!spoons.get(i).isBusy() && !spoons.get(nextI).isBusy()) {
                    spoons.get(i).setBusy(true);
                    spoons.get(nextI).setBusy(true);
                    System.out.println(philosopher.getPhilName() + " взял ложки");
                    return true;
                }
                return false;
    }


    public void returnSpoon(Philosopher philosopher) {
        int i = philosophers.indexOf(philosopher);
        int nextI = i - 1 < 0 ? guestNumber - 1 : i - 1;
        System.out.println(philosopher.getPhilName() + " вернул ложки");
        spoons.get(i).setBusy(false);
        spoons.get(nextI).setBusy(false);
    }

    public void start() {
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }
}
