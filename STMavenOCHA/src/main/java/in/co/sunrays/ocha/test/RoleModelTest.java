package in.co.sunrays.ocha.test;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import in.co.sunrays.ocha.bean.RoleDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.model.RoleModelHibImpl;
import in.co.sunrays.ocha.model.RoleModelInt;
//import in.co.sunrays.ocha.model.RoleModelJDBCImpl;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 

public class RoleModelTest {
 
 
    public static RoleModelInt model = new RoleModelHibImpl();
 
   /* public static void main(String[] args){
       //  testAdd();
       // testDelete();
        // testUpdate();
        // testFindByPK();
        // testFindByName();
        //testSearch();
         testList();
 
    }*/
 
    @Test
    public void testAdd() {
 
        try {
            RoleDTO dto = new RoleDTO();
            
            dto.setId(10L);
            dto.setName("ANIL");
            dto.setDescription("KAPOOR");
            long pk = model.add(dto);
          /*  System.out.println("Test add succ");
            RoleDTO addedDto = model.findByPK(pk);
            if (addedDto == null) {
                System.out.println("Test add fail");
            }*/
            assertNotNull("test fail", model.findByPK(pk));
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    } 
    
    @Test
    public void testDelete() {
    	 
        try {
            RoleDTO dto = new RoleDTO();
            long pk = 11L;
            dto.setId(pk);
            model.delete(dto);
            System.out.println("Test Delete succ");
            RoleDTO deletedDto = model.findByPK(pk);
            if (deletedDto != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    } 
    
    @Test
    public void testUpdate() {
    	 
        try {
            RoleDTO dto = model.findByPK(34L);
            dto.setName("Mona");
            dto.setDescription("This is the HR Dep");
            model.update(dto);
 
            RoleDTO updatedDTO = model.findByPK(12L);
            System.out.println("Test Update ");
           /* if (!"cmc limited".equals(updatedDTO.getName())) {
                System.out.println("Test Update fail");
            }*/
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    } 
    
    @Test
    public void testFindByPK() {
        try {
            RoleDTO dto = new RoleDTO();
            long pk = 1L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getDescription());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    } 
    
    
    @Test
    public void testFindByName(){
    	try
    	{
    		RoleDTO dto=new RoleDTO();
    		dto=model.findByName("Student");
    		if(dto==null)
    		{
    			System.out.println("test FindByname is fail");
    		}
    		System.out.println(dto.getId());
    		System.out.println(dto.getName());
    		System.out.println(dto.getDescription());
    		
    	}catch(ApplicationException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
    @Test
    public void testSearch()
    {
    	try
    	{
    		RoleDTO dto=new RoleDTO();
    		List list=new ArrayList();
    		dto.setName("hr");
    		list=model.search(dto, 1, 1);
    		if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (RoleDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getDescription());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    } 
    
    
    @Test
    public void testList() {
    	 
        try {
            RoleDTO dto = new RoleDTO();
            List list = new ArrayList();
            list = model.list(1, 9);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (RoleDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getDescription());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    } 
    
}