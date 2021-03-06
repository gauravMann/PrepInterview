package treesBTAndGT.binaryTree;

import java.util.ArrayDeque;
import java.util.Scanner;

public class IterativeInorderTraversal_3 {
	private static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public static void display(Node node) {
		if (node == null) {
			return;
		}

		String str = "";

		str += node.left == null ? "." : node.left.data;
		str += " => " + node.data + " <= ";
		str += node.right == null ? "." : node.right.data;

		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		int[] arr = new int[scn.nextInt()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}

		Node[] nodes = new Node[arr.length];
		for (int i = 0; i < nodes.length; i++) {
			if (arr[i] != -1) {
				nodes[i] = new Node(arr[i], null, null);

				if (i > 0) {
					int pi = (i - 1) / 2;

					if (i == 2 * pi + 1) {
						nodes[pi].left = nodes[i];
					} else {
						nodes[pi].right = nodes[i];
					}
				}
			}
		}

//		solve(nodes[0]);
		simulateRecursion(nodes[0]);
	}

	private static void solve(Node root) {
		ArrayDeque<Node> stack = new ArrayDeque<>();

		Node curr = root;

		while (!stack.isEmpty() || curr != null) {

			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				Node tos = stack.pop();
				System.out.print(tos.data + " ");
				curr = tos.right;
			}
		}

	}
	/*Simulate recursion method
	*
	* */
	private static class Pair {
		Node node;
		boolean selfDone;
		boolean leftDone;
		boolean rightDone;

		Pair(Node node, boolean self, boolean left, boolean right) {
			this.node = node;
			this.selfDone = self;
			this.leftDone = left;
			this.rightDone = right;
		}
	}

	private static void simulateRecursion(Node root) {
		Pair rootp = new Pair(root, false, false, false);

		ArrayDeque<Pair> stack = new ArrayDeque<>();
		stack.push(rootp);

		while (!stack.isEmpty()) {
			Pair tosP = stack.peek();

			if (!tosP.leftDone) {
				tosP.leftDone = true;
				if (tosP.node.left != null) {
					Pair leftP = new Pair(tosP.node.left, false, false, false);
					stack.push(leftP);
				}
			} else if (!tosP.selfDone) {
				tosP.selfDone = true;
				System.out.print(tosP.node.data + " ");

			} else if (!tosP.rightDone) {
				tosP.rightDone = true;
				if (tosP.node.right != null) {
					Pair rightP = new Pair(tosP.node.right, false, false, false);
					stack.push(rightP);
				}
			} else {
				stack.pop();
			}

		}
	}
}
