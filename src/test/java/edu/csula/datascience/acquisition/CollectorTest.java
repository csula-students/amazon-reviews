package edu.csula.datascience.acquisition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A test case to show how to use Collector and Source
 */
public class CollectorTest {
	private Collector<SimpleModel, MockData> collector;
	private Source<MockData> source;

	@Before
	public void setup() {
		collector = new MockCollector();
		source = new MockSource();
	}

	@Test
	public void mungee() throws Exception {
		int i = 0;
		while (source.hasNext()) {
			SimpleModel cleaned = (SimpleModel) collector.mungee(source.next());
			if (i == 0){
				Assert.assertEquals(cleaned, null);
			}
			else{
				Assert.assertEquals(cleaned.getId(), "2");
				Assert.assertEquals(cleaned.getContent(), "content2");	
			}
			i++;
		}

	}
}