package com.synology.murali.aws.lab7;

import org.junit.Assert;
import org.junit.Test;

import com.synology.murali.aws.lab7.functions.CreateUpdateFunction;
import com.synology.murali.aws.lab7.pojo.Note;
import com.amazonaws.services.lambda.runtime.Context;

public class CreateUpdateFunctionTest {

	// Input data for the CreateUpdate function
	private Note createCreateInput() {
		Note note = new Note();
		note.setUserId("testuser");
		note.setNoteId("001");
		note.setNote("This is a test");
		return note;
	}

	@Test
	public void testCreateUpdateFunction() {
		// Create a Lambda fake context
		Context ctx = new TestContext();

		// Execute the CreateUpdateFunction
		CreateUpdateFunction createHandler = new CreateUpdateFunction();
		String output = createHandler.handleRequest(createCreateInput(), ctx);
		
		// Test to see if the output is empty
		Assert.assertNotEquals("", output);
	}
}
