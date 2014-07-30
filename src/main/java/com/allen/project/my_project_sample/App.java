package com.allen.project.my_project_sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
	public static Set<String> set = new HashSet<String>();
	
	public void runThread(){
		List s = new ArrayList();
        for(int i=0;i<1000;i++){
        	s.add(i+"");
        }
        List a = new ArrayList();
        List b = new ArrayList();
        List c = new ArrayList();
        a = s.subList(0, 100);
        b = s.subList(100, 500);
        c = s.subList(500, s.size());
		  ThreadList1 thread1 = new ThreadList1(a,"thread1");
	        ThreadList1 thread2 = new ThreadList1(b,"thread2");
	        ThreadList1 thread3 = new ThreadList1(c,"thread3");
	        Thread t1 = new Thread(thread1);
	        t1.start();
	        Thread t2 = new Thread(thread2);
	        t2.start();
	        Thread t3 = new Thread(thread3);
	        t3.start();
	        
	}
	public void print(){
		System.out.println(App.set.size());
	}
	
    public static void main( String[] args ) throws InterruptedException
    {
        
//        for(Object i:a){
//        	System.out.println("a"+i);
//        }
//        for(Object i:b){
//        	System.out.println("b"+i);
//        }
//        for(Object i:c){
//        	System.out.println("c"+i);
//        }
        App app = new App();
        System.out.println("123");
        app.runThread();
        app.print();
    }
}
