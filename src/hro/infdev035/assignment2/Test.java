package hro.infdev035.assignment2;

/**
 * Class that runs <code>runTest</code> <code>amount</code> times before and after executing <code>createIndex</code>.
 */
public abstract class Test implements Runnable {
	private int amount;
	private Result before;
	private Result after;

	public Test(int amount) {
		this.amount = amount;
	}

	@Override
	public void run() {
		deleteIndex();
		before = new Result();
		for(int i = 0; i < amount; i++) {
			runTest();
		}
		before.stop();
		createIndex();
		after = new Result();
		for(int i = 0; i < amount; i++) {
			runTest();
		}
		after.stop();
	}

	/**
	 * @return The execution time in milliseconds before creating the index.
	 */
	public long getBefore() {
		return before.getTime();
	}

	/**
	 * @return The execution time in milliseconds after creating the index.
	 */
	public long getAfter() {
		return after.getTime();
	}

	@Override
	public String toString() {
		return new StringBuilder("	Results\n	Before: ").append(before.getTime()).append(" ms\n	After: ").append(after.getTime()).append(" ms").toString();
	}

	protected abstract void deleteIndex();

	protected abstract void createIndex();

	protected abstract void runTest();

	/**
	 * Redundant helper class to record execution time.
	 */
	private static class Result {
		private long start;
		private long time;

		public Result() {
			this.start = System.currentTimeMillis();
		}

		public void stop() {
			time = System.currentTimeMillis() - start;
		}

		public long getTime() {
			return time;
		}
	}
}
