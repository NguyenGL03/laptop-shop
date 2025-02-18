package vn.hoidanit.laptopshop.repository;

import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User save(User user);

  List<User> findByEmail(String email);

  List<User> findAllByOrderByIdAsc();

  Optional<User> findById(Long id);

  void deleteById(Long id);

}
