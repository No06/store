package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    @Query("SELECT u.defaultAddress FROM User u WHERE u.id = :id")
    UserAddress findDefaultAddressById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.defaultAddress.id = :addressId WHERE u.id = :id")
    void updateDefaultAddressById(@Param("addressId") Long addressId, @Param("id") Long id);

    @Transactional
    void removeById(Long id);
}
