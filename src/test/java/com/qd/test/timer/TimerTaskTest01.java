package com.qd.test.timer;

import java.util.TimerTask;

public class TimerTaskTest01 extends TimerTask {

	public void run() {
		System.out.println("3秒后执行的任务");
		System.out.println("Time's up!!!!");
	}
}