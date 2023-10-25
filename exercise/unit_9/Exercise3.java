package exercise.unit_9;

public class Exercise3 {
    public static void main(String[] args) {
        ListNode init = new ListNode();
        init.item = 1;
        ListNode node2 = new ListNode();
        node2.item = 2;
        ListNode node3 = new ListNode();
        node3.item = 3;
        ListNode node4 = new ListNode();
        node4.item = 4;

        init.next = node2;
        node2.next = node3;
        node3.next = node4;

        printListNode(init);
        printListNode(reverse(init));
    }

    private static void printListNode(ListNode init) {
        ListNode explorer = init;
        while (explorer != null) {
            System.out.println(explorer.item);
            explorer = explorer.next;
        }
    }

    private static ListNode reverse(ListNode init) {
        ListNode reversed = null;
        ListNode explorer = init;
        while (explorer != null) {
            ListNode newNode = new ListNode();
            newNode.item = explorer.item;
            newNode.next = reversed;
            reversed = newNode;
            explorer = explorer.next;
        }

        return reversed;
    }
}

class ListNode {
    int item;
    ListNode next;
}