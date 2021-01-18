package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entities.enums.OrderStatus;

public class Order 
{
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	private Date moment;
	private OrderStatus status;
	private Client client;
	private List<OrderItem> order = new ArrayList<>();
	
	public Order(Date moment, OrderStatus status, Client client) 
	{
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() 
	{
		return moment;
	}
	public void setMoment(Date moment) 
	{
		this.moment = moment;
	}
	public OrderStatus getStatus() 
	{
		return status;
	}
	public void setStatus(OrderStatus status) 
	{
		this.status = status;
	}
	public Client getClient() 
	{
		return client;
	}
	public void setClient(Client client) 
	{
		this.client = client;
	}
	public List<OrderItem> getOrder() {
		return order;
	}
	
	public void addItem(OrderItem item)
	{
		order.add(item);
	}
	public void removeItem(OrderItem item)
	{
		order.remove(item);
	}
	
	public double total()
	{
		double totalValue = 0;
		for (OrderItem list : order)
		{
			totalValue += list.subTotal();
		}
		return totalValue;
	}

	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("ORDER SUMMARY:\n");
		sb.append("Order Moment: ");
		sb.append(sdf.format(moment));
		sb.append("\nOrder status: ");
		sb.append(status);
		sb.append("\nClient: ");
		sb.append(client.getName());
		sb.append(" (");
		sb.append(sdf2.format(client.getBirthDate()));
		sb.append(") - ");
		sb.append(client.getEmail());
		sb.append("\nOrder items:\n");
		
		for (OrderItem oi : order)
		{
			sb.append(oi.getProduct().getName());
			sb.append(", ");
			sb.append(oi.getPrice());
			sb.append(", Quantity: ");
			sb.append(oi.getQuantity());
			sb.append(", Subtotal: ");
			sb.append(oi.subTotal());
			sb.append("\n");
		}
		
		sb.append("Total price: $");
		sb.append(String.format("%.2f", this.total()));
		
		return sb.toString();
	}
	
	
}
