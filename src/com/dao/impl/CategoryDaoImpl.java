package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.inter.CategoryDao;
import com.util.ConnOracle;
import com.vo.Category;

public class CategoryDaoImpl implements CategoryDao {

	// 1.���Connection
	private Connection conn;

	public CategoryDaoImpl() {
		conn = ConnOracle.getConnection();
	}

	@Override
	public int addCategory(Category category) throws Exception{
		int count = 0;
		// 3.����ͨ��
		String sql = "insert into category values(seq_category.nextval,?,?)";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;

		// Ĭ�������ǹرյ� ����addCategory����һ����¼��ʱ�� ���Զ��ύ
		// ��������

		try {

			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname());
			pstmt.setString(2, category.getCdesc());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

		
			if (count >= 1) {
				System.out.println("�����Ʒ����ɹ�!");
			} else {
				System.out.println("û������κ���Ʒ����!");
			}
		} catch (SQLException e) {
			
			System.out.println("����ͨ���������Ʒ����ʧ��");
			e.printStackTrace();
			
			throw new Exception("��Ʒ�������ʧ��");//�쳣ת��
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int deleteCategory(Category category) {
		int count = 0;
		// 3.����ͨ��
		String sql = "delete from category where cid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, category.getCid());

			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("ɾ����Ʒ����ɹ�!");
			} else {
				System.out.println("û��ɾ���κ���Ʒ����!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ����ɾ����Ʒ����ʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int updateCategory(Category category) {
		int count = 0;
		// 3.����ͨ��
		String sql = "update category set cname=?,cdesc=? where cid=?";
		// �����һ��Ԥ�����ͨ�� �൱��IOͨ�� ��������������sql���
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname());
			pstmt.setString(2, category.getCdesc());
			pstmt.setInt(3, category.getCid());
			// 4.ִ�в����ؽ����
			count = pstmt.executeUpdate();// ����ִ�г���DQL�������е���� DML ���ص�����Ӱ�������
											// DCL��DDL��� ����ֵ��0

			if (count >= 1) {
				System.out.println("�޸���Ʒ����ɹ�!");
			} else {
				System.out.println("û���޸��κ���Ʒ����!");
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����޸���Ʒ����ʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public Category getCategoryById(int id) {
		Category category = new Category();

		// 3.����ͨ��
		String sql = "select * from category where cid=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			// 4.ִ�в����ؽ����
			rs = pstmt.executeQuery();

			while (rs.next()) {
				category.setCid(rs.getInt("cid"));
				category.setCname(rs.getString("cname"));
				category.setCdesc(rs.getString("cdesc"));
			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ������Ʒ����ʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, pstmt, conn);
		}

		return category;
	}

	@Override
	public List<Category> getPageByQuery(String sql) {

		List<Category> list = new ArrayList<Category>();
		Category category = null;
		ResultSet rs = null;
		Statement stmt = null;
		// 3.����ͨ��
		try {
			stmt = conn.createStatement();
			// 4.ִ�в����ؽ����
			rs = stmt.executeQuery(sql);
			System.out.println("***************look here*******************");
			System.out.println(rs);
			while (rs.next()) {
				category = new Category();

				category.setCid(rs.getInt("cid"));
				category.setCname(rs.getString("cname"));
				category.setCdesc(rs.getString("cdesc"));

				list.add(category);

			}
		} catch (SQLException e) {
			System.out.println("����ͨ�����ѯ�����ʧ��");
			e.printStackTrace();
		} finally {
			// 5.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}

	public void testScrollAndUpdateResultSet(String sql) {
		Statement stmt = null;
		ResultSet rs = null;

		// ��.����ͨ��
		try {
			// TYPE_FORWARD_ONLY(ֻ����ǰ����),����jdk1.4��ǰ��Ĭ��ֵ
			// TYPE_SCROLL_INSENSITIVE(�����ɹ��� �ɹ����Ľ����),���ǵײ����ݵĸı䲻��Ӱ��ResultSet������
			// TYPE_SCROLL_SENSITIVE(�����ɹ��� �ɹ����Ľ����),���ǵײ����ݵĸı� �� Ӱ��ResultSet������
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			rs = stmt.executeQuery(sql);

			rs.last();
			int rowCount = rs.getRow();
			System.out.println("�ܵļ�¼��:" + rowCount);

			for (int i = rowCount; i > 0; i--) {

				rs.absolute(i);
				System.out.println("���:" + rs.getInt(1) + ",����:"
						+ rs.getString(2) + ",����:" + rs.getString(3));
				rs.updateString(3, "����" + i);
				// �ύ�޸�
				rs.updateRow();
			}
		} catch (SQLException e) {
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
		} finally {
			// ��.�ر�
			ConnOracle.closeConnection(rs, stmt, conn);
		}

	}

	public void transfer(int from, int to, int money) {
		System.out.println("****************look here*********************");
		System.out.println(conn);
		// ��.����ͨ��

		Statement stmt = null;
		int fromSum = 0;
		Savepoint savepoint = null;

		try {
			conn.setAutoCommit(false);// �ر��Զ��ύ ����������

			stmt = conn.createStatement();

			// ��.ִ�в����ؽ����

			// ����˺Ŵ��ڲ�����
			String checkFromExist = "select * from account where account_no="
					+ from;
			String checkToExist = "select * from account where account_no="
					+ to;
			ResultSet rs1 = stmt.executeQuery(checkFromExist);
			boolean flag1 = rs1.next();
			if (flag1 == true) {
				fromSum = rs1.getInt(3);
			}

			if (flag1 == false) {
				System.out.println("ת���˺Ų�����");
				System.exit(-1);
			}

			ResultSet rs2 = stmt.executeQuery(checkToExist);
			boolean flag2 = rs2.next();

			if (flag2 == false) {
				System.out.println("ת���˺Ų�����");
				System.exit(-1);
			}

			// ����Ϊ����
			if (money <= 0) {
				System.out.println("ת�˽�����Ϊ����");
				System.exit(-1);
			}

			// ���ܴ���from�˺ŵ����

			if (fromSum < money) {
				System.out.println("����");
				System.exit(-1);
			}

			String sql1 = "update account set sum=sum-" + money
					+ " where account_no=" + from;
			String sql2 = "update account set sum=sum+" + money
					+ " where account_no=" + to;

			String sql3 = "update account set sum=5000" + " where account_No="
					+ from;
			String sql4 = "update account set sum=5000" + " where account_No="
					+ to;
			System.out.println(sql1);
			System.out.println(sql2);
			// �����ڿ�ʼת
			stmt.executeUpdate(sql1);

			stmt.executeUpdate(sql2);

			// �����м��
			savepoint = conn.setSavepoint();

			int a = 10 / 0;
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql4);

			conn.commit();
			System.out.println("ת�˳ɹ�");
		} catch (SQLException e) {
			try {
				System.out.println("�쳣����");

				// conn.rollback();
				conn.rollback(savepoint);
				System.out.println("ת��ʧ��");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
		} catch (Exception e) {
			try {
				// conn.rollback();
				conn.rollback(savepoint);
				System.out.println("ת��ʧ��");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
		} finally {
			// ��.�ر�
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("�ر�pstmtͨ��ʧ��");
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("�ر����ݿ�����ʧ��");
					e.printStackTrace();
				}
			}
		}

	}

	public void testBatchUpdate() {

		Statement stmt = null;
		try {

			String sql1 = "insert into account values(5,'�1',1000)";
			String sql2 = "insert into account values(6,'�2',2000)";

			String sql3 = "insert into account values(7,'�3',3000)";
			String sql4 = "insert into account values(8,'�4',4000)";

			stmt = conn.createStatement();
			boolean autoCommit = conn.getAutoCommit();

			conn.setAutoCommit(false);
			// �ռ�����SQL��� �ȴ�һ����
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);

			int[] countArr = stmt.executeBatch();

			for (int i = 0; i < countArr.length; i++) {
				System.out.println("��" + i + "��SQL�����Ӱ�������Ϊ:" + countArr[i]);
			}

			conn.commit();

			conn.setAutoCommit(autoCommit);
			System.out.println("�������³ɹ�");
		} catch (SQLException e) {
			try {
				conn.rollback();

				System.out.println("��������ʧ��");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
		} catch (Exception e) {
			try {
				conn.rollback();

				System.out.println("��������ʧ��");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("����ͨ��ʧ��");
			e.printStackTrace();
		} finally {
			// ��.�ر�
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("�ر�pstmtͨ��ʧ��");
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("�ر����ݿ�����ʧ��");
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		CategoryDaoImpl dao = new CategoryDaoImpl();
		String sql = "select * from category";
		List<Category> list = dao.getPageByQuery(sql);
	}
}
