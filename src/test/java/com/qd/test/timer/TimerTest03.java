package com.qd.test.timer;

import java.util.Timer;

/**
 * 2.3、在延迟指定时间后以指定的间隔时间循环执行定时任务
 * 
 * @author Administrator
 *
 */
public class TimerTest03 {
	Timer timer;

	public TimerTest03() {
		timer = new Timer();
		//schedule(TimerTask task, Date firstTime, long period) ：
		//安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(new TimerTaskTest03(), 1000, 2000);
	}

	public static void main(String[] args) {
		new TimerTest03();
	}
}
