package me.IAGO.Media;

import java.util.Observer;
import org.json.JSONObject;

public interface Media_Intfc {
	public interface PullMediaDataCallback {
		boolean Push(Byte data);
	}
	public boolean Config(JSONObject conf);
	
	public JSONObject GetMediaRecordDate();
	public boolean DelectMediaDate(JSONObject date);
	public boolean PullMediaData(JSONObject date, PullMediaDataCallback callbackfunc);
	
	public boolean PushMediaData(Byte data);
	public boolean StartMediaForward(Observer forwardobject);
	public boolean StopMediaForward(Observer forwardobject);
}
