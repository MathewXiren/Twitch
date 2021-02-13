package com.xma.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xma.jupiter.db.MySQLConnection;
import com.xma.jupiter.db.MySQLException;
import com.xma.jupiter.entity.LoginRequestBody;
import com.xma.jupiter.entity.LoginResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Step 1: get user
//            ObjectMapper mapper = new ObjectMapper();
//            LoginRequestBody body = mapper.readValue(request.getReader(), LoginRequestBody.class); // get the user information and convert to object
            LoginRequestBody body = ServletUtil.readRequestBody(LoginRequestBody.class, request);
            if (body == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            // step 2: check whether user exist
            String username;
            MySQLConnection connection = null;
            try {
                connection = new MySQLConnection();
                String userId = body.getUserId();
                String password = ServletUtil.encryptPassword(body.getUserId(), body.getPassword()); // encrypt the password to compare
                username = connection.verifyLogin(userId, password); //verify if the user exist
            } catch (MySQLException e) {
                throw new ServletException(e);
            } finally {
                connection.close();
            }
            // step3: generate session
            if (!username.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", body.getUserId());
                session.setMaxInactiveInterval(600);
                // tomcat directly send te session in the response body

                LoginResponseBody loginResponseBody = new LoginResponseBody(body.getUserId(), username);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(new ObjectMapper().writeValueAsString(loginResponseBody));
//                ServletUtil.writeItemMap(response, loginResponseBody);

            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

