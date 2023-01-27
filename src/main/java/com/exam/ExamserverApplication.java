package com.exam;

import com.exam.impl.UserServiceImpl;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication  {

	@Autowired
	private UserServiceImpl userServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
		System.out.println("stating application");
	}

//	@Override
	public void run(String... args) throws Exception {
		User user  = new User();
		user.setFirstName("Pooja");
		user.setLastName("kadu");
		user.setEmail("pooja@gmail.com");
		user.setPassword("android");
		user.setPhoneNo("9923445874");
		user.setUsername("poojaHacker");
		user.setProfile("ADMIN.png");
		user.setEnabled(true);

		Role role = new Role();
		role.setRoleName("ADMIN");


		Set<UserRole> userRoleList = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		userRoleList.add(userRole);

		User user1 = this.userServiceImpl.createUser(user, userRoleList);
		System.out.println(user1.getUsername());

//		userRoleList.addAll(userRoleList);

//		user.setUserRoles(userRoleList);
	}
}
