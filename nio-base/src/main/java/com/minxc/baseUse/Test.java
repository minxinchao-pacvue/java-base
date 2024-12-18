package com.minxc.baseUse;

import static java.util.Arrays.binarySearch;

public class Test {
    @org.junit.Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = binarySearch(arr, 10);
        System.out.println(index);
    }
}
