package hello.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by gejunwen on 15/8/30.
 */
public class SyncPrimitive implements Watcher {

    static ZooKeeper zk = null;
    static Integer mutex;

    protected String root;

    SyncPrimitive(String address) {
        if(null == zk) {
            try {
                System.out.println("Starting ZK:");
                zk = new ZooKeeper(address, 3000, this);
                mutex = new Integer(-1);
                System.out.println("Finished Starting ZK: " + zk);
            } catch (IOException e) {
                System.out.println(e.toString());
                zk = null;
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Event propagated");
        synchronized (mutex) {
            mutex.notify();
        }
    }
}
