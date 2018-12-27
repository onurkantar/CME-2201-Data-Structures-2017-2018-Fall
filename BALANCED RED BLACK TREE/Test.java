import java.util.Scanner;

public class Test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		RedBlackTree<Integer, String> tree = new RedBlackTree<Integer, String>();
		boolean flag = false;
		boolean addable = false;

		Scanner scanner = new Scanner(System.in);
		int selected = 0;

			while (true) {
				System.out.println("---------------------------");
				System.out.println("Please select an option");
				System.out.println("1--> Add new node(Key&Value)");
				System.out.println("2--> Search Node(Key)");
				System.out.println("3--> Exit");
				System.out.print("Option : ");

				try {
					selected = scanner.nextInt();
				} catch (Exception e) {
					// TODO: handle exception7
					System.out.println("Please enter a number");
					selected = 4;
				}

				switch (selected) {
				case 0:
					break;
				case 1:
					System.out.println(" ");
					System.out.print("Please Enter The Key : ");
					int key = 0;
					try {
						key = scanner.nextInt();
						addable = true;
					} catch (Exception e) {
						// TODO: handle exception
						System.out.print("Please enter an integer");
					}
					String value = null;
					System.out.print("Please enter your value : ");
					try {
						value = scanner.next();
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (value == null) {
						System.out.println("Please enter a value !!");
						break;
					} else {
						if (addable) {
							tree.PutVal(key, value);
						}
					}
					addable = false;
					break;
				case 2:
					key = Integer.MAX_VALUE;
					System.out.print("Please enter key : ");
					key = scanner.nextInt();
					tree.recursiveFind(key);
					break;

				case 3:
					flag = true;
					break;

				case 4:
					System.out.println("I wish you had typed an integer so good-bye !!!");
					flag = true;
					System.out.println("Shutting down ...");
					break;
				default:
					break;
				}

				if (flag) {
					break;
				}

			}

	}

}