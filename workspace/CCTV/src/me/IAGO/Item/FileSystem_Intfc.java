package me.IAGO.Item;

import java.util.List;

public interface FileSystem_Intfc {
	public boolean Available();
	
	public boolean AddUserInfor(String username, String password);
	public boolean RemoveUserInfo(String username);
	public boolean ChangeUserPassword(String newpassword);
	public String GetUserPassword(String username);
	
	public boolean GotoUserPath(String username);
	public List<StoreDate_Intfc> GetUserFileIndex();
	
	public boolean SaveUserFile(Byte[] data, StoreDate_Intfc date);
	public boolean DeleteUserFile(StoreDate_Intfc date);
	public Byte[] GetUserFile(StoreDate_Intfc date);
}
