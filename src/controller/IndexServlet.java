package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        // completed変数の値を参照して
        // DBから取得する内容を切り替える
        String completed = request.getParameter("completed");
        List<Task> tasks = new ArrayList<Task>();
        if (completed == null || completed.equals("all")){
            tasks = em.createNamedQuery("getAllTasks", Task.class)
                        .setFirstResult(10 * (page - 1))
                        .setMaxResults(10)
                        .getResultList();
        } else if (completed.equals("false")) {
            tasks = em.createNamedQuery("getUncompletedTasks", Task.class)
                    .setFirstResult(10 * (page - 1))
                    .setMaxResults(10)
                    .getResultList();
        } else if (completed.equals("true")) {
            tasks = em.createNamedQuery("getCompletedTasks", Task.class)
                    .setFirstResult(10 * (page - 1))
                    .setMaxResults(10)
                    .getResultList();
        }

        // 件数取得
        long tasks_count = 0;
        if (completed == null || completed.equals("all")){
            tasks_count = (long)em.createNamedQuery("getTasksCount", Long.class).getSingleResult();
        } else if (completed.equals("false")) {
            tasks_count = (long)em.createNamedQuery("getUncompletedTasksCount", Long.class).getSingleResult();
        } else if (completed.equals("true")) {
            tasks_count = (long)em.createNamedQuery("getCompletedTasksCount", Long.class).getSingleResult();
        }

        em.close();

        request.setAttribute("tasks", tasks);
        request.setAttribute("tasks_count", tasks_count);
        request.setAttribute("page", page);
        request.setAttribute("completed", completed);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);

    }

}
