package com.ll.exam;

import com.ll.exam.article.ArticleController;
import com.ll.exam.member.MemberController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Rq rq = new Rq(req, resp);

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();

        // getRequestURI는
        // http://localhost:8081/usr/article/list/free?page=1 에서
        // /usr/article/list/free 부분만 가져온다.
        switch (rq.getMethod()) {
            case "GET":
                switch (rq.getActionPath()) {
                    case "/usr/article/detail":
                        articleController.showDetail(rq);
                        break;
                    case "/usr/article/list":
                        articleController.showList(rq);
                    case "/usr/article/write":
                        articleController.showWrite(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.showModify(rq);
                        break;
                    case "/usr/article/delete":
                        articleController.deleteArticle(rq);
                        break;
                    case "/usr/member/login":
                        memberController.showLogin(rq);
                        break;
                }
                break;
            case "POST":
                switch (rq.getActionPath()) {
                    case "/usr/article/write":
                        articleController.doWrite(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.doModify(rq);
                        break;
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // 똑같은 코드의 중복을 막기 위해서 doPost()에서 doGet() 호출!
        doGet(req, resp);
    }
}
