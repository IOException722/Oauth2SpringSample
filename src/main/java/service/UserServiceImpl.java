package  service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.lambdaworks.crypto.SCryptUtil;

import database.Helper;
import model.User;

 
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		System.out.println("inseting data");
		

//	    SCryptUtil.scrypt(passwd, N, r, p)
//	    SCryptUtil.check(passwd, hashed)
//	    
		String password1 = BCrypt.hashpw("password1", BCrypt.gensalt(12));
	    System.out.println("password1"+ password1);
	    
		String password2 = BCrypt.hashpw("password2", BCrypt.gensalt(12));
	    System.out.println("password2"+ password2);
	         
		String password3 = BCrypt.hashpw("password3", BCrypt.gensalt(12));
	    System.out.println("password3"+ password3);
	         
		String password4 = BCrypt.hashpw("password4", BCrypt.gensalt(12));
	    System.out.println("password4"+ password4);
	         
	    
	

		Helper.insertData("Sam",30,password1);
		Helper.insertData("Tom",40, password2);
		Helper.insertData("Jerome",45, password3);
		Helper.insertData("Silvia",50, password4);
		

        
		users.add(new User(counter.incrementAndGet(),"Sam","sam@gmail.com",password1));
		
        System.out.println("is mathed1: "+ BCrypt.checkpw("password1", password2));
        
		users.add(new User(counter.incrementAndGet(),"Tom","tom@gmail.com", password2));
        System.out.println("is mathed1: "+ BCrypt.checkpw("password3", password3));

		users.add(new User(counter.incrementAndGet(),"Jerome","jrom@gmail.com",  password3));
        System.out.println("is mathed1: "+ BCrypt.checkpw("password1", password2));

		users.add(new User(counter.incrementAndGet(),"Silvia","dska@gmail.com", password4));
		return users;
	}

}
