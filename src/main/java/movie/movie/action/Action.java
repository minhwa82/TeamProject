package movie.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// => 동작(DB사용) 통일하기위해서 (다형성)
	
	/**
	 * 동작은 각 필요에 따라서 수행
	 * 
	 * @param request
	 * @param response
	 * @return ActionForward (이동정보)
	 * @throws Exception
	 */
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
