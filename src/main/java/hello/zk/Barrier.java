package hello.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by gejunwen on 15/8/30.
 */
public class Barrier extends SyncPrimitive {

    private int size;
    private String name;


    public Barrier(String address, String root, int size) {
        super(address);
        this.size = size;
        this.root = root;

        if(null != zk) {
            try {
                Stat s = zk.exists(root, false);
                if(s == null) {
                    zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }



            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }

            try {
                name = InetAddress.getLocalHost().getCanonicalHostName();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean enter() throws KeeperException, InterruptedException {
        zk.create(root + "/" + name, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        while(true) {
            synchronized (mutex) {
                List<String> list = zk.getChildren(root, true);
                if(list.size() < size) {
                    mutex.wait();
                } else {
                    return true;
                }
            }
        }
    }

//    public boolean leave() throws KeeperException, InterruptedException {
//        zk.delete(root + "/" + name, 0);
//        while(true) {
//            synchronized (mutex) {
//                zk.getChildren(root, true);
//            }
//        }
//    }
}
