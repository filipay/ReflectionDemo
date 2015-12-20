import java.util.concurrent.TimeUnit;

/**
 * Author: Filip Piskor[12331436] on 20/12/15.
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Engine engine = new Engine();
        System.out.println(engine.currentHealth);
        int SECOND = 1000;
        Event event = new Event(engine, "currentHealth", 10 * SECOND, 80);
        event.execute();
        while(true) {
            System.out.println(engine.currentHealth);
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }
}
