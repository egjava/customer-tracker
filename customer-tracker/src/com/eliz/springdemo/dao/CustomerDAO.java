package com.eliz.springdemo.dao;
import java.util.List;

import com.eliz.springdemo.entity.*;

public interface CustomerDAO {
	public List<Customer> getCustomers();
	public void saveCustomer(Customer theCustomer);
	public Customer getCustomer(int theId);
	public void deleteCustomer(int theId);
	public List<Customer> searchCustomer(String theCustName);
}
