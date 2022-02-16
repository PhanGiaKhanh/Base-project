package com.springmvc.demo.service;

import java.util.List;

public interface GenericService<T> {
	List<T> findAll();

	T findById(String id);

	void save(T entity);

	void delete(String id);
}
