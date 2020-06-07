package com.ls.leetcode.general;

import java.util.List;

public class GeneralUtil {

    public static void printIntArray(int[] T){
        for (int i = 0; i < T.length; i++) {
            System.out.println(T[i]);
        }
    }

    public static void printDoubleIntArray(int[][] T){
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                System.out.print(T[i][j] + "    ");
            }
            System.out.println();
        }
    }

    public static void printDoubleIntegerList(List<List<Integer>> T){
        for (int i = 0; i < T.size(); i++) {
            for (int j = 0; j < T.get(i).size(); j++) {
                System.out.print(T.get(i).get(j) + "    ");
            }
            System.out.println();
        }
    }

    public static void printDoubleStringList(List<List<String>> T){
        for (int i = 0; i < T.size(); i++) {
            for (int j = 0; j < T.get(i).size(); j++) {
                System.out.println(T.get(i).get(j));
            }
            System.out.println();
        }
    }

}
