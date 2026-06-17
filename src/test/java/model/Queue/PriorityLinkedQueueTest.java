package model.Queue;

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Queue.PriorityLinkedQueue;
import pg07algoritmos.model.Queue.QueueException;

import java.util.Random;

class PriorityLinkedQueueTest {

    @Test
    void prioritylinkedQueuetest() {
        PriorityLinkedQueue queue = new PriorityLinkedQueue(); try { for (int i = 0; i < 10; i++) { int value = new Random().nextInt(50); int priority = new Random().nextInt(1,3); System.out.println("enQueue( " + value + ", " + priority + ")"); queue.enQueue(value, priority); }

        System.out.println("-------------------------");
        System.out.println("Queue size: " + queue.size());
        System.out.println("-------------------------");
        System.out.println("Peek / Top: " + queue.peek());
        System.out.println(queue);
        System.out.println("-------------------------");

        for (int i = 0; i < 5; i++) {
            System.out.println("deQueue: " + queue.deQueue());
            System.out.println(queue);
        }

    } catch (QueueException e) {
        throw new RuntimeException(e);
    }
    }

}