package com.plivo.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer2;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;

public class TestListener implements IAnnotationTransformer2{

	@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		/*
		 * This will set data provider at run time with class.
		 * This listener should be set with in testng.xml
		 */
			annotation.setDataProvider("fileDataProvider");
			annotation.setDataProviderClass(com.plivo.util.FileDataProvider.class);			
	}

	@Override
	public void transform(IConfigurationAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {
		// TODO Auto-generated method stub	
	}
	
}