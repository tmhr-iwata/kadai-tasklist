package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.TaskValidator;
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
        Timestamp limitday = Timestamp.valueOf(limitdayTemp + " " + limittimeTemp + ":00");
        t.setLimitday(limitday);

        //バリデーション実行
        List<String> errors = TaskValidator.validate(t);
        if(errors.size() > 0) {
            em.close();

            // フォームに初期値を設定しエラーメッセージを送る
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("task", t);
            request.setAttribute("errors", errors);
            request.setAttribute("limitday", limitdayTemp);
            request.setAttribute("limitTime", limittimeTemp);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
            rd.forward(request, response);

        } else {

            // DB更新
            em.getTransaction().begin();
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "更新しました。");
            em.close();

            // セッションスコープから不要データ削除
            request.getSession().removeAttribute("task_id");

            // index redirect
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
