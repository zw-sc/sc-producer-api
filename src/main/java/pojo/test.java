package pojo;

import java.util.*;

public class test {

    public int threeSumClosest(int[] nums, int larget) {
        int res = 100000;
        int sDiff = 10000;
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int now = nums[left] + nums[right] + nums[i];
                if (now == larget) {
                    return larget;
                }
                int diff = larget - now;
                diff = diff > 0 ? diff : -diff;
                if (diff < sDiff) {
                    sDiff = diff;
                    res = now;
                }
                if (nums[left] + nums[right] >= larget - nums[i]) {
                    right--;
                } else if (nums[left] + nums[right] < larget - nums[i]) {
                    left++;
                }
            }
        }
        return res;
    }

    public static Map<Character, List<String>> map = new HashMap<>();

    {
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
    }

    public List<String> letterCombinations(String digits) {
        if ("".equals(digits) || digits == null) {
            return new ArrayList<>();
        }
        int index = digits.length() - 1;
        List<String> res = map.get(digits.charAt(digits.length() - 1));
        return getChildren(digits, index - 1, res);
    }

    public List<String> getChildren(String digits, int index, List<String> all) {
        if (index < 0) {
            return all;
        }
        List<String> list = new ArrayList<>();
        for (String temp2 : map.get(digits.charAt(index))) {
            for (String temp : all) {
                list.add(temp2 + temp);
            }
        }
        return getChildren(digits, index - 1, list);
    }


    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                Integer pop = stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode res = new ListNode(-1);
        ListNode tail = null;
        ListNode temp = null;
        int index = 0;
        while (head != null) {
            if (index < k) {
                if (temp == null) {
                    temp = new ListNode(head.val);
                } else {
                    ListNode t = new ListNode(head.val);
                    t.next = temp;
                    temp = t;
                }
            }
            head = head.next;
            index++;
            if (index == k) {
                if (res.val == -1) {
                    res.val = (temp.val);
                    temp = temp.next;
                    tail = res;
                }
                while (temp != null) {
                    tail.next = (new ListNode(temp.val));
                    tail = tail.next;
                    temp = temp.next;
                }
                index = 0;
            }
        }
        if (temp != null) {
            ListNode newTemp = new ListNode(temp.val);
            temp = temp.next;
            while (temp != null) {
                ListNode node = new ListNode(temp.val);
                node.next = newTemp;
                newTemp = node;
                temp = temp.next;
            }
            if (res.val == -1) {
                res = newTemp;
            } else {
                tail.next = newTemp;
            }
        }
        printNode(res);
        return res;
    }


    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }


    ListNode createNode(int length) {
        List<Integer> list = Arrays.asList(1, 9, 7, 0, 1, 8, 2, 0, 8);
        ListNode head = new ListNode(list.get(0));
        ListNode tail = head;
        int index = 1;
        while (index < length) {
            tail.next = new ListNode(list.get(index));
            tail = tail.next;
            index++;
        }
        return head;
    }

    void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
    }

    public boolean backspaceCompare(String s, String t) {
        StringBuilder sb = new StringBuilder();
        int counts = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
                counts++;
            } else if (counts != 0) {
                sb.deleteCharAt(counts - 1);
                counts--;
            }
        }
        StringBuilder st = new StringBuilder();
        int countt = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) != '#') {
                st.append(s.charAt(i));
                countt++;
            } else if (countt != 0) {
                st.deleteCharAt(countt - 1);
                countt--;
            }
        }
        System.out.println(sb);
        System.out.println(st);
        return sb.toString().equals(st.toString());
    }

    public static void main(String[] args) {
        test test = new test();
//        test.printNode(test.createNode(9));
        System.out.println(test.findAnagrams2("baa","aa"));
    }
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length() < p.length()){
            return res;
        }
        int[] cs = new int[26];
        for(int i = 0; i < p.length(); i++){
            cs[p.charAt(i) - 'a'] -= 1;
            cs[s.charAt(i) - 'a'] += 1;
        }
        int diff = 0;
        for(int temp : cs){
            if(temp != 0){
                diff += 1;
            }
        }
        if(diff == 0){
            res.add(0);
        }

        int l = 0;
        int r = p.length() - 1;
        while(true){
            if(cs[s.charAt(l) - 'a'] == 0){
                diff++;
            }else if(cs[s.charAt(l) - 'a'] == 1){
                diff--;
            }
            cs[s.charAt(l) - 'a'] -= 1;
            r++;
            if(r == s.length()){
                break;
            }
            if(cs[s.charAt(r) - 'a'] == 0){
                diff++;
            }else if(cs[s.charAt(r) - 'a'] == -1){
                diff--;
            }
            cs[s.charAt(r) - 'a'] +=1;
            l++;
            if(diff == 0){
                res.add(l);
            }
        }
        return res;
    }
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length() < p.length()){
            return res;
        }
        int[] cs = new int[26];
        int[] ps = new int[26];
        for(int i = 0; i < p.length(); i++){
            ps[p.charAt(i) - 'a'] += 1;
            cs[s.charAt(i) - 'a'] += 1;
        }
        int l = 0;
        int r = p.length() - 1;
        while(true){
            if(Arrays.equals(cs, ps)){
                res.add(l);
            }
            r++;
            if(r == s.length()){
                break;
            }
            cs[s.charAt(r) - 'a'] +=  1;
            cs[s.charAt(l) - 'a'] -=  1;
            l++;
        }
        return res;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length() < p.length()){
            return res;
        }
        int l = 0;
        int r = p.length() - 1;
        while(r < s.length()){
            int diff = hasDiff(l, r, s, p);
            if(hasDiff(l, r, s, p) == 0){
                if(isSameChar(l, r, s, p)){
                    res.add(l);
                }
            }else{
                l = diff + 1;
                r = l + p.length() - 1;
            }
        }
        return res;
    }

    public int hasDiff(int l, int r, String s, String p){
        int res = 0;
        while(l <= r){
            if(!p.contains(s.charAt(l) + "")){
                res = Math.max(l, res);
            }
            l++;
        }
        return res;
    }
    public boolean isSameChar(int l, int r, String s, String p){
        char[] sc = s.substring(l, r + 1).toCharArray();
        char[] pc = p.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(pc);
        return Arrays.equals(sc, pc);
    }
}
