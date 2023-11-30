import java.util.concurrent.Semaphore;

class Buffer{
    private int item;
    private Semaphore mutex = new Semaphore(1);
    public void produce(int cuIntem) throws InterruptedException{
        mutex.acquire();
        item = cuIntem;
        System.out.println("produce: "+item);
        mutex.release();
    }
    public void consume() throws InterruptedException{
        mutex.acquire();
        System.out.println("consume: "+item);
        mutex.release();
    }
}
class Producer implements Runnable{
    private Buffer buf;
    public Producer(Buffer buf){
        this.buf = buf;
    }
	@Override
	public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buf.produce(i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
	}
}
class Consumer implements Runnable{
    private Buffer buf;
    public Consumer(Buffer buf){
        this.buf=buf;
    }
	@Override
	public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buf.consume();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
	}

}
public class PC {
    public static void main(String[] args) {
        Buffer bf = new Buffer();
        Thread producerThread = new Thread(new Producer(bf));
        Thread consumerThread = new Thread(new Consumer(bf));
        producerThread.start();
        consumerThread.start();
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
