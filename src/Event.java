import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * Author: Filip Piskor[12331436] on 20/12/15.
 */
public class Event {

    private Object obj;
    private Field field;
    private double value;
    private double change;
    private double result;


    public Event(Object obj, String fieldName, double time, double result) throws NoSuchFieldException,
            IllegalAccessException {

        this.obj = obj;
        this.result = result;
        this.field = obj.getClass().getField(fieldName);
        this.value = field.getDouble(obj);
        double diff = result - value;
        this.change = diff / time;
    }

    public synchronized void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(change * 100.0f);
                while (value - result > change * 100.0f) {
                    try {
                        value += change * 100.0f;
                        field.setDouble(obj, value);
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
