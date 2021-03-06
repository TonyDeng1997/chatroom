package chat.data;

import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import chat.model.Employee;
import io.dropwizard.hibernate.AbstractDAO;

// https://dzone.com/articles/getting-started-with-dropwizard-connecting-to-a-da
	
public class EmployeeDAO extends AbstractDAO<Employee> {
    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public EmployeeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    /**
     * Method returns all employees stored in the database.
     *
     * @return list of all employees stored in the database
     */
    public List<Employee> findAll() {
        return list(namedQuery("chat.model.Employee.findAll"));
    }
    /**
     * Looks for employees whose first or last name contains the passed
     * parameter as a substring.
     *
     * @param name query parameter
     * @return list of employees whose first or last name contains the passed
     * parameter as a substring.
     */
    public List<Employee> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                namedQuery("chat.model.Employee.findByName")
                .setParameter("name", builder.toString())
        );
    }
    /**
     * Method looks for an employee by her id.
     *
     * @param id the id of an employee we are looking for.
     * @return Optional containing the found employee or an empty Optional
     * otherwise.
     */
    public Optional<Employee> findById(long id) {
        return Optional.ofNullable(get(id));
    }
}