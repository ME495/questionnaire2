package pers.chengjian.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.chengjian.dbutils.JdbcUtils;

/**
 * Servlet implementation class SaveResult
 */
public class SaveResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveResult() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private int saveUser(HttpSession session, JdbcUtils jdbcUtils) {
    	String sql = "insert into user(user_name, sex, age, background) values(?, ?, ?, ?)";
    	ArrayList<Object> list = new ArrayList<Object>();
    	list.add(session.getAttribute("user_name"));
    	list.add(session.getAttribute("sex"));
    	list.add(Integer.valueOf((String) session.getAttribute("age")));
    	list.add(session.getAttribute("background"));
    	return jdbcUtils.updateByPreparedStatementRuturnGeneratedValue(sql, list);
    }
    
    private void saveImagePreference(HttpSession session, JdbcUtils jdbcUtils, int user_id) {
    	String sql = "insert into user_photo_method(user_id, photo_id, method_id, score) values(?, ?, ?, ?)";
    	ArrayList<HashMap<String, Integer> > result = (ArrayList<HashMap<String, Integer>>) session.getAttribute("result");
    	ArrayList<Integer> image_id = (ArrayList<Integer>) session.getAttribute("image_id");
    	for (int i=0;i<result.size();++i) {
    		HashMap<String, Integer> map = result.get(i);
    		for (String key: map.keySet()) {
	    		ArrayList<Object> list = new ArrayList<Object>();
	        	list.add(user_id);
	        	list.add(image_id.get(i));
	        	list.add(key);
	        	list.add(map.get(key));
	        	jdbcUtils.updateByPreparedStatement(sql, list);
    		}
    	}
    }
    
    private void saveResult(HttpSession session) throws SQLException {
    	JdbcUtils jdbcUtils = new JdbcUtils();
    	jdbcUtils.getConnection();
    	int user_id = saveUser(session, jdbcUtils);
    	saveImagePreference(session, jdbcUtils, user_id);
    	jdbcUtils.releaseConnection();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int index = (Integer) session.getAttribute("index");
		ArrayList<HashMap<String, Integer> > result = (ArrayList<HashMap<String, Integer> >) session.getAttribute("result");
		int LLE = Integer.valueOf(request.getParameter("LLE"));
		int MRF = Integer.valueOf(request.getParameter("MRF"));
		int Ours2 = Integer.valueOf(request.getParameter("Ours2"));
		int pix2pix = Integer.valueOf(request.getParameter("pix2pix"));
		int RSLCR = Integer.valueOf(request.getParameter("RSLCR"));
		PrintWriter out = response.getWriter();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("LLE", LLE);
		map.put("MRF", MRF);
		map.put("Ours2", Ours2);
		map.put("pix2pix", pix2pix);
		map.put("RSLCR", RSLCR);
		result.add(map);
		++index;
		if (index>20) {
			try {
				saveResult(session);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("exit");
			session.invalidate();
		} else {
			out.print("continue");
			session.setAttribute("index", index);
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
