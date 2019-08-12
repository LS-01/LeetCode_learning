class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        longest = 0
        s_set = {}
        count = 0
        i = 0
        last_save = 0
        while i < len(s):
            st = s[i]
            j = s_set.get(st)
            if j is not None and j >= last_save:
                if count > longest:
                    longest = count
                count = i - j
                s_set[st] = i
                last_save = j + 1
            else:
                count = count + 1
                s_set[st] = i
            i = i + 1
        if count > longest:
            longest = count
        return longest

#test
s = Solution()
print(s.lengthOfLongestSubstring('abcabcbb'))
print(s.lengthOfLongestSubstring('bbbbb'))
print(s.lengthOfLongestSubstring('pwwkew'))
print(s.lengthOfLongestSubstring(' '))
print(s.lengthOfLongestSubstring('dvdf'))
print(s.lengthOfLongestSubstring('abba'))