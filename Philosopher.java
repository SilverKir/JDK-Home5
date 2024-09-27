
public class Philosopher extends Thread {
    private String PhilName;
    private int full;
    private DinnerTable dinnerTable;


    public String getPhilName() {
        return PhilName;
    }

    public Philosopher(String name, int full, DinnerTable dinnerTable) {
        this.PhilName = name;
        this.full = full;
        this.dinnerTable = dinnerTable;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < full; i++) {
                eat();
                if (i!=full-1){
                    think();
                }
            }
            System.out.println("Философ " + this.PhilName + " наелся");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() throws InterruptedException {
        if (dinnerTable.spareSpoon(this)) {
            sleep(200);
            System.out.println(this.PhilName + " поел");
            dinnerTable.returnSpoon(this);
        } else {
                think();
                eat();
            }
    }

    private void think() throws InterruptedException {
        sleep(200);
        System.out.println(this.PhilName + " размышляет");
    }

}
