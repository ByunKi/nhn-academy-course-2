package exercise.unit_10;

import java.util.Arrays;

public class Exercise3 {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put("A", "a");
    }

}

class MyHashMap {
    private class HashNode {
        private String key;
        private String value;
        private HashNode next;

        public HashNode(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public HashNode getNext() {
            return next;
        }

        public void setNext(HashNode node) {
            this.next = node;
        }

    }

    private int count;
    private HashNode[] elements;

    public MyHashMap(int size) {
        classInvariant(size);
        this.count = 0;
        this.elements = new HashNode[size];
    }

    public MyHashMap() {
        this(10);
    }

    private void classInvariant(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("테이블의 초기값은 0보다 커야합니다.");
        }
    }

    private int getCount() {
        return count;
    }

    public HashNode[] getElements() {
        return elements;
    }

    public void setElements(HashNode[] newElements) {
        this.elements = newElements;
    }

    private int hashcode(String key) {
        // 10개의 크기를 맞추기 위해서 나머지 연산을 실시
        return Math.abs(key.hashCode()) % elements.length;
    }

    public void put(String key, String value) {
        int index = hashcode(key);
        HashNode node = elements[index];
        while (node != null) {
            if (node.getKey().equals(key)) {
                break;
            }
            node.setNext(node.next);
        }

        if (node != null) {
            node.value = value;
        } else {
            // 해쉬 테이블의 최적의 효율은 70% 채워져있을 때라고 어디서 들었음.
            if (getCount() >= 0.7 * elements.length) {
                HashNode[] newElements = Arrays.copyOf(elements, 2 * elements.length);
                setElements(newElements);
            }

            HashNode newNode = new HashNode(key, value);
            newNode.setNext(node);
        }
        count++;
    }

    public HashNode get(String key) {
        int index = hashcode(key);
        HashNode explorer = getElements()[index];
        while (explorer != null) {
            if (explorer.key.equals(key)) {
                return explorer;
            }
            explorer = explorer.next;
        }

        return null;
    }

    public HashNode remove(String key) {
        int index = hashcode(key);
        HashNode explorer = getElements()[index];

        if (explorer == null) {
            return explorer;
        }

        if (explorer.getKey().equals(key)) {
            HashNode removed = explorer;
            explorer = explorer.next;
            count--;

            return removed;
        }

        HashNode prev = explorer;
        HashNode current = explorer.getNext();

        while (current != null && current.key.equals(key)) {
            current = current.next;
            prev = current;
        }

        if (current != null) {
            HashNode removed = current;
            prev.next = current.next;
            count--;

            return current;
        }

        return null;
    }

    public boolean containsKey(String key) {
        int index = hashcode(key);
        HashNode explorer = getElements()[index];
        while (explorer != null) {
            if (explorer.key.equals(key)) {
                return true;
            }
            explorer = explorer.next;
        }

        return false;
    }

    public int size() {
        return getCount();
    }
}
