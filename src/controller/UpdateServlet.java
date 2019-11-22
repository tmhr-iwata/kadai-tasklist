package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // セッションスコープからタスクIDを取得
        // 該当ID1件をデータベースから取得
        Task t = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

        // フォームの内容を各プロパティに上書き
        String title = request.getParameter("title");
        t.setTitle(title);

        String content = request.getParameter("content");
        t.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        t.setUpdated_at(currentTime);

        Boolean completed = Boolean.parseBoolean(request.getParameter("completed"));
        t.setCompleted(completed);

        String limitdayTemp = request.getParameter("limitday");
        String limittimeTemp = request.getParameter("limitTime");
        Timestamp limitday = Timestamp.valueOf(limitdayTemp + " " + limittimeTemp );
        // 取得した時間は内部的には秒まであるので、こっちでは+00秒しない。
        t.setLimitday(limitday);

        // DB更新
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();

        // セッションスコープから不要データ削除
        request.getSession().removeAttribute("task_id");

        // index redirect
        response.sendRedirect(request.getContextPath() + "/index");

    }

}
