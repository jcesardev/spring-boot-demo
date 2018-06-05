package mx.cuu.dev.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class to model a Person.
 *
 * @author Julio Cesar Bolaños Palacios
 *
 */
@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = -8344599769291638079L;
    @Id
    @GeneratedValue
    private int id;

    private String fullName;

    private int age;

    /**
     * Empty constructor.
     */
    public Person() {
        super();
    }

    /**
     * Constructor with arguments.
     *
     * @param id
     *            Unique identifier.
     * @param fullName
     *            Person full name.
     * @param age
     *            Person age.
     */
    public Person(int id, String fullName, int age) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.age = age;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     *            the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + id;
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (age != other.age)
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Person [id=" + id + ", fullName=" + fullName + ", age=" + age + "]";
    }

}
