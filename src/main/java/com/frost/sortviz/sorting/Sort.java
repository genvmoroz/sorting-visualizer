package com.frost.sortviz.sorting;

import com.frost.sortviz.Bar;

import java.util.List;
import java.util.function.IntSupplier;

/**
 * Base class for every visualized sorting algorithm.
 *
 * <p>Subclasses implement {@link #sort()} to reorder the shared {@link #bars} list, recoloring
 * elements as they compare and swap them and calling {@link #sleep()} to pace the animation.
 * {@link #start()} drives one full run and then plays a final "sorted" sweep.
 */
public abstract class Sort {

    /** Pause, in milliseconds, while the final sorted-sweep color is shown. */
    private static final int COMPLETION_PAUSE_MILLIS = 250;

    private final String name;

    protected List<Bar> bars;

    /** Per-step delay in milliseconds; the view injects one tied to the window width. */
    private IntSupplier stepDelayMillis = () -> 0;

    protected Sort(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }

    public void setStepDelay(IntSupplier stepDelayMillis) {
        this.stepDelayMillis = stepDelayMillis;
    }

    /**
     * Runs the algorithm to completion and then plays the sorted sweep. Interruption is treated
     * as a request to stop, so the calling menu action returns cleanly.
     */
    public final void start() {
        try {
            sort();
            sweepSorted();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Reorders {@link #bars} into ascending order by {@link Bar#getHeight()}. */
    protected abstract void sort() throws InterruptedException;

    /** Swaps the bars at positions {@code i} and {@code j}. */
    protected void swap(int i, int j) {
        Bar temp = bars.get(i);
        bars.set(i, bars.get(j));
        bars.set(j, temp);
    }

    /** Paces the animation; called at every comparison and move. A non-positive delay is a no-op. */
    protected void sleep() throws InterruptedException {
        int delay = stepDelayMillis.getAsInt();
        if (delay > 0) {
            Thread.sleep(delay);
        }
    }

    /** Sweeps left to right coloring every bar, holds briefly, then restores the default color. */
    private void sweepSorted() throws InterruptedException {
        for (Bar bar : bars) {
            sleep();
            bar.setColor(Bar.SORTED_COLOR);
        }
        Thread.sleep(COMPLETION_PAUSE_MILLIS);
        for (Bar bar : bars) {
            sleep();
            bar.setColor(Bar.DEFAULT_COLOR);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
