package com.qd.test.timer;

import java.util.Timer;

/**
 * 2.1、指定延迟时间执行定时任务
 * 
 * @author Administrator
 *
 */
public class TimerTest01 {
	Timer timer;

	public TimerTest01(int time) {
		timer = new Timer();
		//schedule(TimerTask task, Date time)：安排在指定的时间执行指定的任务。
		timer.schedule(new TimerTaskTest01(), time * 1000);
	}

	public static void main(String[] args) {
		System.out.println("timer begin....");
		new TimerTest01(3);
	}
}
