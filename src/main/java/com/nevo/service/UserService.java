package com.nevo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;
import com.nevo.domain.AlternateId;
import com.nevo.domain.User;
import com.nevo.repository.IUserRepository;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	private MongoOperations mongoOperations;
	public void MongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	public User findById(String id) {
		return userRepository.findById(id);
	}
	
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User create(User user)
	{	
		return userRepository.save(user);
	}
	
	@Override
	public void delete(String userId){
		userRepository.delete(userId);
	}

	@Override
	public void addAlternateId(String userId, AlternateId alternateId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userId)
				.and("alternateIds")
				.not()
				.elemMatch(Criteria.where("context").is(alternateId.getContext()).and("contextId").is(alternateId.getContextId())));

		Update update = new Update();
		update.push("alternateIds", alternateId);

		WriteResult result = mongoOperations.updateFirst(query, update, "users");
		int n = result.getN();
	}
}
