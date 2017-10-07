package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��˾:�������ѧԺ
 * ����:zhangzy
 * ʱ��:2017��8��30�� ����9:26:05
 * ����:�������ݿ�Ĺ�����
 */
public class ConnOracle {

	public static Connection getConnection(){
		Connection conn = null;
		
		String className = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:TEST";
		String user = "jidi16";
		String password = "123456";
		//1.��������
		try {
			Class.forName(className);
			
		
		} catch (ClassNotFoundException e) {
			System.out.println("������û���ҵ�");
			e.printStackTrace();
		}
		
		//2.��������
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("�������ݿ�ʧ��");
			e.printStackTrace();
		}
		

		
		
		return conn;
	}
	
	public static void closeConnection(ResultSet rs,Statement stmt,Connection conn){
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("�ر�ͨ��ʧ��");
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("�ر����ݿ�����ʧ��");
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(ConnOracle.getConnection());
	}
}
