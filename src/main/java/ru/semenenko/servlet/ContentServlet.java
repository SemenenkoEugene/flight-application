package ru.semenenko.servlet;

import ru.semenenko.dto.FlightDto;
import ru.semenenko.service.FlightService;
import ru.semenenko.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flights = flightService.findAll();
        req.setAttribute("flights", flights);

        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
