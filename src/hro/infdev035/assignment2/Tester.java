package hro.infdev035.assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that runs {@link Test}s repeatedly keeping track of execution time.
 */
public class Tester implements Runnable {
	private Test test;
	private int times;

	public Tester(Test test, int times) {
		this.test = test;
		this.times = times;
	}

	@Override
	public void run() {
		Result result = new Result();
		for(int i = 0; i < times; i++) {
			test.run();
			result.addAfter(test.getAfter());
			result.addBefore(test.getBefore());
		}
		System.out.println(result);
	}

	/**
	 * Helper class that can compute average execution time.
	 */
	private static class Result {
		private List<Long> before;
		private List<Long> after;
		private long totalBefore;
		private long totalAfter;

		public Result() {
			before = new ArrayList<>();
			after = new ArrayList<>();
		}

		public void addBefore(long before) {
			this.before.add(before);
			totalBefore += before;
		}

		public void addAfter(long after) {
			this.after.add(after);
			totalAfter += after;
		}

		private double getAverageAfter() {
			return totalAfter / (double)after.size();
		}

		private double getAverageBefore() {
			return totalBefore / (double)before.size();
		}

		@Override
		public String toString() {
			return new StringBuilder("Results:\n	avg. Before: ")
			.append(getAverageBefore())
			.append("ms\n	avg. After: ")
			.append(getAverageAfter())
			.append("ms\n	Before: ")
			.append(before.toString())
			.append("\n	After: ")
			.append(after.toString()).toString();
		}
	}
}
