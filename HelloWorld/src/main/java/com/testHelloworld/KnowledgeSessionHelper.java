package com.testHelloworld;

import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class KnowledgeSessionHelper {
	

	public static KieContainer createRuleBase() {
		
		 KieServices ks = KieServices.Factory.get();
         KieContainer kContainer = ks.getKieClasspathContainer();      
         return kContainer;
		
	}
	
	
	public static KieSession getStatefullKnowledgeSession(KieContainer kieContainer, String  sessionName) {
		KieSession kiesession = kieContainer.newKieSession(sessionName);
		kiesession.addEventListener(new RuleRuntimeEventListener() {
            public void objectInserted(ObjectInsertedEvent event) {
                System.out.println("Object inserted \n"
                        + event.getObject().toString());
            }
            public void objectUpdated(ObjectUpdatedEvent event) {
                System.out.println("Object was updated \n"
                        + "new Content \n" + event.getObject().toString());
            }
            public void objectDeleted(ObjectDeletedEvent event) {
                System.out.println("Object retracted \n"
                        + event.getOldObject().toString());
            }
        });
		
		kiesession.addEventListener(new AgendaEventListener() {
	            public void matchCreated(MatchCreatedEvent event) {
	                System.out.println("The rule "
	                        + event.getMatch().getRule().getName()
	                        + " can be fired in agenda");
	            }
	            public void matchCancelled(MatchCancelledEvent event) {
	                System.out.println("The rule "
	                        + event.getMatch().getRule().getName()
	                        + " cannot b in agenda");
	            }
	            public void beforeMatchFired(BeforeMatchFiredEvent event) {
	                System.out.println("The rule "
	                        + event.getMatch().getRule().getName()
	                        + " will be fired");
	            }
	            public void afterMatchFired(AfterMatchFiredEvent event) {
	                System.out.println("The rule "
	                        + event.getMatch().getRule().getName()
	                        + " has be fired");
	            }
	            public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
	            }
	            public void agendaGroupPushed(AgendaGroupPushedEvent event) {
	            }
	            public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	            }
	            public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	            }
	            public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	            }
	            public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	            }
	        });
		
		return kiesession;
		
	
	}

}