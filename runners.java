// Java code for thread creation by extending
// the Thread class
import java.util.*;
import java.util.Scanner;
class Multithreading extends Thread {
	public void run()
	{
		try {
			// Displaying the thread that is running
			System.out.println(
				"Runner " + Thread.currentThread().getId()
				+ " is running");
		}
		catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}
}

// Main Class
public class runners {
	public static void main(String[] args)
	{
        
        System.out.println("Provide number of runners");
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
		//int n = 8;  Number of threads
		for (int i = 0; i < n; i++) {
			Multithreading object
				= new Multithreading();
			object.start();
		}
	}
}
