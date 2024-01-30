package com.mc.innuce.domain.news.selenium.webdriver;

public class WebDriverTask implements Comparable<WebDriverTask>{
	private String taskName;
	private int priority;
	
	public WebDriverTask(String taskName, int priority) {
		this.taskName = taskName;
		this.priority = priority;
	}
	
	@Override
	public int compareTo(WebDriverTask other) {
		return Integer.compare(this.priority, other.priority);
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
