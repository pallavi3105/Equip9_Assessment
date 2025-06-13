package com.equip9;

import java.util.*;

public class Question2 {

    public static List<Integer> matchRequests(List<Request> requests, List<Seller> sellers) {
        Map<String, List<Integer>> equipmentToPrices = new HashMap<>();

        
        for (Seller seller : sellers) {
            equipmentToPrices.computeIfAbsent(seller.equipmentType, k -> new ArrayList<>()).add(seller.price);
        }

        for (List<Integer> prices : equipmentToPrices.values()) {
            Collections.sort(prices);
        }

        List<Integer> result = new ArrayList<>();

       
        for (Request req : requests) {
            List<Integer> prices = equipmentToPrices.getOrDefault(req.equipmentType, new ArrayList<>());
            Integer matchedPrice = null;
            for (int price : prices) {
                if (price <= req.maxPrice) {
                    matchedPrice = price;
                    break;
                }
            }
            result.add(matchedPrice);         }

        return result;
    }

    public static class Request {
        String equipmentType;
        int maxPrice;

        public Request(String equipmentType, int maxPrice) {
            this.equipmentType = equipmentType;
            this.maxPrice = maxPrice;
        }
    }

    public static class Seller {
        String equipmentType;
        int price;

        public Seller(String equipmentType, int price) {
            this.equipmentType = equipmentType;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        List<Request> requests = Arrays.asList(
                new Request("excavator", 50000),
                new Request("bulldozer", 70000)
        );

        List<Seller> sellers = Arrays.asList(
                new Seller("excavator", 45000),
                new Seller("bulldozer", 68000),
                new Seller("excavator", 48000)
        );

        List<Integer> matches = matchRequests(requests, sellers);
        System.out.println("Matched seller prices: " + matches);
    }
}
