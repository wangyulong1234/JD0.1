package com.dao.inter;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.sql.*;
import java.io.*;



public class QueryExecutor
{
	JFrame jf = new JFrame("查询执行器");
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JButton execBn = new JButton("查询");
	//用于装载数据表的JComboBox
	private JTextField sqlField = new JTextField(45);
	private static Connection conn;
	private static Statement stmt;
	private ResultSet rs;

	static
	{
		try
		{
			Properties props = new Properties();
				
			FileInputStream in = new FileInputStream("src/oracle.ini");
			props.load(in);
			in.close();
			String drivers = props.getProperty("driver");
			String url = props.getProperty("url");
			String username = props.getProperty("user");
			String password = props.getProperty("pass");
			//加载数据库驱动
			Class.forName(drivers);
			//取得数据库连接
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void init()
	{
		JPanel top = new JPanel();
		top.add(sqlField);
		top.add(execBn);
		execBn.addActionListener(new ExceListener());
		jf.add(top , BorderLayout.NORTH);
		jf.setSize(640, 480);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	class ExceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			try
			{
				if (scrollPane != null)
				{
					//从主窗口中删除表格
					jf.remove(scrollPane);
				}
				rs = stmt.executeQuery(sqlField.getText());
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector<String> columnNames =  new Vector<String>();
				Vector<Vector<String>> data = new Vector<Vector<String>>();//存放所有记录的集合
				for (int i = 0 ; i < rsmd.getColumnCount(); i++ )
				{
					columnNames.add(rsmd.getColumnName(i + 1));//把字段名添加到这个集合中
				}
				while (rs.next())
				{
					Vector<String> v = new Vector<String>();//存一条记录的所有字段
					for (int i = 0 ; i < rsmd.getColumnCount(); i++ )
					{
						v.add(rs.getString(i + 1));
					}
					data.add(v);
				}
				System.out.println("---------" + columnNames);
				model = new DefaultTableModel(data , columnNames);
				JTable table = new JTable(model);
				scrollPane = new JScrollPane(table);
				jf.add(scrollPane, BorderLayout.CENTER);
				jf.validate();//刷新frame 让table显示
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) 
	{
		new QueryExecutor().init();
	}
}