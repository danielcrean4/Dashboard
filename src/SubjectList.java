
public class SubjectList {
	private int size;
	private SubjectNode header, trailer;
	
	public SubjectList(){
		size = 0;
		header = new SubjectNode();
		trailer = new SubjectNode();
		header.setNextSubject(trailer);
		trailer.setPrevSubject(header);
	}
	
	//Get Size of List
	
	public int size() {
		return size;
	}

	//returns true if the list is empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	// Get First/Last/Next/Prev
	
	public SubjectNode getFirst() throws Exception {
		if (isEmpty()) throw new Exception("Empty");
		
		return header.getNextSubject();
	}
	
	public SubjectNode getLast() throws Exception {
		if(isEmpty()) throw new Exception("Empty");
		
		return trailer.getPrevSubject();
	}
	
	public SubjectNode getNext(SubjectNode sn) throws Exception {
		SubjectNode ans = sn.getNextSubject();
		if (ans == null || ans == trailer)
			throw new Exception("No such node");
		
		return ans;
		
	}
	
	public SubjectNode getPrev(SubjectNode sn) throws Exception {
		SubjectNode ans = sn.getPrevSubject();
		if (ans == null || ans == trailer)
			throw new Exception("No such node");
		
		return ans;
		
	}
	
	//Methods to add new SubjectNodes
	
	public void addBefore(SubjectNode newNode, SubjectNode beforeThis) {
		
		//Sets the linkages on new node
		newNode.setNextSubject(beforeThis);
		newNode.setPrevSubject(beforeThis.getPrevSubject());
		
		//Amends the linkages on original nodes
		beforeThis.getPrevSubject().setNextSubject(newNode);
		beforeThis.setPrevSubject(newNode);
		
		size++;
	}
	
	public void addAfter(SubjectNode newNode, SubjectNode afterThis) {
		
		//Sets linkages on new node
		newNode.setNextSubject(afterThis.getNextSubject());
		newNode.setPrevSubject(afterThis);
		
		
		//Amends linkages on original nodes
		afterThis.getNextSubject().setPrevSubject(newNode);
		afterThis.setNextSubject(newNode);
		
		size++;
	}
	
	public void addFirst(SubjectNode sn) {
		addAfter(sn, header);
	}
	
	public void addLast(SubjectNode sn) {
		addBefore(sn, trailer);
	}
	
	
	//Remove the given SubjectNode
	
	public SubjectNode remove(SubjectNode sn) throws Exception {
		if (sn == header || sn == trailer)
			throw new Exception("Sentinel");

		//This changes the next pointer in the element before 'sn' to point to the element after 'sn'
		sn.getPrevSubject().setNextSubject(sn.getNextSubject());
		
		//This changes the prev pointer in the element after 'sn' to point to the elemeent before 'sn'
		sn.getNextSubject().setPrevSubject(sn.getPrevSubject());
		
		size--;
		return sn;
	}
	
}
