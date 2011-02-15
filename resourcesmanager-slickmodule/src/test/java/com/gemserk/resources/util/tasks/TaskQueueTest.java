package com.gemserk.resources.util.tasks;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gemserk.resources.util.progress.Progress;
import com.gemserk.resources.util.progress.TaskQueue;
import com.gemserk.resources.util.progress.TaskQueue.ProgressProvider;
import com.gemserk.resources.util.progress.TaskQueue.ProgressTaskImpl;

@RunWith(JMock.class)
public class TaskQueueTest {
	
	Mockery mockery = new Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	@Test
	public void shouldSetProgressForAProgressTask() {

		Progress testProgress = new Progress(1);
		final Progress internalProgress1 = new Progress(100);
		
		final ProgressProvider progressProvider=  mockery.mock(ProgressProvider.class);
		
		mockery.checking(new Expectations() {
			{
				oneOf(progressProvider).get(100);
				will(returnValue(internalProgress1));
			}
		});

		TaskQueue taskQueue = new TaskQueue();
		taskQueue.setProgressProvider(progressProvider);
		taskQueue.setProgress(testProgress);

		taskQueue.add(new ProgressTaskImpl() {
			@Override
			public void run() {
				assertSame(internalProgress1, progress);
			}
		});

		while(!taskQueue.isDone()) 
			taskQueue.processNext();

	}
	
	@Test
	public void shouldReturnCurrentTask() {

		TaskQueue taskQueue = new TaskQueue();

		Runnable task1 = new ProgressTaskImpl();
		Runnable task2 = new ProgressTaskImpl();
		
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
