class Solution:
    def reverse(self, x: int) -> int:
        negative = False
        if x < 0:
            negative = True
            x = -x
        
        res_int = 0
        while x > 0:
            y = x // 10
            res_int = (res_int - y) * 10 + x
            x = y
        
        if negative:
            res_int = -res_int
        max_num = 2 ** 31
        if res_int > max_num - 1 or res_int < - max_num:
            return 0
        return res_int

#test
s = Solution()
print(s.reverse(123))
print(s.reverse(-123))
print(s.reverse(120))
print(s.reverse(12000))
print(s.reverse(1534236469))