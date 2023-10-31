package exercise.unit_10;

import java.util.TreeMap;

public class Exercise1 {
    public static void main(String[] args) {
        try {
            PhoneDirectory directory = new PhoneDirectory();
            directory.putValue("A", "010-0000-0000");
            directory.putValue("B", "010-0000-0001");
            directory.putValue("C", "010-0000-0002");

            System.out.println(directory.getName("010-0000-0000"));
            System.out.println(directory.getNumber("010-0000-0000"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

class PhoneDirectory {

    private static class PhoneEntry {
        private String name;
        private String number;

        public PhoneEntry(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }
    }

    private TreeMap<String, PhoneEntry> data;

    public PhoneDirectory() {
        data = new TreeMap<>();
    }

    private PhoneEntry find(String number) {
        PhoneEntry founded = data.get(number);
        if (founded != null) {
            return founded;
        }
        return null;
    }

    public String getName(String number) {
        PhoneEntry founded = find(number);
        if (founded != null) {
            return founded.getName();
        }
        return null;
    }

    public String getNumber(String number) {
        PhoneEntry founded = find(number);
        if (founded != null) {
            return founded.getNumber();
        }
        return null;
    }

    public void putValue(String name, String number) {
        if (name == null || number == null) {
            throw new IllegalArgumentException("name and number cannot be null");
        }
        PhoneEntry newEntry = new PhoneEntry(name, number);
        data.put(number, newEntry);
    }
}