package com.plivo.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestMethodFinder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



public class FileDataProvider {
		
	@DataProvider
	public static Iterator<Object[]> fileDataProvider(ITestContext context) {
		
		List<Object[]> testData = null;	
		String inputFile = context.getCurrentXmlTest().getParameter("filePath");
		if(inputFile==null){
			throw new RuntimeException("Provide data file path for running test");
		}
		testData = FileDataProvider.getFileContentList(inputFile);
		
		// return the iterator - testng will initialize the test class and calls
		// the test method with each of the content of this iterator.
		return testData.iterator();
	}
	
	private static List<Object[]> getFileContentList(String filenamePath) {
		InputStream is;
		List<Object[]> lines = new ArrayList<Object[]>();
		try {
			is = new FileInputStream(new File(filenamePath));
			DataInputStream in = new DataInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				lines.add(new Object[] { strLine });
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
}
