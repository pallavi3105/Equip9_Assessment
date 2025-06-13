package com.equip9;

import java.util.*;

public class Question3 {

    static class FenwickTree {
        private long[] tree;

        public FenwickTree(int size) {
            tree = new long[size + 2];
        }

        public void update(int i, long value) {
            while (i < tree.length) {
                tree[i] += value;
                i += i & -i;
            }
        }

        public long query(int i) {
            long sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }

        public long rangeSum(int left, int right) {
            return query(right) - query(left - 1);
        }
    }

    public static List<Long> totalMaintenanceCost(List<String[]> maintenanceLogs, List<String[]> queries) {
        Set<String> dateSet = new TreeSet<>();

     
        for (String[] log : maintenanceLogs) {
            dateSet.add(log[1]);
        }
        for (String[] q : queries) {
            dateSet.add(q[0]);
            dateSet.add(q[1]);
        }

     
        Map<String, Integer> dateIndex = new HashMap<>();
        int index = 1;
        for (String date : dateSet) {
            dateIndex.put(date, index++);
        }

       
        FenwickTree ft = new FenwickTree(dateIndex.size());

        // Update  maintenance logs
        for (String[] log : maintenanceLogs) {
            String date = log[1];
            long cost = Long.parseLong(log[2]);
            ft.update(dateIndex.get(date), cost);
        }

        // Step 5: Answer the queries
        List<Long> results = new ArrayList<>();
        for (String[] q : queries) {
            int start = dateIndex.get(q[0]);
            int end = dateIndex.get(q[1]);
            results.add(ft.rangeSum(start, end));
        }

        return results;
    }

    public static void main(String[] args) {
        List<String[]> maintenanceLogs = Arrays.asList(
                new String[]{"101", "2024-01-01", "500"},
                new String[]{"102", "2024-01-10", "300"},
                new String[]{"101", "2024-01-15", "700"}
        );

        List<String[]> queries = Arrays.asList(
                new String[]{"2024-01-01", "2024-01-10"},
                new String[]{"2024-01-01", "2024-01-15"}
        );

        List<Long> results = totalMaintenanceCost(maintenanceLogs, queries);
        System.out.println(results); 
    }
}
