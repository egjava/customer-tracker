package com.eliz.springdemo.dao;

import java.util.List;

import com.eliz.springdemo.entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(){
	
		//get the current session from session Factory
		Session currentSession = sessionFactory.getCurrentSession();
		//create a Query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",
															Customer.class);
		//execute the Query
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get the current session from session Factory
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCustomer);		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get the current session from session Factory
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery= currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String theCustName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery= null;
		
		if(theCustName!=null && theCustName.trim().length() >0){
			theQuery= currentSession.createQuery("from Customer where lower(lastName) like :custName "
												+ "	or lower(firstName) like :custName",Customer.class);
			
			theQuery.setParameter("custName", "%" + theCustName.toLowerCase() +"%");
		}
		else{
			theQuery= currentSession.createQuery("from Customer", Customer.class);
		}
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}

}
