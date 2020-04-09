package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
			boolean done = false;
		 	do {
			String url = "jdbc:mysql://localhost:3306/databasetest?characterEncoding=UTF-8&serverTimezone=UTC";
			String id = "root";
			String password = "1234";
	        Connection conn = null;
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        
	        //db 적재
	        try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, id, password);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("(DateBase 연동 실패)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("(DateBase 연동 실패)");
			} 
	        
	        String select = "select * from addressbook;";
	        String insert = "insert into addressbook ( username, tel, email, address) values (?,?,?,?);";
	        String update ="update addressbook set email = ? where id = ?; ";
	        String delete = "delete from addressbook where id = ?;";
	        
	        
	        Scanner scan = new Scanner(System.in);

	       
	        //db 사용
	        try {
	        	System.out.println("1:read 2:insert 3:update 4:delete" );
	        	System.out.print("입력:");
	        	int menu=scan.nextInt();
	    		if(menu==1) {
				ps = conn.prepareStatement(select);
	        
	        	rs= ps.executeQuery();
	        	 
	        	while(rs.next()) {
	        		
	        		System.out.println(rs.getString("id")+" | "+rs.getString("username")+" | "+rs.getString("tel")+" | "+rs.getString("email")+" | "+rs.getString("address"));
	               
	        	}
	        	
	        	rs.close();
	        	ps.close();
	        	conn.close(); 
	    		}
	    		
	    		else if(menu==2) {
	    			System.out.println("username tel email address 순으로 입력");
	    			String username = scan.next();
	    			
	    			String tel = scan.next();
	    			String email = scan.next();
	    			String address = scan.next();
	    			
	    			
	    			ps = conn.prepareStatement(insert);
	    			ps.setString(1, username );
	    			ps.setString(2, tel);
	    			ps.setString(3, email);
	    			ps.setString(4, address);
	    	        ps.executeUpdate();
	    	        System.out.println("투플 추가 완료!!!");
	    	        
	    	       
		        	ps.close();
		        	conn.close();
	    	      
	    		}
	    		
	    		else if(menu==3) {
	    			System.out.println("업데이트할 투플 id email 순으로 입력");
	    			
	    			int id1 = scan.nextInt();
	    			String email = scan.next();
	    			
	    			ps = conn.prepareStatement(update);
	    	        ps.setString(1, email);
	    	        ps.setInt(2, id1);
	    	        ps.executeUpdate();
	    	        System.out.println("투플 이메일 변경 완료!!!");
	    	        
	    	     
		        	ps.close();
		        	conn.close();
	    		}
	    		
	    		else if (menu==4) {
	    			System.out.println("제거할 투플 id 입력");
	    			int id2 = scan.nextInt();
	    			ps = conn.prepareStatement(delete);
	    			ps.setInt(1, id2);
	            	ps.executeUpdate();
	            	System.out.println("투플 삭제!");
	    		}
	    		
	        	
	        	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
		 	} while (!done);

	}

}
