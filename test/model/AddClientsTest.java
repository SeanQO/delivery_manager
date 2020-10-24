package model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AddClientsTest {
	private Manager manager;
	
	//clients list empty
	public void setUpStageOne() {
		manager = new Manager();
		
	}
	
	//clients list filled
	public void setUpStageTwo() {
		manager = new Manager();
		manager.addClients(new Client(DocumentType.valueOf("CC"), "1222594875", "bb", "bb", "3145689751", "Cra 21 # 14A - 21"));
		manager.addClients(new Client(DocumentType.valueOf("CC"), "1222594875", "aa", "aa", "3145689751", "Cra 21 # 14A - 21"));
		manager.addClients(new Client(DocumentType.valueOf("CC"), "1222594875", "dd", "dd", "3145689751", "Cra 21 # 14A - 21"));
		manager.addClients(new Client(DocumentType.valueOf("CC"), "1222594875", "cc", "cc", "3145689751", "Cra 21 # 14A - 21"));
	}
	
	@Test
	public void testAddClients() {
		setUpStageOne();
		manager.addClients(new Client(DocumentType.valueOf("CC"), "1222594875", "Andrea", "Sosa", "3145689751", "Cra 21 # 14A - 21"));
		
		assertEquals("1222594875", manager.getClients().get(0).getIdNumber(),"The client was not added to the clients list");
		
		setUpStageTwo();
		
		assertEquals("aa", manager.getClients().get(0).getName(),"The client AA was not added to the correct position");
		assertEquals("bb", manager.getClients().get(1).getName(),"The client BB was not added to the correct position");
		assertEquals("cc", manager.getClients().get(2).getName(),"The client CC was not added to the correct position");
		assertEquals("dd", manager.getClients().get(3).getName(),"The client DD was not added to the correct position");
		
	}
	
}
