package com.zc.kindergarten.usercenter.controller;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.vo.FrontUser;
import com.zc.kindergarten.common.vo.PermissionInfo;
import com.zc.kindergarten.common.vo.UserInfo;
import com.zc.kindergarten.usercenter.entity.Menu;
import com.zc.kindergarten.usercenter.service.MenuService;
import com.zc.kindergarten.usercenter.service.UserService;
import com.zc.kindergarten.usercenter.vo.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2017-06-08 11:51
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;

    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FrontUser> getUserInfo(String token) throws Exception {
        FrontUser userInfo = userService.getUserInfo(token);
       return new ResponseEntity(userInfo);
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<MenuTree>> getMenusByUsername(String token) throws Exception {
	    List<MenuTree> list = userService.getMenusByUsername(token);
        return new ResponseEntity<>(list);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Menu>> getAllMenus() {
	    List<Menu> menuList = menuService.selectAllMenus();
	    return new ResponseEntity<>(menuList);
    }

	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PermissionInfo>> getAllPermission(){
		List<PermissionInfo> permissionInfos = userService.getAllPermission();
    	return new ResponseEntity<>(permissionInfos);
	}

	@RequestMapping(value = "/un/{username}/permissions", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<PermissionInfo>> getPermissionByUsername(@PathVariable("username") String username){
		List<PermissionInfo> permissionInfos = userService.getPermissionByUsername(username);
		return new ResponseEntity<>(permissionInfos);
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<UserInfo> validate(@RequestBody Map<String,String> body){
		UserInfo userInfo = userService.validate(body.get("username"),body.get("password"));
		return new ResponseEntity<>(userInfo);
	}

}
