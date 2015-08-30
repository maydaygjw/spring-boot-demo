package hello.zk;

import org.apache.zookeeper.KeeperException;

/**
 * Created by gejunwen on 15/8/30.
 */
public class BarrierClient {

    public static void main(String[] args) throws KeeperException, InterruptedException {
        Barrier barrier = new Barrier("localhost", "/barrier_test", 2);
        barrier.enter();

        System.out.println("Barrier Passed");
    }
}
