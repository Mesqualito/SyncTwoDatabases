package com.eigenbaumarkt.synctwodatabases.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eigenbaumarkt.synctwodatabases.model.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
