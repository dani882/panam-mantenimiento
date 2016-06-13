package com.cementopanam.jrivera.modelo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

public class Test {

	Connection con = null;

	static PreparedStatement ps = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		
/*	    String version = System.getProperty("java.version");
	    char minor = version.charAt(2);
	    char point = version.charAt(4);
	    
	    System.out.println(minor);
	    System.out.println(point);
	    
	    if(minor != '9' || point < '1')
	      throw new RuntimeException("JDK 1.4.1 or higher " +
	        "is required to run the examples in this book.");
	    System.out.println("JDK version "+ version + " found");
		*/
		
		int[] num = new int[]{1,2,3};
		StringBuffer sb = new StringBuffer();
		
		String repeticion = StringUtils.repeat("hola", num.length - 1);
		sb.append("SELECT * FROM items WHERE id IN(").append(StringUtils.repeat("?", num.length)).append(")");
		for (int i = 0; i < num.length; i++) {
			System.out.println("SB es " + sb +" Numero "+ num[i]);
		//	(i, num[i]);
		}
		
		
		
		System.out.println("Cantidad: " + repeticion);
		System.out.println("Cantidad: " + sb);
		
		
		ResultSet rs;
		try {
			rs = ps.executeQuery();
			
			while (rs.next())
			{
				int id = rs.getInt("id");
				String str1 = rs.getString("str1");
				System.out.println(id + ", " + str1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		//sb.append("SELECT * FROM items WHERE id IN(").append(StringUtils.repeat("?", num.length)).append(")");
		
/*		sb.append("SELECT * FROM items WHERE id IN(").append(StringUtils.repeat("?", num.length)).append(")");
		for (int i = 0; i < num.length; i++) {
			ps.setInt(i, num[i]);
		}
		ps.executeQuery();	
		*/
		
		
		
		
		}

	private void testDB() {

		int port = 3306;

		try {
			InetAddress addr = InetAddress.getByName("localhost");
			SocketAddress sockaddr = new InetSocketAddress(addr, port);
			Socket sock = new Socket();
			sock.connect(sockaddr, 2000);
			System.out.println(sock);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // open the connection with a 2 seconds timeout
	}
}