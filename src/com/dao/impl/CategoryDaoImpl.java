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

	// 1.组合Connection
	private Connection conn;

	public CategoryDaoImpl() {
		conn = ConnOracle.getConnection();
	}

	@Override
	public int addCategory(Category category) throws Exception{
		int count = 0;
		// 3.建立通道
		String sql = "insert into category values(seq_category.nextval,?,?)";
		// 获得了一个预编译的通道 相当于IO通道 可以用它来发送sql语句
		PreparedStatement pstmt = null;

		// 默认事务是关闭的 调用addCategory增加一条记录的时候 会自动提交
		// 开启事务

		try {

			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname());
			pstmt.setString(2, category.getCdesc());
			// 4.执行并返回结果集
			count = pstmt.executeUpdate();// 可以执行除了DQL以外所有的语句 DML 返回的是受影响的行数
											// DCL或DDL语句 返回值是0

		
			if (count >= 1) {
				System.out.println("添加商品种类成功!");
			} else {
				System.out.println("没有添加任何商品种类!");
			}
		} catch (SQLException e) {
			
			System.out.println("建立通道或添加商品种类失败");
			e.printStackTrace();
			
			throw new Exception("商品种类添加失败");//异常转译
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int deleteCategory(Category category) {
		int count = 0;
		// 3.建立通道
		String sql = "delete from category where cid=?";
		// 获得了一个预编译的通道 相当于IO通道 可以用它来发送sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, category.getCid());

			// 4.执行并返回结果集
			count = pstmt.executeUpdate();// 可以执行除了DQL以外所有的语句 DML 返回的是受影响的行数
											// DCL或DDL语句 返回值是0

			if (count >= 1) {
				System.out.println("删除商品种类成功!");
			} else {
				System.out.println("没有删除任何商品种类!");
			}
		} catch (SQLException e) {
			System.out.println("建立通道或删除商品种类失败");
			e.printStackTrace();
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public int updateCategory(Category category) {
		int count = 0;
		// 3.建立通道
		String sql = "update category set cname=?,cdesc=? where cid=?";
		// 获得了一个预编译的通道 相当于IO通道 可以用它来发送sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, category.getCname());
			pstmt.setString(2, category.getCdesc());
			pstmt.setInt(3, category.getCid());
			// 4.执行并返回结果集
			count = pstmt.executeUpdate();// 可以执行除了DQL以外所有的语句 DML 返回的是受影响的行数
											// DCL或DDL语句 返回值是0

			if (count >= 1) {
				System.out.println("修改商品种类成功!");
			} else {
				System.out.println("没有修改任何商品种类!");
			}
		} catch (SQLException e) {
			System.out.println("建立通道或修改商品种类失败");
			e.printStackTrace();
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(null, pstmt, conn);
		}

		return count;
	}

	@Override
	public Category getCategoryById(int id) {
		Category category = new Category();

		// 3.建立通道
		String sql = "select * from category where cid=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);
			// 4.执行并返回结果集
			rs = pstmt.executeQuery();

			while (rs.next()) {
				category.setCid(rs.getInt("cid"));
				category.setCname(rs.getString("cname"));
				category.setCdesc(rs.getString("cdesc"));
			}
		} catch (SQLException e) {
			System.out.println("建立通道或查询单个商品种类失败");
			e.printStackTrace();
		} finally {
			// 5.关闭
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
		// 3.建立通道
		try {
			stmt = conn.createStatement();
			// 4.执行并返回结果集
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
			System.out.println("创建通道或查询结果集失败");
			e.printStackTrace();
		} finally {
			// 5.关闭
			ConnOracle.closeConnection(rs, stmt, conn);
		}

		return list;
	}

	public void testScrollAndUpdateResultSet(String sql) {
		Statement stmt = null;
		ResultSet rs = null;

		// 三.建立通道
		try {
			// TYPE_FORWARD_ONLY(只能向前滚动),它是jdk1.4以前的默认值
			// TYPE_SCROLL_INSENSITIVE(可自由滚动 可滚动的结果集),但是底层数据的改变不会影响ResultSet的内容
			// TYPE_SCROLL_SENSITIVE(可自由滚动 可滚动的结果集),但是底层数据的改变 会 影响ResultSet的内容
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			rs = stmt.executeQuery(sql);

			rs.last();
			int rowCount = rs.getRow();
			System.out.println("总的记录数:" + rowCount);

			for (int i = rowCount; i > 0; i--) {

				rs.absolute(i);
				System.out.println("编号:" + rs.getInt(1) + ",名称:"
						+ rs.getString(2) + ",描述:" + rs.getString(3));
				rs.updateString(3, "描述" + i);
				// 提交修改
				rs.updateRow();
			}
		} catch (SQLException e) {
			System.out.println("建立通道失败");
			e.printStackTrace();
		} finally {
			// 五.关闭
			ConnOracle.closeConnection(rs, stmt, conn);
		}

	}

	public void transfer(int from, int to, int money) {
		System.out.println("****************look here*********************");
		System.out.println(conn);
		// 三.建立通道

		Statement stmt = null;
		int fromSum = 0;
		Savepoint savepoint = null;

		try {
			conn.setAutoCommit(false);// 关闭自动提交 开启事务功能

			stmt = conn.createStatement();

			// 四.执行并返回结果集

			// 检查账号存在不存在
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
				System.out.println("转出账号不存在");
				System.exit(-1);
			}

			ResultSet rs2 = stmt.executeQuery(checkToExist);
			boolean flag2 = rs2.next();

			if (flag2 == false) {
				System.out.println("转入账号不存在");
				System.exit(-1);
			}

			// 金额不能为负数
			if (money <= 0) {
				System.out.println("转账金额必须为正数");
				System.exit(-1);
			}

			// 金额不能大于from账号的余额

			if (fromSum < money) {
				System.out.println("余额不足");
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
			// 都存在开始转
			stmt.executeUpdate(sql1);

			stmt.executeUpdate(sql2);

			// 设置中间点
			savepoint = conn.setSavepoint();

			int a = 10 / 0;
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql4);

			conn.commit();
			System.out.println("转账成功");
		} catch (SQLException e) {
			try {
				System.out.println("异常捕获");

				// conn.rollback();
				conn.rollback(savepoint);
				System.out.println("转账失败");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("建立通道失败");
			e.printStackTrace();
		} catch (Exception e) {
			try {
				// conn.rollback();
				conn.rollback(savepoint);
				System.out.println("转账失败");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("建立通道失败");
			e.printStackTrace();
		} finally {
			// 五.关闭
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("关闭pstmt通道失败");
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("关闭数据库连接失败");
					e.printStackTrace();
				}
			}
		}

	}

	public void testBatchUpdate() {

		Statement stmt = null;
		try {

			String sql1 = "insert into account values(5,'杨帆1',1000)";
			String sql2 = "insert into account values(6,'杨帆2',2000)";

			String sql3 = "insert into account values(7,'杨帆3',3000)";
			String sql4 = "insert into account values(8,'杨帆4',4000)";

			stmt = conn.createStatement();
			boolean autoCommit = conn.getAutoCommit();

			conn.setAutoCommit(false);
			// 收集多条SQL语句 等待一起发送
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);

			int[] countArr = stmt.executeBatch();

			for (int i = 0; i < countArr.length; i++) {
				System.out.println("第" + i + "条SQL语句受影响的行数为:" + countArr[i]);
			}

			conn.commit();

			conn.setAutoCommit(autoCommit);
			System.out.println("批量更新成功");
		} catch (SQLException e) {
			try {
				conn.rollback();

				System.out.println("批量更新失败");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("建立通道失败");
			e.printStackTrace();
		} catch (Exception e) {
			try {
				conn.rollback();

				System.out.println("批量更新失败");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			System.out.println("建立通道失败");
			e.printStackTrace();
		} finally {
			// 五.关闭
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("关闭pstmt通道失败");
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("关闭数据库连接失败");
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
