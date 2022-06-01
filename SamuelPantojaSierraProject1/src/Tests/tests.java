//Samuel Pantoja Sierra
package Tests;
import Methods.methods;

public class tests {

	public static void main(String[] args) {
		test(50, 50, 50, 50);
		test(100, 200, 150, 200);
		test(800, 1200, 650, 700);
		test(1, 1, 1, 1);

	}
	
	public static void test(int laptops, int mice, int desktops, int LCDs) {
		methods.app(laptops, mice, desktops, LCDs);
	}

}
