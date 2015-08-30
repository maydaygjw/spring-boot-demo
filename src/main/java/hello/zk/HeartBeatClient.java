package hello.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by gejunwen on 15/8/30.
 */
public class HeartBeatClient implements Watcher {

    public static void main(String[] args) throws IOException, KeeperException {
        new HeartBeatClient().run();
    }

    public void run() throws IOException, KeeperException {
        ZooKeeper zk = new ZooKeeper("localhost", 3000, new HeartBeatClient());

        synchronized (this) {
            try {
                zk.exists("/zk_test", this);
                zk.getData("/zk_test", event -> {
                    System.out.println("getData triggered!");
                }, null);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Event Triggered !");
        System.out.println(event.getState());
    }
}
