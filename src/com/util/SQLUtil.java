package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 * 公司:蓝桥软件学院
 * 作者:zhangzy
 * 时间:2017年8月31日 下午3:21:34
 * 功能:执行存储过程的工具类
 */
public class SQLUtil {

	private Connection conn;
	
	public SQLUtil(){
		conn = ConnOracle.getConnection();
	}
	
	public void callProcedure(){
		
		//3.建立通道
		String sql = "{call proc_increase_salary(?,?)}";
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, 10);//给10号部门涨工资
			cstmt.registerOutParameter(2, Types.VARCHAR);
			
			//4.执行并返回结果集
			cstmt.executeUpdate();
			
			System.out.println("执行存储过程成功");
			
			String desc = cstmt.getString(2);
			System.out.println(desc);
			
		} catch (SQLException e) {
			System.out.println("建立通道失败");
			e.printStackTrace();
		}finally{
			//5.关闭
			ConnOracle.closeConnection(null, cstmt, conn);
		}
	}
	
	public int executeSQLExceptDQL(String sql){
		int count = 0;
		//3.建立通道
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			//4.执行
			count = stmt.executeUpdate(sql);//DML 返回受影响的行数   DDL或DCL  返回的是0
			
			System.out.println("执行成功");
			System.out.println(count);
		} catch (SQLException e) {
			System.out.println("创建通道或执行失败");
			e.printStackTrace();
		}finally{
			//5.关闭
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
