# lab-b

The purpose of this lab is to better understand the Spring MVC Framework Process.
For this lab, the JSP views and the DAOs already exist. Only the controllers to tie it all together needs to be created/updated. 
Here are the mappings you will need to create...

| URL               | View              | Model             | Notes               
|-------------------------------------------------------------------------    
| /users            | user-list.jsp     | List<User> users  | This takes an opional "sort" parameter to sort the user list by firstName, lastName or email. |
| GET /users/{id}   | user.jsp          | User user         | Display the form to edit the given user 
| POST /users/{id}  | user.jsp          | User user         | Saves the submitted User 
| GET /users/create | user.jsp          | none              | Display the form to add a user 
| POST /users/create| redirect to
                      /users            | none              | Saves the submitted User 
                      
|/categories        | category-list.jsp | Set<String>categories |  N/A

-------------------------------------------------------------------------------------------------------------------------------------------
@Controller
  @Autowired
  @RequestMapping
    public String _____User(@PathVariable int id, Model model, User user){
  model.addAttributes
  return;
  }
  ----------------------------------------------------------------------------------------------------------------------------------------
  Request list of Users
  Get URL/users
  Get users and Go to Users Controller
  Controller decides what ISP to select and what data to send to view
  Model is class that calls Model (also an interface)...uses DAO to get data
  JSP stores data and turns it into HTML
  Data access Object = provides access to the data 
  
  
