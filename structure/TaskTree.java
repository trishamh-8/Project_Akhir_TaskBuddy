package structure;

import model.Task;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TaskTree {
    private TreeNode root;
    private int nextTaskId;

    public TaskTree() {
        Task rootTask = new Task(1, "Semua Tugas", "Kategori utama untuk semua tugas", 0, null);
        this.root = new TreeNode(rootTask);
        this.nextTaskId = 2;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode findNodeById(int taskId) {
        return findNodeByIdRecursive(root, taskId);
    }

    private TreeNode findNodeByIdRecursive(TreeNode currentNode, int taskId) {
        if (currentNode.getTask().getId() == taskId) {
            return currentNode;
        }
        for (TreeNode child : currentNode.getChildren()) {
            TreeNode found = findNodeByIdRecursive(child, taskId);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public Task addTask(int parentId, String name, String category, int priority, LocalDate deadline) {
        TreeNode parentNode = findNodeById(parentId);
        if (parentNode != null) {
            try {
                Task newTask = new Task(nextTaskId++, name, category, priority, deadline);
                parentNode.addChild(new TreeNode(newTask));
                return newTask;
            } catch (IllegalArgumentException e) {
                System.out.println("Gagal menambahkan tugas: " + e.getMessage());
                nextTaskId--; // Kembalikan ID yang gagal digunakan
                return null;
            }
        }
        System.out.println("Error: Parent ID tidak ditemukan.");
        return null;
    }

    // Kelas internal untuk membantu menemukan node beserta parent-nya
    private static class NodeAndParent {
        TreeNode node;
        TreeNode parent;

        NodeAndParent(TreeNode node, TreeNode parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    private NodeAndParent findNodeAndParentRecursive(TreeNode currentNode, TreeNode parentNode, int taskId) {
        if (currentNode.getTask().getId() == taskId) {
            return new NodeAndParent(currentNode, parentNode);
        }
        for (TreeNode child : currentNode.getChildren()) {
            NodeAndParent found = findNodeAndParentRecursive(child, currentNode, taskId);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public Task deleteTask(int taskId) {
        if (taskId == 1) {
            System.out.println("Error: Root task tidak dapat dihapus.");
            return null;
        }

        NodeAndParent result = findNodeAndParentRecursive(root, null, taskId);

        if (result != null && result.parent != null) {
            result.parent.removeChild(result.node);
            return result.node.getTask();
        } else {
            System.out.println("Error: Tugas dengan ID " + taskId + " tidak ditemukan.");
            return null;
        }
    }

    public Task updateTask(int taskId, String newName, String newCategory, int newPriority, LocalDate newDeadline) {
        TreeNode nodeToUpdate = findNodeById(taskId);
        if (nodeToUpdate == null) {
            System.out.println("Error: Tugas dengan ID " + taskId + " tidak ditemukan.");
            return null;
        }

        if (taskId == 1) {
            System.out.println("Peringatan: Hanya nama dan deadline dari root task yang dapat diubah.");
            Task task = nodeToUpdate.getTask();
            task.setName(newName);
            ((Task) task).setDeadline(newDeadline);
            return task;
        }

        try {
            Task taskToUpdate = nodeToUpdate.getTask();
            taskToUpdate.setName(newName);
            taskToUpdate.setCategory(newCategory); // Validasi terjadi di dalam setter
            ((Task) taskToUpdate).setPriority(newPriority);
            ((Task) taskToUpdate).setDeadline(newDeadline);
            return taskToUpdate;
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal memperbarui tugas: " + e.getMessage());
            return null;
        }
    }

    public void displayTree() {
        displayTreeRecursive(root, 0);
    }

    private void displayTreeRecursive(TreeNode node, int level) {
        String indent = " ".repeat(level * 4);
        System.out.println(indent + "└── " + node.getTask());
        for (TreeNode child : node.getChildren()) {
            displayTreeRecursive(child, level + 1);
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        getAllTasksRecursive(root, taskList);
        return taskList;
    }

    private void getAllTasksRecursive(TreeNode node, List<Task> taskList) {
        if (node != null) {
            taskList.add(node.getTask());
            for (TreeNode child : node.getChildren()) {
                getAllTasksRecursive(child, taskList);
            }
        }
    }
}