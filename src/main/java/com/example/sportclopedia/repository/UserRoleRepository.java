package com.example.sportclopedia.repository;

import com.example.sportclopedia.model.entity.UserRole;
import com.example.sportclopedia.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserRole(UserRoleEnum userRole);

}
