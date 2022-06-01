//Samuel Pantoja Sierra
package Methods;
import java.util.HashMap;
import Items.Items;


public class methods {
	//a list of the items mapped to their respective volume and weight is generated
	public static void itemMapping(HashMap <String, HashMap<String, Double>> map, String item, double volume, double weight){
		map.put(item, atributesMapping(volume, weight));
	}

	//this creates a map of the attributes for each item so I don't have to manually create a map for each
	public static HashMap<String, Double> atributesMapping(double volume, double weight){
		HashMap<String, Double> atributes = new HashMap<String, Double>();
		atributes.put("Volume", volume);
		atributes.put("Weight", weight);
		
		return atributes;
	}
	
	//this generates a map of the items and their amount
	public static HashMap<String, Integer> readOrder(int laptops, int mice, int desktops, int LCDs){
		HashMap<String, Integer> order = new HashMap<String, Integer>();
		order.put("Laptops", laptops);
		order.put("Mice", mice);
		order.put("Desktops", desktops);
		order.put("LCDs", LCDs);
		return order;
	}
	
	//using the order and items maps, this method calculates the total order volume
	//by multiplying the amount and the individual volume of each item
	public static double orderVolume(HashMap<String, Integer> order, HashMap<String, HashMap<String, Double>> products) {
		//products is a variable name for the map of items
		double totalVolume = 0;
		for(String key : order.keySet()) {
			double amount = order.get(key);
			double individualVolume = products.get(key).get("Volume");
			totalVolume += (amount * individualVolume);
		}
		return totalVolume;
	}
	
	//this generates a map of the containers used as 2 small light containers are more 
	//expensive than a large container, and the large containers is almost twice 
	//the volume, is cheaper to use just a small container for the remaining
	//also, the method is designed to pack the most dense items first on the big
	//containers to try and make the small container less than 500kg.
	//it generates a map of the big and small containers with the amount of
	//containers used and the total weight of big containers and the small container
	public static HashMap<String, HashMap<String, Double>> usedContainers(HashMap<String, Integer> order, double orderVolume, 
																			HashMap<String, Double> containers, 
																			HashMap<String, HashMap<String, Double>> items) {
		
		HashMap<String, HashMap<String, Double>> containersInfo = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, Double> atributesBig = new HashMap<String, Double>();
		HashMap<String, Double> atributesSmall = new HashMap<String, Double>();
		
		atributesBig.put("Amount", 0.0);
		atributesBig.put("Weight", 0.0);
		
		atributesSmall.put("Amount", 0.0);
		atributesSmall.put("Weight", 0.0);
		
		containersInfo.put("Big Container", atributesBig);
		containersInfo.put("Small Container", atributesSmall);
		
		double laptopVolume = items.get("Laptops").get("Volume");
		double laptopWeight = items.get("Laptops").get("Weight");
		
		double mouseVolume = items.get("Mice").get("Volume");
		double mouseWeight = items.get("Mice").get("Weight");
		
		double desktopVolume = items.get("Desktops").get("Volume");
		double desktopWeight = items.get("Desktops").get("Weight");
		
		double LCDVolume = items.get("LCDs").get("Volume");
		double LCDWeight = items.get("LCDs").get("Weight");
		
		while(orderVolume > 0) {
			//this part of the code adds elements to a big container until it's filled up, if the remaining
			//volume of the order is greater than a small container, adds up another big container, otherwise
			//it adds a small container and put the additional items in there
			if (orderVolume > containers.get("Small Container")) {
				//this one adds a big container
				atributesBig.put("Amount", atributesBig.get("Amount") + 1);
				double tempVol = 0;
				double tempWeight = 0;
				//the following while loops are in most dense to less dense elements to try and get the lightest small container
				while(tempVol < (containers.get("Big Container") + laptopVolume) && !order.get("Laptops").equals(0) ) {
					order.replace("Laptops", order.get("Laptops") - 1);
					tempVol = ((tempVol * 100) + (laptopVolume * 100)) / 100;
					//yeah, apparently java doesn't know how to subtract some decimals
					//and took me like an hour to find out and a weekend to debug
					orderVolume = ((orderVolume * 10000000) - (laptopVolume * 10000000)) / 10000000;
					//I tried other ways but resolved it to leave it like this, it works
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += laptopWeight;
				}
				
				while(tempVol < (containers.get("Big Container") + desktopVolume) && !order.get("Desktops").equals(0) ) {
					order.replace("Desktops", order.get("Desktops") - 1);
					tempVol += desktopVolume;
					orderVolume = ((orderVolume * 10000000) - (desktopVolume * 10000000)) / 10000000;
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += desktopWeight;
				}
				while(tempVol < (containers.get("Big Container") + mouseVolume) && !order.get("Mice").equals(0) ) {
					order.replace("Mice", order.get("Mice") - 1);
					tempVol += mouseVolume;
					orderVolume = ((orderVolume * 10000000) - (mouseVolume * 10000000)) / 10000000;
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += mouseWeight;
				}
				while(tempVol < (containers.get("Big Container") + LCDVolume) && !order.get("LCDs").equals(0) ) {
					order.replace("LCDs", order.get("LCDs") - 1);
					tempVol += LCDVolume;
					orderVolume = ((orderVolume * 10000000) - (LCDVolume * 10000000)) / 10000000;
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += LCDWeight;
				}
				atributesBig.put("Weight", atributesBig.get("Weight") + tempWeight);
				
			}
			else {
				//this one adds a small container
				atributesSmall.put("Amount", atributesSmall.get("Amount") + 1);
				double tempVol = 0;
				double tempWeight = 0;
				while(tempVol < (containers.get("Small Container") + laptopVolume) && !order.get("Laptops").equals(0) ) {
					order.replace("Laptops", order.get("Laptops") - 1);
					tempVol = ((tempVol*100) + (laptopVolume * 100))/ 100;
					orderVolume = ((orderVolume * 10000000) - (laptopVolume * 10000000)) / 10000000;
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += laptopWeight;
				}
				
				while(tempVol < (containers.get("Small Container") + desktopVolume) && !order.get("Desktops").equals(0) ) {
					order.replace("Desktops", order.get("Desktops") - 1);
					tempVol += desktopVolume;
					orderVolume = ((orderVolume * 10000000) - (desktopVolume * 10000000)) / 10000000;
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += desktopWeight;
				}
				while(tempVol < (containers.get("Small Container") + mouseVolume) && !order.get("Mice").equals(0) ) {
					order.replace("Mice", order.get("Mice") - 1);
					tempVol += mouseVolume;
					orderVolume = ((orderVolume * 10000000) - (mouseVolume * 10000000)) / 10000000;
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += mouseWeight;
				}
				while(tempVol < (containers.get("Small Container") + LCDVolume) && !order.get("LCDs").equals(0) ) {
					order.replace("LCDs", order.get("LCDs") - 1);
					tempVol += LCDVolume;
					orderVolume = ((orderVolume * 1000000000) - (LCDVolume * 1000000000)) / 1000000000;
					if(orderVolume < mouseVolume) {
						orderVolume = 0;
					}
					tempWeight += LCDWeight;
					
				}
				
				atributesSmall.put("Weight", atributesSmall.get("Weight") + tempWeight);
			}
		}
		return containersInfo;
	}
	
	//this calculates the cost of the order according to the amount of big containers and the weight of the small one
	public static int orderCost(HashMap<String, HashMap<String, Double>> containers){
		double bigContainers = containers.get("Big Container").get("Amount");
		double smallWeight = containers.get("Small Container").get("Weight");
		int smallCost = (smallWeight < 500) ? 1000 : 1200; 

		return (int)(bigContainers * 1800) + smallCost;
	}
	
	public static void app(int laptops, int mice, int desktops, int LCDs) {
		HashMap<String, HashMap<String, Double>> itemsMap = Items.createItems();
		HashMap<String, Double> containersMap = Items.createContainers();
		HashMap<String, Integer> order = readOrder(laptops, mice, desktops, LCDs);
		double totalVolume = orderVolume(order, itemsMap);
		HashMap<String, HashMap<String, Double>> containersUsed = usedContainers(order, totalVolume, containersMap, itemsMap);
		int totalCost = orderCost(containersUsed);
		System.out.println("The best shipping method would be to use:");
		System.out.println("Big Containers: " + containersUsed.get("Big Container").get("Amount"));
		System.out.println("Small Containers: " + containersUsed.get("Small Container").get("Amount"));
		System.out.println("That would have a total cost of: €" + totalCost);
		System.out.println("---------------------------------------------------------------------------------");
	}
}
