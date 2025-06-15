package util;

import model.Task;

import java.util.List;

public class TaskSorter {

    public enum SortOption {
        PRIORITY,
        DEADLINE,
        NAME
    }

    public static void bubbleSort(List<Task> tasks, SortOption option) {
        int n = tasks.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean shouldSwap = false;

                Task t1 = tasks.get(j);
                Task t2 = tasks.get(j + 1);

                switch (option) {
                    case PRIORITY -> {
                        if (t1.getPriority() < t2.getPriority()) {
                            shouldSwap = true;
                        }
                    }

                    case DEADLINE -> {
                        if (t1.getDeadline() == null) {
                            shouldSwap = false;
                        } else if (t2.getDeadline() == null) {
                            shouldSwap = true;
                        } else if (t1.getDeadline().isAfter(t2.getDeadline())) {
                            shouldSwap = true;
                        }
                    }

                    case NAME -> {
                        if (t1.getName().compareToIgnoreCase(t2.getName()) > 0) {
                            shouldSwap = true;
                        }
                    }
                }

                if (shouldSwap) {
                    tasks.set(j, t2);
                    tasks.set(j + 1, t1);
                }
            }
        }
    }
}
