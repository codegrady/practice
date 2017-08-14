package thread;

/**
 * ThreadLocal为解决多线程程序的并发问题提供了一种新的思路。使用这个工具类可以很简洁地编写出优美的多线程程序。
 　　当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 　　从线程的角度看，目标变量就象是线程的本地变量，这也是类名中“Local”所要表达的意思。
 　　所以，在Java中编写线程局部变量的代码相对来说要笨拙一些，因此造成线程局部变量没有在Java开发者中得到很好的普及。
 ThreadLocal的接口方法
 ThreadLocal类接口很简单，只有4个方法，我们先来了解一下：
 void set(Object value)设置当前线程的线程局部变量的值。
 public Object get()该方法返回当前线程所对应的线程局部变量。
 public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
 protected Object initialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。
 */
public class ThreadLocalPractice {
    public  static ThreadLocal<Integer> tl = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };
    public  int getNextValue(){
        tl.set(tl.get()+1);
        return tl.get();
    }

    public static void main(String[] args) {
        ThreadLocalPractice tlp = new ThreadLocalPractice();

        System.out.println("Main Thread Startting.....");

        ThreadClient t1 = new ThreadClient(tlp);
        ThreadClient t2 = new ThreadClient(tlp);
        ThreadClient t3 = new ThreadClient(tlp);
        ThreadClient t4 = new ThreadClient(tlp);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.println("Main Thread Ended.....");
    }

    private static class ThreadClient extends Thread{
        private ThreadLocalPractice tlp;
        public ThreadClient(ThreadLocalPractice tlp){
            this.tlp = tlp;
        }
        public void  run(){
            for (int i = 0; i < 3; i++) {
                // 每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> tlp[" + tlp.getNextValue() + "]");
            }
        }
    }
}
