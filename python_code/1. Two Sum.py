from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        possible_result = {}
        for i in range(len(nums)):
            if possible_result.get(target - nums[i]) is not None:
                return [possible_result.get(target - nums[i]), i]
            else:
                possible_result[nums[i]] = i
        return None

#test
s = Solution()
print(s.twoSum([2, 7, 11, 15], 9))
print(s.twoSum([2, 3, 11, 15], 18))
print(s.twoSum([2, 4, 11, 15], 26))
print(s.twoSum([2, 6, 11, 15], 9))
print(s.twoSum([-3, 4, 3, 90], 0))