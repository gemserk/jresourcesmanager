package com.gemserk.resources.slick.gamestates;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class TaskQueueTest {

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

	@Test
	public void shouldSetProgressForAProgressTask() {

		Progress testProgress = new Progress(100);

		TaskQueue taskQueue = new TaskQueue();
		taskQueue.setProgress(testProgress);

		taskQueue.add(new ProgressTaskImpl() {
			@Override
			public void run() {
				progress.setMessage("A");
			}
		});
		
		while(!taskQueue.isDone())
			taskQueue.processNext();

		assertThat(testProgress.getMessage(), IsEqual.equalTo("A"));

	}

}
