class BuilderTask implements Runnable {
	private final StringBuilder builder;
	private final String threadName;

	public BuilderTask(StringBuilder builder, String threadName) {
		this.builder = builder;
		this.threadName = threadName;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
            builder.append(threadName + " - " + i + "\n"); // ==> gives wrong results as all threads will append at the same time
            
            
            /*synchronized (builder) {
                builder.append(threadName + " - " + i + "\n");
                
            }*/
            try {
                Thread.sleep(1000); // Simulate some work with sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}

class StringBuilderExample {
    public static void main(String[] args) {
        // Shared StringBuffer instance
        StringBuilder sharedBuilder = new StringBuilder();

        // Create and start multiple threads
        Thread thread1 = new Thread(new BuilderTask(sharedBuilder, "Thread1"));
        Thread thread2 = new Thread(new BuilderTask(sharedBuilder, "Thread2"));
        Thread thread3 = new Thread(new BuilderTask(sharedBuilder, "Thread3"));

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
        System.out.println("Final buffer content: " + sharedBuilder.toString());
    }
}