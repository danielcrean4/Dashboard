
public class NoteNode {
	private String title, content;
	private NoteNode next, prev;
	
	// Constructors
	
	public NoteNode() {
		title = content = null;
		next = prev = null;
	}
	
	public NoteNode(String title) {
		this.title = title;
		content = "(To add content, click 'edit text' button below)";
		next = prev = null;
	}
	
	public NoteNode(String title, String content) {
		this.title = title;
		this.content = content;
		
		next = prev = null;
	}
	
	//Get String methods - title/content
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}


	//Set Text methods - title/content
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	//Get Next/Previous Nodes
	
	public NoteNode getNext() {
		return next;
	}
	
	public NoteNode getPrev() {
		return prev;
	}
	
	
	// Set Next/Previous Nodes
	
	public void setNext(NoteNode nn) {
		next = nn;
	}
	
	public void setPrev(NoteNode nn) {
		prev = nn;
	}
	
	
}
