package in.co.sunrays.ocha.model;



import in.co.sunrays.ocha.bean.RoleDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import java.util.List;

public interface RoleModelInt {

public long add(RoleDTO dto) throws ApplicationException,  DuplicateRecordException;

public void update(RoleDTO dto) throws ApplicationException,  DuplicateRecordException;

public void delete(RoleDTO dto) throws ApplicationException;

public RoleDTO findByName(String name) throws ApplicationException;

public RoleDTO findByPK(long pk) throws ApplicationException;

public List search(RoleDTO dto, int pageNo, int pageSize) throws ApplicationException;

public List search(RoleDTO dto) throws ApplicationException;

public List list() throws ApplicationException;

public List list(int pageNo, int pageSize) throws ApplicationException;

}