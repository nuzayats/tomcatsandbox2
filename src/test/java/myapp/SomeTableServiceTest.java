package myapp;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class SomeTableServiceTest {
    private static final String JNDI = "java:/comp/env/jdbc/myds";

    @Test
    public void name() throws NamingException {

        final BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:h2:mem:mydb");

        final Context context = new InitialContext();
        try {
            context.createSubcontext("java:");
            context.createSubcontext("java:/comp");
            context.createSubcontext("java:/comp/env");
            context.createSubcontext("java:/comp/env/jdbc");
            context.bind(JNDI, ds);

            Object foo = context.lookup(JNDI);
            EntityManagerFactory myPU = Persistence.createEntityManagerFactory("myPU");
            System.out.println(myPU);
        } finally {
            context.close();
        }

    }
}
