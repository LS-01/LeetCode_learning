class Solution:
    def isPalindrome(self, x: int) -> bool:
        isPail = True
        str_x = str(x)
        mi = len(str_x) // 2
        i = 0
        while i <= mi:
            if str_x[i] != str_x[len(str_x) - 1 - i]:
                return False
            i = i + 1
        return isPail
    
    def isPalindromeFollowUp(self, x: int) -> bool:
        if x < 0 or (x % 10 == 0 and x != 0):
            return False
        new_x = 0
        old_x = x
        while x > 0:
            y = x // 10
            new_x = (new_x - y) * 10 + x
            x = y
        if new_x == old_x:
            return True
        else:
            return False

#test
s = Solution()
print(s.isPalindrome(121))
print(s.isPalindrome(1221))
print(s.isPalindrome(-121))
print(s.isPalindrome(10))
print(s.isPalindromeFollowUp(121))
print(s.isPalindromeFollowUp(1221))
print(s.isPalindromeFollowUp(-121))
print(s.isPalindromeFollowUp(10))