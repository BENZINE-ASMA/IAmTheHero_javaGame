package representation;


public abstract class NodeDecorator implements Event {
	protected Event decoratedNode;

    public NodeDecorator(Event decoratedNode) {
        this.decoratedNode = decoratedNode;
    }

    @Override
    public void display() {
        decoratedNode.display();
    }

    @Override
    public Event chooseNext() {
        return decoratedNode.chooseNext();
    }
   
    @Override
    public Event chooseNext(String choice) {
    	return decoratedNode.chooseNext(choice);
    }
    
    
	public Event chooseNext2(String choice) {
		return decoratedNode.chooseNext2(choice);
	}
	
}
