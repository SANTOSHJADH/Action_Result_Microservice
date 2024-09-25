package com.collection.ActionResult.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collection.ActionResult.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

	UserEntity findByUsername(String username);

}
