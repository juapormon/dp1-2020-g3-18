package rateacher.repository;

import org.springframework.data.repository.CrudRepository;

import rateacher.model.User;


public interface UserRepository extends  CrudRepository<User, String>{

	
}
