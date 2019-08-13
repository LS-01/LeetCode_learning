class Solution:
    def longestPalindrome(self, s: str) -> str:
        s_len = len(s)
        if s_len == 0:
            return ''
        longest_str = s[0]
        longest_len = 1
        for i in range(s_len):
            j = 1
            temp_len = 1
            while i - j >= 0 and i + j < s_len and s[i - j] == s[i + j]:
                temp_len = temp_len + 2
                j = j + 1
            if temp_len > longest_len:
                longest_len = temp_len
                longest_str = s[i - j + 1 : i + j]
            
            j = 0
            temp_len = 0
            while i - j >= 0 and i + j + 1 < s_len and s[i - j] == s[i + j + 1]:
                temp_len = temp_len + 2
                j = j + 1
            if temp_len > longest_len:
                longest_len = temp_len
                longest_str = s[i - j + 1 : i + j + 1]
        return longest_str

#test
s = Solution()
print(s.longestPalindrome('babad'))
print(s.longestPalindrome('cbbcd'))
print(s.longestPalindrome('c'))