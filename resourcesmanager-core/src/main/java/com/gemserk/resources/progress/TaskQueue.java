package com.gemserk.resources.progress;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author acoppes
 * Provides a way to process tasks with a progress associated.
 */
public class TaskQueue {

	private final Queue<Runnable> tasks;
	private final Map<Runnable, String> taskNames;

	private Progress progress;

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
		this.taskNames = new HashMap<Runnable, String>();
	}

	public void add(Runnable task) {
		tasks.add(task);
		progress.setTotal(tasks.size());
	}
	
	public void add(Runnable task, String name) {
		add(task);
		taskNames.put(task, name);
	}

	public void processNext() {
		Runnable task = tasks.poll();
		if (task == null)
			return;
		task.run();
		progress.increment();
		taskNames.remove(task);
	}

	public boolean isDone() {
		return tasks.isEmpty();
	}

	public Progress getProgress() {
		return progress;
	}

	public Runnable getCurrentTask() {
		return tasks.peek();
	}
	
	public String getCurrentTaskName() {
		Runnable task = getCurrentTask();
		if (!taskNames.containsKey(task))
			return "";
		return taskNames.get(task);
	}

}