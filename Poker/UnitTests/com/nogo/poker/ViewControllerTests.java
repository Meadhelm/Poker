package com.nogo.poker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ViewControllerTests {

	public ViewController controller = new ViewController();

	@Test
	public void home() {
		String result = controller.home();
		assertEquals("home", result);
	}

}
