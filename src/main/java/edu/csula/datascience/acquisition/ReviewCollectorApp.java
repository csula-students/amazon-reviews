package edu.csula.datascience.acquisition;

public class ReviewCollectorApp {

	public static void main(String[] args) {
		ReviewSource source = new ReviewSource("C:/A/Big Data CS594/complete.json/complete.json");
		ReviewCollector collector = new ReviewCollector();
		Review r = null;
		int i = 0;
		while (source.hasNext()) {
			r = source.next();
			r = collector.mungee(r);
			collector.save(r);
			if(i++ % 10000 == 0) {
				System.out.println(i);
			}
		}
	}
}
