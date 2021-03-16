package coms362.cards.model;

public class PresentationObject {
	
	public final String selector;
	private String remoteId = ""; 
	
	public PresentationObject(String selector){
		this.selector = selector;
	}
	
	public void setId(String remoteId){
		this.remoteId = remoteId;
	}
	
	public String getRemoteId(){
		if (remoteId.isEmpty()) {
			throw new IllegalStateException(
					"prestentation object does not have remote id"
			);
		}
		return remoteId;
	}
	
	
}
