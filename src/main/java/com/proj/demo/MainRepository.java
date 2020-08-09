package com.proj.demo;

//import java.awt.List;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MainRepository extends JpaRepository<Users, Integer> {
	
	@Query(value = "SELECT username,password FROM projuser where name=?1",nativeQuery = true)
	ArrayList<Object []> findUserByUsername(String name);
	
	@Query(value = "SELECT * FROM projuser where name=?1",nativeQuery = true)
	ArrayList<Users> findUsernamePasswordByName(String name);
	
	@Query(value = "SELECT name FROM projuser where username=?1 AND password=?2",nativeQuery = true)
	ArrayList<Object []> findRegUser(String username,String password);
	
	//Query using name as parameter
	@Query(value="SELECT coord FROM citydetails where name=?1",nativeQuery = true)
	ArrayList<Object []> findCoord(String name);
	
	//newly added
	@Query(value="SELECT name,country,state FROM citydetails where name like %?1% limit 15",nativeQuery=true)
	public List<String> search(@Param("term") String keyword);
	
	@Query(value="SELECT id FROM citydetails where name=?1 AND country=?2 AND state=?3 limit 1",nativeQuery=true)
	public String findId(String name,String country,String state);
	
	//Query using id as parameter
	@Query(value="SELECT coord FROM citydetails where id=?1",nativeQuery = true)
	ArrayList<Object []> findCoordById(int id);
}
