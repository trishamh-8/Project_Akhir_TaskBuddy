package cli;

import model.UserRole;

public class Menu {

    public static void displayUserMenu(String username) {
        System.out.println("\n===== TaskBuddy CLI =====");
        System.out.println("Pengguna Aktif: " + username + " (Peran: USER)");
        System.out.println("-------------------------");
        System.out.println("1. Tambah Tugas/Subtugas");
        System.out.println("2. Tampilkan Semua Tugas (Struktur Pohon)");
        System.out.println("3. Tampilkan Tugas Terurut");
        System.out.println("4. Cari Tugas");
        System.out.println("5. Undo Aktivitas Terakhir");
        System.out.println("6. Redo Aktivitas");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    public static void displayAdminMenu(String username) {
        System.out.println("\n===== TaskBuddy CLI =====");
        System.out.println("Pengguna Aktif: " + username + " (Peran: ADMIN)");
        System.out.println("-------------------------");
        System.out.println("1. Tampilkan Log Aktivitas (Semua Pengguna)");
        System.out.println("2. Tampilkan Antrian Pengguna");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }
}