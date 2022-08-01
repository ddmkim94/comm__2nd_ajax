package com.ll.exam;

import com.ll.exam.article.ArticleController;
import com.ll.exam.chat.ChatController;
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
        ChatController chatController = new ChatController();

        // getRequestURI는
        // http://localhost:8081/usr/article/list/free?page=1 에서
        // /usr/article/list/free 부분만 가져온다.
        switch (rq.getRouteMethod()) {
            case "GET":
                switch (rq.getActionPath()) {
                    case "/usr/chat/createRoom":
                        chatController.showCreateRoom(rq);
                        break;
                    case "/usr/chat/modifyRoom":
                        chatController.showModifyRoom(rq);
                        break;
                    case "/usr/chat/roomList":
                        chatController.showRoomList(rq);
                        break;
                    case "/usr/chat/room":
                        chatController.showRoom(rq);
                        break;
                    case "/usr/chat/roomManual":
                        chatController.showRoomManual(rq);
                        break;
                    case "/usr/chat/getMessages":
                        chatController.getMessages(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.showModify(rq);
                        break;
                    case "/usr/article/detail":
                        articleController.showDetail(rq);
                        break;
                    case "/usr/article/list":
                        articleController.showList(rq);
                        break;
                    case "/usr/article/listAuto":
                        articleController.showListAuto(rq);
                        break;
                    case "/usr/article/getArticles":
                        articleController.getArticles(rq);
                        break;
                    case "/usr/article/write":
                        articleController.showWrite(rq);
                        break;
                    case "/usr/member/login":
                        memberController.showLogin(rq);
                        break;
                }
                break;
            case "POST":
                switch (rq.getActionPath()) {
                    case "/usr/chat/writeMessage":
                        chatController.writeMessage(rq);
                        break;
                    case "/usr/chat/writeMessageAjax":
                        chatController.writeMessageAjax(rq);
                        break;
                    case "/usr/chat/createRoom":
                        chatController.createRoom(rq);
                        break;
                    case "/usr/chat/modifyRoom":
                        chatController.modifyRoom(rq);
                        break;
                    case "/usr/article/write":
                        articleController.write(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.modify(rq);
                        break;
                }
                break;
            case "DELETE":
                switch (rq.getActionPath()) {
                    case "/usr/chat/deleteRoom":
                        chatController.deleteRoom(rq);
                        break;
                    case "/usr/chat/deleteMessage":
                        chatController.deleteMessage(rq);
                        break;
                    case "/usr/chat/deleteMessageAjax":
                        chatController.deleteMessageAjax(rq);
                        break;
                    case "/usr/article/delete":
                        articleController.deleteArticle(rq);
                        break;
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // 똑같은 코드의 중복을 막기 위해서 doPost()에서 doGet() 호출!
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
