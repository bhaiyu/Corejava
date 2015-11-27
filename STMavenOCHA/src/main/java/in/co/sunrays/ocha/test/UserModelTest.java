package in.co.sunrays.ocha.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.ocha.bean.UserDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.exception.RecordNotFoundException;
import in.co.sunrays.ocha.model.UserModelHibImpl;
import in.co.sunrays.ocha.model.UserModelInt;
//import in.co.sunrays.ocha.model.UserModelJDBCImpl;

public class UserModelTest {

	// public static UserModelInt model = new UserModelHibImpl();

	public static UserModelInt model = new UserModelHibImpl();

	public static void main(String[] args) throws ParseException {
		// testAdd();
		// testDelete();
		 testUpdate();
		// testFindByPK();
		// testFindByLogin();
		// testSearch();
		// testList();
		// testGetRoles();
		// testAuthenticate();
		// testchangePassword();
		// testRegisterUser();
		 //testforgetPassword();
		// testresetPassword();
	}

	public static void testAdd() throws ParseException {

		try {
			UserDTO dto = new UserDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			dto.setId(8L);
			dto.setFirstName("jayesh");
			dto.setLastName("manikpuri");
			dto.setLogin("jayesh.manikpuri20@gmail.com");
			dto.setPassword("1234");
			dto.setDob(sdf.parse("31-12-1990"));
			dto.setRoleId(2L);
			dto.setUnSuccessfulLogin(2);
			dto.setGender("Male");
			dto.setLastLogin(new Timestamp(new Date().getTime()));
			dto.setLock("Yes");
			dto.setMobileNo("1234567890");
			dto.setCreatedBy("Admin");
			dto.setModifiedBy("Admin");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(dto);
			System.out.println("Successfully add");
			System.out.println(dto.getDob());
			UserDTO addedDto = model.findByPK(pk);
			if (addedDto == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		try {
			UserDTO dto = new UserDTO();
			long pk = 2;
			dto.setId(pk);
			model.delete(dto);
			UserDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws ParseException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			UserDTO dto = model.findByLogin("sunilmca575@gmail.com");
			dto.setFirstName("sonuOne");
			dto.setLastName("dshak");
			dto.setMobileNo("9173985746");
			dto.setDob(sdf.parse("31/12/1990"));

			model.update(dto);

		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPK() {
		try {
			UserDTO dto = new UserDTO();
			long pk = 2;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getRoleId());
			System.out.println(dto.getUnSuccessfulLogin());
			System.out.println(dto.getGender());
			System.out.println(dto.getLastLogin());
			System.out.println(dto.getLock());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByLogin() {
		UserDTO dto = new UserDTO();
		try {
			dto = model.findByLogin("jayesh.manikpuri20@gmail.com");
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getRoleId());
			System.out.println(dto.getUnSuccessfulLogin());
			System.out.println(dto.getGender());
			System.out.println(dto.getLastLogin());
			System.out.println(dto.getLock());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testList() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList();
			list = model.list(1, 3);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getLogin());
				System.out.println(dto.getPassword());
				System.out.println(dto.getDob());
				System.out.println(dto.getRoleId());
				System.out.println(dto.getUnSuccessfulLogin());
				System.out.println(dto.getGender());
				System.out.println(dto.getLastLogin());
				System.out.println(dto.getLock());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void testGetRoles() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList();
			dto.setRoleId(2);
			list = model.getRoles(dto);
			if (list.size() < 0) {
				System.out.println("Test Get Roles fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getLogin());
				System.out.println(dto.getPassword());
				System.out.println(dto.getDob());
				System.out.println(dto.getRoleId());
				System.out.println(dto.getUnSuccessfulLogin());
				System.out.println(dto.getGender());
				System.out.println(dto.getLastLogin());
				System.out.println(dto.getLock());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList();
			 //dto.setFirstName("jayesh");
			 dto.setLastName("dshak");
			//dto.setLogin("jayesh.manikpuri20@gmail.com");

			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getLogin());
				System.out.println(dto.getPassword());
				System.out.println(dto.getDob());
				System.out.println(dto.getRoleId());
				System.out.println(dto.getUnSuccessfulLogin());
				System.out.println(dto.getGender());
				System.out.println(dto.getLastLogin());
				System.out.println(dto.getLock());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testAuthenticate() {

		try {
			UserDTO dto = new UserDTO();
			dto.setLogin("jayesh.manikpuri20@gmail.com");
			dto.setPassword("1234");
			dto = model.authenticate(dto.getLogin(), dto.getPassword());
			if (dto != null) {
				System.out.println("Successfully login");

			} else {
				System.out.println("Invalied login Id & password");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testchangePassword() {

		try {
			UserDTO dto = model.findByLogin("jayesh.manikpuri20@gmail.com");
			String oldPassword = dto.getPassword();
			dto.setId(15L);
			dto.setPassword("rr");
			String newPassword = dto.getPassword();
			try {
				model.changePassword(15L, oldPassword, newPassword);
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testRegisterUser() throws ParseException {
		try {
			UserDTO dto = new UserDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			// dto.setId(8L);
			dto.setFirstName("vipin");
			dto.setLastName("kumawat");
			dto.setLogin("ranjitch11oudha12ry20@gmail.com");
			dto.setPassword("4444");
			dto.setConfirmPassword("4444");
			dto.setDob(sdf.parse("11/20/2015"));
			dto.setGender("Male");
			dto.setRoleId(2);
			long pk = model.registerUser(dto);
			System.out.println("Successfully register");
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getLastName());
			System.out.println(dto.getDob());
			UserDTO registerDto = model.findByPK(pk);
			if (registerDto == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testforgetPassword() {
		try {
			boolean b = model.forgetPassword("ranjitchoudhary20@gmail.com");

			System.out.println("Suucess : Test Forget Password Success");

		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testresetPassword() {
		UserDTO dto = new UserDTO();
		try {
			dto = model.findByLogin("ranjitchoudhary20@gmail.com");
			if (dto != null) {
				boolean pass = model.resetPassword(dto);
				if (pass = false) {
					System.out.println("Test Update fail");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
