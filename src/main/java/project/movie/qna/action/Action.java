package project.movie.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface Action {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return ActionForward(이동정보)
	 * @throws Exception
	 *  
	 */
	
	public ActionForward execute(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
			
		
	}


