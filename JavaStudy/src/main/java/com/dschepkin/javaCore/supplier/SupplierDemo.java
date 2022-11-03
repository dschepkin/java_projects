package com.dschepkin.javaCore.supplier;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Вызов с использование Supplier
 */

public class SupplierDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};

        Optional<Integer> add = add(arr);
        Integer resultAdd = add.orElse(-1);
        System.out.println("add result = " + resultAdd);

        Optional<Integer> multi = multi(arr);
        Integer resultMulti = multi.orElse(-1);
        System.out.println("multi result = " + resultMulti);

        /**
         * А теперь тоже самое что и выше, но с использованием Supplier
         */
        System.out.println();
        int addRestult = execResult(() -> add(arr));
        int multiResult = execResult(() -> multi(arr));
        System.out.println(addRestult);
        System.out.println(multiResult);
    }

    /**
     * Принимает Supplier<Optional> потому что методы add & multi его возвращают
     */

    private static int execResult(Supplier<Optional> task) {
        Optional optional = task.get();
        return (int) optional.orElse(-1);
    }

    private static Optional<Integer> add(int[] values) {
        Optional<Integer> result = Optional.empty();
        Integer sum = null;

        for (int value : values) {
            sum = sum == null ? value : sum + value;
        }

        return result.of(sum);
    }

    private static Optional<Integer> multi(int[] values) {
        Optional<Integer> result = Optional.empty();
        Integer sum = null;

        for (int value : values) {
            sum = sum == null ? value : sum * value;
        }

        return result.of(sum);
    }

}
