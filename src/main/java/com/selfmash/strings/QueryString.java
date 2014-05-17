package com.selfmash.strings;

public class QueryString {

	public static final String getUser = "from User as u where u.login = :login";
	
	public static final String addFriend = "insert into friends values ( :userId, :friendId, 0)";
	
	public static final String selectFriends = "select distinct * from user as u "
			+ "inner join friends as f on f.friend_id = u.id and accepted = 1 "
			+ "join user_role  as ur on ur.user_id = u.id where f.user_id = :userId "
			+ "union select distinct * from user as u "
			+ "inner join friends as f on f.user_id = u.id and accepted = 1  "
			+ "join user_role  as ur on ur.user_id = u.id where f.friend_id = :userId";
}