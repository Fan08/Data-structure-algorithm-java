package 动态查找表;

public class LinkStack {
	public static void main(String[] args) {
		LinkStack stack = new LinkStack();
		System.out.print(stack.isEmpty());
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack.top());
		System.out.println(stack.pop());
		System.out.println(stack.top());
		stack.linkStack();
		System.out.println(stack.isEmpty());
	}
	
	private class node{
		int data;
		node next;
	}
	
	private node elem;
	
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
	public void push(int x) {
		node tmp = new node();
		tmp.data = x;
		tmp.next = elem;
		elem = tmp;
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public int pop() {
		node tmp = elem;
		int x = tmp.data;
		elem = elem.next;
		return x;
	}
	
	/**
	 * 获取栈顶
	 * @return
	 */
	public int top() {
		return elem.data;
	}
	
	/**
	 * 析构函数
	 */
	public void linkStack() {
		node tmp;
		while(elem != null) {
			tmp = elem;
			elem = elem.next;
		}
	}
}
