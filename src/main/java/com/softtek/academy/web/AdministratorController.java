package com.softtek.academy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtek.academy.domain.CartEntity;
import com.softtek.academy.domain.CategoryEntity;
import com.softtek.academy.domain.CategoryItemEntity;
import com.softtek.academy.domain.CategoryKey;
import com.softtek.academy.domain.ItemEntity;
import com.softtek.academy.domain.UserEntity;
import com.softtek.academy.domain.UserRoleEntity;
import com.softtek.academy.services.CartService;
import com.softtek.academy.services.CategoryItemService;
import com.softtek.academy.services.CategoryService;
import com.softtek.academy.services.ItemService;
import com.softtek.academy.services.UserRoleService;
import com.softtek.academy.services.UserService;

@Controller
@RequestMapping(value="/admin")
public class AdministratorController {
	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired
	private CategoryItemService categoryItemService;
	
	@Autowired
	private CartService cartService;
	@RequestMapping(value="/listItemView", method = RequestMethod.GET)
	public String itemListView(){
		return "listItem";
	}
	@RequestMapping(value="/listUserView", method = RequestMethod.GET)
	public String userListView(){
		return "listUser";
	}
	@RequestMapping(value="/editUserView", method = RequestMethod.GET)
	public String userEditView(){
		return "editUser";
	}
	@RequestMapping(value="/editItemView", method = RequestMethod.GET)
	public String itemEditView(){
		return "editItem";
	}
	@RequestMapping(value="/newItemView", method = RequestMethod.GET)
	public String itemNewView(){
		return "newItem";
	}
	@RequestMapping(value="/newUserView", method = RequestMethod.GET)
	public String userNewView(){
		return "newUser";
	}
	//Return list of user
	@RequestMapping(value="/listUser", method = RequestMethod.GET)
	public ResponseEntity<?>  listUser( ) {
		final List<UserEntity> users = userService.findAll();
		if(users.isEmpty()){
			return new ResponseEntity<List<UserEntity>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
	}
	
	//Return list of items and categories
	@RequestMapping(value="/listItem", method = RequestMethod.GET)
	public ResponseEntity<?>  listItem( ) {
		final List<ItemEntity> items = itemService.findAll();
		final List<CategoryEntity>category=categoryService.findAll();
		final Map<String,Object>map=new HashMap<String, Object>();
	
		map.put("items", items);
		map.put("categories", category);
		if(items==null){
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	//Return categories
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public ResponseEntity<?>  filterCategory(@RequestBody String description  ) {
		
		final List<ItemEntity> items = itemService.findItemByCategory(description);
		final List<CategoryEntity>category=categoryService.findAll();
		final Map<String,Object>map=new HashMap<String, Object>();
		
		map.put("items", items);
		map.put("categories", category);
		if(items==null){
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	//Find item by id  and their respective categories
	@RequestMapping(value="/editItem", method = RequestMethod.POST)
	public ResponseEntity<?>  editItem(@RequestBody Long id ) {;
		final ItemEntity item = itemService.findOne(id);
		final List<CategoryEntity>category=categoryService.findAll();
		final Map<String,Object>map=new HashMap<String, Object>();
		map.put("item", item);
		map.put("categories", category);
		if(item==null){
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	//Send attributes to edit user
	@RequestMapping(value="/editUser", method = RequestMethod.POST)
	public ResponseEntity<?>  editUser(@RequestBody String username ) {
		final UserEntity user = userService.findOne(username);
		final List<UserRoleEntity> userRoleList = userRoleService.findAll();
		final List<String> listStatus = userService.listStatus();
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("users", user);
		map.put("userRole", userRoleList);
		map.put("listStatus", listStatus);
		if(userRoleList.isEmpty() && listStatus.isEmpty() && user!=null ){
			return new ResponseEntity<List<UserEntity>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	 @RequestMapping(value="/updateUserView" ,method=RequestMethod.POST)
	 public String updateUser(@RequestBody UserEntity user) {
		 if( userService.save(user)){
			 return "redirect:/admin/listUserView";
		 }
		 return "redirect:/admin/editUserView";

	 }
	 @RequestMapping(value="/updateItemView" ,method=RequestMethod.POST)
	 public String updateItem(@RequestBody ItemEntity item) {
		 if( itemService.save(item)){
			 return "redirect:/admin/listItemView";
		 }
		 return "redirect:/admin/editItemView";

	 }
	 @RequestMapping(value="/updateCategory" ,method=RequestMethod.POST)
	 public String updateItem(@RequestBody CategoryItemEntity category) {
		 if( categoryItemService.save(category)){
			 return "redirect:/admin/listItemView";
		 }
		 return "redirect:/admin/editItemView";

	 }
	@RequestMapping(value="/deleteItem", method = RequestMethod.POST)
	public String deleteItem(@RequestBody Long id ) {
		
		final ItemEntity item=itemService.findOne(id);
		final List<CategoryEntity> categories=categoryService.findCategoryByItemId(id);

		for (CategoryEntity category : categories) {
			categoryItemService.delete(new CategoryKey(id, category.getId()));
		}
		if(itemService.delete(item)){
			return "redirect:/admin/listItemView";
		}
		return "redirect:/admin/editItemView";
		
	}
	@RequestMapping(value="/newItem", method = RequestMethod.POST)
	public String newItem(@RequestBody CategoryItemEntity categoryItem ) {
		if(itemService.save(categoryItem.getId().getItem())){
			categoryItemService.save(categoryItem);
			return "redirect:/admin/listItemView";
		}
		return "redirect:/admin/editItemView";
		
	}
	@RequestMapping(value="/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestBody String username ) {
		UserEntity user=userService.findOne(username);
		CartEntity cart=cartService.findCartByUser(user.getUsername());
		cartService.delete(cart);
		if(userService.delete(user)){
			return "redirect:/admin/listItemView";
		}
		return "redirect:/admin/editItemView";
		
	}
	
	//Save new user
	@RequestMapping(value="/newUser", method = RequestMethod.POST)
	public String newUser(@RequestBody UserEntity user) {
		UserRoleEntity userRole=userRoleService.findOne(user.getRole().getId());
		user.setRole(userRole);
		
		if(userService.save(user)){
		
			return "redirect:/admin/listItemView";
		}
		return "redirect:/admin/editItemView";
		
	}
	 //Return user info
	 @RequestMapping(value = "/dataUser", method = RequestMethod.GET)
		public ResponseEntity<?> dataUser() {
		 UserEntity user=new UserEntity();
		 user.setRole(new UserRoleEntity());
		 final List<UserRoleEntity> userRoleList = userRoleService.findAll();
		 final List<String> listStatus = userService.listStatus();
		 final Map<String,Object>map=new HashMap<String, Object>();
		 map.put("user", user);
		 map.put("userRole", userRoleList);
		 map.put("listStatus", listStatus);
		 if(userRoleList.isEmpty() && listStatus.isEmpty()  ){
			 return new ResponseEntity<List<UserEntity>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		 }
		 return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);

	 }
	 
	 //Return item info
	 @RequestMapping(value = "/dataItem", method = RequestMethod.GET)
		public ResponseEntity<?> dataItem() {
		
		final  List<CategoryEntity> category = categoryService.findAll();
		final Map<String,Object>map=new HashMap<String, Object>();
		final Integer count =itemService.count();
		map.put("category", category);
		map.put("count", count);
		 if(category.isEmpty() && category.isEmpty()  ){
			 return new ResponseEntity<List<UserEntity>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		 }
		 return new ResponseEntity< Map<String,Object>>(map, HttpStatus.OK);
	 }
}
