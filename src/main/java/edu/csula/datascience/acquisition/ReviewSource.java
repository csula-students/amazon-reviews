package edu.csula.datascience.acquisition;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReviewSource implements Source<Review> {

	BufferedReader br;
	String nextLine;
	Gson gson;

	public ReviewSource(String sourceFile) {
		gson = new GsonBuilder().create();
		nextLine = "";
		try {
			br = new BufferedReader(new FileReader(sourceFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasNext() {
		return nextLine != null;
	}

	@Override
	public Review next() {
		try {
			nextLine = br.readLine();
			return gson.fromJson(nextLine, Review.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
