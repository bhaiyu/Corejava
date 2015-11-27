/*package in.co.sunrays.ocha.model;

import java.sql.PreparedStatement;


import java.sql.ResultSet;

import javax.management.modelmbean.ModelMBean;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.ocha.bean.MarksheetDTO;
import in.co.sunrays.ocha.bean.StudentDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DatabaseException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;

public class MarksheetModelJDBCImpl implements MarksheetModelInt {

	Logger log = Logger.getLogger(MarksheetModelJDBCImpl.class);
	
	public Integer nextPK ()throws DatabaseException{
		log.debug("Model nextPK Started");
		Connection conn=null;
		int pk=0;
		try {
			conn=(Connection) JDBCDataSource.getConnection();
			System.out.println("Connection Successfully Established.....");
			PreparedStatement pstmt=conn.prepareStatement("select max(ID) from ST_MARKSHEET");
			ResultSet rs=pstmt.executeQuery();
			System.out.println("new data");
			while(rs.next()){
				pk=rs.getInt(1);
			}
			rs.close();
			System.out.println("new data2"+ pk);
			
		} catch (Exception e) {
			log.error(e);
			throw new DatabaseException("Exception in Marksheet getting PK");
		}
		finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk+1;
		
	}

	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started...");
		Connection conn=null;
		
		StudentModelInt sModel=ModelFactory.getInstance().getStudentModel();
		StudentDTO studentDTO=sModel.findByPK(dto.getStudentId());
		System.out.println(sModel.findByPK(dto.getStudentId()));
		dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
		System.out.print((studentDTO.getFirstName() + " " + studentDTO.getLastName()));
	

		
		MarksheetDTO duplicateMarksheet=findByRollNo(dto.getRollNo());
		int pk=0;
		if(duplicateMarksheet != null)
		{
			throw new DuplicateRecordException("Roll No already exist....");
		}
		
		try
		{
			conn=JDBCDataSource.getConnection();
			pk=nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement("INSERT INTO ST_MARKSHEET VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, dto.getRollNo());
			pstmt.setLong(3, dto.getStudentId());
			pstmt.setString(4, dto.getName());
			pstmt.setInt(5, dto.getPhysics());
			pstmt.setInt(6, dto.getChemistry());
			pstmt.setInt(7, dto.getMaths());
			pstmt.setString(8, dto.getCreatedBy());
			pstmt.setString(9, dto.getModifiedBy());
			pstmt.setTimestamp(10, dto.getCreatedDatetime());
			pstmt.setTimestamp(11, dto.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			
			
		}catch(Exception e)
		{
			log.error(e);
			try
			{
				conn.rollback();
			}catch(Exception ex)
			{
				throw new ApplicationException("Add rollback exception " + ex.getMessage());
			}
			e.printStackTrace();
			throw  new ApplicationException("add marksheet exception " + e.getMessage());
		}finally
		{
			JDBCDataSource.closeConnection(conn);
		}
		
		
		System.out.println("Marksheet data inserted");
		return pk;
	}

	public void update(MarksheetDTO dto) throws ApplicationException,DuplicateRecordException {
		
		log.debug("Model update Started");

        Connection conn = null;
        MarksheetDTO dtoExist = findByRollNo(dto.getRollNo());

        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("Roll No is already exist");
        }

        StudentModelInt sModel = ModelFactory.getInstance().getStudentModel();
        StudentDTO studentDTO = sModel.findByPK(dto.getStudentId());
        dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());

        try {
            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); 
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE ST_MARKSHEET SET ROLL_NO=?,STUDENT_ID=?,NAME=?,PHYSICS=?,CHEMISTRY=?,MATHS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setString(1, dto.getRollNo());
            pstmt.setLong(2, dto.getStudentId());
            pstmt.setString(3, dto.getName());
            pstmt.setInt(4, dto.getPhysics());
            pstmt.setInt(5, dto.getChemistry());
            pstmt.setInt(6, dto.getMaths());
            pstmt.setString(7, dto.getCreatedBy());
            pstmt.setString(8, dto.getModifiedBy());
            pstmt.setTimestamp(9, dto.getCreatedDatetime());
            pstmt.setTimestamp(10, dto.getModifiedDatetime());
            pstmt.setLong(11, dto.getId());
            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (Exception e) {
            log.error(e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Update rollback exception "+ ex.getMessage());
            }
            throw new ApplicationException("Exception in updating Marksheet ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model update End");

    }
	
    public void delete(MarksheetDTO dto) throws ApplicationException {
    	
    	log.debug("Model delete started");
    	Connection conn=null;
    	try
    	{
    		conn=JDBCDataSource.getConnection();
    		conn.setAutoCommit(false);
    		PreparedStatement pstmt=conn.prepareStatement("DELETE FROM ST_MARKSHEET WHERE ID=?");
    		pstmt.setLong(1, dto.getId());
    		pstmt.executeUpdate();
    		conn.commit();
    		System.out.println("Data deleted");
    		pstmt.close();
    	}catch(Exception e)
    	{
    		log.error(e);
    		try
    		{
    			conn.rollback();
    		}catch(Exception ex)
    		{
    			throw new ApplicationException("delete rollback exception " + ex.getMessage());
    		}
    		throw new ApplicationException("Exception in delete " + e.getMessage());
    	}finally
    	{
    		JDBCDataSource.closeConnection(conn);
    	}
    	
    	log.debug("Model delete end");
    	
    }
	
	
	
	
	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException {
        log.debug("Model findByRollNo Started");

        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_MARKSHEET WHERE ROLL_NO=?");
        MarksheetDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, rollNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new MarksheetDTO();
                dto.setId(rs.getLong(1));
                dto.setRollNo(rs.getString(2));
                dto.setStudentId(rs.getLong(3));
                dto.setName(rs.getString(4));
                dto.setPhysics(rs.getInt(5));
                dto.setChemistry(rs.getInt(6));
                dto.setMaths(rs.getInt(7));
                dto.setCreatedBy(rs.getString(8));
                dto.setModifiedBy(rs.getString(9));
                dto.setCreatedDatetime(rs.getTimestamp(10));
                dto.setModifiedDatetime(rs.getTimestamp(11));
            }
            rs.close();
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException(
                    "Exception in getting marksheet by roll no");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model findByRollNo End");
        return dto;
    }
	
	
	public MarksheetDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");

        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_MARKSHEET WHERE ID=?");
        MarksheetDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new MarksheetDTO();
                dto.setId(rs.getLong(1));
                dto.setRollNo(rs.getString(2));
                dto.setStudentId(rs.getLong(3));
                dto.setName(rs.getString(4));
                dto.setPhysics(rs.getInt(5));
                dto.setChemistry(rs.getInt(6));
                dto.setMaths(rs.getInt(7));
                dto.setCreatedBy(rs.getString(8));
                dto.setModifiedBy(rs.getString(9));
                dto.setCreatedDatetime(rs.getTimestamp(10));
                dto.setModifiedDatetime(rs.getTimestamp(11));

            }
            rs.close();
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException(
                    "Exception in getting marksheet by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");
        return dto;
    }

	public List search(MarksheetDTO dto) throws ApplicationException
	{
		return search(dto, 0, 0);
	}
	
	
	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws ApplicationException
	{
		
		log.debug("model search started");
		
		StringBuffer sql=new StringBuffer("select * from ST_MARKSHEET where true");
		if(dto != null)
		{
			if(dto.getId() > 0)
			{
				sql.append("AND id = " + dto.getId());
			}
			if(dto.getRollNo() !=null && dto.getRollNo().length() >0)
			{
				 sql.append(" AND roll_no like '" + dto.getRollNo()+"'");
	             System.out.println("sql" + sql);
			}
		}
		
		ArrayList list=new ArrayList();
		Connection conn=null;
		try
		{
	 
			
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				dto=new MarksheetDTO();
				dto.setId(rs.getLong(1));
				dto.setRollNo(rs.getString(2));
				dto.setStudentId(rs.getLong(3));
                dto.setName(rs.getString(4));
                dto.setPhysics(rs.getInt(5));
                dto.setChemistry(rs.getInt(6));
                dto.setMaths(rs.getInt(7));
                dto.setCreatedBy(rs.getString(8));
                dto.setModifiedBy(rs.getString(9));
                dto.setCreatedDatetime(rs.getTimestamp(10));
                dto.setModifiedDatetime(rs.getTimestamp(11));
                list.add(dto);
				
			}
			conn.commit();pstmt.close();
			
		}catch(Exception e)
		{
			log.error(e);
			try
			{
				conn.rollback();
			}catch(Exception ex)
			{
				throw new ApplicationException("search rollback exception" + ex.getMessage());
			}
			
			throw new ApplicationException("search exception " + e.getMessage());
		}
		
		log.debug("search model end");
			return list;
		
	}
	
	public List list() throws ApplicationException
	{
		return list(0, 0);
	}
	
	public List list(int pageNo, int pageSize) throws ApplicationException
	{
		log.debug("model list started");
		StringBuffer sql=new StringBuffer("select * from st_marksheet");
		List list=new ArrayList();
		if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
		
		Connection conn=null;
		try
		{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				MarksheetDTO dto = new MarksheetDTO();
                dto.setId(rs.getLong(1));
                dto.setRollNo(rs.getString(2));
                dto.setStudentId(rs.getLong(3));
                dto.setName(rs.getString(4));
                dto.setPhysics(rs.getInt(5));
                dto.setChemistry(rs.getInt(6));
                dto.setMaths(rs.getInt(7));
                dto.setCreatedBy(rs.getString(8));
                dto.setModifiedBy(rs.getString(9));
                dto.setCreatedDatetime(rs.getTimestamp(10));
                dto.setModifiedDatetime(rs.getTimestamp(11));
                list.add(dto);
			}
			conn.close();
			pstmt.close();
			
			
			
		}catch(Exception e)
		{
			log.error(e);
			try
			{
				conn.rollback();
			}catch(Exception ex)
			{
				throw new ApplicationException("list rollback exception" + ex.getMessage());
			}
			
			throw new ApplicationException("list exception" + e.getMessage());
		}
		finally
		{
			JDBCDataSource.closeConnection(conn);
		}
		
		log.debug("model list end");
		return list;
	}
	
	public List getMeritList(int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model  MeritList Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer(
                "SELECT `ID`,`ROLL_NO`, `NAME`, `PHYSICS`, `CHEMISTRY`, `MATHS` , (PHYSICS + CHEMISTRY + MATHS) as total from `ST_MARKSHEET` order by total desc");
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
                MarksheetDTO dto = new MarksheetDTO();
                dto.setId(rs.getLong(1));
                dto.setRollNo(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setPhysics(rs.getInt(4));
                dto.setChemistry(rs.getInt(5));
                dto.setMaths(rs.getInt(6));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException(
                    "Exception in getting merit list of Marksheet");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model  MeritList End");
        return list;
    }
	

}
*/