package in.co.sunrays.ocha.model;

import java.util.List;

import in.co.sunrays.ocha.bean.CollegeDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;

public interface CollegeModelInt {
	
     //Add a College
   
    public long add(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;
    //Update a College
   
    public void update(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;

    
     //Delete a College
    
    public void delete(CollegeDTO dto) throws ApplicationException;

    // Find College by Name
     
    
    public CollegeDTO findByName(String name) throws ApplicationException;
    
    //Find College by PK
  
    public CollegeDTO findByPK(long pk) throws ApplicationException;

    
     // Search College with pagination
      
     
    public List search(CollegeDTO dto, int pageNo, int pageSize)
            throws ApplicationException;
    //Search College
     
     
    public List search(CollegeDTO dto) throws ApplicationException;

    
    public List list() throws ApplicationException;

    
    public List list(int pageNo, int pageSize) throws ApplicationException;


}
