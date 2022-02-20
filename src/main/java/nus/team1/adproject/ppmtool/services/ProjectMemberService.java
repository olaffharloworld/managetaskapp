package nus.team1.adproject.ppmtool.services;

import nus.team1.adproject.ppmtool.domain.Project;
import nus.team1.adproject.ppmtool.domain.User;
import nus.team1.adproject.ppmtool.exceptions.ProjectNotFoundException;
import nus.team1.adproject.ppmtool.exceptions.UserNotFoundException;
import nus.team1.adproject.ppmtool.repositories.ProjectRepository;
import nus.team1.adproject.ppmtool.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectMemberService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * public User addProjectMember(String projectIdentifier, User member) {
	 * 
	 * //Exceptions: Project not found
	 * 
	 * try { //PTs to be added to a specific project, project != null, BL exists
	 * Project project =
	 * projectRepository.findByProjectIdentifier(projectIdentifier); //set the
	 * project to member System.out.println(project); member.setProject(project);
	 * member.setProjectIdentifier(projectIdentifier);
	 * 
	 * return userRepository.save(member); } catch (Exception e) { throw new
	 * ProjectNotFoundException("Project not Found");
	 * 
	 * }
	 * 
	 * }
	 */
	public Iterable<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findUserByusername(String name) {
		return userRepository.findUserByuserName(name);
	}

	public void saveUser(User user) {

		userRepository.save(user);
	}

//    public User addProjectMember(String projectIdentifier, String username) {
//
//        //Exceptions: Project not found
//
//        try {
//            //PTs to be added to a specific project, project != null, BL exists
//            Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
//            //set the project to member
//            System.out.println(project);
//            User member=userRepository.findUserByuserName(username);
//            member.setProject(project);
//            member.setProjectIdentifier(projectIdentifier);
//
//            return userRepository.save(member);
//        } catch (Exception e) {
//            throw new ProjectNotFoundException("Project not Found");
//
//        }
//
//    }
	public User addProjectMember(String projectIdentifier, String username) {

		// Exceptions: Project not found

		try {
			// PTs to be added to a specific project, project != null, BL exists
			Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
			// set the project to member
			System.out.println(project);
			User member = userRepository.findUserByuserName(username);
			member.setProjectIdentifier(projectIdentifier);
			return userRepository.save(member);
		} catch (Exception e) {
			throw new ProjectNotFoundException("Project not Found");
		}

	}

	public Iterable<User> findMemberByProjectId(String id) {

		Project project = projectRepository.findByProjectIdentifier(id);

		if (project == null) {
			throw new ProjectNotFoundException("Project with ID: '" + id + "' does not exist");
		}

		return userRepository.findByProjectIdentifier(id);
	}

	public User findMemberByProjectIdName(String project_id, String user_name) {

		Project project = projectRepository.findByProjectIdentifier(project_id);
		if (project == null) {
			throw new ProjectNotFoundException("Project with ID: '" + project_id + "' does not exist");
		}

		User member = userRepository.findUserByuserName(user_name);
		if (member == null) {
			throw new UserNotFoundException("User '" + user_name + "' not found");
		}

		// make sure that the backlog/project id in the path corresponds to the right
		// project
		if (!member.getProjectIdentifier().equals(project_id)) {
			throw new UserNotFoundException("User '" + user_name + "' does not exist in project: '" + project_id);
		}

		return member;
	}

	public User updateByProjectIdName(User updatedmember, String project_id, String user_name) {

		User member = userRepository.findUserByuserName(user_name);

		member = updatedmember;

		return userRepository.save(member);
	}

	public void deletePTByProjectIdName(String project_id, String user_name) {
		User member = findMemberByProjectIdName(project_id, user_name);
		userRepository.delete(member);
	}

	public void RemoveMember(String user_name) {
		User member = userRepository.findUserByuserName(user_name);
		member.setProjectIdentifier(null);
		userRepository.save(member);
	}
}