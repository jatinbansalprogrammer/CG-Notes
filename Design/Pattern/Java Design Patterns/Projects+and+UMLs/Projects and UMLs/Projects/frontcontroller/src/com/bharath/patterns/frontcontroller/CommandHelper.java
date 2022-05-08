package com.bharath.patterns.frontcontroller;

public class CommandHelper {

	public Command getCommand(String requestURI) {

		if (requestURI.contains("viewStudentDetails.do")) {
			return new ViewStudentCommand();
		}
		return null;

	}

}
