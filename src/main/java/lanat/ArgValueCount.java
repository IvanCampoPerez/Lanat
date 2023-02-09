package lanat;


import org.jetbrains.annotations.NotNull;

/**
 * Used to specify the number values an argument may accept. This is essentially a classic range,
 * but if only one value is specified, both min and max will have that value.
 */
public class ArgValueCount {
	public static final ArgValueCount ANY = new ArgValueCount(0, -1);
	public static final ArgValueCount AT_LEAST_ONE = new ArgValueCount(1, -1);
	public static final ArgValueCount NONE = new ArgValueCount(0);
	public static final ArgValueCount ONE = new ArgValueCount(1);

	public final short min, max;

	public ArgValueCount(int min, int max) {
		if (min < -1 || max < -1)
			throw new IllegalArgumentException("min and max values can only be positive, or -1 for any");
		if ((min != -1 && max != -1) && (min > max))
			throw new IllegalArgumentException("min value cannot be higher than max");
		if (min == -1 && max == -1)
			throw new IllegalArgumentException("min and max cannot both be -1");
		this.min = (short)(min == -1 ? Short.MAX_VALUE : min);
		this.max = (short)(max == -1 ? Short.MAX_VALUE : max);
	}

	public ArgValueCount(int value) {
		this(value, value);
	}

	public boolean isRange() {
		return this.min != this.max;
	}

	public boolean isZero() {
		return this.max == 0;
	}

	public @NotNull String getMessage() {
		return this.isRange()
			? "from %d to %s values".formatted(this.min, this.max == Short.MAX_VALUE ? "any number of" : this.max)
			: "%s value%s".formatted(this.min, this.min == 1 ? "" : "s");
	}

	public @NotNull String getRegexRange() {
		return this.isRange()
			? "{%d, %s}".formatted(this.min, "" + (this.max == Short.MAX_VALUE ? "..." : this.max))
			: "{%d}".formatted(this.min);
	}

	public boolean isInRange(int value) {
		return value >= this.min && value <= this.max;
	}

	public boolean isIndexInRange(int value) {
		return value >= 0 && value < this.max;
	}
}