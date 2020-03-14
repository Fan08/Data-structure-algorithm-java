package ��̬���ұ�;

public class AvlTree {

	public static void main(String[] args) {
		AvlTree tree = new AvlTree();
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
//		tree.remove(3);
		System.out.println(tree.find(3));
	}
	
	private class AvlNode{
		int data;
		AvlNode left;
		AvlNode right;
		int height;
	}
	
	private AvlNode root;
	
	private int height(AvlNode x) {
		int height = -1;
		if (x == null) {
			height = -1;
		}
		else if (x.height != -1) {
			height = x.height;
		}
		return height;
	}
	
	public void insert(int x) {
		root = insert(x, root);
	}
	
	public void remove(int x) {
		remove(x, root);
	}
	
	/**
	 * �ǵݹ�ʵ��Ԫ�ز���
	 * @param x����������
	 * @return �������ͣ��ҵ��򷵻�true����֮Ϊfalse
	 */
	public boolean find(int x) {
		AvlNode t = root;
		boolean resutl = false;
		while(t != null && t.data != x) {
			if(t.data > x) {
				t = t.left;
			}
			else {
				t = t.right;
			}
		}
		if (t == null) resutl = false;
		else resutl = true;
		return resutl;
	}
	
	/**
	 * �����½ڵ�
	 * @param x���������ͣ��µ���ֵ
	 * @param t��ԭ����
	 * @return �µ���
	 */
	private AvlNode insert(int x, AvlNode t) {
		if(t == null) {
			AvlNode new_tree = new AvlNode();
			new_tree.data = x;
			t = new_tree;
		}
		else if(x < t.data) {
			t.left = insert(x, t.left);
			if(height(t.right) - height(t.left) == 2) {
				if(x < t.left.data) t = LL(t);
				else t = LR(t);
			}
		}
		else if (t.data < x) {
			t.right = insert(x, t.right);
			if (height(t.right) - height(t.left) == 2) {
				if (t.right.data < x) t = RR(t);
				else t = RL(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
	
	//	������������ת
	/**
	 * ��������������ڵ�
	 * @param t����ǰ��
	 * @return �µ���
	 */
	private AvlNode LL(AvlNode t) {
		AvlNode tl = t.left;
		t.left = tl.right;
		tl.right = t;
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		tl.height = Math.max(height(tl.left), height(t)) + 1;
		t = tl;
		return t;
	}
	
	/**
	 * ���������ҵ����ڵ�
	 * @param t
	 * @return
	 */
	private AvlNode RR(AvlNode t) {
		AvlNode tl = t.right;
		t.right = tl.left;
		tl.left = t;
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		tl.height = Math.max(height(tl.right), height(t)) + 1;
		t = tl;
		return t;
	}
	
	/**
	 * �ڲ�ڵ����ת��������ڲ�ڵ�
	 * @param t
	 * @return
	 */
	private AvlNode LR(AvlNode t) {
		t = RR(t.left);
		t = LL(t);
		return t;
	}
	
	/**
	 * ���ҵ��ڲ�ڵ�
	 * @param t
	 * @return
	 */
	private AvlNode RL(AvlNode t) {
		t = LL(t.right);
		t = RR(t);
		return t;
	}
	
	/**
	 * ɾ����������AVL����ɾ���ڵ�
	 * @param x
	 * @param t
	 * @return
	 */
	private boolean remove(int x, AvlNode t) {
		boolean stop = false;
		boolean result = false;
		int subTree = 0;
		if (t == null) result = true;
		if(x<t.data) {
			stop = remove(x, t.left);
			subTree = 1;
		}
		else if(t.data<x) {
			stop = remove(x, t.right);
			subTree = 2;
		}
		else if(t.left != null && t.right != null) {
			AvlNode tmp = t.right;
			while (tmp.left != null) tmp = tmp.left;
			t.data = tmp.data;
			stop = remove(t.data, t.right);
			subTree = 2;
		}
		else {
			root = ((t.left != null) ? t.left : t.right);
			result = false;
		}
		if(stop) result = true;
		int bf;
		switch (subTree) {
		case 1:
			bf = height(t.left) - height(t.right) + 1;
			if(bf == 0) result = true;
			if(bf == 1) result = false;
			if(bf == -1) {
				int bfr = height(t.right.left) - height(t.right.right);
				switch (bfr) {
				case 0:
					root = RR(t);
					result = true;
					break;
				case 1:
					root = RR(t);
					result = false;
					break;
				default:
					root = RL(t);
					result = false;
					break;
				}
			}
			break;
		
		case 2:
			bf = height(t.left) - height(t.right) - 1;
			if(bf == 0) result = true;
			if(bf == -1) result = false;
			if(bf == 1) {
				int bfl = height(t.right.left) - height(t.right.left);
				switch (bfl) {
				case 0:
					root = LL(t);
					result = true;
					break;
				case 1:
					root = LL(t);
					result = false;
					break;
				default:
					root = LR(t);
					result = false;
					break;
				}
			}
			break;
		default:
			break;
		}
		return result;
	}
}
