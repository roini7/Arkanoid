package ListenerNotifier;

/**
 * Counter class defines a counter attributes and applicable methods.
 */
public class Counter {
    private int counter = 0;

    /**
     * Constructor for Counter class.
     *
     * @param number is the number that counter holds reference to
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * add number to current count.
     *
     * @param number is the value to increment
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number is the value to decrement
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     * @return value of counter
     */
    public int getValue() {
        return counter;
    }
}
