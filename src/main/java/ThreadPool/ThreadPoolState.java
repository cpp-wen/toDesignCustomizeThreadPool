package ThreadPool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: use bit operation symbol
 * @author:maidang
 * @date:2022/08/01
 **/
public class ThreadPoolState {

	private final int RUN = 0;
	private final int STOP = 1;
	private final int DESTROY = 2;
	private final int poolState = (1 << 2) - 1;
	private final int poolThreadSize = Integer.MAX_VALUE - 3;
	private final AtomicInteger
		poolStateAndWorkThreadSize =
		new AtomicInteger(poolStateMergeSize(RUN, 0));


	private static ThreadPoolState threadPoolState;

	private int poolStateMergeSize(int poolState, int poolThreadSize) {
		return poolState | (poolThreadSize << 2);
	}

	public int getWorkThreadSize() {
		return (getPoolSizeAndState() & poolThreadSize) >> 2;
	}

	public int getPoolState() {
		return getPoolSizeAndState() & poolState;
	}

	public boolean setPoolStateToStop(){
		return poolStateAndWorkThreadSize.compareAndSet(getPoolState(),poolStateMergeSize(STOP,getWorkThreadSize()));
	}

	public boolean setPoolStateToDestroy(){
		return poolStateAndWorkThreadSize.compareAndSet(getPoolState(),poolStateMergeSize(DESTROY,getWorkThreadSize()));
	}

	public int increasePoolThreadSize(){
		int poolThreadSiz= getWorkThreadSize();
		int newPoolThreadSize =poolThreadSiz+1;
		poolStateAndWorkThreadSize.compareAndSet(getPoolState(), poolStateMergeSize(getPoolState(),newPoolThreadSize));
		return newPoolThreadSize;
	}

	public int getPoolSizeAndState() {
		return poolStateAndWorkThreadSize.get();
	}


	public int RunState() {
		return RUN;
	}

	public int StopState() {
		return STOP;
	}

	public int DestroyState() {
		return DESTROY;
	}

	/**
	 * this method if too many thread into ,they will block util the  prev thread is finish
	 * @return
	 */
	public static ThreadPoolState getInstance(){
		synchronized (ThreadPoolState.class){
			if(threadPoolState==null){
				threadPoolState = new ThreadPoolState();
			}
			return threadPoolState;
		}
	}
}
