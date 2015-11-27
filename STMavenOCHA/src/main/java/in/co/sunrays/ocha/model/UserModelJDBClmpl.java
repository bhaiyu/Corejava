/*package in.co.sunrays.ocha.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import in.co.sunrays.ocha.bean.UserDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.exception.RecordNotFoundException;
import in.co.sunrays.util.EmailBuilder;
import in.co.sunrays.util.EmailMessage;
import in.co.sunrays.util.JDBCDataSource;

public class UserModelJDBClmpl implements UserModelInt {
	private static final String UserData = null;

	public void update(UserDTO userdata2) throws ApplicationException,
			DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		UserDTO dtoExist = findByLogin(userdata2.getLogin());
		if (dtoExist != null && !(dtoExist.getId() == userdata2.getId())) {
			throw new DuplicateRecordException("LoginId is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=?,USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, dtoExist.getFirstName());
			pstmt.setString(2, userdata2.getLastName());
			pstmt.setString(3, userdata2.getLogin());
			pstmt.setString(4, userdata2.getPassword());
			pstmt.setDate(5, new java.sql.Date(userdata2.getDob().getTime()));
			pstmt.setString(6, userdata2.getMobileNo());
			pstmt.setLong(7, userdata2.getRoleId());
			pstmt.setInt(8, userdata2.getUnSuccessfulLogin());
			pstmt.setString(9, userdata2.getGender());
			pstmt.setTimestamp(10, userdata2.getLastLogin());
			pstmt.setString(11, userdata2.getLock());
			pstmt.setString(12, userdata2.getRegisteredIP());
			pstmt.setString(13, userdata2.getLastLoginIP());
			pstmt.setString(14, userdata2.getCreatedBy());
			pstmt.setString(15, userdata2.getModifiedBy());
			pstmt.setTimestamp(16, userdata2.getCreatedDatetime());
			pstmt.setTimestamp(17, userdata2.getModifiedDatetime());
			pstmt.setLong(18, userdata2.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete RollbackException"
								+ ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public boolean forgetPassword(String login) throws ApplicationException,
			RecordNotFoundException {
		UserDTO userData = findByLogin(login);
		boolean flag = false;

		if (UserData == null) {
			throw new RecordNotFoundException("Email Id Dose not matched");
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("SUNARYS ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		// EmailUtility.sendMail(msg);
		flag = true;
		return flag;
	}

	private static Logger log = Logger.getLogger(UserModelJDBClmpl.class);

	public Integer nextPK() throws Exception {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM ST_USER");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public UserDTO findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_USER WHERE LOGIN=?");
		UserDTO dto = null;
		Connection conn = null;
		System.out.println("sql" + sql);
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLock(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return dto;
	}

	public long add(UserDTO dto) throws ApplicationException,
			DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		UserDTO existDto = findByLogin(dto.getLogin());

		if (existDto != null) {
			throw new DuplicateRecordException("Login Id allready exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println(pk + " in modelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, dto.getFirstName());
			pstmt.setString(3, dto.getLastName());
			pstmt.setString(4, dto.getLogin());
			pstmt.setString(5, dto.getPassword());
			pstmt.setDate(6, new java.sql.Date(dto.getDob().getTime()));
			pstmt.setString(7, dto.getMobileNo());
			pstmt.setLong(8, dto.getRoleId());
			pstmt.setInt(9, dto.getUnSuccessfulLogin());
			pstmt.setString(10, dto.getGender());
			pstmt.setString(11, dto.getLastLoginIP());
			pstmt.setString(12, dto.getLock());
			pstmt.setString(13, dto.getRegisteredIP());
			pstmt.setString(14, dto.getLastLoginIP());
			pstmt.setString(15, dto.getCreatedBy());
			pstmt.setString(16, dto.getModifiedBy());
			pstmt.setTimestamp(17, dto.getCreatedDatetime());
			pstmt.setTimestamp(18, dto.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("modal add End");
		return pk;
	}

	public void delete(UserDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM ST_USER WHERE ID=?");
			pstmt.setLong(1, dto.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception"
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Execption : Exception in delete user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	public UserDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPk Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ID=?");
		UserDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLock(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Databasse Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in geting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPk End");
		return dto;
	}

	public List search(UserDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	public List search(UserDTO dto, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model serch Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND id = " + dto.getId());
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + dto.getFirstName() + "%'");
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + dto.getLastName() + "%'");
			}
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + dto.getLogin() + "%'");
			}
			if (dto.getPassword() != null && dto.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + dto.getPassword() + "%'");
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + dto.getGender());
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO = " + dto.getMobileNo());
			}
			if (dto.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + dto.getRoleId());
			}
			if (dto.getUnSuccessfulLogin() > 0) {
				sql.append(" AND UNSUCCESSFUL_LOGIN = "
						+ dto.getUnSuccessfulLogin());
			}
			if (dto.getGender() != null && dto.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + dto.getGender() + "%'");
			}
			if (dto.getLastLogin() != null && dto.getLastLogin().getTime() > 0) {
				sql.append(" AND LAST_LOGIN = " + dto.getLastLogin());
			}
			if (dto.getRegisteredIP() != null
					&& dto.getRegisteredIP().length() > 0) {
				sql.append(" AND REGISTERED_IP like '" + dto.getRegisteredIP()
						+ "%'");
			}
			if (dto.getLastLoginIP() != null
					&& dto.getLastLoginIP().length() > 0) {
				sql.append(" AND LAST_LOGIN_IP like '" + dto.getLastLoginIP()
						+ "%'");
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + "," + pageSize);
		}
		System.out.println(sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLock(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Database Exceotion..", e);
			throw new ApplicationException(
					"Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.error("Model search End");
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_USER");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLock(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return (List) list;

	}

	public boolean changePassword(Long id, String oldPassword,
			String newPassword) throws RecordNotFoundException,
			ApplicationException {

		log.debug("model changePassword Started");
		boolean flag = false;
		UserDTO dtoExit = null;

		dtoExit = findByPK(id);
		if (dtoExit != null && dtoExit.getPassword().equals(oldPassword)) {
			dtoExit.setPassword(newPassword);
			try {
				update(dtoExit);
			} catch (DuplicateRecordException e) {
				log.equals(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", dtoExit.getLogin());
		map.put("password", dtoExit.getPassword());
		map.put("firstName", dtoExit.getFirstName());
		map.put("lastName", dtoExit.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dtoExit.getLogin());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		// EmailUtility.sendMail(msg);

		log.debug("Model changePassword End");
		return flag;

	}

	public UserDTO authenticate(String login, String password)
			throws ApplicationException {
		log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");
		UserDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLock(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model authenticate End");
		return dto;
	}

	public boolean lock(String login) throws RecordNotFoundException,
			ApplicationException {
		log.debug("Service lok Started");
		boolean flag = false;
		UserDTO dtoExist = null;
		try {
			dtoExist = findByLogin(login);
			if (dtoExist != null) {
				dtoExist.setLock(UserDTO.ACTIVE);
				update(dtoExist);
				flag = true;
			} else {
				throw new RecordNotFoundException("LoginId not exist");
			}
		} catch (DuplicateRecordException e) {
			log.error("Application Exception..", e);
			throw new ApplicationException("Database Exception");
		}
		log.debug("Service lock End");
		return flag;
	}

	public List getRoles(UserDTO dto) throws ApplicationException {
		log.debug("Model get roles Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_USER WHERE role_Id=?");
		Connection conn = null;
		List list = new ArrayList();
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, dto.getRoleId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setDob(rs.getDate(6));
				dto.setMobileNo(rs.getString(7));
				dto.setRoleId(rs.getLong(8));
				dto.setUnSuccessfulLogin(rs.getInt(9));
				dto.setGender(rs.getString(10));
				dto.setLastLogin(rs.getTimestamp(11));
				dto.setLock(rs.getString(12));
				dto.setRegisteredIP(rs.getString(13));
				dto.setLastLoginIP(rs.getString(14));
				dto.setCreatedBy(rs.getString(15));
				dto.setModifiedBy(rs.getString(16));
				dto.setCreatedDatetime(rs.getTimestamp(17));
				dto.setModifiedDatetime(rs.getTimestamp(18));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model get roles End");
		return list;
	}

	public long registerUser(UserDTO dto) throws ApplicationException,
			DuplicateRecordException {

		log.debug("Model add Started");

		long pk = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrtionMessage(map);
		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		// EmailUtility.sendMail(msg);
		return pk;
	}

	public boolean resetPassword(UserDTO dto) throws ApplicationException {

		String newPassword = String.valueOf(new Date().getTime()).substring(0,
				4);
		UserDTO userData = findByPK(dto.getId());
		userData.setPassword(newPassword);

		try {
			update(userData);
		} catch (DuplicateRecordException e) {

			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Login", dto.getLogin());
		map.put("password", dto.getPassword());
		map.put("firstName", dto.getFirstName());
		map.put("lastName", dto.getLastName());

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage("message");
		msg.setMessageType(EmailMessage.HTML_MSG);

		// EmailUtility.sendMail(msg);

		return true;

	}

	public UserDTO updateAccess(UserDTO dto) throws ApplicationException,
			DuplicateRecordException {
		// TODO Auto-generated method stub
		return null;
	}

}*/