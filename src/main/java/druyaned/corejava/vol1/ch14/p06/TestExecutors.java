package druyaned.corejava.vol1.ch14.p06;

public class TestExecutors implements Runnable {
    
    @Override public void run() {
        System.out.print(
        """
        - ExecutorService lifecycle
            1. Executors.newCachedThreadPool()
                Executors.newFixedThreadPool()
                Executors.newScheduledThreadPool()
            2. ExecutorService.submit(task) // Runnable or Callable, returns Future
            3. ExecutorService.shutdown();
        - ExecutorCompletionService // efficient fetching of results
            ExecutorCompletionService service = new ExecutorCompletionService(executor);
            for (Callable<T> task : tasks)
                service.submit(task);
            for (int i = 0; i < tasks.size(); i++)
                processFurther(service.take().get());
        - ForkJoinPool
            class MyTask extends RecursiveTask<V> {
                @Override protected Object compute() {
                    if (condition) {
                        // solve the problem
                    } else {
                        // split the task and solve it recursively
                        MyTask t1 = new MyTask(...);
                        MyTask t2 = new MyTask(...);
                        MyTask tn = new MyTask(...);
                        invokeAll(t1, t2, tn);
                        // or forkService.submit(...); one by one
                    }
                }
            }
        - CompletableFuture<V>
            CompletableFuture<String> contents = readPage(url);
            CompletableFuture<List<URL>> links = contents.thenApply(Parser::getLinks);
            // thenApply is not blocked
        """);
    }
    
}
