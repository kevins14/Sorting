package com.bridgephase.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bridgephase.store.interfaces.IInventory;

public class Inventory implements IInventory{
	
	ArrayList<Product> list = new ArrayList<Product>();
	
	@Override
	public void replenish(InputStream inputStream) {
		// TODO Auto-generated method stub
		
		// Using InputStreamReader make inputStream readable
		// then using BufferedReader to read texts from inputStream
		
		InputStreamReader tempRead = new InputStreamReader(inputStream);
		BufferedReader read = new BufferedReader(tempRead);
		
		try {
			String line = read.readLine();
			
			while(read.ready()) {
				
				line = read.readLine();
				String[] starray = line.split(",");
				boolean updated = false;
				
				BigDecimal valueWSP = new BigDecimal(starray[2]);
				BigDecimal valueREP = new BigDecimal(starray[3]);
				int valueQuan = Integer.parseInt(starray[4]);
				
				
				for(int i=0; i<list.size(); i++) {
					if(starray[0].equals(list.get(i).getUpc())) {
						list.get(i).setName(starray[1]);
						list.get(i).setWsPrice(valueWSP);
						list.get(i).setRePrice(valueREP);
						list.get(i).setQuantity(list.get(i).getQuantity()+valueQuan);
						updated = true;
					}
				}
				if (updated == false) {
					Product elem = new Product(starray[0], starray[1], valueWSP, valueREP, valueQuan);
					list.add(elem);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		
		return list;
	}
	
	public int getSize() {
		return list.size();
	}

}
