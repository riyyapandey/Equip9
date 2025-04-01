import java.util.*;

public class EquipmentRental {
    public static int findNearestProvider(int n, List<int[]> edges, Map<Integer, List<String>> availability, int startProvider, String targetEquipment) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(new int[]{startProvider, 0});
        visited.add(startProvider);

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int provider = curr[0], distance = curr[1];
            
            if (availability.containsKey(provider) && availability.get(provider).contains(targetEquipment)) {
                return distance;
            }
            
            if (graph.containsKey(provider)) {
                for (int neighbor : graph.get(provider)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(new int[]{neighbor, distance + 1});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = 5;
        List<int[]> edges = Arrays.asList(
            new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 5}
        );
        
        Map<Integer, List<String>> availability = new HashMap<>();
        availability.put(1, Arrays.asList("excavator"));
        availability.put(3, Arrays.asList("bulldozer"));
        availability.put(4, Arrays.asList("excavator"));
        availability.put(5, Arrays.asList("crane"));
        
        int startProvider = 2;
        String targetEquipment = "excavator";
        
        int result = findNearestProvider(n, edges, availability, startProvider, targetEquipment);
        System.out.println("Shortest path to nearest provider with " + targetEquipment + ": " + result);
    }
}
