package com.ls.leetcode.done;

import java.util.*;

/**
 * 212
 *
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 *
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 *
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？
 * 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）208。
 *
 * 前缀树
 *
 * 执行用时 : 222 ms, 在所有 Java 提交中击败了27.94%的用户
 * 内存消耗 :45.4 MB , 在所有 Java 提交中击败了77.55%的用户
 */
public class Q212_FindWords {

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();
        Q208_Trie trie = new Q208_Trie();
        for (String s : words) {
            trie.insert(s);
        }
        int[][] piced = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                lookInto(board, i, j, "", trie, set, piced);
            }
        }
        return new ArrayList<>(set);
    }

    private boolean lookInto(char[][] board, int i, int j, String s, Q208_Trie trie, Set<String> res, int[][] piced){
        if(piced[i][j] == 1){
            return false;
        }
        piced[i][j] = 1;
        boolean b = lookAround(board, i, j, s + board[i][j], trie, res, piced);
        piced[i][j] = 0;
        return b;
    }

    private boolean lookAround(char[][] board, int ik, int jk, String s, Q208_Trie trie, Set<String> res, int[][] piced){
        if(trie.startsWith(s)){
            if(trie.search(s)){
                res.add(s);
            }

            for (int i = ik + 1; i < board.length; i++) {
                if(!lookInto(board, i, jk, s, trie, res, piced)){
                    break;
                }
            }

            for (int i = ik - 1; i >= 0; i--) {
                if(!lookInto(board, i, jk, s, trie, res, piced)){
                    break;
                }
            }

            for (int j = jk + 1; j < board[ik].length; j++) {
                if(!lookInto(board, ik, j, s, trie, res, piced)){
                    break;
                }
            }

            for (int j = jk - 1; j >= 0; j--) {
                if(!lookInto(board, ik, j, s, trie, res, piced)){
                    break;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        char[][] board = new char[][]{
//                {'o','a','a','n'},
//                {'e','t','a','e'},
//                {'i','h','k','r'},
//                {'i','f','l','v'}
//        };
//        String[] words = new String[]{"oath","pea","eat","rain"};

//        char[][] board = new char[][]{
//                {'b'},
//                {'a'},
//                {'b'},
//                {'b'},
//                {'a'}
//        };
//        String[] words = new String[]{"baa","abba","baab","aba"};

//        char[][] board = new char[][]{
//                {'a'}
//        };
//        String[] words = new String[]{"a"};

//        char[][] board = new char[][]{
//                {'a', 'a'},
//                {'a', 'a'}
//        };
//        String[] words = new String[]{"aaaaa"};

//        char[][] board = new char[][]{
//                {'a', 'b'},
//                {'c', 'd'}
//        };
//        String[] words = new String[]{"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};

        char[][] board = new char[][]{
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'}
        };
        String[] words = new String[]{"aaaaaaaaaaaa","aaaaaaaaaaaaa","aaaaaaaaaaab"};

        System.out.println(new Q212_FindWords().findWords(board, words));
    }

}
