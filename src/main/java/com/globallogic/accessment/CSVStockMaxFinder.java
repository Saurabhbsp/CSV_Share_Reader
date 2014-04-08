package com.globallogic.accessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CSVStockMaxFinder {
	
	Logger logger = Logger.getLogger(CSVStockMaxFinder.class.getName());
	
	public void displayMax(File file){
		
		StringBuilder msg = new StringBuilder();
		List<MaxResult> results = getMaxStockPerCompany(file);
		for(MaxResult result: results) {
			msg.append(result.toString()).append("\n");
		}
		
		logger.info(msg.toString());
	}
	
	public List<MaxResult> getMaxStockPerCompany(File file) {
		String[][] data = getData(file);
		return getMaxStockPerCompany(data);
	}
	
	public List<MaxResult> getMaxStockPerCompany(String[][] data) {
		int colCount = data[0].length;
		List<MaxResult> results =  new ArrayList<MaxResult>(colCount);
		for(int i = 2; i < colCount; i++) {
			results.add(getMaxSharePrice(data, i));
		}
		return results;
	}

	public MaxResult getMaxSharePrice(String[][] data, int colIndex) {
		int max = 0;
		String colName = data[0][colIndex];
		int maxRowIndex = -1;
		for(int i = 1; i < data.length; i++) {
			int temp  = Integer.parseInt(data[i][colIndex]);
			if(temp > max) {
				max = temp;
				maxRowIndex = i;
			}
		}
		MaxResult r = new MaxResult(data[maxRowIndex][0], data[maxRowIndex][1], max, colName);
		return r;
	}
	
	
	public List<String> getDataRows(File file) {
		List<String> dataRows = new ArrayList<String>(100);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while(true) {
				String row = reader.readLine();
				if(row == null) {
					break;
				}
				dataRows.add(row);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataRows;
	}
	
	public String[][] getData(List<String> dataRows) {
		String[][] data = null;
		for(int i = 0; i < dataRows.size(); i++) {
			String row = dataRows.get(i);
			String[] cols = splitByComma(row);
			if(data == null) {
				data = new String[dataRows.size()][cols.length];
			}
			data[i] = cols;
		}
		return data;
	}
	
	public String[][] getData(File file) {
		return getData(getDataRows(file));
	}
	
	public String[] splitByComma(String msg) {
		return msg.split("[\\s,;]+");
	}
}
