package me.IAGO.Media;

import java.util.List;
import java.util.concurrent.Callable;

import org.json.JSONException;
import org.json.JSONObject;

import me.IAGO.Item.FileSystem_Intfc;
import me.IAGO.Item.StoreDate;
import me.IAGO.Item.StoreDate_Intfc;;

public class Media implements Media_Intfc {
	final String INDEX_NUM = "fileindexnum";
	
	private String _userpath;
	FileSystem_Intfc _filesystem;

	Media(JSONObject config, FileSystem_Intfc filesystem) {
		_filesystem = filesystem;
		try {
			_userpath = config.getString("Username");
			
		}
		catch(JSONException ep) {
			
		}
	}
	
	@Override
	public JSONObject GetMediaRecordDate() {
		JSONObject json = new JSONObject();
		if(SetFileSystemPath()) {
			List<StoreDate_Intfc> index = _filesystem.GetUserFileIndex();
			json.put(INDEX_NUM, index.size());
			for(int key = 0; key < index.size(); key++) {
				json.put(String.valueOf(key), index.get(key).toString());
			}
		}
		return json;
	}

	@Override
	public boolean DelectMediaDate(JSONObject date) {
		if(SetFileSystemPath()) {
			for(int key = 0; key < date.getInt(INDEX_NUM); key++) {
				try {
					_filesystem.DeleteUserFile(
							new StoreDate(date.getJSONObject(String.valueOf(key))));
				}
				catch (JSONException ex) {
					
				}
			}
		}
		return false;
	}

	@Override
	public boolean PullMediaData(JSONObject date, Callable<Byte[]> transmissiondata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean PushMediaData(Byte[] data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean StartMediaForward(Forward_Intfc forwardobject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean StopMediaForward(Forward_Intfc forwardobject) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean SetFileSystemPath() {
		if(_filesystem.Available()) {
			return _filesystem.GotoUserPath(_userpath);
		}
		return false;
	}
}
