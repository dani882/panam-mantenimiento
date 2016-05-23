package com.cementopanam.jrivera.modelo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

public class Test {

	Connection con = null;

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		
	    String version = System.getProperty("java.version");
	    char minor = version.charAt(2);
	    char point = version.charAt(4);
	    
	    System.out.println(minor);
	    System.out.println(point);
	    
	    if(minor != '9' || point < '1')
	      throw new RuntimeException("JDK 1.4.1 or higher " +
	        "is required to run the examples in this book.");
	    System.out.println("JDK version "+ version + " found");
		
		
		
		
		
		
		
		
		
		
		/*long currentTime = System.currentTimeMillis();

		System.out.println("Tiempo" + currentTime);
		int year2 = Calendar.getInstance().get(Calendar.YEAR);
		Test t = new Test();
		t.testDB();

		System.out.println(year2);
*/	}

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