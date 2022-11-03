package com.dschepkin.javaCore.Multithreading;

/**
 * если поток начал работу, то остановить его нельзя пока он не завершит работу
 * если передать объекту interrupt(), то это будет сигналом - заверши работу сразу как только сможешь
 * join() - говорит, о том, чтобы программа ждала пока метод завершит свою работу, и только после этого шла далее
 *      - можно регулировать очередность выполнение поток
 *      - в нашем случае поток main вызывает join() на других потоках,
 *              соответственно main будет ждать пока они все выполняться
 *              поток в этом случае будет находится в состоянии WAITING
 * join(100)  - main будет ждать 100 мс, и будет в состоянии TIME_WAITING, и дальше начнет свое выполнение
 *                  не дожидаясь завершения потока, на котором был вызван метод join()
 */

public class CreateThread {
    public static void main(String[] args) {
        SimpleThreadExtendsThread simpleThread = new SimpleThreadExtendsThread(); //создали объекта
        /**
         * Если мы вызовем run(), будет последовательное выполнение
         * Т.е метод main пойдет по ссылке simpleThread и вызовет метод run(),
         * а затем пойдем дальше по стеку программы
         *
         * Чтобы создать отдельный потом, надо вызывать метод start()
         */
//        simpleThread.run();

        Thread threadRunnable = new Thread(new ThreadImplRunnable());
        Thread thread_from_lambda = new Thread(() -> System.out.println("Thread from lambda"), "Thread - LAMBDA");
        simpleThread.start();
        threadRunnable.start();
        thread_from_lambda.start();

        /**
         * Теперь метод main будет ждать пока поток не завершит свою работу
         */
        try {
            simpleThread.join();
            threadRunnable.join();
            thread_from_lambda.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName);
    }

    private static class SimpleThreadExtendsThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread - SimpleThreadExtendsThread. Name is " + getName());
        }
    }

    private static class ThreadImplRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread - ThreadImplRunnable " + Thread.currentThread().getName());
        }
    }
}
