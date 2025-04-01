import java.util.*;

class Main {
    public static List<Integer> matchRequests(List<String[]> requests, List<String[]> sellers) {
        Map<String, PriorityQueue<Integer>> equipmentMap = new HashMap<>();
        
        for (String[] seller : sellers) {
            String equipmentType = seller[0];
            int price = Integer.parseInt(seller[1]);
            
            equipmentMap.putIfAbsent(equipmentType, new PriorityQueue<>());
            equipmentMap.get(equipmentType).offer(price);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (String[] request : requests) {
            String equipmentType = request[0];
            int maxPrice = Integer.parseInt(request[1]);
            
            if (equipmentMap.containsKey(equipmentType)) {
                PriorityQueue<Integer> pq = equipmentMap.get(equipmentType);
                
                while (!pq.isEmpty() && pq.peek() > maxPrice) {
                    pq.poll(); 
                }
                
                result.add(pq.isEmpty() ? null : pq.poll());
            } else {
                result.add(null);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        List<String[]> requests = Arrays.asList(
            new String[]{"excavator", "50000"}, 
            new String[]{"bulldozer", "70000"}
        );
        
        List<String[]> sellers = Arrays.asList(
            new String[]{"excavator", "45000"}, 
            new String[]{"bulldozer", "68000"}, 
            new String[]{"excavator", "48000"}
        );
        
        List<Integer> result = matchRequests(requests, sellers);
        System.out.println(result); 
    }
}
