package com.ls.leetcode.done;

import java.util.ArrayList;
import java.util.List;

/**
 * 208
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 *
 * 执行用时 : 57 ms, 在所有 Java 提交中击败了46.38%的用户
 * 内存消耗 : 53.3 MB, 在所有 Java 提交中击败了40.29%的用户
 */

public class Q208_Trie {

    private char data;
    private boolean isEnd;
    private List<Q208_Trie> children;

    /** Initialize your data structure here. */
    public Q208_Trie() {
        data = '/';
        isEnd = false;
        children = new ArrayList<>();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Q208_Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = trie.whereInChildren(c);
            if(index < 0){
                Q208_Trie t = new Q208_Trie();
                t.setData(c);
                trie.getChildren().add(t);
                trie = t;
            } else {
                trie = trie.getChildren().get(index);
            }
        }
        trie.setEnd(true);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Q208_Trie trie = whereInTrie(word);
        if(trie == null){
            return false;
        }
        return trie.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return whereInTrie(prefix) != null;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public List<Q208_Trie> getChildren() {
        return children;
    }

    public void setChildren(List<Q208_Trie> children) {
        this.children = children;
    }

    private int whereInChildren(char c){
        for (int i = 0; i < getChildren().size(); i++) {
            if(c == getChildren().get(i).getData()){
                return i;
            }
        }
        return -1;
    }

    private Q208_Trie whereInTrie(String word){
        Q208_Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = trie.whereInChildren(c);
            if(index < 0){
                return null;
            } else {
                trie = trie.getChildren().get(index);
            }
        }
        return trie;
    }

    public static void main(String[] args) {
        Q208_Trie trie = new Q208_Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app"));       // 返回 true
        System.out.println(trie.startsWith("apple"));     // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }

}
