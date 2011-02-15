package com.gemserk.resources.util.tasks;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gemserk.resources.util.progress.TaskQueue;

@RunWith(JMock.class)
public class TaskQueueTest {

	class RunnableNullImpl implements Runnable {
		@Override
		public void run() {

		}
	}

	Mockery mockery = new Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	@Test
	public void shouldReturnCurrentTask() {

		TaskQueue taskQueue = new TaskQueue();

		Runnable task1 = new RunnableNullImpl();
		Runnable task2 = new RunnableNullImpl();

		taskQueue.add(task1);
		taskQueue.add(task2);

		assertThat(taskQueue.getCurrentTask(), IsNull.notNullValue());
		assertThat(taskQueue.getCurrentTask(), IsSame.sameInstance(task1));

		taskQueue.processNext();

		assertThat(taskQueue.getCurrentTask(), IsNull.notNullValue());
		assertThat(taskQueue.getCurrentTask(), IsSame.sameInstance(task2));

		taskQueue.processNext();

		assertThat(taskQueue.getCurrentTask(), IsNull.nullValue());

	}

}
