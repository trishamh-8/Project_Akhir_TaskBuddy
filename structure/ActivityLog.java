package structure;

public class ActivityLog {
    private LogNode head;
    private LogNode tail;
    private LogNode current;

    public void addLog(String username, String activity) {
        LogNode newNode = new LogNode(username, activity);

        // Jika sedang di tengah (ada undo sebelumnya), hapus redo yang tersisa
        if (current != tail && current != null) {
            LogNode temp = current.getNext();
            while (temp != null) {
                LogNode next = temp.getNext();
                temp.setPrev(null);
                temp.setNext(null);
                temp = next;
            }
            current.setNext(null);
            tail = current;
        }

        // Tambahkan node baru
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        current = tail;
        System.out.println("[Log] " + activity);
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
        if (current == null && head != null) {
            current = head;
            System.out.println("Redo: " + current.getActivity() + " - dipulihkan.");
        } else if (current != null && current.getNext() != null) {
            current = current.getNext();
            System.out.println("Redo: " + current.getActivity() + " - dipulihkan.");
        } else {
            System.out.println("Tidak ada aktivitas untuk di-redo.");
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
