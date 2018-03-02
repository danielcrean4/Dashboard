/*
 * Class to store a Subject Title, and a doubly linked list of NoteNodes
 * 
 */
public class SubjectNode {
	
	private String title;
	private SubjectNode next, prev;
	private NoteNode header, trailer;
	private int noteCount;
	
	
	//No arg constructor - creates sentinels in SubjectList Class
	public SubjectNode() {
		noteCount = 0;
		title = null;
		header = new NoteNode();
		trailer = new NoteNode();
		header.setNext(trailer);
		trailer.setPrev(header);
	}
	
	//1 arg constructor for Subjects with titles
	
	public SubjectNode(String title) {
		noteCount = 0;
		this.title = title;
		header = new NoteNode();
		trailer = new NoteNode();
		header.setNext(trailer);
		trailer.setPrev(header);
		
	}
	
	// Size Checks
	
	public int getNoteCount() {
		return noteCount;
	}
	
	public boolean isEmpty() {
		return noteCount == 0;
	}
	
	//Access methods for NoteNodes
	
	public NoteNode getFirst() throws Exception {
		if(isEmpty())
			throw new Exception("Empty");
		return header.getNext();
	}
	
	public NoteNode getLast() throws Exception{
		if(isEmpty())
			throw new Exception("Empty");
		return trailer.getPrev();
		
	}
	
	public NoteNode getNextNote(NoteNode nn) throws Exception {
		NoteNode ans = nn.getNext();
		if (ans == null || ans == trailer)
			throw new Exception("No such node");
		return ans;
	}
	
	public NoteNode getPrevNote(NoteNode nn) throws Exception {
		NoteNode ans = nn.getPrev();
		if (ans == null || ans == header)
			throw new Exception("No such node");
		return ans;
		
	}
	
	//Access methods for linked SubjectNodes
	
	public SubjectNode getNextSubject() {
		
		return next;
	}
	
	public SubjectNode getPrevSubject() {
			
		return prev;
	}
	
	// Set Next/Prev SubjectNodes
	
	public void setNextSubject(SubjectNode next) {
		this.next = next;
	}
	
	public void setPrevSubject(SubjectNode prev) {
		this.prev = prev;
	}
	
	
	//Methods to add elements to the list
	
	public void addBefore(NoteNode newNode, NoteNode beforeThis) {
		
		//Set Next/Prev for 'newNode' before amending 'beforeThis' node
		newNode.setNext(beforeThis);
		newNode.setPrev(beforeThis.getPrev());
		
		//change next/prev in original nodes to insert 'newNode' in between them
		beforeThis.getPrev().setNext(newNode);
		beforeThis.setPrev(newNode);
		noteCount++;
	}
	
	public void addAfter(NoteNode newNode, NoteNode afterThis) {
		
		//Set Next/Prev for 'newNode' before amending 'afterThis' node
		newNode.setNext(afterThis.getNext());
		newNode.setPrev(afterThis);
		
		
		//change next/prev for newNode
		afterThis.getNext().setPrev(newNode);
		afterThis.setNext(newNode);
		
		
		noteCount++;
	}
	
	public void addFirst(NoteNode nn) {
	
		addAfter(nn, header);	
		
	}
	
	public void addLast(NoteNode nn) {
				
		addBefore(nn, trailer);
		
	}
	
	public NoteNode remove(NoteNode nn) throws Exception{
		
		if(nn == header || nn == trailer)
			throw new Exception("Sentinel");
				
		nn.getNext().setPrev(nn.getPrev());
		nn.getPrev().setNext(nn.getNext());
		noteCount--;
		return nn;
	}
	
	
	//Get Title Method
	
	public String getTitle() {
		return title;
	}
	

	
}
