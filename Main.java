import model.Task;
import model.User;
import model.UserRole;
import structure.ActivityLog;
import structure.TaskTree;
import structure.UserQueue;
import cli.Menu;
import util.TaskSearcher;
import util.TaskSorter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserQueue userQueue = new UserQueue();
        ActivityLog globalActivityLog = new ActivityLog();

        System.out.println("Selamat datang di TaskBuddy!");
        System.out.print("Berapa jumlah pengguna yang ingin menggunakan aplikasi ini? ");
        int jumlahUser = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= jumlahUser; i++) {
            System.out.print("Masukkan nama pengguna ke-" + i + ": ");
            String username = scanner.nextLine();

            System.out.print("Pilih peran untuk " + username + " (1 = ADMIN, 2 = USER): ");
            int roleChoice = -1;
            UserRole userRole = UserRole.USER;
            try {
                roleChoice = Integer.parseInt(scanner.nextLine());
                if (roleChoice == 1) {
                    userRole = UserRole.ADMIN;
                }
            } catch (NumberFormatException ignored) {}

            User user = new User(username, userRole);
            userQueue.enqueue(user);
            globalActivityLog.addLog("SYSTEM", "Pengguna '" + user.getUsername() + "' (" + user.getRole() + ") masuk ke sistem.");
        }

        System.out.println("\nMemulai simulasi TaskBuddy untuk semua pengguna...\n");

        while (!userQueue.isEmpty()) {
            User currentUser = userQueue.peek();
            System.out.println("\nGiliran " + currentUser.getUsername() + " (" + currentUser.getRole() + ") menggunakan TaskBuddy!");

            TaskTree taskTree = currentUser.getUserTaskTree();
            ActivityLog userActivityLog = currentUser.getUserActivityLog();

            boolean isRunning = true;
            while (isRunning) {
                int choice;
                if (currentUser.getRole() == UserRole.ADMIN) {
                    Menu.displayAdminMenu(currentUser.getUsername());
                } else {
                    Menu.displayUserMenu(currentUser.getUsername());
                }

                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                    continue;
                }

                if (currentUser.getRole() == UserRole.USER) {
                    switch (choice) {
                        case 1 -> {
                            System.out.println("== Menu Tambah Tugas Baru ==");
                            System.out.print("Nama Tugas: ");
                            String name = scanner.nextLine();
                            System.out.print("Kategori(Akademik/Non-Akademik): ");
                            String category = scanner.nextLine();
                            System.out.print("Prioritas (1-5): ");
                            int priority = Integer.parseInt(scanner.nextLine());
                            System.out.print("Deadline (YYYY-MM-DD, kosongkan jika tidak ada): ");
                            String deadlineStr = scanner.nextLine();
                            LocalDate deadline = null;
                            if (!deadlineStr.isEmpty()) {
                                try {
                                    deadline = LocalDate.parse(deadlineStr);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Format tanggal tidak valid. Deadline tidak diatur.");
                                }
                            }

                            List<Task> availableParents = taskTree.getAllTasks();
                            System.out.println("\n--- Pilih Parent untuk Tugas Baru ---");
                            for (Task task : availableParents) {
                                System.out.println(task);
                            }
                            System.out.println("-------------------------------------");
                            System.out.print("Masukkan ID Parent: ");
                            int parentId = Integer.parseInt(scanner.nextLine());

                            Task addedTask = taskTree.addTask(parentId, name, category, priority, deadline);
                            if (addedTask != null) {
                                String logMessage = "Tugas '" + name + "' (ID: " + addedTask.getId() + ") ditambahkan.";
                                userActivityLog.addLog(currentUser.getUsername(), logMessage);
                                globalActivityLog.addLog(currentUser.getUsername(), logMessage);
                            }
                        }
                        case 2 -> {
                            taskTree.displayTree();
                            String logMessage = "Melihat struktur tugas.";
                            userActivityLog.addLog(currentUser.getUsername(), logMessage);
                            globalActivityLog.addLog(currentUser.getUsername(), logMessage);
                        }
                        case 3 -> {
                            List<Task> allTasks = taskTree.getAllTasks();
                            if (allTasks.isEmpty()) {
                                System.out.println("Tidak ada tugas untuk diurutkan.");
                                break;
                            }
                            System.out.println("Urutkan berdasarkan (1 = Prioritas, 2 = Deadline, 3 = Nama): ");
                            int sortOption = Integer.parseInt(scanner.nextLine());
                            TaskSorter.SortOption optionEnum = switch (sortOption) {
                                case 1 -> TaskSorter.SortOption.PRIORITY;
                                case 2 -> TaskSorter.SortOption.DEADLINE;
                                case 3 -> TaskSorter.SortOption.NAME;
                                default -> null;
                            };
                            if (optionEnum != null) {
                                TaskSorter.bubbleSort(allTasks, optionEnum);
                                System.out.println("\n--- Tugas Terurut ---");
                                for (Task task : allTasks) {
                                    System.out.println(task);
                                }
                                String logMessage = "Tugas diurutkan berdasarkan " + optionEnum.name().toLowerCase() + ".";
                                userActivityLog.addLog(currentUser.getUsername(), logMessage);
                                globalActivityLog.addLog(currentUser.getUsername(), logMessage);
                            }
                        }
                        case 4 -> {
                            List<Task> allTasks = taskTree.getAllTasks();
                            if (allTasks.isEmpty()) {
                                System.out.println("Tidak ada tugas untuk dicari.");
                                break;
                            }
                            System.out.println("Cari berdasarkan (1 = Nama, 2 = Kategori, 3 = Prioritas, 4 = Deadline): ");
                            int searchOption = Integer.parseInt(scanner.nextLine());
                            System.out.print("Masukkan kata kunci: ");
                            String keyword = scanner.nextLine();

                            List<Task> foundTasks = TaskSearcher.linearSearch(allTasks, keyword, searchOption);
                            if (foundTasks.isEmpty()) {
                                System.out.println("Tidak ada tugas yang ditemukan.");
                            } else {
                                System.out.println("\n--- Hasil Pencarian ---");
                                for (Task task : foundTasks) {
                                    System.out.println(task);
                                }
                            }
                            String logMessage = "Mencari tugas dengan kata kunci '" + keyword + "'.";
                            userActivityLog.addLog(currentUser.getUsername(), logMessage);
                            globalActivityLog.addLog(currentUser.getUsername(), logMessage);
                        }
                        case 5 -> userActivityLog.undo();
                        case 6 -> userActivityLog.redo();
                        case 0 -> {
                            String logMessage = "Keluar dari sesi.";
                            userActivityLog.addLog(currentUser.getUsername(), logMessage);
                            globalActivityLog.addLog(currentUser.getUsername(), logMessage);
                            userQueue.dequeue();
                            isRunning = false;
                            System.out.println("Sesi " + currentUser.getUsername() + " selesai.\n");
                        }
                        default -> System.out.println("Pilihan tidak valid untuk peran USER. Silakan coba lagi.");
                    }
                } else {
                    switch (choice) {
                        case 1 -> globalActivityLog.displayLog();
                        case 2 -> userQueue.displayQueue();
                        case 0 -> {
                            globalActivityLog.addLog("ADMIN", "Admin '" + currentUser.getUsername() + "' keluar dari sesi.");
                            userQueue.dequeue();
                            isRunning = false;
                            System.out.println("Sesi " + currentUser.getUsername() + " selesai.\n");
                        }
                        default -> System.out.println("Pilihan tidak valid untuk peran ADMIN. Silakan coba lagi.");
                    }
                }
            }
        }

        System.out.println("Semua pengguna telah menyelesaikan sesi TaskBuddy. Terima kasih!");
        scanner.close();
    }
}
