package com.gemserk.resources.util.progress.task;

import com.gemserk.resources.util.progress.Progress;
import com.gemserk.resources.util.progress.ProgressTask;

public class SimulateLoadingTimeRunnable implements Runnable, ProgressTask {

	int time;

	private Progress progress;

	public SimulateLoadingTimeRunnable(int time) {
		this.time = time;
	}

	@Override
	public void run() {
		try {
			progress.setMessage("Waiting for some time...");
			Thread.sleep(time);
			progress.finish();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setProgress(Progress progress) {
		this.progress = progress;
	}
}