package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
	@Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
	public List<Cart> findCartsByUserId(@Param("userId")Long userId);


	@Query("Select c from Cart c where c.user.id =:userId and c.product.id = :productId")
	public Cart findByUserIdAndProductId(@Param("userId") long userId, @Param("productId") long productId);
}
