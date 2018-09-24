package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		List<Integer> result = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		set.addAll(subsetA);
		set.addAll(subsetB);
		result.addAll(set);
		return result;
	}
	
	private List<Integer> intersection() {
		int index;
		List<Integer> result = new ArrayList<>();
		
		if (subsetA.size() > subsetB.size()) {
			for (int i = 0; i < subsetA.size(); i++) {
				index = Collections.binarySearch(subsetB, subsetA.get(i));
				if (index >= 0)	result.add(subsetA.get(i));
			}
		}
		else {
			for (int i = 0; i < subsetB.size(); i++) {
				index = Collections.binarySearch(subsetA, subsetB.get(i));
				if (index >= 0)	result.add(subsetB.get(i));
			}
		}
		return result;
	}
	
	private List<Integer> difference(List<Integer> subset) {
		List<Integer> other = new ArrayList<>();
		List<Integer> result = new ArrayList<>(subset);
		
		// Determine the order of difference
		if (result.equals(subsetA))	other = new ArrayList<>(subsetB);
		else						other = new ArrayList<>(subsetA);
		
		for (int i = 0; i < other.size(); i++) {
			if (Collections.binarySearch(result, other.get(i)) >= 0) {
				result.remove(Collections.binarySearch(result, other.get(i)));
				Collections.sort(result);
			}
		}
		return result;
	}
	
	private List<Integer> exclusiveOr() {
		List<Integer> result = union();
		List<Integer> intersection = intersection();
		
		for (int i = 0; i < intersection.size(); i++)
			result.remove(Collections.binarySearch(result, intersection.get(i)));
	
		return result;
	}
	
	public static void main(String[] args) {
		Project1 p = new Project1();

		System.out.println("A = " + p.getSetArray(p.getSubsetA()));
		System.out.println("A union B = " + p.getSetArray(p.union()));
		System.out.println("A intersection B = " + p.getSetArray(p.intersection()));
		System.out.println("A difference B = " + p.getSetArray(p.difference(p.getSubsetA())));
		System.out.println("A exclusiveOR B = " + p.getSetArray(p.exclusiveOr()));
		

	}
}
