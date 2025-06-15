package structure;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Task task;
    private List<TreeNode> children;

    public TreeNode(Task task) {
        this.task = task;
        this.children = new ArrayList<>();
    }

    public Task getTask() {
        return task;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public void removeChild(TreeNode child) {
        this.children.remove(child);
    }
}