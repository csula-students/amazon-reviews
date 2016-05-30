package edu.csula.datascience.acquisition;

public class ReviewCollectorApp {

	public static void main(String[] args) {
		ReviewSource source = new ReviewSource("C:/A/Big Data CS594/complete.json/complete.json");
		ReviewCollector collector = new ReviewCollector();
		Review r = null;
		double start = System.currentTimeMillis();
		int i = 0;
		while (source.hasNext()) {
			r = source.next();
			r = collector.mungee(r);
			collector.save(r);
			if(i++ % 100000 == 0) {
				System.out.println(i);
			}
		}
		double time = System.currentTimeMillis() - start;
		System.out.println(i + " elements added");
		System.out.println(time/1000 + " seconds");
	}
}
