#Problem Statement:

#Equipe manages a network of equipment rental providers. Each prowder has connedions with other providers, allowing customers to rent equipment even if ther preferred provides does not have availability. Given a liest of providens and ther connections, determine the shortest path to find the nearest available equipment of a given type


package com.equip9;

import java.util.*;

public class Question1 {
	public static List<Integer> findShortestPath(int n, List<List<Integer>> edges,
			Map<Integer, List<String>> availability, int startProvider, String targetEquipment) {

		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (List<Integer> edge : edges) {
			int a = edge.get(0);
			int b = edge.get(1);
			graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
			graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
		}	

		// BFS Setup
		Queue<List<Integer>> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		queue.add(Arrays.asList(startProvider));
		visited.add(startProvider);

		while (!queue.isEmpty()) {
			List<Integer> path = queue.poll();
			int current = path.get(path.size() - 1);

			if (availability.getOrDefault(current, new ArrayList<>()).contains(targetEquipment)) {
				return path;
			}

			for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					List<Integer> newPath = new ArrayList<>(path);
					newPath.add(neighbor);
					queue.add(newPath);
				}
			}

		}
		return Collections.singletonList(-1);
	}

	public static void main(String[] args) {
		int n = 5;
		List<List<Integer>> edges = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4),
				Arrays.asList(4, 5));

		Map<Integer, List<String>> availability = new HashMap<>();
		availability.put(1, Arrays.asList("excavator"));
		availability.put(2, Arrays.asList());
		availability.put(3, Arrays.asList("bulldozer"));
		availability.put(4, Arrays.asList("excavator"));
		availability.put(5, Arrays.asList("crane"));

		int startProvider = 2;
		String targetEquipment = "excavator";

		List<Integer> path = findShortestPath(n, edges, availability, startProvider, targetEquipment);
		System.out.println("Shotest Path: " + path);
	}
}
