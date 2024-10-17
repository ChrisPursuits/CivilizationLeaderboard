package ccy.civilizationleaderboard.user.comparator;

import ccy.civilizationleaderboard.user.model.User;

import java.util.Comparator;
import java.util.List;

public class PlacementHistoryComparator implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        List<Integer> ph1 = u1.getPlacementHistory();
        List<Integer> ph2 = u2.getPlacementHistory();

        int size = Math.min(ph1.size(), ph2.size()); // Compare up to the length of the shorter list

        for (int i = 0; i < size; i++) {
            int comparison = Integer.compare(ph2.get(i), ph1.get(i)); // Compare in descending order
            if (comparison != 0) {
                return comparison;
            }
        }

        // If all compared values are equal, the shorter list should come first
        return Integer.compare(ph1.size(), ph2.size());
    }
}
