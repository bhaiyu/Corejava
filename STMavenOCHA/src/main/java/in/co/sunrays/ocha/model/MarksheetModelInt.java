package in.co.sunrays.ocha.model;

import in.co.sunrays.ocha.bean.MarksheetDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;

import java.util.*;

public interface MarksheetModelInt {

	public long add(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public void update(MarksheetDTO dto) throws ApplicationException,
			DuplicateRecordException;

	
	public MarksheetDTO findByRollNo(String rolllNo) throws ApplicationException;

	public MarksheetDTO findByPK(long pk) throws ApplicationException;

    public void delete(MarksheetDTO dto) throws ApplicationException;



public List search(MarksheetDTO dto) throws ApplicationException;

public List search(MarksheetDTO dto, int pageNO, int pageSize) throws ApplicationException;

public List list() throws ApplicationException;

public List list(int pageNo,  int pageSize) throws ApplicationException;

public List getMeritList(int pageNo,  int pageSize) throws ApplicationException;

}