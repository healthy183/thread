package org.example;

import org.junit.Test;

import java.util.Random;

public class RandomTest {

    @Test
    public void run(){
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            long lval = random.nextInt(3000);
            System.out.println(lval);
        }
    }
}
