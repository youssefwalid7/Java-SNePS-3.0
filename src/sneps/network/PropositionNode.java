package sneps.network;

import sneps.network.classes.Term;
import sneps.network.classes.setClasses.ChannelSet;
import sneps.network.classes.setClasses.ReportSet;
import sneps.snebr.SNeBrSupports;

public class PropositionNode extends Node {
	private SNeBrSupports basicSupport;
	
	protected ChannelSet outgoingChannels;
	protected ChannelSet incomingChannels;
	protected ReportSet knownInstances;

	public PropositionNode() {
		outgoingChannels = new ChannelSet();
		incomingChannels = new ChannelSet();
		knownInstances = new ReportSet();
	}
	public PropositionNode(Term trm) {
		this();
		setTerm(trm);
	}
	
	
	
	
	
	
	
	
	
	
	public SNeBrSupports getBasicSupport() {
		return basicSupport;
	}
	public void setBasicSupport(SNeBrSupports basicSupport) {
		this.basicSupport = basicSupport;
	}
	public ChannelSet getOutgoingChannels() {
		return outgoingChannels;
	}
	public void setOutgoingChannels(ChannelSet outgoingChannels) {
		this.outgoingChannels = outgoingChannels;
	}
	public ChannelSet getIncomingChannels() {
		return incomingChannels;
	}
	public void setIncomingChannels(ChannelSet incomingChannels) {
		this.incomingChannels = incomingChannels;
	}
	public ReportSet getKnownInstances() {
		return knownInstances;
	}
	public void setKnownInstances(ReportSet knownInstances) {
		this.knownInstances = knownInstances;
	}

}