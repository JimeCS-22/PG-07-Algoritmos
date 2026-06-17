package model.stack;

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.stack.ArrayStack;
import pg07algoritmos.model.stack.StackException;

import java.util.Random;

class ArrayStackTest {

    @Test
    void push(){

        ArrayStack<Integer> stack = new ArrayStack<>(20);

        try{
        for (int i = 0; i < 5; i++) {
            int value = new Random().nextInt(50);
            System.out.println("push( " + value + ")");
            stack.push(value);
        }

            System.out.println("-------------------------");
            System.out.println("Stack size: " + stack.size());
            System.out.println("-------------------------");
            System.out.println("Peek / Top: " + stack.peek());
            System.out.println(stack);
            System.out.println("-------------------------");

            for (int i = 0; i < 5; i++) {

                System.out.println("pop(): " + stack.pop());
                System.out.println(stack);

            }
        }catch (StackException e){
            throw  new RuntimeException(e);
        }

    }

}