package me.IAGO.Media;

import java.util.concurrent.Callable;
import org.json.JSONObject;

public interface Media_Intfc {
	public interface Forward_Intfc extends java.util.EventListener {
		void PushMediaData(Byte[] data);
	}
	
	public JSONObject GetMediaRecordDate();
	public boolean DelectMediaDate(JSONObject date);
	public boolean PullMediaData(JSONObject date, Callable<Byte[]> transmissiondata);
	
	public boolean PushMediaData(Byte[] data);
	public boolean StartMediaForward(Forward_Intfc forwardobject);
	public boolean StopMediaForward(Forward_Intfc forwardobject);
}
