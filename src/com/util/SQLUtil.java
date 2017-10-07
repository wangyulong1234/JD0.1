package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 * ��˾:�������ѧԺ
 * ����:zhangzy
 * ʱ��:2017��8��31�� ����3:21:34
 * ����:ִ�д洢���̵Ĺ�����
 */
public class SQLUtil {

	private Connection conn;
	
	public SQLUtil(){
		conn = ConnOracle.getConnection();
	}
	
	public void callProcedure(){
		
		//3.����ͨ��
		String sql = "{call proc_increase_salary(?,?)}";
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, 10);//��10�Ų����ǹ���
			cstmt.registerOutParameter(2, Types.VARCHAR);
			
			//4.ִ�в����ؽ����
			cstmt.executeUpdate();
			
			System.out.println("ִ�д洢���̳ɹ�");
			
			String desc = cstmt.getString(2);
			System.out.println(desc);
			
		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
		}finally{
			//5.�ر�
			ConnOracle.closeConnection(null, cstmt, conn);
		}
	}
	
	public int executeSQLExceptDQL(String sql){
		int count = 0;
		//3.����ͨ��
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			//4.ִ��
			count = stmt.executeUpdate(sql);//DML ������Ӱ�������   DDL��DCL  ���ص���0
			
			System.out.println("ִ�гɹ�");
			System.out.println(count);
		} catch (SQLException e) {
			System.out.println("����ͨ����ִ��ʧ��");
			e.printStackTrace();
		}finally{
			//5.�ر�
			ConnOracle.closeConnection(null, stmt, conn);
		}
		
		return count;
		
	
	}
	
	public static void main(String[] args) {
		SQLUtil util = new SQLUtil();
		
		String sql = "create table student(sno number(10) primary key,name varchar2(50)," + 
				     " age number(3))";
		util.executeSQLExceptDQL(sql);
	}
	
}
