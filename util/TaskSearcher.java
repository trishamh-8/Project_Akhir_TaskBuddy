package util;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskSearcher {

    public static List<Task> linearSearch(List<Task> tasks, String keyword, int option) {
        List<Task> result = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (Task task : tasks) {
            switch (option) {
                case 1 -> {
                    if (task.getName().toLowerCase().contains(keyword)) {
                        result.add(task);
                    }
                }
                case 2 -> {
                    if (task.getCategory().equalsIgnoreCase(keyword)) {
                        result.add(task);
                    }
                }
                case 3 -> {
                    try {
                        int priority = Integer.parseInt(keyword);
                        if (task.getPriority() == priority) {
                            result.add(task);
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
                case 4 -> {
                    if (task.getDeadline() != null &&
                            task.getDeadline().toString().toLowerCase().contains(keyword)) {
                        result.add(task);
                    }
                }
                default -> {
                    // Tidak ada aksi
                }
            }
        }

        return result;
    }
}
