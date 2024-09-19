package routeServlet;


import javax.servlet.ServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import board.BoardMgr;

@WebServlet("/board/*")
public class BoardServlet extends HttpServlet {
		
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	// 요청URL 불러오기
    	String path = request.getPathInfo();
    	
    	// 새글작성 요청
    	if ("/boardPost".equals(path)) {
    		BoardMgr bMgr = new BoardMgr();
    		bMgr.insertBoard(request);
    		response.sendRedirect("board01");
    	}
    	
    	if("/boardComment".equals(path)) {
    		BoardMgr bMgr = new BoardMgr();
    		bMgr.insertComment(request);
    		response.sendRedirect(request.getHeader("Referer")); // 이전페이지로 리다이렉트
    	}
        
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();  // 전체 요청 URI를 가져옴 // /MillkyWay/board/board01
        String contextPath = request.getContextPath();  // contextPath를 가져옴 // /MillkyWay
        String relativePath = path.substring(contextPath.length());  // contextPath를 제외한 경로 // /board/board01

        // /procs 경로를 처리하기 위한 JSP 경로 생성
        String jspPath = "/WEB-INF/jsp/board" + relativePath.substring("/board".length()) + ".jsp"; // /WEB-INF/jsp/board/board01.jsp

        try {
            // JSP로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            // JSP 파일을 찾을 수 없는 경우 에러 처리
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "JSP 파일을 찾을 수 없습니다.");
        }
    }
}
