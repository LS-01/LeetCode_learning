package com.ls.leetcode.done;

import java.util.HashMap;
import java.util.Map;

/**
 * 242
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 */
public class Q242_IsAnagram {

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if(map.containsKey(c) && map.get(c) > 0){
                int r = map.get(c) - 1;
                map.put(c, r);
                if(r == 0){
                    map.remove(c);
                }
            } else {
                return false;
            }
        }
        return map.size() <= 0;
    }

    public static void main(String[] args) {
        System.out.println(new Q242_IsAnagram().isAnagram("anagram", "nagaram"));
        System.out.println(new Q242_IsAnagram().isAnagram("rat", "car"));
    }

}
