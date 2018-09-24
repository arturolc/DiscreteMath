package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Project1 {
	List<Integer> universalSet = new ArrayList<>();
	List<Integer> subsetA = new ArrayList<>();
	List<Integer> subsetB = new ArrayList<>();
	
	Project1() {
		universalSet = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		subsetA = Arrays.asList(2, 4, 6, 8, 10, 1, 3);
		subsetB = Arrays.asList(1, 3, 5, 7, 9);
		
		Collections.sort(universalSet);
		Collections.sort(subsetA);
		Collections.sort(subsetB);
	}
	private List<Integer> getSubsetA() {
		return subsetA;
	}
	
	
	private List<Boolean> getSetArray(List<Integer> subset) {
	int index;
	List<Boolean> result = new ArrayList<>();
	for (int i = 0; i < universalSet.size(); i++) {
		index = Collections.binarySearch(subset, universalSet.get(i));
		
		if (index >= 0) result.add(true);
		else			result.add(false);
	}
	return result;		
	}
	
	private List<Integer> union() {
		int index;
		List<Integer> result = new ArrayList<>();
		
		for (int i = 0; i < subsetA.size(); i++) {
			index = Collections.binarySearch(subsetB, subsetA.get(i));
			if (index >= 0) result.add(subsetA.get(i));
		}
		return result;
	}
	
	private List<Integer> intersection() {
		int index;
		List<Integer> result = new ArrayList<>();
		
		if (subsetA.size() > subsetB.size()) {
			for (int i = 0; i < subsetA.size(); i++) {
				index = Collections.binarySearch(subsetB, subsetA.get(i));
				if (index >= 0)	result.add(index);
			}
		}
		else {
			for (int i = 0; i < subsetB.size(); i++) {
				index = Collections.binarySearch(subsetA, subsetB.get(i));
				if (index >= 0)	result.add(index);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Project1 p = new Project1();

		System.out.println("A = " + p.getSetArray(p.getSubsetA()));
		System.out.println("A union B" + p.getSetArray(p.union()));
		System.out.println("A intersection B" + p.getSetArray(p.intersection()));
		

	}
}
