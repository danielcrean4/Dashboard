import java.util.ArrayList;

/*
 * Class to store a Subject Title, and a doubly linked list of NoteNodes
 * 
 */
public class SubjectNode {
	
	private String title;
	private ArrayList<NoteNode> noteList;
	
	//No arg constructor - creates sentinels in SubjectList Class
	public SubjectNode() {
		title = null;
		noteList = new ArrayList<NoteNode>();

	}
	
	//1 arg constructor for Subjects with titles
	
	public SubjectNode(String title) {
		this.title = title;		
		noteList = new ArrayList<NoteNode>();
	}
	
	// Size Checks
	
	public int getNoteCount() {
		return noteList.size();
	}
	
	public boolean isEmpty() {
		return noteList.size() == 0;
	}
	
	//Access methods for NoteNodes
	
	public NoteNode getFirst(){
		return noteList.get(0);
	}
	
	public NoteNode getLast() {
		return noteList.get(noteList.size());
		
	}
	
	//Get List
	
	public ArrayList<NoteNode> getNoteList() {
		return noteList;
	}
	
	
	//Methods to add elements to the list
	
	
	public void addFirst(NoteNode nn) {
	
		noteList.add(0, nn);
		
	}
	
	public void addLast(NoteNode nn) {
				
		noteList.add(nn);
		
	}
	
	public boolean remove(NoteNode nn) {
		
		return noteList.remove(nn);
		

	}
	
	
	//Get Title Method
	
	public String getTitle() {
		return title;
	}
	

	
}
