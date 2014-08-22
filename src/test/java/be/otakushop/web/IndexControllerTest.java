package be.otakushop.web;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import be.otakushop.web.IndexController;

public class IndexControllerTest {
	private IndexController indexController;
	
	@Before
	public void setUp() {
		indexController = new IndexController();
	}
	
	@Test
	public void indexActiveertJuisteView() {
		Assert.assertEquals("index", indexController.index().getViewName());
	}
}
