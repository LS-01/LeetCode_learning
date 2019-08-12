# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        result = None
        temp_result = None
        added = 0
        while l1 is not None or l2 is not None:
            sum = added
            if l1 is not None:
                sum = sum + l1.val
                l1 = l1.next
            if l2 is not None:
                sum = sum + l2.val
                l2 = l2.next
            added = sum // 10
            sum = sum % 10
            if result is None:
                result = ListNode(sum)
                temp_result = result
            else:
                temp = ListNode(sum)
                temp_result.next = temp
                temp_result = temp_result.next
        if added > 0:
            temp = ListNode(added)
            temp_result.next = temp
        return result

#test
def list_to_listNode(list):
    result = None
    temp_result = None
    for i in range(len(list)):
        if result is None:
            result = ListNode(list[i])
            temp_result = result
        else:
            temp = ListNode(list[i])
            temp_result.next = temp
            temp_result = temp_result.next
    return result

def print_listNode(listNode):
    pr = ''
    while listNode is not None:
        pr = pr + '-' + str(listNode.val)
        listNode = listNode.next
    print(pr)

s = Solution()
print_listNode(s.addTwoNumbers(list_to_listNode([5]), list_to_listNode([5])))
print_listNode(s.addTwoNumbers(list_to_listNode([2, 4, 3]), list_to_listNode([5, 6, 4])))