class Solution:
    def convert(self, s: str, numRows: int) -> str:
        result_list = [''] * numRows
        i = 0
        j = 0
        count = 0
        up_to_down = True
        str_len = len(s)
        while count < str_len:
            result_list[j] = result_list[j] + s[count]
            count = count + 1
            if up_to_down:
                j = j + 1
            else:
                j = j - 1
                i = i + 1
            
            if j >= numRows:
                i = i + 1
                j = j - 2
                up_to_down = False
            elif j < 0:
                j = j + 2
                up_to_down = True
        res_str = ''
        for res in result_list:
            res_str = res_str + res
        return res_str

#test
s = Solution()
print(s.convert('PAYPALISHIRING', 3))
print(s.convert('PAYPALISHIRING', 3) == 'PAHNAPLSIIGYIR')
print(s.convert('PAYPALISHIRING', 4))
print(s.convert('PAYPALISHIRING', 4) == 'PINALSIGYAHRPI')