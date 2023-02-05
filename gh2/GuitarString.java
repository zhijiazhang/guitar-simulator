package gh2;

import deque.Deque;
import deque.LinkedListDeque;

public class GuitarString {

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(GuitarString.SR / frequency);

        this.buffer = new LinkedListDeque<>();

        for (int i = 0; i < capacity; i++) {
            this.buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            double randomNum = Math.random() - 0.5;
            this.buffer.removeFirst();
            this.buffer.addLast(randomNum);

        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        //record the item behind the one getting removed
        double second = this.buffer.get(1);

        //remove the first
        double first = this.buffer.removeFirst();

        double newSample = ((first + second) / 2) * GuitarString.DECAY;
        this.buffer.addLast(newSample);
    }


    /* Return the double at the front of the buffer. */
    public double sample() {
        return this.buffer.get(0);
    }


}

