package co.nilin.mvc.tst;

import static java.lang.Thread.sleep;

public class Thread1 extends Thread {

 int num=0;
        public void run() {
            num++;
            System.out.println(" [z" + num + "] Sent '" + num*num + "'");

        }
    }

