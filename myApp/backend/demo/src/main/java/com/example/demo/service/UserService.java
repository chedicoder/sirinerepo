package com.example.demo.service;


import java.util.List;

import com.example.demo.entities.Project;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;

public interface UserService {
    List<User> retrieveAllUsers();

    User addUser(User user);

    void deleteUser(Integer id);

    User updateUser(User user);

    User retrieveUser(Integer id);

    List<Role> retrieveAllRoles();

    Role addRole(Role role);

    void deleteRole(Integer id);

    Role updateRole(Role role);

    Role retrieveRole(Integer id);

	void addRoleToUser(Integer userId, Integer roleId);

	void deleteRoleFromUser(Integer userId,Integer roleId);


	Role retrieveRoleFromUser(Integer userId);

	User retrieveUser(String username);

	List <User> findUserByroleandusername(String username,String roleName);
	
	List<Project> retrieveAllProjects();
    Project addProject(Project project);
    void deleteProject(Integer id);
    Project updateProject(Project project);
    Project retrieveProject(Integer id);

	void deleteProjectFromUser(Integer userId, Integer projectId);

	void addProjectToUser(Integer userId, Integer projectId);
	

    
}
