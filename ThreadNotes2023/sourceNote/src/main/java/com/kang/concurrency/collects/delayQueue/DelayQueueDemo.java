package com.kang.concurrency.collects.delayQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Order> delayQueue = new DelayQueue<>();
        // 添加三个订单，分别延迟 5 秒、2 秒和 3 秒
        delayQueue.put(new Order("order5000", System.currentTimeMillis(), 5000));
        delayQueue.put(new Order("order3000", System.currentTimeMillis(), 3000));
        delayQueue.put(new Order("order1000", System.currentTimeMillis(), 1000));

        // 循环取出订单，直到所有订单都被处理完毕
        while (!delayQueue.isEmpty()) {
            Order order = delayQueue.take();
            System.out.println("处理订单：" + order.getOrderId());
        }
    }

    static class Order implements Delayed {
        private String orderId;
        private long createTime;
        private long delayTime;

        public Order(String orderId, long createTime, long delayTime) {
            this.orderId = orderId;
            this.createTime = createTime;
            this.delayTime = delayTime;
        }

        public String getOrderId() {
            return orderId;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = createTime + delayTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayed) {
            long diff = this.getDelay(TimeUnit.MILLISECONDS)
                    - delayed.getDelay(TimeUnit.MILLISECONDS);
            return Long.compare(diff, 0);
        }
    }
}
