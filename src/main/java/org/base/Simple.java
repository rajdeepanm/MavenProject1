package org.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class Simple implements IAnnotationTransformer{

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstrutor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if(retry==null) {
			annotation.setRetryAnalyzer(FailedSample.class);
		}
	}
	
}
