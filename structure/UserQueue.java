package structure;

import model.User;

// Implementasi Queue untuk antrian pengguna
public class UserQueue {
    private UserNode front;
    private UserNode rear;

    public boolean isEmpty() {
        return front == null;
    }

    // Menambah user ke antrian
    public void enqueue(User user) {
        UserNode newNode = new UserNode(user);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode); // Gunakan setter
            // newNode.setPrev(rear); // Hapus ini jika UserNode tidak punya prev
            rear = newNode;
        }
        System.out.println(user.getUsername() + " masuk ke dalam sesi.");
    }

    // Menghapus user dari antrian
    public User dequeue() {
        if (isEmpty()) {
            return null;
        }
        User user = front.getUser(); // Gunakan getter
        front = front.getNext(); // Gunakan getter
        if (front == null) {
            rear = null;
        }
        System.out.println(user.getUsername() + " keluar dari sesi.");
        return user;
    }

    public User peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getUser(); // Gunakan getter
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Tidak ada pengguna dalam antrian.");
            return;
        }
        System.out.println("\n--- Antrian Pengguna Aktif ---");
        UserNode current = front;
        int i = 1;
        while (current != null) {
            System.out.println((i == 1 ? "Giliran -> " : "") + i + ". " + current.getUser().getUsername() + " (" + current.getUser().getRole() + ")"); // Gunakan getter
            current = current.getNext(); // Gunakan getter
            i++;
        }
    }

    // Tambahkan getter ini agar Main bisa mengakses UserNode di queue
    public UserNode getFront() {
        return front;
    }
}