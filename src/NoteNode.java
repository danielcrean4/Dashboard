
public class NoteNode {
	private String title, content;
	
	// Constructors
	
	public NoteNode() {
		title = content = null;
	}
	
	public NoteNode(String title) {
		this.title = title;
		content = "(To add content, click 'edit text' button below)";
	}
	
	public NoteNode(String title, String content) {
		this.title = title;
		this.content = content;
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
		
}
