package com.icodian.careervia.user.exception;

public class DuplicateSkillException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateSkillException (String message) {
		super(message);
	}
	
	public DuplicateSkillException() {
		super("This skill arleady exists. Try adding another one.");
	}

}
