package in	.co.sunrays.ocha.model;

import java.util.List;

import in.co.sunrays.ocha.bean.UserDTO;
import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.ocha.exception.DuplicateRecordException;
import in.co.sunrays.ocha.exception.RecordNotFoundException;

public interface UserModelInt {
	public long add(UserDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public void update(UserDTO dto) throws ApplicationException,
			DuplicateRecordException;
	
	 public void delete(UserDTO dto) throws ApplicationException;


	public UserDTO findByLogin(String login) throws ApplicationException;

	public UserDTO findByPK(long pk) throws ApplicationException;

	public List search(UserDTO dto) throws ApplicationException;

	public List search(UserDTO dto, int pageNo, int pageSize)
			throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public boolean changePassword(Long id, String oldPassword,
			String newPassword) throws RecordNotFoundException,
			ApplicationException;

	public UserDTO authenticate(String login, String password)
			throws ApplicationException;

	public boolean lock(String login) throws RecordNotFoundException,
			ApplicationException;

	public List getRoles(UserDTO dto) throws ApplicationException;

	public long registerUser(UserDTO dto) throws ApplicationException,
			DuplicateRecordException;

	public boolean resetPassword(UserDTO dto) throws ApplicationException;

	public boolean forgetPassword(String login) throws ApplicationException,
			RecordNotFoundException;
}
