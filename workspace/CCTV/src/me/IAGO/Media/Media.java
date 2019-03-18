package me.IAGO.Media;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import me.IAGO.Item.FileSystem_Intfc;
import me.IAGO.Item.StoreDate;
import me.IAGO.Item.StoreDate_Intfc;;

public class Media implements Media_Intfc {
	final String MEDIA_INDEXNUM = "media_indexnum";
	final String MEDIA_SAVE = "media_save";
	
	private class MediaData {
	    
	    private StringBuffer [] _mediadata = new [2] StringBuffer() ;
	    private int _bufferselect = 0;
	    
	    public void Save(Byte data) {
	        int bufferptr = ChangeBuffer(false);
	        
	    }
	    
	    private synchronized int ChangeBuffer(boolean bufferswitch) {
	        if(bufferswitch) {
	            _bufferselect = 1 - _bufferselect;
	        }
	        return _bufferselect;
	    }
	}
	
	private class MediaDataWatcher extends Observable {
	    public void Notice(Byte data) {
	        super.setChanged();
	        super.notifyObservers(data);
	    }
	}
	private MediaDataWatcher mediawatcher;
	private FileSystem_Intfc _filesystem;
	private String _username;
	
    @Override
    public boolean Config(JSONObject conf) {
        
        if(conf.getBoolean(MEDIA_SAVE)) {
            
        }
        return true;
    }
    
	@Override
	public JSONObject GetMediaRecordDate()
	        throws JSONException{
		JSONObject json = new JSONObject();
		if(SetFileSystemPath()) {
			List<StoreDate_Intfc> index = _filesystem.GetUserFileIndex();
			json.put(MEDIA_INDEXNUM, index.size());
			for(int key = 0; key < index.size(); key++) {
				json.put(String.valueOf(key), index.get(key).toString());
			}
		}
		return json;
	}

	@Override
	public boolean DelectMediaDate(JSONObject date)
	        throws JSONException {
		if(SetFileSystemPath()) {
			for(int key = 0; key < date.getInt(MEDIA_INDEXNUM); key++) {
			    _filesystem.DeleteUserFile(new StoreDate(date.getJSONObject(String.valueOf(key))));
			}
		}
		return false;
	}

	@Override
	public boolean PullMediaData(JSONObject date, PullMediaDataCallback callbackfunc)
	        throws JSONException {
	    if(SetFileSystemPath()) {
	        StringBuffer mergemediadata = new StringBuffer();
	        for(int key = 0; key < date.getInt(MEDIA_INDEXNUM); key++) {
	            mergemediadata.append(_filesystem.GetUserFile(new StoreDate(date.getJSONObject(String.valueOf(key)))).toString());
	        }  
	        return callbackfunc.Push(new Byte(mergemediadata.toString()));
	    }
		return false;
	}

	@Override
	public boolean PushMediaData(Byte data) {
	    mediawatcher.Notice(data);
		return false;
	}

	@Override
	public boolean StartMediaForward(Observer forwardobject) {
	    mediawatcher.addObserver(forwardobject);
		return true;
	}

	@Override
	public boolean StopMediaForward(Observer forwardobject) {
	    mediawatcher.deleteObserver(forwardobject);
		return false;
	}

	private boolean SetFileSystemPath()
	        throws JSONException{
		if(_filesystem.Available()) {
			return _filesystem.GotoUserPath(_username);
		}
		return false;
	}
	
	private void SaveMediaFile(Byte data) {
	    
	}
}
