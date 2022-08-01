package ThreadPool;

/**
 * @description:
 * @author:maidang
 * @date:2022/08/01
 **/
public class RejectStrategy {

	public final static int ABANDONED = 1;
	public final static int CALLER = 2;

	private static void abandoned() {
		try {
			throw new OutOfTaskQueueException();
		} catch (OutOfTaskQueueException e) {
			e.printStackTrace();
		}
	}

	private static void caller(Runnable task) {
		task.run();
	}

	public static void rejectStrategy(Runnable task, int rejectChoose) {
		if (rejectChoose == ABANDONED) {
			abandoned();
		} else if (rejectChoose == CALLER) {
			caller(task);
		}
	}

}
