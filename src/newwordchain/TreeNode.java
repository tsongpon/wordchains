package newwordchain;

import java.util.List;

/**
 *
 */
public class TreeNode {

    private String data;
    private TreeNode parentNode;
    private List<TreeNode> childs;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public List<TreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeNode> childs) {
        this.childs = childs;
    }
}
