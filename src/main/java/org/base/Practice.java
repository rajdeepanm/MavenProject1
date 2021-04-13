package org.base;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Practice extends LibGlobal{
	@Test
	private void tc0() {
		System.out.println("method 1");
	}
	@Test
	private void tc1() {
		System.out.println("method 2");
		

	}
	@Test
	private void tc2() {
		System.out.println("method 3");
		Assert.assertTrue(true);
		

	}
	@Test
	private void tc3() {
		System.out.println("method 4");
		

	}
	@Test
	private void tc4() {
		System.out.println("method 5");
		

	}
	
	
	
	
	
	
}
