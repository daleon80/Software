package Almundo.AlmundoCall;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * @author dleon	Daniel Leon
 *
 */
public class JUnitDispatcherTest {
	
	@Rule
    public Timeout globalTimeout = Timeout.seconds(1000);
	
	 @BeforeClass
	     public static void beforeClass() {
	 
	         System.out.println("Before Class");
	 
	     }
	 
	  
	 
	     @Before
	 
	     public void before() {
	 
	         System.out.println("Before Test Case");
	 
	     }
	 
	  
	 
	     @Test
	 
	     public void tenCallsTest() {
	 
	         System.out.println("Test");
	         Call llamada1= new Call("1");
	         Call llamada2= new Call("2");
	         Call llamada3= new Call("3");
	         Call llamada4= new Call("4");
	         Call llamada5= new Call("5");
	         Call llamada6= new Call("6");
	         Call llamada7= new Call("7");
	         Call llamada8= new Call("8");
	         Call llamada9= new Call("9");
	         Call llamada10= new Call("10");
	         
	         
	 
	         Dispatcher dispatcher = new Dispatcher(llamada1);
	         //dispatcher.setCalltoBeQueued(llamada1);
	         Thread dispatcherThread= new Thread(dispatcher) ;
         	dispatcherThread.start();
         	
            dispatcher.setCalltoBeQueued(llamada2);
	         Thread dispatcherThread2= new Thread(dispatcher) ;
	         dispatcherThread2.start();
	         
	         dispatcher.setCalltoBeQueued(llamada3);
	         Thread dispatcherThread3= new Thread(dispatcher) ;
	         dispatcherThread3.start();
	         
	         dispatcher.setCalltoBeQueued(llamada4);
	         Thread dispatcherThread4= new Thread(dispatcher) ;
	         dispatcherThread4.start();
	         
	         dispatcher.setCalltoBeQueued(llamada5);
	         Thread dispatcherThread5= new Thread(dispatcher) ;
	         dispatcherThread5.start();
	         
	         dispatcher.setCalltoBeQueued(llamada6);
	         Thread dispatcherThread6= new Thread(dispatcher) ;
	         dispatcherThread6.start();
	         
	         dispatcher.setCalltoBeQueued(llamada7);
	         Thread dispatcherThread7= new Thread(dispatcher) ;
	         dispatcherThread7.start();
	         
	         dispatcher.setCalltoBeQueued(llamada8);
	         Thread dispatcherThread8= new Thread(dispatcher) ;
	         dispatcherThread8.start();
	         
	         dispatcher.setCalltoBeQueued(llamada9);
	         Thread dispatcherThread9= new Thread(dispatcher) ;
	         dispatcherThread9.start();
	         
	         dispatcher.setCalltoBeQueued(llamada10);
	         Thread dispatcherThread10= new Thread(dispatcher) ;
	         dispatcherThread10.start();
	         
	                 //assertTrue("Num 1 is greater than Num 2", helloWorld.isGreater(4, 3));
	 	     }
	 
	  
	 
	     @After
	 
	     public void after() {
	 
	         System.out.println("After Test Case");
	 
	     }
	 
	  
	 
	     @AfterClass
	 
	     public static void afterClass() {
	 
	         System.out.println("After Class");
	 
	     }
	 
	 


}
