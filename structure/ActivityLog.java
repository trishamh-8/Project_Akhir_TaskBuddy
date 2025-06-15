package structure;

import java.time.LocalDateTime;

public class ActivityLog {
    private LogNode head;
    private LogNode tail;
    private LogNode current;

    public void addLog(String username, String activity) {
        LogNode newNode = new LogNode(username, activity);
        if (current != tail && current != null) {
            current.setNext(null);
            tail = current;
        } else if (current == null && head != null) {
            head = tail = null;
        }

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        current = tail;
    }

    public void undo() {
        if (current == null) {
            System.out.println("Tidak ada aktivitas untuk di-undo.");
        } else {
            System.out.println("Undo: " + current.getActivity() + " - dibatalkan.");
            current = current.getPrev();
        }
    }

    public void redo() {
        if (current == tail) {
            System.out.println("Tidak ada aktivitas untuk di-redo.");
        } else {
            current = (current == null) ? head : current.getNext();
            if (current != null) {
                System.out.println("Redo: " + current.getActivity() + " - dipulihkan.");
            }
        }
    }

    public void displayLog() {
        if (head == null) {
            System.out.println("Log aktivitas masih kosong.");
            return;
        }

        System.out.println("\n--- Log Aktivitas ---");
        LogNode temp = head;
        while (temp != null) {
            if (temp == current) {
                System.out.println("> " + temp);
            } else {
                System.out.println("  " + temp);
            }
            temp = temp.getNext();
        }
    }
}