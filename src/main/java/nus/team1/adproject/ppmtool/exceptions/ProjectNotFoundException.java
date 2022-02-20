package nus.team1.adproject.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4030773860964427836L;

	public ProjectNotFoundException(String message) {
        super(message);
    }
}