/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author win
 */
public class Array {

    private int[] array;

    public Array(int number) throws Exception {
        if (number <= 0) {
            throw new Exception("Number of array must be >0");
        }
        array = new int[number];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(number);
        }
    }

    public Array(int[] array) throws Exception {
        if (array == null) {
            throw new Exception("Array can not null!");
        }
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    //Cô yêu cầu thêm hàm tìm tất cả index chứa key
    public  ArrayList<Integer> searchAllIndex(int key) {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                indexList.add(i);
            }
        }
        return indexList;
    }
}
