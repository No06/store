package com.example.demo.repository;

import com.example.demo.entity.UserAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findAllByUserId(Long userId);
    Long countAllByUserId(Long userId);
    void removeByIdAndUserId(Long id, Long userId);
}
