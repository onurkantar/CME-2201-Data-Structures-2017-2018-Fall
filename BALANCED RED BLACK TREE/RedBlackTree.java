import java.util.ArrayList;

public class RedBlackTree<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private static boolean recursiveControl = false;

	@SuppressWarnings("rawtypes")
	ArrayList<Node> trace = null;

	@SuppressWarnings("rawtypes")
	public Node root;

	public boolean isEmpty() {
		return root == null;
	}

	@SuppressWarnings("rawtypes")
	private int Size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.size;
	}

	public int Size() {
		return Size(root);
	}

	public Value GetVal(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		return GetVal(root, key);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getDepth(Node node) throws NullPointerException { // returns true if right is heavy , returns false
																		// if left is
		// heavy , return null if balanced.Finds depth iteratively.
		Node temp;
		int rightDepth = 1;
		int leftDepth = 1;
		if (node.right != null) {
			temp = node.right;
		} else {
			temp = new Node(0, "0", RED, 1);
			rightDepth = 0;
		}
		while (temp.size != 1) {// right depth
			if (temp.right != null && temp.left != null) {
				if (temp.right.size > temp.left.size) {
					temp = temp.right;
					rightDepth++;
				} else if (temp.left.size > temp.right.size) {
					temp = temp.left;
					rightDepth++;
				} else {
					temp = temp.right;
					rightDepth++;
				}
			} else if (temp.right == null && temp.left != null) {
				temp = temp.left;
				rightDepth++;
			} else if (temp.right != null && temp.left == null) {
				temp = temp.right;
				rightDepth++;
			}
		}
		if (root.left != null) {
			temp = node.left;
		} else {
			temp = new Node(0, "0", RED, 1);
			leftDepth = 0;
		}

		while (temp.size != 1) { // left depth
			if (temp.right != null && temp.left != null) {
				if (temp.right.size > temp.left.size) {
					temp = temp.right;
					leftDepth++;
				} else if (temp.left.size > temp.right.size) {
					temp = temp.left;
					leftDepth++;
				} else {
					temp = temp.left;
					leftDepth++;
				}
			}

			else if (temp.right == null && temp.left != null) {
				temp = temp.left;
				leftDepth++;
			} else if (temp.right != null && temp.left == null) {
				temp = temp.right;
				leftDepth++;
			}

		}
		if (rightDepth - leftDepth > 1) {
			return true;
		} else if (rightDepth - leftDepth < -1) {
			return false;
		}
		NullPointerException ex = new NullPointerException(); // if balanced
		throw ex;
	}

	@SuppressWarnings("rawtypes")
	public void recursiveFind(Key key) { // Calls search method for tracing the node.

		Node node = root;
		trace = new ArrayList<Node>();

		try {
			search(node, key);
			recursiveControl = false;
			String color;
			if (trace.get(0).color == BLACK) {
				color = "BLACK";
			} else {
				color = "RED";
			}
			System.out.println("Search : " + trace.get(0).key);
			System.out.println("Value : " + trace.get(0).val.toString());
			System.out.println("Color : " + color);
			try {
				if (trace.get(1).color == BLACK) {
					color = "BLACK";
				} else {
					color = "RED";
				}
				System.out.println("Parent Key : " + trace.get(1).key);
				System.out.println("Parent Color : " + color);
				System.out.println("Parent Value : " + trace.get(1).val.toString());

				try {
					if (trace.get(2).right != null && trace.get(2).right.equals(trace.get(1))) {
						if (trace.get(2).left != null) {
							if (trace.get(2).left.color == BLACK) {
								color = "BLACK";
							} else {
								color = "RED";
							}
							System.out.println("Uncle Key : " + trace.get(2).left.key);
							System.out.println("Uncle Color : " + color);
							System.out.println("Uncle Value : " + trace.get(2).left.val.toString());
						}

						else {
							System.out.println("THERE IS NO UNCLE !!");
						}
					} else if (trace.get(2).left != null && trace.get(2).left.equals(trace.get(1))) {

						if (trace.get(2).right != null) {

							if (trace.get(2).right.color == BLACK) {
								color = "BLACK";
							} else {
								color = "RED";
							}

							System.out.println("Uncle Key : " + trace.get(2).right.key);
							System.out.println("Uncle Color : " + color);
							System.out.println("Uncle Value : " + trace.get(2).right.val.toString());
						}

						else {
							System.out.println("THERE IS NO UNCLE !!");
						}
					}
					if (trace.get(2).color == BLACK) {
						color = "BLACK";
					} else {
						color = "RED";
					}

					System.out.println("Grandparent Key : " + trace.get(2).key);
					System.out.println("Grandparent Color : " + color);
					System.out.println("Grandparent Value : " + trace.get(2).val.toString());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("NODE IS THE ROOT'S CHILD SO THERE IS NO GRANDPARENT !!!");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("NODE IS THE ROOT SO THERE IS NO PARENT OR GRANDPARENT !!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("---------------------------");
			System.out.println("Not FOUND !!!");
		}

	}

	@SuppressWarnings("rawtypes")
	public void search(Node node, Key key) { // if value is unique then change "key" to "value" this function will find
												// the node correctly.Recursively traces the path.

		if (node.key.equals(key)) {
			trace.add(node);
			recursiveControl = true;
			return;
		}

		if (node.left != null && !recursiveControl) {
			search(node.left, key);
		}

		if (node.right != null && !recursiveControl) {
			search(node.right, key);
		}
		trace.add(node);
		if (!recursiveControl) {
			trace.removeAll(trace);
		}
		if (trace.size() > 3) { // if this part is removed trace keeps whole path to root from node. We only
								// need parent and grandparent in this project.
			trace.remove(3);
		}

		return;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Value GetVal(Node node, Key key) {
		while (node != null) {
			int cmp = key.compareTo((Key) node.key);
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {
				return (Value) node.val;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void PutVal(Key key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("Argument is null");
		}
		if (val == null) {
			return;
		}

		root = PutVal(root, key, val);
		root.color = BLACK;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node PutVal(Node node, Key key, Value val) {
		if (node == null) {
			return new Node(key, val, RED, 1);
		}

		int cmp = key.compareTo((Key) node.key);

		if (cmp < 0) {
			node.left = PutVal(node.left, key, val);
		} else if (cmp > 0) {
			node.right = PutVal(node.right, key, val);
		} else {
			node.val = val;
		}

		// YOU SHOULD ENTER YOUR CONTROL STATEMENTS

		try {
			if (node.right.color == RED && node.right.right == null && node.right.left.color == RED) {
				node.right = RotateRight(node.right);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (node.left.color == RED && node.left.left == null && node.left.right.color == RED) {
				node = RotateLeft(node);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (node.right.color == RED && node.right.right.color == RED) {
				if (node.left == null) {
					node = RotateLeft(node);
				} else {
					FlipColors(node);
				}
				node.size = Size(node.left) + Size(node.right) + 1;

				return node;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (node.left.color == RED && node.left.left.color == RED) {
				if (node.right == null) {
					node = RotateRight(node);
				} else {
					FlipColors(node);
				}
				node.size = Size(node.left) + Size(node.right) + 1;

				return node;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			if (node.right.color == RED && node.right.right.color == RED && node.left.color == BLACK
					&& node.right.left.color == BLACK) {
				node = RotateLeft(node);
				node.left.color = RED;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (node.left.color == RED && node.left.left.color == RED && node.right.color == BLACK
					&& node.left.right.color == BLACK) {
				node = RotateRight(node);
				node.right.color = RED;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try { // This part does the rotating part due to depth of each side of node .
			if (getDepth(node)) {
				node = RotateLeft(node);
				FlipColors(node.left);

			} else {
				node = RotateRight(node);
				FlipColors(node.right);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		node.size = Size(node.left) + Size(node.right) + 1;

		return node;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node RotateRight(Node node) {
		Node temp = node.left;
		node.left = temp.right;
		temp.right = node;
		temp.color = temp.right.color;
		temp.right.color = RED;
		temp.size = node.size;
		node.size = Size(node.left) + Size(node.right) + 1;
		return temp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node RotateLeft(Node node) {
		Node temp = node.right;
		node.right = temp.left;
		temp.left = node;
		temp.color = temp.left.color;
		temp.left.color = RED;
		temp.size = node.size;
		node.size = Size(node.left) + Size(node.right) + 1;
		return temp;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void FlipColors(Node node) {
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}

}
