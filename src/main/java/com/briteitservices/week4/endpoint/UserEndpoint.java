package com.briteitservices.week4.endpoint;

import com.briteitservices.week4.model.User;
import com.briteitservices.week4.repository.UserRepository;
import com.briteitservices.week4.serialization.SerializedObject;
import com.briteitservices.week4.serialization.SerializedView;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Stateless
@PermitAll
public class UserEndpoint {

    private static final SerializedView<User> VIEW = SerializedView.builder(User.class)
            .build();

    @Inject
    private UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SerializedObject<List<User>> get() {
        List<User> users = userRepository.findAll();
        return VIEW.serializeList(users);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SerializedObject<User> get(@PathParam("id") int id) {
        User user = userRepository.findBy(id);
        return VIEW.serialize(user);
    }
}
