package com.healthedge.healthchain.user.repository;

import com.healthedge.healthchain.user.entity.UserDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends PagingAndSortingRepository<UserDetails, Long> {

}
