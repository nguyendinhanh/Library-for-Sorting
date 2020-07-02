import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookListSort {

	public static void main(String[] args) {
		String text;
		LinkedList books = new LinkedList();
		Node bk;
		File diskfile = new File("book.txt");
		Scanner bookfile = null;
		try {
			bookfile = new Scanner(diskfile);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the Book file!");
			System.exit(1);
		}
		text = bookfile.nextLine();
		while (bookfile.hasNext()) {
			text = bookfile.nextLine();
			bk = new Node(text);
			books.insertRear(bk);
			System.out.println(text);
		}
		System.out.println("/nThe Linked List\n");
		books.print();
	}

}
