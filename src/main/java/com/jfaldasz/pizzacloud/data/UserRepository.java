package com.jfaldasz.pizzacloud.data;

import com.jfaldasz.pizzacloud.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
