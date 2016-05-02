package edu.csula.datascience.acquisition;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * A mock source to provide data
 */
public class MockSource implements Source<MockData> {
	private int index = 0;
	List<MockData> list = Lists.newArrayList(new MockData("1", null), new MockData("2", "content2"));

	@Override
	public boolean hasNext() {
		return index < 2;
	}

	@Override
	public MockData next() {
		return list.get(index++);
	}

}
