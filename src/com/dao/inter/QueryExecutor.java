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
	JFrame jf = new JFrame("��ѯִ����");
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JButton execBn = new JButton("��ѯ");
	//����װ�����ݱ���JComboBox
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
			//�������ݿ�����
			Class.forName(drivers);
			//ȡ�����ݿ�����
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
					//����������ɾ������
					jf.remove(scrollPane);
				}
				rs = stmt.executeQuery(sqlField.getText());
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector<String> columnNames =  new Vector<String>();
				Vector<Vector<String>> data = new Vector<Vector<String>>();//������м�¼�ļ���
				for (int i = 0 ; i < rsmd.getColumnCount(); i++ )
				{
					columnNames.add(rsmd.getColumnName(i + 1));//���ֶ������ӵ����������
				}
				while (rs.next())
				{
					Vector<String> v = new Vector<String>();//��һ����¼�������ֶ�
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
				jf.validate();//ˢ��frame ��table��ʾ
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