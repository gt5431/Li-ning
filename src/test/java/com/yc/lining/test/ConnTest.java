package com.yc.lining.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ConnTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private Connection conn;

	@Test
	public void testConn1() {
		try {
			conn = dataSource.getConnection();
			assertNotNull("连接失败!!",conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConn2() {
		conn = sqlSessionFactory.openSession(true).getConnection();
		assertNotNull("连接失败!!",conn);
	}



}
