package org.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;
import java.util.TimeZone;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain) throws IOException, ServletException {
        String timezoneParam = req.getParameter("timezone");
        if (!isValidTimezone(timezoneParam)) {
            resp.getWriter().write("Invalid timezone");
            resp.setStatus(400);
            return;
        }
        chain.doFilter(req, resp);
    }

    private boolean isValidTimezone(String timezone) {
        return  timezone == null || timezone.isEmpty() || Set.of(TimeZone.getAvailableIDs()).contains(timezone);
    }
}