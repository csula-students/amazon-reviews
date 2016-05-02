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

	// public void readStream() {
	// try {
	// // JsonReader reader = new JsonReader(new InputStreamReader(stream,
	// // "UTF-8"));
	// // Gson gson = new GsonBuilder().create();
	// byte[] bytes = new byte[400];
	// stream.read(bytes, 0, 400);
	// System.out.println(new String(bytes, StandardCharsets.UTF_8));
	// return;/*
	// * // Read file in stream mode reader.beginObject(); while
	// * (reader.hasNext()) { // Read data into object model
	// * Review review = gson.fromJson(reader, Review.class);
	// * System.out.println(review.getReviewText()); break; }
	// * reader.close();
	// */
	// } catch (UnsupportedEncodingException ex) {
	// Logger.getLogger(ReviewSource.class.getName()).log(Level.SEVERE, null,
	// ex);
	// } catch (IOException ex) {
	// Logger.getLogger(ReviewSource.class.getName()).log(Level.SEVERE, null,
	// ex);
	// }
	// }

}
