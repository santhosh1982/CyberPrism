package com.searchthecheese.codetrees.statetrees;

import com.searchthecheese.codetrees.states.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chandramouliswaran on 5/3/2018.
 */

public class StateTree {

    private StateNode root =  new StateNode(null,new State("<root>"));

    private List<StateNode> stateNodes;
    private List<StateLink> stateLinks;

    public StateTree(List<StateNode> stateNodes, List<StateLink> stateLinks) {
        this.stateNodes = stateNodes;
        this.stateLinks = stateLinks;
    }

    public StateTree(StateNode root) {
        this.root = root;
    }

    public StateTree() {
    }

    public List<StateNode> getStateNodes() {
        return stateNodes;
    }

    public void setStateNodes(List<StateNode> stateNodes) {
        this.stateNodes = stateNodes;
    }

    public List<StateLink> getStateLinks() {
        return stateLinks;
    }

    public void setStateLinks(List<StateLink> stateLinks) {
        this.stateLinks = stateLinks;
    }

    public StateTree addStateNode(StateNode node) {
        if(this.getStateNodes()==null) {
            this.setStateNodes(new ArrayList<StateNode>());
        }

        this.getStateNodes().add(node);
        return this;
    }

    public StateTree addStateLink(StateLink link) {
        if(this.getStateLinks()==null) {
            this.setStateLinks(new ArrayList<StateLink>());
        }

        this.getStateLinks().add(link);
        return this;
    }

    public static StateTree createSubTree(StateTree tree,StateNode node) {
        StateTree subTree = new StateTree(node);

        List<StateLink> links2 = new ArrayList<StateLink>();
        List<StateNode> nodes = new ArrayList<StateNode>();

        for(StateLink link : tree.getStateLinks()) {
            if(link.getFrom()==node) {
                links2.add(link);
                nodes.add(link.getTo());
            }
        }

        subTree.setStateNodes(nodes);
        subTree.setStateLinks(links2);

        return subTree;
    }
}
