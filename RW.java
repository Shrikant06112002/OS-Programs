import java.util.concurrent.locks.ReentrantReadWriteLock;

class Buffer{
    private int item;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read(String name){
        lock.readLock().lock();
        System.out.println(name+" is reading "+item);
        lock.readLock().unlock();
    }
    public void write(String name,int data){
        lock.writeLock().lock();
        item = data;
        System.out.println(name+" is writing "+item);
        lock.writeLock().unlock();
    }
}
class Reader implements Runnable{
    private Buffer buf;
    private String name;
    public Reader(Buffer buf,String name){
        this.buf=buf;
        this.name = name;
    }
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            buf.read(name);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
class Writer implements Runnable{
    private Buffer buf;
    private String name;
    public Writer(Buffer buf,String name){
        this.buf = buf;
        this.name = name;
    }
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            int n = (int)(Math.random()*100);
            buf.write(name, n);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
public class RW {
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        Thread r1 = new Thread(new Reader(buf, "r1"));
        Thread r2 = new Thread(new Reader(buf, "r2"));
        Thread w1 = new Thread(new Writer(buf, "w1"));
        Thread w2 = new Thread(new Writer(buf, "w2"));
        r1.start();
        r2.start();
        w1.start();
        w2.start();
        try {
            r1.join();
            r2.join();
            w1.join();
            w2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
