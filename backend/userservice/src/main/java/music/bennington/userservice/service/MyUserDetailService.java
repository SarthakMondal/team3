package music.bennington.userservice.service;

import java.util.List;
import java.util.Optional;

import music.bennington.userservice.entity.UserEntity;
import music.bennington.userservice.repo.UserRepo;
import music.bennington.userservice.security.MyUserDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(MyUserDetailService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepo.findByEmailId(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Sorry User is Not Registered"));
		return  user.map(MyUserDetails::new).orElse(null);

	}

	public String getLoggedInUserId() {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		LOGGER.debug(userId);
		return userId;
	}

	public String getLoggedInUserRole() {
		List<GrantedAuthority> list = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();
		return String.valueOf(list.get(0));
	}

	public boolean ifUserAlreadyLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return false;
		}
		return authentication.isAuthenticated();
	}

}
