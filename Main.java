import java.util.*; 

class Order{
	int noOfItems;
	String itemName;
	float itemPrice;
	float itemIntr;
	float totalAmount;

	Order(
		int noOfItems, 
		String itemName, 
		float itemPrice, 
		float itemIntr,
		float totalAmount){
			this.noOfItems = noOfItems;
			this.itemName = itemName;
			this.itemPrice = itemPrice;
			this.itemIntr = itemIntr;
			this.totalAmount = totalAmount;
		}
}

class OrderList{
	ArrayList<Order> orderList1;
	float totalInterest;
	float totalAmount;
	OrderList(
		ArrayList<Order> orderList1, 
		float totalInterest, 
		float totalAmount){
			this.orderList1 = orderList1;
			this.totalInterest = totalInterest;
			this.totalAmount = totalAmount;
	}
}

public class Main {
	public static String getSlice(
		String[] array, 
		int startIndex, 
		int endIndex){
			String[] slicedArray = new String[endIndex - startIndex];   
        	for (int i = 0; i < slicedArray.length; i++) 
				slicedArray[i] = array[startIndex + i];   
        	return String.join(" ",slicedArray);
		}

	public static OrderList loadDataIntoOrderList(){
		// var init 
		ArrayList<String> taxException = new ArrayList<String>(
			Arrays.asList("book", "chocolate","chocolates", "pills"));
		ArrayList<Order> orders = new ArrayList<Order>();
		float ordersInterest = 0.0f;
		float totalOrdersAmount = 0.0f;
		Scanner myObj = new Scanner(System.in);
		//set user input
		System.out.println("Please enter the Item");
		while(myObj.hasNextLine()) 
		{ 
			Boolean isTaxExceptionLocal = true;
			Boolean isImported = false;
			float orderInterest = 0.0f;
			float totalOrderAmount = 0.0f;

			String l1 = myObj.nextLine();
			if(l1.isEmpty() || l1.isBlank()) break;
			String[] words = l1.split("\\s");
			//Check on tax expence and import
			for(String word : words) {
				if(taxException.contains(word)) isTaxExceptionLocal = false;
				if(word.contains("imported")) isImported = true;
			}

			orderInterest = isTaxExceptionLocal 
								?  Float.parseFloat(words[words.length - 1]) 
									* 0.1f
								: 0.0f;
			
			if(isImported) 
				orderInterest = orderInterest 
				+ (Float.parseFloat(words[words.length - 1]) * 0.05f ) ;			
			orderInterest = orderInterest*Integer.parseInt(words[0]);
			orderInterest = Math.round(orderInterest * 20.0) / 20.0f;
			totalOrderAmount = Float.parseFloat(words[words.length - 1]) 
								* Integer.parseInt(words[0]);
			//creating order object
			Order orderItem = new Order(
				Integer.parseInt(words[0]), 
				getSlice(words, 1,words.length - 2  ),
				totalOrderAmount,
				orderInterest,
				totalOrderAmount + orderInterest
				);

			ordersInterest += orderInterest;
			totalOrdersAmount += totalOrderAmount + orderInterest;
			orders.add(orderItem);
		}  
		return  new OrderList(orders, ordersInterest,totalOrdersAmount);
	}
	
	public static void displayOrderList(OrderList ol){
		System.out.println("##########################");
		System.out.println("       Order List         ");
		System.out.println("##########################");
		//looping on orderlist
		for(Order order1 : ol.orderList1){
			System.out.println(order1.noOfItems+ " " + order1.itemName + ": " + order1.totalAmount);
		}
		//display sales taxes and total
		System.out.println("Sales Taxes: "+ ol.totalInterest);
		System.out.println("Total: "+ ol.totalAmount);
	}
	 public static void main(String[] args) {
		 //Loading data into order list
		 OrderList ol =loadDataIntoOrderList();
		 //Display order
		 displayOrderList(ol);
	}
}

