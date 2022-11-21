import argparser.*;

class Ball extends ArgumentType<Integer> {
	@Override
	public ArgValueCount getNumberOfArgValues() {
		return new ArgValueCount(0, 2);
	}

	@Override
	public void parseValues(String[] args) {
		this.addError("This is a test error", 0, ErrorLevel.INFO);
	}
}



public class SimpleTests {
	public static void main(String[] args) {

		var a = new ArgumentParser("SimpleTesting") {{
			setErrorCode(64);
			addArgument(new Argument<>("test", ArgumentType.STRING()));
			addArgument(new Argument<>("what", ArgumentType.FILE()));
			addSubCommand(new Command("subcommand") {{
				setErrorCode(128);
				addArgument(new Argument<>("what", ArgumentType.FILE()));
				addArgument(new Argument<>('h', "hey", ArgumentType.KEY_VALUES(ArgumentType.INTEGER())).callback(System.out::println));
			}});
		}};

		a.addError("hello", ErrorLevel.ERROR);

		a.parseArgs("subcommand --hey [=12 test=24] jeoijdoiwad");
	}
}
