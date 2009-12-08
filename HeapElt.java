/**
 * A generic class for heap elements that include handles
 */

public class HeapElt {

    protected Comparable record;

    protected int handle = 0;
    
    public HeapElt() {
    	
    }
    
    public HeapElt(HeapElt copy) {
    	record = copy.getRecord();
    	handle = copy.getHandle();    	
    }

    public void setRecord(Comparable inRec){
	record = inRec;
    }

    public Comparable getRecord() {
	return record;
    }

    public void setHandle(int inHandle){
	handle = inHandle;
    }

    public int getHandle() {
	return handle;
    }

    public String toString() {
    	
    	return "HeapElt Record: " + record.toString() + " at (" + getHandle() + ")";
    	
    }
    
}

