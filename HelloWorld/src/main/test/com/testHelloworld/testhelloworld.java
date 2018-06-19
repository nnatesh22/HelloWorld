package com.testHelloworld;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.testHelloworld.KnowledgeSessionHelper;

public class testhelloworld {

	KieSession statefullsession =null;
	static KieContainer kieContainer = null;
	
	
	@BeforeClass
	public static void beforeClass() {
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}
	
	@Before
    public void setUp() throws Exception{
        System.out.println("------------Before------------");
    }
	
	
	@After
    public void tearDown() throws Exception{
        System.out.println("------------After------------");
    }
	
	@Test
	public void test() {
		statefullsession = KnowledgeSessionHelper.getStatefullKnowledgeSession(kieContainer,"hello");
		HelloWorld h = new HelloWorld();
		statefullsession.insert(h);
		statefullsession.startProcess("com.testHelloworld.helloworldRuleFlow");
		statefullsession.fireAllRules();
	}

}
