package com.xma.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xma.jupiter.entity.Game;
import com.xma.jupiter.external.TwitchClient;
import com.xma.jupiter.external.TwitchException;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import com.xma.jupiter.entity.Game;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String name = jsonRequest.getString("name");
        String developer = jsonRequest.getString("developer");
        String releaseTime = jsonRequest.getString("release_time");
        String website = jsonRequest.getString("website");
        float price = jsonRequest.getFloat("price");


        System.out.println("Name is: " + name);
        System.out.println("Developer is: " + developer);
        System.out.println("Release time is: " + releaseTime);
        System.out.println("Website is: " + website);
        System.out.println("Price is: " + price);

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String gameName = request.getParameter("gamename");
//        response.getWriter().print("<html><body>Game is " + gameName+"</body></html>");
////
////        Game.GameBuilder gameBuilder = new Game.GameBuilder();
////        Game game = gameBuilder.build();
//
//        JSONObject obj = new JSONObject();
//        obj.put("name", "World of Warcraft");
//        obj.put("developer", "Blizzard Entertainment");
//        obj.put("release_time", "Feb 11, 2005");
//        obj.put("website", "https://www.worldofwarcraft.com");
//        obj.put("price", 49.99);
//
//        response.setContentType("application/json");
//        response.getWriter().print(obj);


//        response.setContentType("application/json");
//        ObjectMapper mapper = new ObjectMapper();
////        Game game = new Game("World of Warcraft", "Blizzard Entertainment", "Feb 11, 2005", "https://www.worldofwarcraft.com", 49.99);
////        Game game = new Game("12924", "World of Warcraft", "https://static-cdn.jtvnw.net/ttv-boxart/Warcraft%20III-{width}x{height}.jpg");
//        Game game = new Game.Builder()
//                .id("12924")
//                .name("World of Warcraft")
//                .boxArtUrl("[San Francisco](https://static-cdn.jtvnw.net/ttv-boxart/Warcraft%20III-{width}x{height}.jpg)")
//                .build();
//        response.getWriter().print(mapper.writeValueAsString(game));


        String gameName = request.getParameter("game_name");
        TwitchClient client = new TwitchClient();

        response.setContentType("application/json;charset=UTF-8");
        try {
            if (gameName != null) {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.searchGame(gameName)));
            } else {
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.topGames(0)));
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }


    }
}
