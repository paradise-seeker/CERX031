package me.IAGO.Security;

public interface Security_Intfc {
	public enum VerificationStatus {
		VerificationPassed,
		VerificationFailed
	}
	public VerificationStatus GetVerificationStatus();
	public String OnetimeVerificationInfo(String username, int verificationtimeout);
	public VerificationStatus Verification(String verificationinfo);
	public Byte[] DecryptData(Byte[] encrypteddata);
}
