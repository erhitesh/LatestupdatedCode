package com.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation testAnnotation, @SuppressWarnings("rawtypes") Class testClass, @SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer analyzer = testAnnotation.getRetryAnalyzer();
		
		if (analyzer == null)
			testAnnotation.setRetryAnalyzer(Retry.class);
	}
}
