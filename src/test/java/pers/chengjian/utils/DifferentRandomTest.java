package pers.chengjian.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DifferentRandomTest {

	@Test
	public void testRandintList() {
		ArrayList<Integer> list = DifferentRandom.randintList(20, 60);
		assertEquals(20, list.size());
	}

}
