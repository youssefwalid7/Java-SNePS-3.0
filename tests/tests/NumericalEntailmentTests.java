import java.util.ArrayList;

import java.util.LinkedList;

import org.junit.Test;

import sneps.exceptions.CannotBuildNodeException;
import sneps.exceptions.DuplicateContextNameException;
import sneps.exceptions.EquivalentNodeException;
import sneps.exceptions.IllegalIdentifierException;
import sneps.exceptions.NodeNotFoundInNetworkException;
import sneps.exceptions.NotAPropositionNodeException;
import sneps.network.Network;
import sneps.network.Node;
import sneps.network.PropositionNode;
import sneps.network.VariableNode;
import sneps.network.cables.DownCable;
import sneps.network.cables.DownCableSet;
import sneps.network.classes.CaseFrame;
import sneps.network.classes.Relation;
import sneps.network.classes.Semantic;
import sneps.network.classes.Wire;
import sneps.network.classes.term.Closed;
import sneps.network.classes.term.Open;
import sneps.network.classes.term.Variable;
import sneps.network.classes.setClasses.NodeSet;
import sneps.network.classes.setClasses.PropositionSet;
import sneps.snebr.Context;
import sneps.snebr.Controller;
import sneps.snip.InferenceTypes;
import sneps.snip.Report;
import sneps.snip.classes.SIndex;
import sneps.snip.matching.Binding;
import sneps.snip.matching.LinearSubstitutions;
import sneps.snip.rules.NumericalEntailment;
import junit.framework.TestCase;

public class NumericalEntailmentTests extends TestCase {
	private static Context context;
	private static String contextName = "TempContext";
	private static NumericalEntailment numerical;
	private static Node fido, leo, var, var1, dog, barks, walks;
	private static Node prop1, prop2, prop3, prop4, prop5;
	private static Report report, report1, report2, report3;

 	public void setUp() {
 		try {
			context = Controller.createContext(contextName);
		} catch (DuplicateContextNameException e1) {
			assertNotNull(e1.getMessage(), e1);
		}

		LinearSubstitutions sub = new LinearSubstitutions();
		PropositionSet support = new PropositionSet();
 		ArrayList<Wire> wires = new ArrayList<Wire>();
 		LinkedList<DownCable> dc = new LinkedList<DownCable>();
		LinkedList<Relation> rels = new LinkedList<Relation>();
		NodeSet nodeSet = new NodeSet();
		NodeSet nodeSett = new NodeSet();
		Relation memberRel = Network.defineRelation("Member", "NodeSet");
		Relation classRel = Network.defineRelation("Class", "NodeSet");
		Relation doesRel = Network.defineRelation("Does", "NodeSet");
		Relation antsRel = Network.defineRelation("iant", "&ant");
		Relation consRel = Network.defineRelation("iconsq", "&consq");
		rels.add(memberRel);	rels.add(classRel);
		CaseFrame caseFrameMC = Network.defineCaseFrame("MemberClass", rels);
		rels.clear();		rels.add(classRel);		rels.add(doesRel);
		CaseFrame caseFrameCD = Network.defineCaseFrame("ClassDoes", rels);
		rels.clear();		rels.add(memberRel);		rels.add(doesRel);
 		CaseFrame caseFrameMD = Network.defineCaseFrame("MemberDoes", rels);
		rels.clear();		rels.add(antsRel);		rels.add(consRel);
 		CaseFrame caseFrameAC = Network.defineCaseFrame("AntsCons", rels);
		Wire wire1 = null, wire2 = null, wire3 = null, wire4 = null;
		Wire wire5 = null, wire6 = null, wire7 = null;
		rels.clear();

//----------------------------- fido, dog, barks, X -----------------------------//

		try {
			var = Network.buildVariableNode("X");
			var1 = Network.buildVariableNode("Y");
			fido = Network.buildBaseNode("Fido", new Semantic("Base"));
			leo = Network.buildBaseNode("Leo", new Semantic("Base"));
			dog = Network.buildBaseNode("Dog", new Semantic("Base"));
			barks = Network.buildBaseNode("Barks", new Semantic("Base"));
			walks = Network.buildBaseNode("Walks", new Semantic("Base"));
			wire1 = new Wire(memberRel, fido);
			wire2 = new Wire(classRel, dog);
			wire3 = new Wire(doesRel, barks);
			wire4 = new Wire(memberRel, var);
			wire5 = new Wire(memberRel, var1);
			wire6 = new Wire(memberRel, leo);
			wire7 = new Wire(doesRel, walks);
		} catch (IllegalIdentifierException | NotAPropositionNodeException 
				| NodeNotFoundInNetworkException e1) {
			assertNotNull(e1.getMessage(), e1);
		}
		
		var = new VariableNode(new Variable("X"));
		var1 = new VariableNode(new Variable("Y"));


//--------------------------- prop1, prop2, prop3, prop4 -----------------------------//

		try {
			wires.clear();	wires.add(wire1);	wires.add(wire2);
			prop1 = Network.buildMolecularNode(wires, caseFrameMC);

			wires.clear();	wires.add(wire2);	wires.add(wire3);
			prop2 = Network.buildMolecularNode(wires, caseFrameCD);

			wires.clear();	wires.add(wire4);	wires.add(wire2);
			prop3 = Network.buildMolecularNode(wires, caseFrameMC);

			wires.clear();	wires.add(wire1);	wires.add(wire3);
			prop4 = Network.buildMolecularNode(wires, caseFrameMD);
			
			wires.clear();	wires.add(wire4);	wires.add(wire2);
			prop5 = Network.buildMolecularNode(wires, caseFrameMC);
		} catch (CannotBuildNodeException | EquivalentNodeException
				| NotAPropositionNodeException | NodeNotFoundInNetworkException e1) {
			assertNotNull(e1.getMessage(), e1);
		}
		
		LinkedList<DownCable> dcList = new LinkedList<DownCable>();
		NodeSet nodeSet1 = new NodeSet();
		NodeSet nodeSet2 = new NodeSet();
		NodeSet nodeSet3 = new NodeSet();
		NodeSet nodeSet4 = new NodeSet();
		NodeSet nodeSet5 = new NodeSet();
		NodeSet nodeSet6 = new NodeSet();
		NodeSet nodeSet7 = new NodeSet();
		NodeSet nodeSet8 = new NodeSet();
		NodeSet nodeSet9 = new NodeSet();
		NodeSet nodeSet10 = new NodeSet();
		DownCable dc1;	DownCableSet dcs;

		nodeSet1.addNode(fido);
		dc1 = new DownCable(memberRel, nodeSet1);
		dcList.add(dc1);
		nodeSet2.addNode(dog);
		dc1 = new DownCable(classRel, nodeSet2);
		dcList.add(dc1);
		dcs = new DownCableSet(dcList, caseFrameMC); 
		prop1 = new PropositionNode(new Closed("Prop1", dcs));
		dcList.clear();
		//------------------------------------------------------------//
		nodeSet3.addNode(dog);
		dc1 = new DownCable(classRel, nodeSet3);
		dcList.add(dc1);
		nodeSet4.addNode(barks);
		dc1 = new DownCable(doesRel, nodeSet4);
		dcList.add(dc1);
		dcs = new DownCableSet(dcList, caseFrameCD); 
		prop2 = new PropositionNode(new Closed("Prop2", dcs));
		dcList.clear();
		//------------------------------------------------------------//
		nodeSet5.addNode(var);
		dc1 = new DownCable(memberRel, nodeSet5);
		dcList.add(dc1);
		nodeSet6.addNode(dog);
		dc1 = new DownCable(classRel, nodeSet6);
		dcList.add(dc1);
		dcs = new DownCableSet(dcList, caseFrameMC);
		prop3 = new PropositionNode(new Open("Prop3", dcs));
		dcList.clear();
		//------------------------------------------------------------//
		nodeSet7.addNode(fido);
		dc1 = new DownCable(memberRel, nodeSet7);
		dcList.add(dc1);
		nodeSet8.addNode(barks);
		dc1 = new DownCable(doesRel, nodeSet8);
		dcList.add(dc1);
		dcs = new DownCableSet(dcList, caseFrameMD); 
		prop4 = new PropositionNode(new Closed("Prop4", dcs));
		dcList.clear();
		//------------------------------------------------------------//
		nodeSet9.addNode(var);
		dc1 = new DownCable(memberRel, nodeSet9);
		dcList.add(dc1);
		nodeSet10.addNode(walks);
		dc1 = new DownCable(doesRel, nodeSet10);
		dcList.add(dc1);
		dcs = new DownCableSet(dcList, caseFrameMC);
		prop5 = new PropositionNode(new Open("Prop5", dcs));
		dcList.clear();
		//------------------------------------------------------------//

//------------------------- Numerical Setup ------------------------------------//

		nodeSet.addNode(prop1);
		nodeSet.addNode(prop2);
		nodeSet.addNode(prop3);
		nodeSet.addNode(prop5);
		dc.add(new DownCable(antsRel, nodeSet));
		
		nodeSett.addNode(prop4);
		dc.add(new DownCable(consRel, nodeSett));

		DownCableSet dcss = new DownCableSet(dc, caseFrameAC);

//---------------------------- NUMERICAL -----------------------------------//

		numerical = new NumericalEntailment(new Closed("Closed", dcss));
		numerical.setI(1);
		
		sub = new LinearSubstitutions();
		support = new PropositionSet();
		/*try {
			support.add(prop1.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException | NodeNotFoundInNetworkException e) {
			e.printStackTrace();
		}*/

		report = new Report(sub, support, true, InferenceTypes.BACKWARD);
		
		LinearSubstitutions sub1 = new LinearSubstitutions();
		PropositionSet support1 = new PropositionSet();
		/*try {
			support.add(prop3.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException | NodeNotFoundInNetworkException e) {
			e.printStackTrace();
		}*/
		sub1.putIn(new Binding((VariableNode) var, fido));
		report1 = new Report(sub1, support1, true, InferenceTypes.BACKWARD);
		
		LinearSubstitutions sub2 = new LinearSubstitutions();
		PropositionSet support2 = new PropositionSet();
		/*try {
			support.add(prop5.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException | NodeNotFoundInNetworkException e) {
			e.printStackTrace();
		}*/
		sub2.putIn(new Binding((VariableNode) var, leo));
		report2 = new Report(sub2, support2, true, InferenceTypes.BACKWARD);
		
		LinearSubstitutions sub3 = new LinearSubstitutions();
		PropositionSet support3 = new PropositionSet();
		/*try {
			support.add(prop5.getId());
		} catch (DuplicatePropositionException | NotAPropositionNodeException | NodeNotFoundInNetworkException e) {
			e.printStackTrace();
		}*/
		sub3.putIn(new Binding((VariableNode) var, fido));
		report3 = new Report(sub3, support3, true, InferenceTypes.BACKWARD);
	}

 	@Test
 	public void testAntsandConsq() {
 		assertEquals(1, numerical.getConsequents().size());
		assertEquals(4, numerical.getAntecedents().size());
		assertEquals(2, numerical.getAntsWithVars().size());
		assertEquals(2, numerical.getAntsWithoutVars().size());
 	}
 	
	@Test
	public void testApplyRuleHandler() {
		numerical.applyRuleHandler(report, prop1);
		
		assertEquals(1, numerical.getReplies().size());
		assertEquals(numerical.getReplies().get(0), report);
	}
	
	@Test
	public void testApplyRuleHandler2() {
		numerical.clear();
		numerical.setI(2);
		
		numerical.applyRuleHandler(report, prop1);
		assertEquals(0, numerical.getReplies().size());
	}
	
	@Test
	public void testApplyRuleHandler3() {
		numerical.clear();
		numerical.setI(2);
		
		numerical.applyRuleHandler(report, prop1);
		assertEquals(0, numerical.getReplies().size());
		
		numerical.applyRuleHandler(report1, prop3);
		assertNotNull("NumericalEntailment: Null RuisHandler", 
				numerical.getRuisHandler());
		assertTrue("NumericalEntailment: addRuiHandler doesn't create an SIndex as a RuisHandler", 
				numerical.getRuisHandler() instanceof SIndex);
		assertEquals(SIndex.SINGLETON, ((SIndex) (numerical.getRuisHandler())).getRuiHandlerType());
		assertEquals(1, numerical.getReplies().size());
	}
	
	@Test
	public void testApplyRuleHandler4() {
		numerical.clear();
		numerical.setI(2);

		numerical.applyRuleHandler(report1, prop3);
		assertEquals(0, numerical.getReplies().size());
		
		numerical.applyRuleHandler(report2, prop5);
		assertEquals(0, numerical.getReplies().size());
		
		numerical.applyRuleHandler(report3, prop5);
		assertEquals(1, numerical.getReplies().size());
	}

	@Test
	public void testGetDownNodeSet() {
		NodeSet downAntNodeSet = numerical.getDownAntNodeSet();
		assertNotNull("NumericalEntailment: getDownAntNodeSet retuns null", 
				downAntNodeSet);
		assertFalse("NumericalEntailment: getDownAntNodeSet retuns an empty NodeSet", 
				downAntNodeSet.isEmpty());
		assertEquals(1, numerical.getDownConsqNodeSet().size());
		assertEquals(4, numerical.getDownAntNodeSet().size());
	}

	public void tearDown() {
		Network.clearNetwork();
		numerical.clear();
		fido = null;
		var = null;
		dog = null;
		report = null;
	}
}
