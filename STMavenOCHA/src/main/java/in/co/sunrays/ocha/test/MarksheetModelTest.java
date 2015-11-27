package in.co.sunrays.ocha.test;

import in.co.sunrays.ocha.bean.MarksheetDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.MarksheetModelHibImpl;
import in.co.sunrays.ocha.model.MarksheetModelInt;
//import in.co.sunrays.ocha.model.MarksheetModelJDBCImpl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MarksheetModelTest {

	static MarksheetModelInt model = new MarksheetModelHibImpl();

	public static void main(String args[]) {
		testAdd();
		// testDelete();
		// testUpdate();
		// testSearch();
		// testList();
		// testFindByPK();
		// testFindByRollNo();
	}

	public static void testAdd() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			dto.setId(10L);
			dto.setRollNo("189");
			dto.setName(" Marry");
			dto.setPhysics(88);
			dto.setChemistry(77);
			dto.setMaths(99);
			dto.setStudentId(25L);
			long pk = model.add(dto);
			System.out.println("Test add succ");
			MarksheetDTO addedDto = model.findByPK(pk);
			if (addedDto == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		try {
			MarksheetDTO dto = model.findByPK(3L);
			dto.setName("Mohan Lal");
			dto.setChemistry(100);
			dto.setMaths(100);
			dto.setPhysics(100);
			model.update(dto);
			System.out.println("Test Update ");
			MarksheetDTO updatedDTO = model.findByPK(3L);
			if ("rk choudhary".equals(updatedDTO.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			long pk = 15;
			dto.setId(pk);
			model.delete(dto);
			System.out.println("Test delete");
			MarksheetDTO deletedDTO = model.findByPK(pk);
			if (deletedDTO != null) {
				System.out.println("Test delete fail");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPK() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			long pk = 3L;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getRollNo());
			System.out.println(dto.getName());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getChemistry());
			System.out.println(dto.getMaths());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByRollNo() {

		try {
			MarksheetDTO dto = model.findByRollNo("189");
			if (dto == null) {
				System.out.println("Test Find By RollNo fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getRollNo());
			System.out.println(dto.getName());
			System.out.println(dto.getPhysics());
			System.out.println(dto.getChemistry());
			System.out.println(dto.getMaths());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testSearch() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			List list = new ArrayList();
			// dto.setName("raju mali");
			dto.setRollNo("1");
			list = model.search(dto, 1, 10);
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getRollNo());
				System.out.println(dto.getName());
				System.out.println(dto.getPhysics());
				System.out.println(dto.getChemistry());
				System.out.println(dto.getMaths());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testList() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			List list = new ArrayList();
			list = model.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getRollNo());
				System.out.println(dto.getName());
				System.out.println(dto.getPhysics());
				System.out.println(dto.getChemistry());
				System.out.println(dto.getMaths());
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
