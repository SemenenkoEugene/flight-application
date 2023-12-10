package ru.semenenko.servlet;

import ru.semenenko.service.FlightService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Список перелетов:</h1>");
            writer.write("<ul>");
            flightService.findAll().forEach(flightDto ->
                    writer.write("""
                            <li>
                            <a href='/tickets?flightId=%d'>%s</a>
                            </li>""".formatted(flightDto.id(), flightDto.description())));
            writer.write("</ul>");
        }
    }
}
