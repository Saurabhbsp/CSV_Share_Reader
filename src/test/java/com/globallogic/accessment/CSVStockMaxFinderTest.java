package com.globallogic.accessment;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class CSVStockMaxFinderTest {

	CSVStockMaxFinder finder = new CSVStockMaxFinder();
	File file = new File(getClass().getResource("/data.txt").getFile());
	
	Logger logger = Logger.getLogger(CSVStockMaxFinderTest.class.getName());
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMaxSharePrice() {
		String[][] data= {
				{"Year", "Month", "CompanyA", "CompanyB", "ComapanyC"},
				{"1990", "Jan", "100", "200", "300"},
				{"1991", "Feb", "400", "100", "200"},
				{"1992", "Mar", "99", "300", "100"}
		};
		MaxResult r1 = finder.getMaxSharePrice(data, 2);
		assertEquals(400, r1.getMaxShare());
		assertEquals("Feb", r1.getMonth());
		assertEquals("CompanyA", r1.getCompany());	
		
	}
	
	@Test
	public void testSplitByComma() {
		String test = "1,2,,,,,,     3,   4";
		String[] data = finder.splitByComma(test);
		assertEquals(data.length, 4);
		assertEquals(data[0], "1");
		assertEquals(data[1], "2");
		assertEquals(data[2], "3");
		assertEquals(data[3], "4");
		
	}
	
	@Test
	public void testGetData() {
		
		List<String> dataRows = new ArrayList<String>(){
			{
				add("1990, Jan, 100, 200, 300");
				add("1991, Feb, 400, 100, 200");
				add("1992, Mar, 99,   300,   ,,,,100");
				
			}
		};
		
		String[][] data= {
				{"1990", "Jan", "100", "200", "300"},
				{"1991", "Feb", "400", "100", "200"},
				{"1992", "Mar", "99", "300", "100"}
		};
		
		String[][] result = finder.getData(dataRows);
		
		assertEquals(result.length, data.length);
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				assertEquals(data[i][j], result[i][j]);
			}
		}
		
	}
	
	@Test
	public void tesGetMaxStockPerCompany() {
		List<MaxResult> results = finder.getMaxStockPerCompany(file);
		int[] maxStocks = {399, 567, 999};
		String[] years = {"1992", "1993", "1993"};
		String[] mons = {"Mar", "Apr", "Apr"};
		
		for(int i = 0; i < results.size(); i++) {
			assertEquals(results.get(i).getMaxShare(), maxStocks[i]);
			assertEquals(results.get(i).getMonth(), mons[i]);
			assertEquals(results.get(i).getYear(), years[i]);
		}
	}
	
	@Test
	public void display() {
		finder.displayMax(file);
	}

}
