package com.bridgephase.store;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;

import com.bridgephase.store.interfaces.IInventory;

public class CashRegister {
	
	IInventory inventory;
	
	IInventory customer;
	
	BigDecimal cashAmount;
	
	//Total products bought
	int counter;
	//Individual product quantity
	int quantity;
	String recipt;
	//Total price
	BigDecimal total;
	
	//constructor
	public CashRegister(IInventory inventory2) {
		this.inventory = inventory2;
	}
	
	// will setup the CashRegister 
	// to create a new transaction which 
	// means that a new customer is cheking out
	public void beginTransaction() {
		
		counter = 0;
		quantity = 0;
		recipt = "";
		total = BigDecimal.ZERO;
		
		customer = new Inventory();
		String myString = "upc,name,wholesalePrice,retailPrice,quantity\n" + 
				"A123,null,0,0,3\n" + 
				"C123,null,0,0,2\n" +
				"B234,null,0,0,12";
		InputStream input = new ByteArrayInputStream( myString.getBytes() );
		customer.replenish(input);
		this.cashAmount = BigDecimal.ZERO;
		
	}
	
	// Need to modify
	public boolean scan(String upc) {
		
		try {
			
			for (int i = 0; i< inventory.getSize(); i++) {
				
				if (inventory.list().get(i).getUpc().equals(upc) && inventory.list().get(i).getQuantity() > 0) {
					
					BigDecimal mul = BigDecimal.ZERO;
					mul = inventory.list().get(i).getRePrice().multiply(new BigDecimal(quantity));
					//System.out.println(mul);
					total = total.add(mul);
					recipt += quantity + " " + inventory.list().get(i).getName() + " @ " + inventory.list().get(i).getRePrice() + ": " + mul + "\n";
					
					return true;
				}
			}
		}
		catch (Exception e) {
			
			System.out.println("Please call the beginTransaction first!!!!");
		}
		
		
		return false;
	}
	
	
	public BigDecimal getTotal() {
		//System.out.println(customer.size());
		
		
		for(int i=0; i< customer.getSize(); i++) {
			
			quantity = customer.list().get(i).getQuantity();
			counter += quantity;
			scan(customer.list().get(i).getUpc());
		}
		
		return total;
	}
	
	public BigDecimal pay(BigDecimal cashAmount) {
		
		int check = cashAmount.compareTo(total);
		BigDecimal finalAmount = BigDecimal.ZERO;
		this.cashAmount = cashAmount;
		
		if (check == 1) {
			finalAmount = cashAmount.subtract(total);
		} else {
			System.out.println("Amout you entered in less than total amount.");
		}
		return finalAmount;
	}
	
	/*
	 * OutputStream did not work, so I print out in String
	 */
	public void printReceipt(OutputStream os) {
		
		// get Total has global variable
		getTotal();
		
		String setUp = "BridgePhase Convenience Store\n" + "-----------------------------\n" + 
						"Total Products Bought: " + counter + "\n\n" + 
						recipt + 
						"-----------------------------\n" + 
						"Total: $" + total + 
						"\nPaid: $" + this.cashAmount +
						"\nChange: $" + pay(this.cashAmount) + 
						"\n-----------------------------\n";
		System.out.println(setUp);
		System.out.println("PROBLEM");
		
		try {
			os.write(setUp.getBytes("UTF-8"));
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
