package representation;

public interface Event {
	void display();
	Event chooseNext();
	Event chooseNext(String choice);
	Event chooseNext2(String choice);

}
