package hro.infdev035.assignment2;

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

	public long getBefore() {
		return before.getTime();
	}

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
