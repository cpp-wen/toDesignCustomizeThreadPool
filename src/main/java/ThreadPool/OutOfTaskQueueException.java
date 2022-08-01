package ThreadPool;

/**
 * @description:
 * @author:maidang
 * @date:2022/08/01
 **/
public class OutOfTaskQueueException extends Exception {
	private final String exceptionMessage = "拒绝该任务，队列已经满了 或者 线程已经达到线程池的最大核心数量";

	@Override
	public String toString() {
		return exceptionMessage;
	}
}
