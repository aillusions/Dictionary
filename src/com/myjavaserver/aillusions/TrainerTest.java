package com.myjavaserver.aillusions;

/*import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myjavaserver.aillusions.model.Pair;*/

public class TrainerTest {/*

	Trainer trainer = new Trainer(null);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMaxPriorityPair() {
		Pair[] pairs = new Pair[4];
		
		for(int i = 0; i < 4; i++){
			
			pairs[i] = new Pair();			
			pairs[i].setEnglish("eng" + i);
			pairs[i].setRussian("rus" + i);	
			pairs[i].setTranscription("transcr" + i);
			pairs[i].addSample("sample" + i);			
		}
		
		//-- 1 
		pairs[0].setInuse(false);
		pairs[0].incrAsksQuantity();
		pairs[0].incrRightAnswerQuantity();
		pairs[0].incrAttemttsQuantity();		
		
		//-- 2 
		pairs[1].setInuse(false);
		pairs[1].incrAsksQuantity();
		pairs[1].incrRightAnswerQuantity();
		pairs[1].incrAttemttsQuantity();
		
		//-- 3 
		pairs[2].setInuse(true);
		pairs[2].incrAsksQuantity();
		pairs[2].incrRightAnswerQuantity();
		pairs[2].incrAttemttsQuantity();		
		
		//-- 4 
		pairs[3].setInuse(false);
		pairs[3].incrAsksQuantity();
		pairs[3].incrRightAnswerQuantity();
		pairs[3].incrAttemttsQuantity();		
		// only In use  
		assertEquals(pairs[2], trainer.getMaxPriorityPair(pairs));	
		
		pairs[0].setInuse(true);
		pairs[1].setInuse(true);
		pairs[2].setInuse(true);
		pairs[3].setInuse(true);	
		
		// last added has priority
		assertEquals(pairs[3], trainer.getMaxPriorityPair(pairs));		
		
		pairs[0].incrAttemttsQuantity();
		pairs[0].incrAttemttsQuantity();		
		// less right answers - high priority
		assertEquals(pairs[0], trainer.getMaxPriorityPair(pairs));		
		
	}

	@Test
	public void testOnChuseAnsversVariant() {
		//fail("Not yet implemented");
	}
*/
}
