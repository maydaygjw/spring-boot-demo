package hello.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by gejunwen on 15/8/29.
 */
public class Executor implements Watcher, Runnable, DataMonitor.DataMonitorListener {

    String znode;
    DataMonitor dm;
    ZooKeeper zk;
    String filename;
    String exec[];
    Process child;

    public Executor(String hostPort, String znode, String filename, String exec[]) throws KeeperException, IOException {
        this.filename = filename;
        this.exec = exec;
        zk = new ZooKeeper(hostPort, 3000, this);
        //dm = new DataMonitor(zk, znode, null, this);
    }

    @Override
    public void exists(byte[] data) {

    }

    @Override
    public void closing(int rc) {

    }

    @Override
    public void run() {

        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("Event Triggered!");
    }

    public static void main(String[] args) {
//        if (args.length < 4) {
//            System.err
//                    .println("USAGE: Executor hostPort znode filename program [args ...]");
//            System.exit(2);
//        }
//        String hostPort = args[0];
//        String znode = args[1];
//        String filename = args[2];
//        String exec[] = new String[args.length - 3];
//        System.arraycopy(args, 3, exec, 0, exec.length);
        try {
            new Executor("localhost", "/zk_test", null, null).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
