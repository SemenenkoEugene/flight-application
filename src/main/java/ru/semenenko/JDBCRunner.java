package ru.semenenko;

import ru.semenenko.dao.FlightDao;
import ru.semenenko.dao.TicketDao;
import ru.semenenko.dto.TickerFilter;
import ru.semenenko.entity.Flight;
import ru.semenenko.entity.FlightStatus;
import ru.semenenko.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCRunner {
    public static void main(String[] args) throws SQLException {
        TicketDao ticketDao = TicketDao.getInstance();
//        TickerFilter filter = new TickerFilter(null, null, 2, 0);
//        System.out.println(ticketDao.findAll(filter));
        System.out.println(ticketDao.findById(3L).get());

        FlightDao flightDao = FlightDao.getInstance();
        System.out.println(flightDao.findById(9L).get());

        Flight flight = flightDao.findById(9L).get();
        flight.setStatus(FlightStatus.valueOf("ARRIVED"));

        System.out.println(flightDao.update(flight));
//        System.out.println(flightDao.findById(9L).get());


    }

    public static List<Long> getTicketsByFlightId(Long flightId) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
                SELECT * FROM ticket
                WHERE flight_id = ?
                """;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, flightId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tickets.add(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public static List<Long> getFlightsBetween(LocalDateTime start, LocalDateTime end) {
        List<Long> flights = new ArrayList<>();
        String sql = """
                SELECT * FROM flight
                WHERE departure_date BETWEEN ? AND ?
                """;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.println(statement);
            statement.setTimestamp(1, Timestamp.valueOf(start));
            System.out.println(statement);
            statement.setTimestamp(2, Timestamp.valueOf(end));
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flights.add(resultSet.getLong("id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    public static void checkMetaData() throws SQLException {
        try (Connection connection = ConnectionManager.get()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet catalogs = metaData.getCatalogs();
            ResultSet clientInfoProperties = metaData.getClientInfoProperties();
            while (catalogs.next()) {
                System.out.println(catalogs.getString(1));
            }
            while (clientInfoProperties.next()) {
                System.out.println(clientInfoProperties.getString(1));

            }
        }
    }
}
