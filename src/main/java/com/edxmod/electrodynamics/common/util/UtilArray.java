package com.edxmod.electrodynamics.common.util;

/**
 * @author dmillerw
 */
public class UtilArray {

    /**
     * Takes an array size and returns an int array containing all the indexes contained in that array
     */
    public static int[] getArrayIndexes(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }

}
