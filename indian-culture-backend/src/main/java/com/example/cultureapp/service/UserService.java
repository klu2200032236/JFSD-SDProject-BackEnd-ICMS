//package com.example.cultureapp.service;
//import com.example.cultureapp.model.User;
//public interface UserService {
//	User registerUser(User user);
//    User findByUsername(String username);
//}


package com.example.cultureapp.service;

import com.example.cultureapp.model.User;

public interface UserService {
    User registerUser(User user);
    User findByUsername(String username);
    boolean matchesPassword(String rawPassword, String encodedPassword);
}
