package in.co.sunrays.ocha.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import in.co.sunrays.ocha.bean.CollegeDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.CollegeModelHibImpl;
import in.co.sunrays.ocha.model.CollegeModelInt;
//import in.co.sunrays.ocha.model.CollegeModelJDBCImpl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class CollegeModelTest {

	// public static CollegeModelInt model = new CollegeModelHibImpl();

	public static CollegeModelInt model = new CollegeModelHibImpl();

	/*
	 * public static void main(String[] args) { //testAdd(); //testDelete();
	 * //testUpdate(); //testFindByName(); //testFindByPK(); // testSearch();
	 * testList();
	 * 
	 * }
	 */
	// test Add

	@Test
	public void testAdd() {

		try {
			CollegeDTO dto = new CollegeDTO();
			// dto.setId(1L);
			dto.setName("mjeet");
			dto.setAddress("borawana");
			dto.setState("up");
			dto.setCity("indore");
			dto.setPhoneNo("073124244");
			dto.setCreatedBy("user");
			dto.setModifiedBy("user");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			System.out.println("in test class");
			long pk = model.add(dto);
			System.out.println("Test add succ");
			/*
			 * CollegeDTO addedDto = model.findByPK(pk); if (addedDto == null) {
			 * System.out.println("Test add fail"); } fail("test add fail");
			 */
			assertNotNull(model.findByPK(pk));
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	// Test Delete
	@Ignore
	public void testDelete() {

		try {
			CollegeDTO dto = new CollegeDTO();
			long pk = 7L;
			dto.setId(pk);
			model.delete(dto);
			// System.out.println("Test Delete succ");

			assertNull(model.findByPK(pk));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	// Update College
	@Test
	public void testUpdate() {

		try {
			CollegeDTO dto = model.findByPK(3L);
			dto.setName("Truba2");
			model.update(dto);
			
			assertNotNull(model.findByPK(3L));

		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	// Find By Pk
	@Test
	public void testFindByPK() {
		try {
			CollegeDTO dto = new CollegeDTO();
			long pk = 11L;
			dto = model.findByPK(pk);

			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getAddress());
			System.out.println(dto.getState());
			System.out.println(dto.getCity());
			System.out.println(dto.getPhoneNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());

			assertNotNull("Test Find By Pk fail", dto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testFindByName() {
		try {
			CollegeDTO dto = model.findByName("LNCT");
			/*
			 * if(dto==null){ //System.out.println("Test Find By Name fail");
			 * fail("Test Find By Name fail"); }
			 */
			assertNotNull("Test Find By Name fial ", dto);
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getAddress());
			System.out.println(dto.getState());
			System.out.println(dto.getCity());
			System.out.println(dto.getPhoneNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	// Search College with pagination
	@Test
	public void testSearch() {
		try {
			CollegeDTO dto = new CollegeDTO();
			List list = new ArrayList();
			// dto.setName("Truba");
			list = model.search(dto, 1, 10);
			if (list.size() < 0) {
				fail("Test Search fail");
				// System.out.println("Test Search fail");
			}

			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (CollegeDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getAddress());
				System.out.println(dto.getState());
				System.out.println(dto.getCity());
				System.out.println(dto.getPhoneNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	// Get List With pagination
	@Test
	public void testList() {
		try {
			CollegeDTO dto = new CollegeDTO();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				fail("Test list Fail");
				// System.out.println("Test list Fail");
			}

			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (CollegeDTO) it.next();

				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getAddress());
				System.out.println(dto.getState());
				System.out.println(dto.getCity());
				System.out.println(dto.getPhoneNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

}
