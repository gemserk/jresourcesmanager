package com.gemserk.resources.util.progress;

public class Progress {

	private float total;

	private float current;

	public Progress(int total) {
		this.total = total;
		this.current = 0;
	}

	public void increment() {
		increment(1);
	}

	public void increment(float count) {
		current += count;
		if (current > total)
			current = total;
	}
	
	public void finish() {
		this.increment(total);
	}

	public float getPercentage() {
		return 100 * current / total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}