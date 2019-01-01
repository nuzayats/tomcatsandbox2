package myapp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "foo", urlPatterns = "/")
public class MyServlet extends HttpServlet {

    private DataSource ds;
    private String foo;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        try {
            Context initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/myds");
            foo = (String) initContext.lookup("java:/comp/env/foo");
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try (Connection cn = ds.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT 1")) {
            rs.next();
            resp.getWriter().println(rs.getInt(1) + " " + foo);
        } catch (SQLException e1) {
            throw new ServletException(e1);
        }
    }
}
