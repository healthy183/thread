package com.kang.thread.interrput;

import lombok.extern.slf4j.Slf4j;

/**
 *类说明：阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次
 */
@Slf4j
public class HasInterrputException {
	
	private static class UseThread extends Thread{
		
		public UseThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			while(!isInterrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					log.info(Thread.currentThread().getName()
							+" in InterruptedException interrupt flag is " +isInterrupted());
					//interrupt(); // 第一次只是打断睡眠，如果需要继续中断，需要手动再中断一次
					e.printStackTrace();
				}
				log.info(Thread.currentThread().getName() + " I am extends Thread.");
			}
			//while 循环结束后才会打印本句
			log.info(Thread.currentThread().getName() +" interrupt flag is "+isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread endThread = new UseThread("HasInterrputEx");
		endThread.start();
		Thread.sleep(500);
		endThread.interrupt();
	}

}
