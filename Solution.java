import java.lang.Math;

public class Solution {
	//	This class addresses needs of "Ion-Flux-Relabeling" specification
	public static int[] solution(int treeHeight, int[] childKeys) {
		//	Provides array of keys for parent nodes of child nodes whose keys are provided, for perfect binary tree of provided height.
		int[] parentKeys = new int[childKeys.length];
		int nodeCount = (int)(Math.pow(2, treeHeight) - 1);
		int[] allNodes = new int[nodeCount];
		for (int i = 0; i < nodeCount; i++) allNodes[i] = i + 1;
		Solution.buildBinaryTree(allNodes, 0, nodeCount - 1, -1, parentKeys, childKeys);
		return parentKeys;
	}	
	static Node buildBinaryTree(int[] allNodes, int first, int last, int parent, int[] parentKeys, int[] childKeys) {
		//	Builds perfect binary tree with values from {allNodes} assigned to node keys sequentially via a post order traversal
		Node node = new Node(allNodes[last]);
		populateParentKeys(node, childKeys, parentKeys, parent);
		if (last == first) return node;
		node.left = buildBinaryTree(allNodes, first, (last + first - 2)/2, node.key, parentKeys, childKeys);
		node.right = buildBinaryTree(allNodes, (last + first)/2, last - 1, node.key, parentKeys, childKeys);
		return node;
	}
	static void populateParentKeys(Node node, int[] childKeys, int[] parentKeys, int parent) {
		//	When current node is contained in {childKeys}, and parent node exists, populates {parentKeys}
		for (int i = 0; i < childKeys.length; i++) if (childKeys[i] == node.key) parentKeys[i] = parent;
	}
}
class Node {
	int key;
	Node left, right;
	public Node(int key) {
		this.key = key;
		left = right = null;
	}
}
