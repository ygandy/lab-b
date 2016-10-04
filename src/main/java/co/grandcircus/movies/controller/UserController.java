package co.grandcircus.movies.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import co.grandcircus.movies.dao.UserDao;
import co.grandcircus.movies.model.Movie;
import co.grandcircus.movies.model.User;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	
	private UserDao userDao;
	
	/**
	 * List all users (READ)
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model, @RequestParam(value="sort", required=false) String sortOrder) {
		List<User> users;
		if (sortOrder != null) {
			users = userDao.getAllUsersSortedBy(sortOrder);
		} else {
		users = userDao.getAllUsers();
		}
		model.addAttribute("users",users);

		logger.info("/users -> user-list.jsp");
		return "user-list";
	}//ends listUsers Method
	
	/**
	 * Display one user
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String displayUser(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("user", userDao.getUser(id));

		logger.info("GET /users/" + id + " -> user.jsp");
		return "user";
	}//ends displayUser Method

	/**
//	 * Save one user (UPDATE)
//	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public String saveUser(@PathVariable int id, User user, Model model) {
		userDao.updateUser(id, user);
		model.addAttribute("id", id);//model add variable id to jsp
		model.addAttribute("user", user);//model add variable user to jsp
		
		logger.info("POST /users/" + id + " -> user.jsp");
	return "user";
	}

/**
//	 * Delete one movie (DELETE)
//	 */
//	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
//	public String deleteUser(@PathVariable int id, Model model) {
//		userDao.deleteUser(id);
//		model.asMap().clear();
//
//		logger.info("POST /users/" + id + "/delete -> redirect to /users");
//		return "redirect:/users";
//	}

/**
	 * Display (GET) one movie to add/populate new createUser form
	 */
@RequestMapping(value = "/users/create", method = RequestMethod.GET)
	public String createUserForm(Model model) {
		model.addAttribute("user", new User());//create new instance of object User

		logger.info("GET /users/create -> user-create.jsp");
		return "user-create";
	}

	/**
	 * Save add/new movie (CREATE)
	 */
	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	public String createUser(Model model, User user) {
		userDao.addUser(user);
		model.asMap().clear();
		
		logger.info("POST /users/create -> redirect to /users");
		return "redirect:/users";
	}

}//end class User Controller
	

