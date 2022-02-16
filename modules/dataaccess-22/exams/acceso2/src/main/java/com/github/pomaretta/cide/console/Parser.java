package com.github.pomaretta.cide.console;

import com.github.pomaretta.termux.Command.DefaultCommandParser;

public abstract class Parser extends DefaultCommandParser {

	@Override
	protected int parseBlock(String command) throws Exception {
		switch (command) {
			case "help":
				System.out.println("Help!");
				break;
			default:
				return this.callBack(command);
		}
		return 0;
	}

}
