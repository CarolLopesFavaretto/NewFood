package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
