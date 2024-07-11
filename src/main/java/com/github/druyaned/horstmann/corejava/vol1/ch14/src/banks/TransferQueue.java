package com.github.druyaned.horstmann.corejava.vol1.ch14.src.banks;

import java.util.LinkedList;

import static com.github.druyaned.ConsoleColors.bold;

/**
 * A wrapper for the {@link java.util.LinkedList LinkedList}.
 * The class provides a synchronized queue
 * for the {@link BankQueued} to make transactions.
 */
public class TransferQueue {

    public static int MAX_AMOUNT = 10000;

    /**
     * Provides basic transfer info:
     * index of an account {@code from} which the transfer is sent,
     * index of an account {@code to} which the transfer is sent
     * and {@code value} of the transfer.
     */
    public static class Transfer {
        private final int from;
        private final int to;
        private final int value;

        public Transfer(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        public int getFrom() { return from; }
        public int getTo() { return to; }
        public int getValue() { return value; }
    }

    private final LinkedList<Transfer> queue;
    private final Bank bank;
    
    public TransferQueue(Bank bank) {
        queue = new LinkedList<>();
        this.bank = bank;
    }

    /**
     * Adds the transfer to the queue.
     * The method is {@code synchronized}.
     * @param from index of an account {@code from} which the transfer is sent.
     * @param to index of an account {@code to} which the transfer is sent.
     * @param value {@code value} of the transfer.
     * @return {@code true} if the addition was successful, else {@code false}.
     */
    public synchronized boolean addLast(int from, int to, int value) {
        if (queue.size() >= MAX_AMOUNT) { return false; }
        queue.addLast(new Transfer(from, to, value));
        return true;
    }

    public synchronized boolean addLast(Transfer transfer) {
        if (queue.size() >= MAX_AMOUNT) { return false; }
        queue.addLast(transfer);
        return true;
    }

    /**
     * Returns the first element of the queue.
     * If the queue is empty returns {@code null}.
     * The method is {@code synchronized}.
     * @return the first element of the queue or {@code null} if the queue is empty.
     */
    public synchronized Transfer getFirst() {
        if (queue.isEmpty()) { return null; }
        return queue.getFirst();
    }

    /**
     * Returns and removes the first element of the queue.
     * If the queue is empty returns {@code null}.
     * The method is {@code synchronized}.
     * @return the first element of the queue and removes it
     * or {@code null} if the queue is empty.
     */
    public synchronized Transfer pollFirst() { return queue.pollFirst(); }

    /**
     * Removes the first element of the queue and in a synchronized way
     * executes the method {@link Bank#transfer} of given in the constructor bank.
     * @return {@code false} if the queue is empty, else {@code true}.
     */
    public synchronized boolean toTransfer() throws InterruptedException {
        Transfer t = pollFirst();
        if (t == null) {
            System.out.println(
                    bold("Queue is empty") + ". " + Thread.currentThread().toString()
            );
            return false;
        }
        System.out.printf(
                "%s=%d %s\n",
                bold("QueueSize"), queue.size(), Thread.currentThread().toString()
        );
        bank.transfer(t.from, t.to, t.value);
        return true;
    }
    
}
