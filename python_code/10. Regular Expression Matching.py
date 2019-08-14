import copy

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        len_s = len(s)
        len_p = len(p)
        i = 0
        j = 0
        
        k = -1
        p_list = []
        p_list_cou = {}
        p_list_star = {}
        while j < len_p:
            if k < 0 or p[j] != p_list[k] or p[j] == '.':
                p_list.append(p[j])
                k = k + 1
            added = 1
            if j + 1 < len_p and p[j + 1] == '*':
                added = 0
                j = j + 1
                p_list_star[k] = 1
            
            val = p_list_cou.get(k)
            if val == None:
                p_list_cou[k] = added
            else:
                p_list_cou[k] = val + added
            j = j + 1
        
        k = 0
        end_s = ''
        passing = None
        while i < len_s:
            if k >= len(p_list):
                if passing == None:
                    return False
                else:
                    k = passing['k']
                    i = passing['i']
                    k = k + 1
                    p_list_cou = passing['p']
                    while k < len(p_list):
                        if p_list_star.get(k) != 1:
                            break
                        k = k + 1
                    if k >= len(p_list):
                        return True
                    passing = {}
                    passing['k'] = k
                    passing['i'] = i
                    passing['p'] = copy.deepcopy(p_list_cou)
                    while i < len_s:
                        if s[i] == p_list[k] or p_list[k] == '.':
                            break
                        i = i + 1
                    if i >= len_s:
                        return False
            if s[i] == p_list[k]:
                val = p_list_cou.get(k)
                if val > 0:
                    p_list_cou[k] = val - 1
                    end_s = ''
                    if val == 1 and p_list_star.get(k) == None:
                        k = k + 1
                else:
                    if p_list_star.get(k) == 1:
                        end_s = s[i]
                    else:
                        if passing == None:
                            return False
                        else:
                            k = passing['k']
                            i = passing['i']
                            k = k + 1
                            p_list_cou = passing['p']
                            while k < len(p_list):
                                if p_list_star.get(k) != 1:
                                    break
                                k = k + 1
                            if k >= len(p_list):
                                return True
                            passing = {}
                            passing['k'] = k
                            passing['i'] = i
                            passing['p'] = copy.deepcopy(p_list_cou)
                            while i < len_s:
                                if s[i] == p_list[k] or p_list[k] == '.':
                                    break
                                i = i + 1
                            if i >= len_s:
                                return False
                i = i + 1
            elif p_list[k] == '.':
                if p_list_star.get(k) == 1:
                    while k < len(p_list):
                        if p_list_star.get(k) != 1:
                            break
                        k = k + 1
                    if k >= len(p_list):
                        return True
                    passing = {}
                    passing['k'] = k
                    passing['i'] = i
                    passing['p'] = copy.deepcopy(p_list_cou)
                    while i < len_s:
                        if s[i] == p_list[k] or p_list[k] == '.':
                            break
                        i = i + 1
                    if i >= len_s:
                        return False
                else:
                    val = p_list_cou.get(k)
                    if val > 0:
                        i = i + 1
                        p_list_cou[k] = val - 1
                        end_s = ''
                        if val == 1:
                            k = k + 1
                
            elif p_list_cou.get(k) == 0:
                k = k + 1
            else:
                if passing == None:
                    return False
                else:
                    k = passing['k']
                    i = passing['i']
                    k = k + 1
                    p_list_cou = passing['p']
                    while k < len(p_list):
                        if p_list_star.get(k) != 1:
                            break
                        k = k + 1
                    if k >= len(p_list):
                        return True
                    passing = {}
                    passing['k'] = k
                    passing['i'] = i
                    passing['p'] = copy.deepcopy(p_list_cou)
                    while i < len_s:
                        if s[i] == p_list[k] or p_list[k] == '.':
                            break
                        i = i + 1
                    if i >= len_s:
                        return False
        
        while k < len(p_list):
            if p_list_cou.get(k) == 0 or end_s == p_list[k]:
                k = k + 1
            else:
                return False
        
        return True
    
#test
s = Solution()
print(s.isMatch('aa', 'a') == False)
print(s.isMatch('aa', 'a*') == True)
print(s.isMatch('ab', '.*') == True)
print(s.isMatch('aab', 'c*a*b') == True)
print(s.isMatch('mississippi', 'mis*is*p*.') == False)
print(s.isMatch('ab', '.*c') == False)
print(s.isMatch('ab', '.*c*') == True)
print(s.isMatch('ab', '.*c*a*') == True)
print(s.isMatch('aaa', 'aaaa') == False)
print(s.isMatch('aaa', 'a*a') == True)
print(s.isMatch('aaa', 'a*b*a*') == True)
print(s.isMatch('a', 'ab*') == True)
print(s.isMatch('', '.*') == True)
print(s.isMatch('aba', 'ab*bb*a') == True)
print(s.isMatch('aba', 'ab*ba*a') == True)
print(s.isMatch('aaa', 'ab*a*c*a') == True)
print(s.isMatch('aa', 'a*.*a') == True)
print(s.isMatch('bbbba', '.*a*a') == True)
print(s.isMatch('aasdfasdfasdfasdfas', 'aasdf.*asdf.*asdf.*asdf.*s') == True)
print(s.isMatch('aasdfaeasdfasdfasdfas', 'aasdf.*asdf.*asdf.*asdf.*s') == True)
print(s.isMatch('mississippi', 'mis*is*ip*.') == True)
print(s.isMatch('a', '.*..a*') == False)
print(s.isMatch('abcdede', 'ab.*de') == True)
print(s.isMatch('baba', '.*b') == False)