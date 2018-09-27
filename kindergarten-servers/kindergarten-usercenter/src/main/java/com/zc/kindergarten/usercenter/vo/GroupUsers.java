package com.zc.kindergarten.usercenter.vo;

import com.zc.kindergarten.usercenter.entity.User;
import lombok.Data;

import java.util.List;

/**
 * Created by Ace on 2017/6/18.
 */
@Data
public class GroupUsers {

    List<User> members ;
    List<User> leaders;

    public GroupUsers() {
    }

    public GroupUsers(List<User> members, List<User> leaders) {
        this.members = members;
        this.leaders = leaders;
    }
}
