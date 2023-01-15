package com.example.json_product_shop.repositories;

import com.example.json_product_shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLastName (String lastName);
}
