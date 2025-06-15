package model;

import java.time.LocalDate;

public class Task extends AbstractTask {
    private int priority;

    public Task(int id, String name, String category, int priority, LocalDate deadline) {
        super(id, name, category, deadline);
        // Validasi kategori hanya untuk tugas biasa, bukan root task (ID=1)
        if (id > 1) {
            validateCategory(category);
        }
        this.priority = priority;
    }

    private void validateCategory(String category) {
        if (category == null || (!category.equalsIgnoreCase("akademik") && !category.equalsIgnoreCase("non-akademik"))) {
            throw new IllegalArgumentException("Kategori harus 'Akademik' atau 'Non-Akademik'.");
        }
    }

    @Override
    public void setCategory(String category) {
        validateCategory(category);
        super.setCategory(category);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Task: %s | Kategori: %s | Prioritas: %d | Deadline: %s",
                id, name, category, priority,
                (deadline != null ? deadline.toString() : "Tidak ditentukan")
        );
    }
    @Override
    public void display() {
        System.out.println(this.toString());
    }
}