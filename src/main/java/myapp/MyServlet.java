package myapp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "foo", urlPatterns = "/")
public class MyServlet extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private String someJndiValue;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        try {
            Context initContext = new InitialContext();
            someJndiValue = (String) initContext.lookup("java:/comp/env/someJndiValue");
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        String someTableValue;
        try {
            List<SomeTableEntity> list = em.createQuery("select e FROM SomeTableEntity e", SomeTableEntity.class).getResultList();
            someTableValue = list.get(0).getSomeValue();
        } finally {
            em.close();
        }
        resp.getWriter().write(String.format("Hello, someJndiValue: %s, someTableValue: %s",
                someJndiValue, someTableValue));
    }
}
