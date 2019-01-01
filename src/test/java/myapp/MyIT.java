package myapp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MyIT {

    @Test
    public void name() throws IOException {
        URL url = new URL("http://localhost:8080/tomcatsandbox2/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        String actual;
        try (InputStream is = con.getInputStream();
             Reader reader = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(reader)) {
            actual = br.readLine();
        }

        assertThat(actual, is("Hello, someJndiValue: from_jndi, someTableValue: from_database"));
    }
}
