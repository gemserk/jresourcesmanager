package com.gemserk.resources.slick.gamestates;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {

	private final Queue<Runnable> tasks;

	private Progress progress;

	public TaskQueue() {
		this(new LinkedList<Runnable>());
	}

	public TaskQueue(Queue<Runnable> tasks) {
		this.tasks = tasks;
		progress = new Progress(tasks.size());
	}

	public void add(Runnable task) {
		tasks.add(task);
		progress.setTotal(tasks.size());
	}

	public void processNext() {
		Runnable task = tasks.poll();
		if (task == null)
			return;
		task.run();
		progress.increment();
	}

	public boolean isDone() {
		return tasks.isEmpty();
	}

	public Progress getProgress() {
		return progress;
	}

}