package com.gemserk.resources.util.progress;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {

	public static class ProgressProvider {

		/**
		 * Returns a new Progress with specified quantity of internal tasks.
		 */
		public Progress get(int quantity) {
			return new Progress(quantity);
		}

	}

	public static class ProgressTaskImpl implements Runnable, ProgressTask {

		protected Progress progress;

		@Override
		public void run() {

		}

		@Override
		public void setProgress(Progress progress) {
			this.progress = progress;
		}
	}

	private final Queue<Runnable> tasks;

	private Progress progress;

	private ProgressProvider progressProvider;

	public void setProgressProvider(ProgressProvider progressProvider) {
		this.progressProvider = progressProvider;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public TaskQueue() {
		this(new LinkedList<Runnable>());
	}

	public TaskQueue(Queue<Runnable> tasks) {
		this(tasks, new Progress(tasks.size()));
	}

	public TaskQueue(Queue<Runnable> tasks, Progress progress) {
		this.tasks = tasks;
		this.progress = progress;
		this.progressProvider = new ProgressProvider();
	}

	public void add(Runnable task) {
		tasks.add(task);
		progress.setTotal(tasks.size());
	}

	public void processNext() {
		Runnable task = tasks.poll();
		if (task == null)
			return;
		if (task instanceof ProgressTask) {
			ProgressTask progressTask = (ProgressTask) task;
			Progress internalProgress = progressProvider.get(100);
			progressTask.setProgress(internalProgress);
		}
		task.run();
		progress.increment();
	}

	public boolean isDone() {
		return tasks.isEmpty();
	}

	public Progress getProgress() {
		// if subprogress... current task.getProgress?
		return progress;
	}

	public Runnable getCurrentTask() {
		return tasks.peek();
	}

}