package ThreadPool.SimpleThreadPool;

import java.util.concurrent.Future;

/**
 * @description:
 * @author:maidang
 * @date:2022/08/01
 **/
public interface ExecutorService {

	void shutdown();

	boolean isShutDown();

	<T>Future<T> submit(Runnable task,T result);


}
