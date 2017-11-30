package com.briteitservices.week4.repository;

import com.briteitservices.week4.model.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;


@Repository
public interface UserRepository extends EntityRepository<User, Integer> {

    @Query(value = "select u from User u where u.name=:name order by u.id", max = 1, singleResult = SingleResultType.OPTIONAL)
    User get(@QueryParam("name") String name);
}