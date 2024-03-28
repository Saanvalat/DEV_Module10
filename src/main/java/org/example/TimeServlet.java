package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String timezoneParam = req.getParameter("timezone");
        ZoneId zoneId = (timezoneParam != null && !timezoneParam.isEmpty()) ? ZoneId.of(timezoneParam) : ZoneId.of("UTC");
        String currentTime = ZonedDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        resp.getWriter().write(currentTime);
        resp.getWriter().close();
    }

}
