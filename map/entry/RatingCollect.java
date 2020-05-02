package com.map.entry;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Utility {
	private int count;
	private double avgRating;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	@Override
	public String toString() {
		return "Utility [count=" + count + ", avgRating=" + avgRating + "]";
	}

}

public class RatingCollect {
	/*
	 * static int count=0; double avgrating=0;
	 */

	
	//This map object to store key value pairs
	static Map<String, Utility> movieStore = new HashMap<String, Utility>();

	//Concreate method to store data
	public static void putNewMovie(String movieName, Double movieRating) {

		
		//In order to iterate over a map getting entry set
		Set<Map.Entry<String, Utility>> s = movieStore.entrySet();

		// If there is new record in the map make a entry of it
		if (s.isEmpty()) {
			Utility ut = new Utility();

			ut.setCount(1);
			// double avg = ut.getAvgRating();
			ut.setAvgRating(movieRating);
			movieStore.put(movieName, ut);
		} else {

			
			//If the map is not empty update the existing movie details like count and average
			for (Map.Entry<String, Utility> it : s) {
				for (int i = 0; i < movieStore.size(); i++) {
					if (it.getKey().equals(movieName)) {
						Utility ut = it.getValue();
						int count = ut.getCount();
						ut.setCount(++count);
						double rating = ut.getAvgRating();
						rating += movieRating;
						ut.setAvgRating(rating);
						movieStore.replace(movieName, ut);
						return;
					}
				}
			}
			
			//if the there is new movie to be recorded then make it entry
			Utility ut = new Utility();

			ut.setCount(1);
			// double avg = ut.getAvgRating();
			ut.setAvgRating(movieRating);
			movieStore.put(movieName, ut);
		}
	}
	
	
	//Concreate method to get average rating for a particular movie
	public static double getAvgRating(String movieName) {
		Set<Map.Entry<String, Utility>> s = movieStore.entrySet();
		for (Map.Entry<String, Utility> it : s) {
			for (int i = 0; i < s.size(); i++) {
				if (it.getKey().equals(movieName)) {
					Utility ut = it.getValue();
					return ut.getAvgRating() / ut.getCount();
				}
			}
		}
		return 0;
	}

	
	//Concreate method to get count of the movies
	public static int getMovieCount(String movieName) {
		Set<Map.Entry<String, Utility>> s = movieStore.entrySet();
		for (Map.Entry<String, Utility> it : s) {
			for (int i = 0; i < s.size(); i++) {
				if (it.getKey().equals(movieName)) {
					Utility ut = it.getValue();
					return ut.getCount();
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// To Read number of lines of input
		System.out.println("Enter the number of lines");
		int numLines = Integer.parseInt(scan.nextLine());

		int current = 0;
		
		//While loop to iterate line by line
		while (current++ < numLines) {
			
			//To store movie name temporary and call the put method
			Set<String> movies = new TreeSet<>();
			
			//This is to read the first line 
			System.out.println("Enter the line of movies and ratings");
			String input = scan.nextLine();
			
			//Separate the given line by comma in order to find movie name and its corresponding ratings
			//Later convert into array and iterate one by one and store the values
			String[] token = input.split(",");
			for (int i = 0; i < token.length; i++) {
				String[] movietoken = token[i].split(" ");
				String movieName = movietoken[0];
				movies.add(movieName);
				Double movieRating = Double.parseDouble(movietoken[1]);
				putNewMovie(movieName, movieRating);
			}
			
			//for each loop to print the data
			for (String movie : movies) {
				System.out.println(String.format("%s %.4f %d", movie, getAvgRating(movie), getMovieCount(movie)));
			}
		}
		scan.close();
	}
}
