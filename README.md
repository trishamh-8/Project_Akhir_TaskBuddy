# 🧠 TaskBuddy – Manajemen Tugas Personal (CLI-Based)

**TaskBuddy** adalah aplikasi berbasis _Command Line Interface_ (CLI) yang dirancang untuk membantu pengguna mengelola tugas-tugas harian secara efisien. Proyek ini menggabungkan berbagai struktur data (Tree, Queue, Linked List) untuk menyusun fitur-fitur utama dalam pengelolaan tugas yang mendukung banyak pengguna (multi-user).

---

## 📌 Deskripsi Singkat

Dengan TaskBuddy, pengguna dapat menambahkan, melihat, mengurutkan, dan mencari tugas berdasarkan prioritas, deadline, atau nama. Sistem juga mencatat seluruh riwayat aktivitas serta mendukung antrian pengguna yang bergiliran menggunakan aplikasi. Semua fitur ini terintegrasi dalam menu CLI yang sederhana dan mudah dipahami.

---

## 🎯 Fitur Utama

### 1. Struktur Tugas (Tree) 
- Menggunakan struktur **Tree** untuk mengelola tugas dan subtugas.
- Mendukung penambahan tugas dengan parent ID untuk membentuk hierarki tugas.
- Fitur `displayTree()` menampilkan seluruh struktur tugas secara terformat.

### 2. Log Perubahan (Double Linked List) 
- Mencatat setiap aksi pengguna menggunakan struktur **Double Linked List**.
- Fitur `Undo` dan `Redo` memungkinkan pembatalan dan pengulangan aksi pengguna.
- Riwayat aktivitas ditampilkan lengkap melalui `ActivityLog`.

### 3. Antrian Pengguna (Queue) 
- Simulasi **multi-user** menggunakan struktur **Queue**.
- Setiap pengguna akan masuk antrian saat memulai sesi dan keluar saat selesai.
- Fitur `displayQueue()` menampilkan daftar pengguna aktif dalam sesi.

### 4. Sorting & Searching 
- **Sorting**: mengurutkan semua tugas berdasarkan:
  - Prioritas (tertinggi ke terendah)
  - Deadline (terdekat ke terjauh)
  - Nama tugas (A-Z)
- **Searching**: pencarian tugas dengan kata kunci berdasarkan:
  - Nama
  - Kategori
  - Prioritas
  - Deadline

### 5. Manajemen Pengguna & Peran 
- Setiap pengguna didefinisikan sebagai objek `User` dengan atribut `username`.
- Pengguna dapat menambahkan tugas dan aktivitas mereka dicatat secara personal.
- Sistem mendukung banyak pengguna namun tetap terkontrol melalui giliran antrian.

### 6. Menu CLI & Logika Utama 
- Menu CLI menampilkan navigasi fitur yang mudah dimengerti.
- Integrasi seluruh komponen: pengguna masuk -> menambahkan tugas -> menyortir -> log aktivitas -> keluar sesi.
- Kontrol program terdapat dalam `Main.java`.


---

## 👥 Pembagian Tugas Anggota

| Nama                     | NIM             | Tugas                                  |
|--------------------------|-----------------|----------------------------------------|
| Trisha Malina Hanim      | 245150400111042 | Struktur Tugas (Tree)                  |
| Tiara Aristawidya        | 245150400111041 | Log Perubahan (Double Linked List)     |
| Sandra Triana Nursyafri  | 245150401111027 | Antrian Pengguna (Queue)               |
| Dennisa Syafa            | 245150400111040 | Sorting dan Searching                  |
| Ailsa Nasywa Hidayat     | 245150407111052 | Manajemen Pengguna dan Peran           |
| Semua                    |                 | Menu CLI & Logika Utama                |


## ▶️ Cara Menjalankan Program

1. Jalankan program
2. Masukkan jumlah user yang akan menggunakan aplikasi
3. Masukkan nama pengguna
4. Menu utama akan tampil, pilih fitur dengan angka:
  1 ➝ Tambah Tugas Baru
  2 ➝ Lihat Struktur Tugas
  3 ➝ Urutkan Tugas (Prioritas, Deadline, atau Nama)
  4 ➝ Cari Tugas (Nama, Kategori, Prioritas, atau Deadline)
  5 ➝ Lihat Log Aktivitas
  6 ➝ Lihat Antrian Pengguna
  7 ➝ Undo Aktivitas Terakhir
  8 ➝ Redo Aktivitas
  0 ➝ Keluar dari sesi
Setiap perubahan yang dilakukan akan tercatat di log aktivitas, dan struktur tugas dikelola dalam bentuk Tree.
Multi-user: Jika program di-run ulang oleh pengguna lain, nama mereka akan masuk ke antrian (Queue), dan bisa melihat giliran mereka melalui menu 6.
Undo/Redo digunakan untuk membatalkan atau mengulang aktivitas terakhir, seperti menambah tugas.
Keluar: Pilih opsi 0 jika ingin keluar dari sesi, dan nama akan dihapus dari antrian pengguna.


TaskBuddy dirancang sebagai aplikasi manajemen tugas berbasis konsol yang tidak hanya membantu pengguna dalam mengatur aktivitas secara terstruktur, tetapi juga mengintegrasikan berbagai konsep struktur data dan prinsip OOP dalam praktik nyata.
Dengan fitur seperti struktur tugas berbasis Tree, log aktivitas melalui Double Linked List, simulasi multi-user melalui Queue, serta sorting dan searching, proyek ini menjadi bukti implementasi materi praktikum Pemrograman Lanjut secara komprehensif.
Kami berharap aplikasi ini dapat menjadi fondasi yang kuat untuk membangun sistem manajemen yang lebih kompleks di masa depan. 
Terima kasih.

