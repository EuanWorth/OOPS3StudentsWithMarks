import java.util.*;

public class TestResults {
    TreeMap<String, Integer> scoresMap;

    public TestResults() {
        scoresMap = new TreeMap<String, Integer>();
    }

    public boolean addStudent(String name, Integer score) {
        try {
            scoresMap.put(name, score);
            return true;
        } catch (ClassCastException | NullPointerException c) {
            return false;
        }
    }

    public List<String> getStudents() {
        return new ArrayList<String>(scoresMap.keySet());
    }

    public Integer getMedianScore() {
        class entry implements Comparable<entry>{
            private int s;
            private String n;

            private entry(int s, String n) {
                this.s = s;
                this.n = n;
            }

            @Override
            public int compareTo(entry o) {
                return s - o.s;
            }
        }
        Iterator<String> namesIt = scoresMap.keySet().iterator();
        Iterator<Integer> scoresIt = scoresMap.values().iterator();
        TreeSet<entry> sortedSet = new TreeSet<entry>();
        while(namesIt.hasNext() && scoresIt.hasNext()) {
            sortedSet.add(new entry(scoresIt.next(), namesIt.next()));
        }
        List<entry> SortedList = new ArrayList<>(sortedSet);
        if (sortedSet.size() % 2 != 0) {
            return (SortedList.get(SortedList.size() / 2).s + SortedList.get(SortedList.size() / 2 + 1).s) /2;
        } else {
            return SortedList.get(SortedList.size() / 2 + 1).s;
        }
    }

    public List<String> percentile(int P) {
        if (0 > P || P > 100) {
            throw new IllegalArgumentException();
        } else {
            class entry implements Comparable<entry>{
                private int s;
                private String n;

                private entry(int s, String n) {
                    this.s = s;
                    this.n = n;
                }

                @Override
                public int compareTo(entry o) {
                    return s - o.s;
                }
            }
            Iterator<String> namesIt = scoresMap.keySet().iterator();
            Iterator<Integer> scoresIt = scoresMap.values().iterator();
            TreeSet<entry> SortedEntrySet = new TreeSet<entry>();
            while(namesIt.hasNext() && scoresIt.hasNext()) {
                SortedEntrySet.add(new entry(scoresIt.next(), namesIt.next()));
            }
            List<entry> SortedList = new ArrayList<>(SortedEntrySet);
            int EntryNo = (SortedList.size() * P) / 100;
            Iterator<entry> SortedIt = SortedList.iterator();
            List<String> returnList = new ArrayList<String>(EntryNo);
            for (int i = 0; i < EntryNo; i++) {
                returnList.add(i, SortedIt.next().n);
            }
            return returnList;
        }
    }
}
