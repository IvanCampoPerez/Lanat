package argparser.displayFormatter;

public enum Color {
	BLACK(30),
	RED(31),
	GREEN(32),
	YELLOW(33),
	BLUE(34),
	MAGENTA(35),
	CYAN(36),
	WHITE(37),
	GRAY(90),
	BRIGHT_RED(91),
	BRIGHT_GREEN(92),
	BRIGHT_YELLOW(93),
	BRIGHT_BLUE(94),
	BRIGHT_MAGENTA(95),
	BRIGHT_CYAN(96),
	BRIGHT_WHITE(97);

	private final Byte value;

	Color(int value) {
		this.value = (byte)value;
	}

	@Override
	public String toString() {
		return String.format("\033[%dm", this.value);
	}

	public String toStringBackground() {
		return String.format("\033[%dm", this.value + 10);
	}
}