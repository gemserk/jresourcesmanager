/**
 * 
 */
package com.gemserk.resources.slick.runnables;

public class SimulateLoadingTimeRunnable implements Runnable {

	int time;

	public SimulateLoadingTimeRunnable(int time) {
		this.time = time;
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}