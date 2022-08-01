package ThreadPool.SimpleThreadPool;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ThreadPool.ThreadPoolState;
import ThreadPool.ThreadRepository;
import ThreadPool.RejectStrategy;

/**
 * @description: simple ThreadPool
 * @author:maidang
 * @date:2022/08/01
 **/
public class SimpleThreadPool {

	protected int bestPoolThreadSize;
	protected int theMostPoolThreadSize;
	protected final BlockingQueue<Runnable> taskQueue;
	protected final HashSet<WorkThread> workThreadHashSet;
	protected volatile int workThreadSize;
	protected ThreadPoolState threadPoolState;
	private ThreadRepository threadRepository;
	private final Lock mainLock = new ReentrantLock();
	protected final Condition mainCondition = mainLock.newCondition();
	protected int rejectStrategy;


	public SimpleThreadPool() {
		this.bestPoolThreadSize = Runtime.getRuntime().availableProcessors();
		this.theMostPoolThreadSize = bestPoolThreadSize;
		rejectStrategy = RejectStrategy.ABANDONED;
		this.taskQueue = new LinkedBlockingDeque<Runnable>(theMostPoolThreadSize * 2);
		this.workThreadHashSet = new HashSet<>();
		this.threadPoolState = ThreadPoolState.getInstance();
		this.threadRepository = ThreadRepository.newInstance();
	}


	public SimpleThreadPool(int theMostPoolThreadSize){
		this.bestPoolThreadSize =Runtime.getRuntime().availableProcessors();
		this.theMostPoolThreadSize=theMostPoolThreadSize;
		rejectStrategy = RejectStrategy.ABANDONED;
		this.taskQueue = new LinkedBlockingDeque<Runnable>(theMostPoolThreadSize * 2);
		this.workThreadHashSet = new HashSet<>();
		this.threadPoolState = ThreadPoolState.getInstance();
		this.threadRepository = ThreadRepository.newInstance();
	}

	public SimpleThreadPool(int bestPoolThreadSize,BlockingQueue blockingQueue){
		this.bestPoolThreadSize=bestPoolThreadSize;
		this.theMostPoolThreadSize=Integer.MAX_VALUE;
		this.taskQueue=blockingQueue;
		this.workThreadHashSet=new HashSet<>();
		rejectStrategy=1;
		this.threadPoolState=ThreadPoolState.getInstance();
		this.threadRepository=ThreadRepository.newInstance();
	}



	public SimpleThreadPool(int bestPoolThreadSize, int theMostPoolThreadSize,
							BlockingQueue<Runnable> taskQueue,
							HashSet<WorkThread> workThreadHashSet, int workThreadSize,
							ThreadPoolState threadPoolState,
							ThreadRepository threadRepository) {
		this.bestPoolThreadSize = Runtime.getRuntime().availableProcessors();
		this.theMostPoolThreadSize = theMostPoolThreadSize;
		this.taskQueue = taskQueue;
		this.workThreadHashSet = workThreadHashSet;
		this.workThreadSize = workThreadSize;
		this.threadPoolState = ThreadPoolState.getInstance();
		this.threadRepository = threadRepository;
		this.rejectStrategy = RejectStrategy.ABANDONED;
	}


	class WorkThread implements Runnable {

		private Lock lock;
		private Runnable firstTask;
		private Thread thread;
		private volatile int completeTask = 0;

		@Override
		public void run() {

		}
	}
}
