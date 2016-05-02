package edu.csula.datascience.acquisition;

import java.util.Collection;

/**
 * A mock implementation of collector for testing
 */
public class MockCollector implements Collector<SimpleModel, MockData> {
	@Override
	public Collection<SimpleModel> mungee(Collection<MockData> src) {
		return null;
	}

	@Override
	public void save(Collection<SimpleModel> data) {
	}

	@Override
	public SimpleModel mungee(MockData src) {
		return src.getContent() != null ? SimpleModel.build(src) : null;
	}

	@Override
	public void save(SimpleModel data) {
	}
}
