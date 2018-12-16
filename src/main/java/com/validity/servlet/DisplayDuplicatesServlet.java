package com.validity.servlet;

import com.validity.businessLogic.DuplicateFinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name="GetDupesServlet",
        urlPatterns = "/getDupes"
)
public class DisplayDuplicatesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DuplicateFinder finder = new DuplicateFinder();

    }
}
