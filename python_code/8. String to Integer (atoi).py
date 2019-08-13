class Solution:
    def myAtoi(self, str: str) -> int:
        res_int = 0
        i = 0
        len_s = len(str)
        negative = False
        while i < len_s:
            if str[i] != ' ':
                if str[i] == '-':
                    negative = True
                    i = i + 1
                elif str[i] == '+':
                    i = i + 1
                break
            i = i + 1
        
        while i < len_s:
            if str[i] == '0':
                res_int = res_int * 10 + 0
            elif str[i] == '1':
                res_int = res_int * 10 + 1
            elif str[i] == '2':
                res_int = res_int * 10 + 2
            elif str[i] == '3':
                res_int = res_int * 10 + 3
            elif str[i] == '4':
                res_int = res_int * 10 + 4
            elif str[i] == '5':
                res_int = res_int * 10 + 5
            elif str[i] == '6':
                res_int = res_int * 10 + 6
            elif str[i] == '7':
                res_int = res_int * 10 + 7
            elif str[i] == '8':
                res_int = res_int * 10 + 8
            elif str[i] == '9':
                res_int = res_int * 10 + 9
            else:
                break
            i = i + 1
        if negative:
            res_int = -res_int
        max_num = 2 ** 31
        if res_int > max_num - 1:
            return max_num - 1
        elif res_int < - max_num:
            return - max_num
        return res_int
    
#test
s = Solution()
print(s.myAtoi('42'))
print(s.myAtoi('   -42'))
print(s.myAtoi('4193 with words'))
print(s.myAtoi('words and 987'))
print(s.myAtoi('-91283472332'))
print(s.myAtoi('+1'))
print(s.myAtoi('2147483648'))