package 动态查找表;

/**
 * 	二叉查找树
 * 	@author 唐帆
 *
 */

public class BinarySearchTree {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		System.out.println(tree.find(4));
	}
	
	private class Node{
		int data;
		Node left;
		Node right;
	}
	
	private Node root;
	
	public void insert(int key) {
		root = insert(key, root);
	}
	
	public Boolean find(int key) {
		boolean result = find(key, root);
		return result;
	}
	
	public void remove(int key) {
		root = remove(key, root);
	}
	
	public Node insert(int key, Node t) {
		Node p = new Node();
		p.data = key;
		if(t==null) {
			t = p;
		}else if(key < t.data) {
			t.left = insert(key, t.left);
		}else if(key > t.data) {
			t.right = insert(key, t.right);
		}
		return t;
	}
	
	public Boolean find(int x, Node t) {
		Boolean result = false;
		if (t==null) {
			result = false;
		}else if(x < t.data) {
			result = find(x, t.left);
		}else if(x > t.data) {
			result = find(x, t.right);
		}else {
			result = true;
		}
		return result;
	}
	
	public Node remove(int x, Node t) {
		Node result = new Node();
		if(t==null) return null;
		if(x < t.data) result = remove(x, t.left);
		else if (t.data < x) result = remove(x, t.right);
		else if (t.left != null && t.right != null) {
			Node tmp = t.right;
			while(tmp.left != null) tmp = tmp.left;
			t.data = tmp.data;
			result = remove(t.data, t.right);
		}
		else {
			if(t.left != null) return t.left;
			else if(t.left == null) return t.right;
		}
		return result;
	}
}
