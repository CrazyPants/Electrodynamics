package com.edxmod.electrodynamics.common.util;

/**
 * @author dmillerw
 */
public class ArrayHelper {

    /**
     * Takes an array and returns an int array containing all the indexes contained in that array
     */
    public static int[] getArrayIndexes(Object[] array) {
        int[] returnArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
			returnArray[i] = i;
        }
        return returnArray;
    }

}
