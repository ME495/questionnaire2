package pers.chengjian.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Timer;

public class DifferentRandom {
	
	static Random rand = new Random(System.currentTimeMillis());
	
	public static ArrayList<Integer> randintList(int num, int size) {
		if (num > size) {
			return null;
		}
		ArrayList<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for(int i=0;i<num;++i) {
			int x;
			while (true) {
				x = rand.nextInt(size)+1;
				if (!set.contains(x)) {
					set.add(x);
					list.add(x);
					break;
				}
			}
		}
		return list;
	}
}
