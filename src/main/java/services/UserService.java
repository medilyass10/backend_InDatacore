package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import Repository.UserRepository;
import jakarta.transaction.Transactional;
import model.User;

@Service
@Transactional
@Component
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(Long id, User updatedUser) {
		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isPresent()) {
			User user = existingUser.get();
			user.setFullName(updatedUser.getFullName());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			return userRepository.save(user);
		} else {
			return null;
		}
	}
	 public Optional<User> getUserByEmail(String email) {
	        return userRepository.findAll().stream()
	                .filter(user -> email.equals(user.getEmail()))
	                .findFirst();
	    }

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
