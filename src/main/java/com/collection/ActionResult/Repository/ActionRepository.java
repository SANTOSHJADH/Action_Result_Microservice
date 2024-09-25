package com.collection.ActionResult.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collection.ActionResult.Entity.ActionEntity;
@Repository
public interface ActionRepository extends JpaRepository<ActionEntity,String> {

}
