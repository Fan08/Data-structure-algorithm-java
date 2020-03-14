package 动态查找表;

import 动态查找表.LinkStackForRedBlackTree.node;

public class RedBlackTree {
	public static void main(String[] args) {
		
	}
	
	public class RedBlackNode{
		int data;
		RedBlackNode left;
		RedBlackNode right;
		//	0-red, 1-black
		int color;
	}
	
	private RedBlackNode root;
	
	/**
	 * 以下四个均为旋转
	 * 左旋转，靠左的外侧节点
	 * @param t
	 * @return
	 */
	public RedBlackNode LL(RedBlackNode t) {
		RedBlackNode tl = t.left;
		t.left = tl.right;
		tl.right = t;
		t = tl;
		return t;
	}
	
	public RedBlackNode RR(RedBlackNode t) {
		RedBlackNode tl = t.right;
		t.right = tl.left;
		tl.left = t;
		t = tl;
		return t;
	}
	
	public RedBlackNode LR(RedBlackNode t) {
		t = RR(t.left);
		t = LL(t);
		return t;
	}
	
	public RedBlackNode RL(RedBlackNode t) {
		t = LL(t.right);
		t = RR(t);
		return t;
	}
	
	public node reLink(
			RedBlackNode oldp, 
			RedBlackNode newp, 
			LinkStackForRedBlackTree path) {
		node result = null;
		if(path.isEmpty()) root = newp;
		else {
			RedBlackNode grandParent = path.pop();
			if(grandParent.left == oldp) {
				grandParent.left = newp;
			}
			else grandParent.right = newp;
			path.push(grandParent);
			path.elem = result;
		}
		return result;
	}
	
	public void insert(int x) {
		LinkStackForRedBlackTree path = new LinkStackForRedBlackTree();
		if(root==null) {
			root = new RedBlackNode();
			root.data = x;
		}
		RedBlackNode t = root;
		while(t != null && t.data != x) {
			path.push(t);
			if(t.data < x) t = t.right;
			else t = t.left;
		}
		if(t != null) return;
		t = new RedBlackNode();
		t.data = x;
		RedBlackNode parent = path.pop();
		if(x < parent.data) parent.left = t;
		else parent.right = t;
		if (parent.color == 1) return;
		path.push(parent);
		//	此处可能需要进行修改
		insertRebalance(t, path);
	}
	
	public void insertRebalance(RedBlackNode t, LinkStackForRedBlackTree path) {
		RedBlackNode parent, grandParent, uncle, rootOfSubTree;
		parent = path.pop();	//	弹出父节点
		while (parent.color == 0) {
			if(parent == root) {
				parent.color = 1;
				return;
			}
			grandParent = rootOfSubTree = path.pop();
			if(grandParent.data > parent.data) uncle = grandParent.right;
			else uncle = grandParent.left;
			if(uncle == null || uncle.color == 1) {
				if(grandParent.left == parent) {
					if(t == parent.left) {
						parent.color = 1;
						grandParent.color = 0;
						LL(grandParent);
					}
					else {
						grandParent.color = 0;
						t.color = 1;
						LR(grandParent);
					}
				}
				else if(t==parent.right) {
					parent.color = 1;
					grandParent.color = 0;
					RR(grandParent);
				}
				else {
					grandParent.color = 0;
					t.color = 1;
					RL(grandParent);
				}
				reLink(rootOfSubTree, grandParent, path);
				return;
			}
			else {
				grandParent.color = 0;
				parent.color = 1;
				uncle.color = 1;
				if(root == grandParent) {
					root.color = 1;
					return ;
				}
				t = grandParent;
				parent = path.pop();
			}
		}
	}
	
	public void remove(int x) {
		LinkStackForRedBlackTree path = new LinkStackForRedBlackTree();
		RedBlackNode t = root, old, parent = null;
		while(t != null && t.data != x) {
			path.push(t);
			if(t.data > x) t = t.left;
			else t = t.right;
		}
		if(t==null) return;
		
		//	找到替代节点并替代
		if(t.left != null && t.right != null) {
			path.push(t);
			old = t;
			t = t.right;
			while(t.left != null) {
				path.push(t);
				t = t.left;
			}
			old.data = t.data;
		}
		
		//	执行删除
		if(t == root) {
			root = (t.left != null?t.left:t.right);
			if (root != null) root.color = 1;
			return;
		}
		parent = path.pop();
		old = t;
		t = (t.left != null?t.left:t.right);
		if(parent.left == old) parent.left = t;
		else parent.right = t;
		if(old.color==0) {
			old = null;
			return;
		}
		if(t!=null) {
			t.color = 1;
			return;
		}
		path.push(parent);
		removeReBalance(t, path);
	}
	
	public void removeReBalance(RedBlackNode t, LinkStackForRedBlackTree path) {
		RedBlackNode parent, sibling, notOfSubTree;
		parent = notOfSubTree = path.pop();
		while(parent != null) {
			
		}
	}
}
