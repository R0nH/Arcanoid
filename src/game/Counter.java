//216727644 Ron Hefetz
package game;
/**counting class. */
public class Counter {
    private int value;
    /**@param value the count value */
    public Counter(int value) {
        this.value = value;
    }
    /**creates new counter that starts at 0. */
    public Counter() {
        value = 0;
    }
    /**add number to current count.
     * @param number an integer */
    public void increase(int number) {
        value += number;
    }
    /**subtract number from current count.
     * @param number an integer */
    public void decrease(int number) {
        value -= number;
    }
    /**@return get current count. */
    public int getValue() {
        return value;
    }
}
