/*-
 * #%L
 * This file is part of "Apromore Core".
 * %%
 * Copyright (C) 2018 - 2020 Apromore Pty Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package org.apromore.similaritysearch.tools;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apromore.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.apromore.processmining.models.graphbased.directed.bpmn.BPMNDiagramSupport;
import org.apromore.processmining.models.graphbased.directed.bpmn.BPMNNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bruce Nguyen
 *
 */
public class MergeProcessesTest extends TestDataSetup {

    @Test
    public void testMergeProcesses_two_simple_models() {
        try {
            List<BPMNDiagram> diagrams = Arrays.asList(new BPMNDiagram[] {read_one_task_A(), read_one_task_B()});
            BPMNDiagram merge = MergeProcesses.mergeProcesses(diagrams, false, "Greedy", 0.6, 0.6, 0.75, 1.0, 1.0, 1.0);
            
            BPMNDiagramSupport bpmnSupport = new BPMNDiagramSupport(merge);
            BPMNNode node = bpmnSupport.getStartEvent();
            
            node = bpmnSupport.getTargets(node).iterator().next();
            Assert.assertEquals(true, bpmnSupport.isXORSplitGateway(node));
            
            Collection<BPMNNode> nodes = bpmnSupport.getTargets(node);
            node = nodes.iterator().next();
            Assert.assertEquals(true, node.getLabel().equals("A") || node.getLabel().equals("B"));
            node = nodes.iterator().next();
            Assert.assertEquals(true, node.getLabel().equals("A") || node.getLabel().equals("B"));
            
            node = bpmnSupport.getTargets(node).iterator().next();
            Assert.assertEquals(true, bpmnSupport.isXORJoinGateway(node));
            
            node = bpmnSupport.getTargets(node).iterator().next();
            Assert.assertEquals(true, bpmnSupport.isEndEvent(node));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
    
    @Test
    public void testMergeProcesses_three_simple_models() {
        try {
            List<BPMNDiagram> diagrams = Arrays.asList(new BPMNDiagram[] {read_one_task_A(), read_one_task_B(), 
                                                                            read_one_task_C()});
            BPMNDiagram merge = MergeProcesses.mergeProcesses(diagrams, false, "Greedy", 0.6, 0.6, 0.75, 1.0, 1.0, 1.0);
            
            BPMNDiagramSupport bpmnSupport = new BPMNDiagramSupport(merge);
            BPMNNode node = bpmnSupport.getStartEvent();
            
            node = bpmnSupport.getTargets(node).iterator().next();
            Assert.assertEquals(true, bpmnSupport.isXORSplitGateway(node));
            
            Collection<BPMNNode> nodes = bpmnSupport.getTargets(node);
            node = nodes.iterator().next();
            Assert.assertEquals(true, node.getLabel().equals("A") || node.getLabel().equals("B") || node.getLabel().equals("C"));
            node = nodes.iterator().next();
            Assert.assertEquals(true, node.getLabel().equals("A") || node.getLabel().equals("B") || node.getLabel().equals("C"));
            node = nodes.iterator().next();
            Assert.assertEquals(true, node.getLabel().equals("A") || node.getLabel().equals("B") || node.getLabel().equals("C"));
            
            node = bpmnSupport.getTargets(node).iterator().next();
            Assert.assertEquals(true, bpmnSupport.isXORJoinGateway(node));
            
            node = bpmnSupport.getTargets(node).iterator().next();
            Assert.assertEquals(true, bpmnSupport.isEndEvent(node));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

}