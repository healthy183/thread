package com.kang.thread.interrput;

import lombok.extern.slf4j.Slf4j;

/**
 *类说明：实现接口Runnable的线程如何中断
 */
@Slf4j
public class EndRunnable {
	
	private static class UseRunnable implements Runnable{
		
		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				log.info(Thread.currentThread().getName()
						+ " I am implements Runnable.");
			}
			log.info(Thread.currentThread().getName()
					+" interrupt flag is "+Thread.currentThread().isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		UseRunnable useRunnable = new UseRunnable();
		Thread endThread = new Thread(useRunnable,"endThread");
		endThread.start();
		Thread.sleep(20);
		endThread.interrupt();
	}

}
