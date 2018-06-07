package com.cyberprism.lanchat.db;

import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import com.cyberprism.lanchat.model.*;


public class DBUtils {
	public static final String url="jdbc:mysql://localhost:3306/lanchat?user=root&password=mysql";
	public static Connection con=null;

	public static boolean connect() throws Exception {
		if(con==null || con.isClosed()){
			Class.forName(com.mysql.jdbc.Driver.class.getCanonicalName());
			con = DriverManager.getConnection(url);
			if(con!=null && !con.isClosed()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static List<Media> getMedias() throws Exception {
		final List<Media> medias = new ArrayList<Media>();
		final String sql = "select * from media";

		final Statement stmt = con.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);

		while(rs.next()){
			Media media = new Media();
			media.setid(rs.getLong("id"));
			media.setmedia(rs.getString("media"));
			media.setfile(rs.getString("file"));
			media.setmediatype(rs.getString("mediatype"));
			media.setstatus(rs.getString("status"));
			medias.add(media);
		}
		return medias;
	}

	public static boolean addMedia(Media media) throws Exception {
		final String sql = "INSERT INTO media (media,file,mediatype,status) VALUES('"+media.getmedia()+"','"+media.getfile()+"','"+media.getmediatype()+"','"+media.getstatus()+"')";
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean removeMedia(Media media) throws Exception {
		final String sql = "DELETE FROM media WHERE id="+media.getid();
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else { 
			return false;
		}
	}

	public static List<Photo> getPhotos() throws Exception {
		final List<Photo> photos = new ArrayList<Photo>();
		final String sql = "select * from photos";

		final Statement stmt = con.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);

		while(rs.next()){
			Photo photo = new Photo();
			photo.setid(rs.getLong("id"));
			photo.setmediaid(rs.getLong("mediaid"));
			photo.setimagetype(rs.getString("imagetype"));
			photo.setstatus(rs.getString("status"));
			photos.add(photo);
		}
		return photos;
	}

	public static boolean addPhoto(Photo photo) throws Exception {
		final String sql = "INSERT INTO photos (mediaid,imagetype,status) VALUES("+photo.getmediaid()+",'"+photo.getimagetype()+"','"+photo.getstatus()+"')";
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean removePhoto(Photo photo) throws Exception {
		final String sql = "DELETE FROM photos WHERE id="+photo.getid();
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else { 
			return false;
		}
	}

	public static List<Userinfo> getUserinfos() throws Exception {
		final List<Userinfo> userinfos = new ArrayList<Userinfo>();
		final String sql = "select * from userinfo";

		final Statement stmt = con.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);

		while(rs.next()){
			Userinfo userinfo = new Userinfo();
			userinfo.setid(rs.getLong("id"));
			userinfo.setuserid(rs.getLong("userid"));
			userinfo.setphotoid(rs.getLong("photoid"));
			userinfo.setfirstname(rs.getString("firstname"));
			userinfo.setlastname(rs.getString("lastname"));
			userinfo.setaddress(rs.getString("address"));
			userinfo.setcity(rs.getString("city"));
			userinfo.setstate(rs.getString("state"));
			userinfo.setcountry(rs.getString("country"));
			userinfo.setphone(rs.getString("phone"));
			userinfo.setpincode(rs.getString("pincode"));
			userinfos.add(userinfo);
		}
		return userinfos;
	}

	public static boolean addUserinfo(Userinfo userinfo) throws Exception {
		final String sql = "INSERT INTO userinfo (userid,photoid,firstname,lastname,address,city,state,country,phone,pincode) VALUES("+userinfo.getuserid()+","+userinfo.getphotoid()+",'"+userinfo.getfirstname()+"','"+userinfo.getlastname()+"','"+userinfo.getaddress()+"','"+userinfo.getcity()+"','"+userinfo.getstate()+"','"+userinfo.getcountry()+"','"+userinfo.getphone()+"','"+userinfo.getpincode()+"')";
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean removeUserinfo(Userinfo userinfo) throws Exception {
		final String sql = "DELETE FROM userinfo WHERE id="+userinfo.getid();
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else { 
			return false;
		}
	}

	public static List<User> getUsers() throws Exception {
		final List<User> users = new ArrayList<User>();
		final String sql = "select * from users";

		final Statement stmt = con.createStatement();
		final ResultSet rs = stmt.executeQuery(sql);

		while(rs.next()){
			User user = new User();
			user.setid(rs.getLong("id"));
			user.setusername(rs.getString("username"));
			user.setpassword(rs.getString("password"));
			user.setusertype(rs.getString("usertype"));
			user.setemailid(rs.getString("emailid"));
			user.setstatus(rs.getString("status"));
			users.add(user);
		}
		return users;
	}

	public static boolean addUser(User user) throws Exception {
		final String sql = "INSERT INTO users (username,password,usertype,emailid,status) VALUES('"+user.getusername()+"','"+user.getpassword()+"','"+user.getusertype()+"','"+user.getemailid()+"','"+user.getstatus()+"')";
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean removeUser(User user) throws Exception {
		final String sql = "DELETE FROM users WHERE id="+user.getid();
		final Statement stmt = con.createStatement();
		if(stmt.executeUpdate(sql)>0) {
			return true;
		} else { 
			return false;
		}
	}

}

