package ThreadPool;

import java.util.concurrent.ThreadFactory;

/**
 * @description:
 * @author:maidang
 * @date:2022/08/01
 **/
public class ThreadRepository implements ThreadFactory {

	private static ThreadRepository repository=null;
	@Override
	public Thread newThread(Runnable r) {
		return new Thread();
	}

	//singular
	public static  ThreadRepository newInstance(){
		if(repository==null){
			synchronized (ThreadRepository.class){
				if(repository==null){
					repository = new ThreadRepository();
					return repository;
				}
			}
		}
		return repository;
	}
	public Thread newThread(Runnable r, String name){
		return new Thread(r,name);
	}

}
