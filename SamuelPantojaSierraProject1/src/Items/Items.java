//Samuel Pantoja Sierra
package Items;
import java.util.HashMap;
import Methods.methods;

public class Items {
	
	public static HashMap<String, HashMap<String, Double>> createItems(){
		HashMap<String, HashMap<String, Double>> Products = new HashMap<String, HashMap<String, Double>>();

		methods.itemMapping(Products, "Laptops", 0.15, 6.5);
		methods.itemMapping(Products, "Mice", 0.018, 0.2);
		methods.itemMapping(Products, "Desktops", 0.75, 20);
		methods.itemMapping(Products, "LCDs", 1.344, 2.6);
		
		
		return Products;
	}
	
	public static HashMap<String, Double> createContainers(){
		HashMap<String, Double> Containers = new HashMap<String, Double>();
		
		Containers.put("Small Container", 2.59 * 2.43 * 6.06);
		Containers.put("Big Container", 2.59 * 2.43 * 12.01);
		
		return Containers;
	}		
		
		//fill the small container with low density and big container with high density
		//create counters for the products
		//create a map where you add a container and it's weight
		//calculate the total volume of each item and make it divisible by a whole number
		//pack the numbers by groups, like 20 laptops, 50 mouses at a time 
		
		//try and get the volume for 100 laps
		//System.out.println(methods.orderVolume(methods.readOrder(100, 0, 0, 0), Products));
		
		//same but for weight
		//System.out.println(methods.orderWeight(methods.readOrder(100, 0, 0, 0), Products));
		
		//HashMap<String, Integer> order = methods.readOrder(100, 99, 0, 0);
		//methods.substractItems(order, "Mice");
		//System.out.println(order);

}
