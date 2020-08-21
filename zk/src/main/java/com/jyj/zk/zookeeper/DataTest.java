package com.jyj.zk.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DataTest {

    private ZooKeeper zk;

    @Before
    public void init() throws IOException {
        String conn = "124.70.220.2";
        int sessionTimeOut = 2000 ;

        if (zk == null) {
            zk = new ZooKeeper(conn, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getPath());
                    System.out.println(event);
                }
            });
        }

    }

    @Test
    public void getData01() throws KeeperException, InterruptedException {
        byte[] data = zk.getData("/JYJ", false, null);
        System.out.println(new String(data));
    }

    @Test
    public void getData02() throws KeeperException, InterruptedException {
        byte[] data = zk.getData("/JYJ", true, null);
        System.out.println(new String(data));
        Thread.sleep(Integer.MAX_VALUE);
    }


    /**
     * 自定义监听
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getData03() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        byte[] data = zk.getData("/JYJ", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getPath());
                try {
                    zk.getData(event.getPath(),this,null);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },  stat);
        System.out.println(stat.toString());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void getData04() throws KeeperException, InterruptedException {
        zk.getData("/JYJ", false, new AsyncCallback.DataCallback(){

            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("path:" + path + " ctx:" + ctx + " stat : " + stat.toString());
            }
        },"11");

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void getChild01() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/JYJ", false, null);
       children.forEach(System.out::println);
    }

    @Test
    public void getChildWatcher() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/JYJ", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getPath());
                try {
                    List<String> children1 = zk.getChildren("/JYJ",this ,null);
                    children1.forEach(System.out::println);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, null);
        children.forEach(System.out::println);
        Thread.sleep(Integer.MAX_VALUE);
    }


}

