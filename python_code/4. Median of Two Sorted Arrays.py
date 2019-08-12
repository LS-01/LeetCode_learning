from typing import List

class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        list_len1 = len(nums1)
        list_len2 = len(nums2)
        sum_len = list_len1 + list_len2
        mid_len = sum_len // 2
        count = 0
        i = 0
        j = 0
        result = 0
        now_val = 0
        last_val = 0
        while i < list_len1 or j < list_len2:
            last_val = now_val
            if i < list_len1 and ((j >= list_len2) or (j < list_len2 and nums1[i] <= nums2[j])):
                now_val = nums1[i]
                i = i + 1
            else:
                now_val = nums2[j]
                j = j + 1
            if count == mid_len:
                if sum_len % 2 == 0:
                    return (last_val + now_val) / 2
                else:
                    return now_val
            count = count + 1
        return result

#test
s = Solution()
print(s.findMedianSortedArrays([1, 3], [2]))
print(s.findMedianSortedArrays([1, 2], [3, 4]))
print(s.findMedianSortedArrays([], [3, 4, 5, 6]))
print(s.findMedianSortedArrays([], [2, 3, 4]))
print(s.findMedianSortedArrays([3, 4, 5, 6], []))
print(s.findMedianSortedArrays([2, 3, 4], []))