import java.util.*;

class BufferTask implements Runnable {
	private final StringBuffer buffer;
	private final String threadName;

	public BufferTask(StringBuffer buffer, String threadName) {
		this.buffer = buffer;
		this.threadName = threadName;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
            buffer.append(threadName + " - " + i + "\n");
            
            /*synchronized (buffer) { // actually don't need because buffer is synchronised itself
                threads will append one after another
            }*/
            try {
                Thread.sleep(1000); // Simulate some work with sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}

class StringBufferExample {
    public static void main(String[] args) {
        // Shared StringBuffer instance
        StringBuffer sharedBuffer = new StringBuffer();

        // Create and start multiple threads
        Thread thread1 = new Thread(new BufferTask(sharedBuffer, "Thread1"));
        Thread thread2 = new Thread(new BufferTask(sharedBuffer, "Thread2"));
        Thread thread3 = new Thread(new BufferTask(sharedBuffer, "Thread3"));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Output the final contents of the StringBuffer
        System.out.println("Final buffer content: " + sharedBuffer.toString());
    }
}