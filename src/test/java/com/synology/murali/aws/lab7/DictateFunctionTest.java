package com.synology.murali.aws.lab7;

import org.junit.Assert;
import org.junit.Test;
import com.synology.murali.aws.lab7.solution.CreateUpdateFunctionSolution;
import com.synology.murali.aws.lab7.functions.DictateFunction;
import com.synology.murali.aws.lab7.pojo.DictateRequest;
import com.synology.murali.aws.lab7.pojo.Note;
import com.amazonaws.services.lambda.runtime.Context;

public class DictateFunctionTest {

	// Input data for the Dictate function
	private DictateRequest createDictateInput() {
		DictateRequest request = new DictateRequest();
		Note note = new Note();
		note.setUserId("testuser");
		note.setNoteId("001");
		request.setVoiceId("Russell");
		request.setNote(note);
		return request;
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
	public void testDictateFunctionTest() {
		// Create a Lambda fake context
		Context ctx = new TestContext();
		
		// Create the note in DynamoDB first
		CreateUpdateFunctionSolution createHandler = new CreateUpdateFunctionSolution();
		createHandler.handleRequest(createCreateInput(), ctx);
		
		// Execute the DictateFunction
		DictateFunction dictateHandler = new DictateFunction();
		String output = dictateHandler.handleRequest(createDictateInput(), ctx);
		
		// Test to see if the URL starts with https://
		Assert.assertTrue(output.startsWith("https://"));
	}
}
