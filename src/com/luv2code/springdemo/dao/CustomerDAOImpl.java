package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();

		// create query, sorts by lastname
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		// execute query and return result list
		return query.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();

		// saves or update the customer to the database
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();

		// finds customer with given id
		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();

		// deletes given customer by id
		Query<?> query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String q) {
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query<Customer> theQuery = null;
        
        if (!StringUtils.isEmpty(q)) {
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :q or lower(lastName) like :q", Customer.class);
            theQuery.setParameter("q", "%" + q.toLowerCase() + "%");
        }
        else {
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        return theQuery.getResultList();
	}

}
