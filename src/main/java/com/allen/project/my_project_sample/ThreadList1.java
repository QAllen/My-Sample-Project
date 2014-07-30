package com.allen.project.my_project_sample;

import java.util.ArrayList;
import java.util.List;

public class ThreadList1 implements Runnable {
	private List<String> list = new ArrayList<String>();

	private String thread;
	public ThreadList1(List<String> list, String thread) {
		super();
		this.list = list;
		this.thread = thread;
	}


	@Override
	public void run() {
			for(String s:list){
				System.out.println(thread+":"+s);
				App.set.add(s);
			}
	}

}
