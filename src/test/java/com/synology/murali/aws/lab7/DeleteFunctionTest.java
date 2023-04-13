package com.synology.murali.aws.lab7;

import org.junit.Assert;
import org.junit.Test;

import com.synology.murali.aws.lab7.solution.CreateUpdateFunctionSolution;
import com.synology.murali.aws.lab7.functions.DeleteFunction;
import com.synology.murali.aws.lab7.pojo.Note;
import com.amazonaws.services.lambda.runtime.Context;

public class DeleteFunctionTest {

	// Input data for the Delete function
	private Note createDeleteInput() {
		Note note = new Note();
		note.setUserId("testuser");
		note.setNoteId("001");
		return note;
	}
	
	// Input data for the CreateUpdate function
	private Note createCreateInput() {
		Note note = new Note();
		note.setUserId("testuser");
		note.setNoteId("001");
		note.setNote("This is a test");
		return note;
	}

	@Test
	public void testDeleteFunctionTest() {
		// Create a Lambda fake context
		Context ctx = new TestContext();
		
		// Create the data in DynamoDB first
		CreateUpdateFunctionSolution createHandler = new CreateUpdateFunctionSolution();
		createHandler.handleRequest(createCreateInput(), ctx);
		
		// Execute the DeleteFunction
		DeleteFunction deleteHandler = new DeleteFunction();
		String output = deleteHandler.handleRequest(createDeleteInput(), ctx);
		
		// Test to see if the output is empty
		Assert.assertNotEquals("", output);
	}
}
