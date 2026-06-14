package app.dao;

import app.mapper.CustomerMapper;
import app.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerDAO implements CrudDAO<Customer, Long>{

    private final JdbcTemplate jdbcTemplate;
    private final CustomerMapper customerMapper;

    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customers (full_name, email, social_security_number) VALUES (?,?,?) returning *";
        return jdbcTemplate.queryForObject(sql, customerMapper, customer.getFullName(), customer.getEmail(), customer.getSocialSecurityNumber());
    }

    @Override
    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try {
           return Optional.ofNullable(jdbcTemplate.queryForObject(sql, customerMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, customerMapper);
    }

    @Override
    public boolean update(Customer customer) {
        String sql = "UPDATE customers SET full_name = ?, email = ?, social_security_number = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                customer.getFullName(),
                customer.getEmail(),
                customer.getSocialSecurityNumber(),
                customer.getId()
        ) > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}