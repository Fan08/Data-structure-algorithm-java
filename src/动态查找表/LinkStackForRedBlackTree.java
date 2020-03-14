package ��̬���ұ�;

import ��̬���ұ�.RedBlackTree.RedBlackNode;

public class LinkStackForRedBlackTree {
	public static void main(String[] args) {
	}
	
	public class node{
		RedBlackNode data;
		node next;
	}
	
	public node elem;
	
	/**
	 * �ж��Ƿ��ǿ�ջ
	 * @return
	 */
	public boolean isEmpty() {
		return elem == null;
	}
	
	/**
	 * ѹջ�������ݴ���ջ��
	 * @param x
	 */
	public void push(RedBlackNode x) {
		node tmp = new node();
		tmp.data = x;
		tmp.next = elem;
		elem = tmp;
	}
	
	/**
	 * ��ջ
	 * @return
	 */
	public RedBlackNode pop() {
		node tmp = elem;
		RedBlackNode x = tmp.data;
		elem = elem.next;
		return x;
	}
	
	/**
	 * ��ȡջ��
	 * @return
	 */
	public RedBlackNode top() {
		return elem.data;
	}
	
	/**
	 * ��������
	 */
	public void linkStack() {
		while(elem != null) {
			elem = elem.next;
		}
	}
}
