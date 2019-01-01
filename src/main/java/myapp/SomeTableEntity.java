package myapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "some_table")
public class SomeTableEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, name = "some_value")
    private String someValue;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(final String someValue) {
        this.someValue = someValue;
    }
}
