package com.qd.test.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 2.2、在指定时间执行定时任务
 * 
 * @author Administrator
 *
 */
public class TimerTest02 {
	Timer timer;

	public TimerTest02() {
		Date time = getTime();
		System.out.println("指定时间time=" + time);
		timer = new Timer();
		timer.schedule(new TimerTaskTest02(), time);
	}

	/**
	 * 指定日期
	 * 
	 * @return
	 */
	public Date getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 01);
		calendar.set(Calendar.SECOND, 00);
		Date time = calendar.getTime();

		return time;
	}

	public static void main(String[] args) {
		new TimerTest02();
	}
}
