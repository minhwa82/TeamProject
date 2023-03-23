package project.movie.qna.action;

public class ActionForward {
	private String path;
	private boolean isRedirect;
	
	public ActionForward() {
		System.out.println("------------------------");
		System.out.println("페이지 이동정보(티켓) 생성");
		System.out.println("------------------------");
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	

}
