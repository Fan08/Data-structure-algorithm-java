package 动态查找表;

import 动态查找表.RedBlackTree.RedBlackNode;

public class LinkStackForRedBlackTree {
	public static void main(String[] args) {
	}
	
	public class node{
		RedBlackNode data;
		node next;
	}
	
	public node elem;
	
	/**
	 * 判断是否是空栈
	 * @return
	 */
	public boolean isEmpty() {
		return elem == null;
	}
	
	/**
	 * 压栈，将数据存入栈中
	 * @param x
	 */
	public void push(RedBlackNode x) {
		node tmp = new node();
		tmp.data = x;
		tmp.next = elem;
		elem = tmp;
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public RedBlackNode pop() {
		node tmp = elem;
		RedBlackNode x = tmp.data;
		elem = elem.next;
		return x;
	}
	
	/**
	 * 获取栈顶
	 * @return
	 */
	public RedBlackNode top() {
		return elem.data;
	}
	
	/**
	 * 析构函数
	 */
	public void linkStack() {
		while(elem != null) {
			elem = elem.next;
		}
	}
}
