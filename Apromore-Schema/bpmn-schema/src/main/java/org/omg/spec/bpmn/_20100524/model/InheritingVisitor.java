/*-
 * #%L
 * This file is part of "Apromore Core".
 * 
 * Copyright (C) 2013 - 2017 Queensland University of Technology.
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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.22 at 09:02:20 AM EST 
//


package org.omg.spec.bpmn._20100524.model;

import com.processconfiguration.Configurable;
import com.processconfiguration.ConfigurationAnnotation;
import com.processconfiguration.ConfigurationAnnotationAssociation;
import com.processconfiguration.ConfigurationAnnotationShape;
import com.processconfiguration.Variants;
import com.signavio.SignavioMetaData;
import org.omg.spec.bpmn._20100524.di.BPMNDiagram;
import org.omg.spec.bpmn._20100524.di.BPMNEdge;
import org.omg.spec.bpmn._20100524.di.BPMNLabel;
import org.omg.spec.bpmn._20100524.di.BPMNLabelStyle;
import org.omg.spec.bpmn._20100524.di.BPMNPlane;
import org.omg.spec.bpmn._20100524.di.BPMNShape;
import org.omg.spec.dd._20100524.dc.Bounds;
import org.omg.spec.dd._20100524.dc.Font;
import org.omg.spec.dd._20100524.dc.Point;

/**
 * Implementation of @link Visitor which recursively calls the visit methods of <var>aBean</var>'s superclasses.
 *
 * @link TBaseElement and @link DiagramElement are considered root elements and have no superclasses to calls.
 * Note that the knowledge about the class hierarchy hardcoded into this class needs to be manually updated whenever the class hierarchy is changed.
 *
 * @author <a href="simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class InheritingVisitor extends BaseVisitor {

    /*
    @Override public void visit(Configurable aBean) { }

    @Override public void visit(Configurable.Configuration aBean) { }

    @Override public void visit(ConfigurationAnnotation aBean) { }

    @Override public void visit(ConfigurationAnnotation.Configuration aBean) { }

    @Override public void visit(ConfigurationAnnotationAssociation aBean) { }

    @Override public void visit(ConfigurationAnnotationShape aBean) { }

    @Override public void visit(Variants aBean) { }

    @Override public void visit(Variants.Variant aBean) { }

    @Override public void visit(SignavioMetaData aBean) { }
    */

    /*
    @Override public void visit(BPMNDiagram aBean) { }

    @Override public void visit(BPMNEdge aBean) { }

    @Override public void visit(BPMNLabel aBean) { }

    @Override public void visit(BPMNLabelStyle aBean) { }

    @Override public void visit(BPMNPlane aBean) { }

    @Override public void visit(BPMNShape aBean) { }
    */

    @Override public void visit(TAdHocSubProcess aBean) { visit((TSubProcess) aBean); }

    @Override public void visit(TAssignment aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TAssociation aBean) { visit((TArtifact) aBean); }

    @Override public void visit(TAuditing aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TBoundaryEvent aBean) { visit((TCatchEvent) aBean); }

    @Override public void visit(TBusinessRuleTask aBean) { visit((TTask) aBean); }

    @Override public void visit(TCallActivity aBean) { visit((TActivity) aBean); }

    @Override public void visit(TCallChoreography aBean) { visit((TChoreographyActivity) aBean); }

    @Override public void visit(TCallConversation aBean) { visit((TConversationNode) aBean); }

    @Override public void visit(TCallableElement aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TCancelEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TCategory aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TCategoryValue aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TChoreography aBean) { visit((TCollaboration) aBean); }

    @Override public void visit(TChoreographyTask aBean) { visit((TChoreographyActivity) aBean); }

    @Override public void visit(TCollaboration aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TCompensateEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TComplexBehaviorDefinition aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TComplexGateway aBean) { visit((TGateway) aBean); }

    @Override public void visit(TConditionalEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TConversation aBean) { visit((TConversationNode) aBean); }

    @Override public void visit(TConversationAssociation aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TConversationLink aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TCorrelationKey aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TCorrelationProperty aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TCorrelationPropertyBinding aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TCorrelationPropertyRetrievalExpression aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TCorrelationSubscription aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TDataAssociation aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TDataInput aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TDataInputAssociation aBean) { visit((TDataAssociation) aBean); }

    @Override public void visit(TDataObject aBean) { visit((TFlowElement) aBean); }

    @Override public void visit(TDataObjectReference aBean) { visit((TFlowElement) aBean); }

    @Override public void visit(TDataOutput aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TDataOutputAssociation aBean) { visit((TDataAssociation) aBean); }

    @Override public void visit(TDataState aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TDataStore aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TDataStoreReference aBean) { visit((TFlowElement) aBean); }

    @Override public void visit(TDefinitions aBean) { }  // not part of either the semantic or graphical class hierarchies

    @Override public void visit(TDocumentation aBean) { }

    @Override public void visit(TEndEvent aBean) { visit((TThrowEvent) aBean); }

    @Override public void visit(TEndPoint aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TError aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TErrorEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TEscalation aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TEscalationEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TEventBasedGateway aBean) { visit((TGateway) aBean); }

    @Override public void visit(TExclusiveGateway aBean) { visit((TGateway) aBean); }

    @Override public void visit(TExpression aBean) { visit((TBaseElementWithMixedContent) aBean); }

    @Override public void visit(TExtension aBean) { }

    @Override public void visit(TExtensionElements aBean) { }

    @Override public void visit(TFormalExpression aBean) { visit((TExpression) aBean); }

    @Override public void visit(TGateway aBean) { visit((TFlowNode) aBean); }

    @Override public void visit(TGlobalBusinessRuleTask aBean) { visit((TGlobalTask) aBean); }

    @Override public void visit(TGlobalChoreographyTask aBean) { visit((TChoreography) aBean); }

    @Override public void visit(TGlobalConversation aBean) { visit((TCollaboration) aBean); }

    @Override public void visit(TGlobalManualTask aBean) { visit((TGlobalTask) aBean); }

    @Override public void visit(TGlobalScriptTask aBean) { visit((TGlobalTask) aBean); }

    @Override public void visit(TGlobalTask aBean) { visit((TCallableElement) aBean); }

    @Override public void visit(TGlobalUserTask aBean) { visit((TGlobalTask) aBean); }

    @Override public void visit(TGroup aBean) { visit((TArtifact) aBean); }

    @Override public void visit(THumanPerformer aBean) { visit((TPerformer) aBean); }

    @Override public void visit(TImplicitThrowEvent aBean) { visit((TThrowEvent) aBean); }

    @Override public void visit(TImport aBean) { }

    @Override public void visit(TInclusiveGateway aBean) { visit((TGateway) aBean); }

    @Override public void visit(TInputOutputBinding aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TInputOutputSpecification aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TInputSet aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TInterface aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TIntermediateCatchEvent aBean) { visit((TCatchEvent) aBean); }

    @Override public void visit(TIntermediateThrowEvent aBean) { visit((TThrowEvent) aBean); }

    @Override public void visit(TItemDefinition aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TLane aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TLaneSet aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TLinkEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TManualTask aBean) { visit((TTask) aBean); }

    @Override public void visit(TMessage aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TMessageEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TMessageFlow aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TMessageFlowAssociation aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TMonitoring aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TMultiInstanceLoopCharacteristics aBean) { visit((TLoopCharacteristics) aBean); }

    @Override public void visit(TOperation aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TOutputSet aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TParallelGateway aBean) { visit((TGateway) aBean); }

    @Override public void visit(TParticipant aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TParticipantAssociation aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TParticipantMultiplicity aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TPartnerEntity aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TPartnerRole aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TPerformer aBean) { visit((TResourceRole) aBean); }

    @Override public void visit(TPotentialOwner aBean) { visit((THumanPerformer) aBean); }

    @Override public void visit(TProcess aBean) { visit((TCallableElement) aBean); }

    @Override public void visit(TProperty aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TReceiveTask aBean) { visit((TTask) aBean); }

    @Override public void visit(TRelationship aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TRendering aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TResource aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TResourceAssignmentExpression aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TResourceParameter aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TResourceParameterBinding aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TResourceRole aBean) { visit((TBaseElement) aBean); }

    @Override public void visit(TScript aBean) { }

    @Override public void visit(TScriptTask aBean) { visit((TTask) aBean); }

    @Override public void visit(TSendTask aBean) { visit((TTask) aBean); }

    @Override public void visit(TSequenceFlow aBean) { visit((TFlowElement) aBean); }

    @Override public void visit(TServiceTask aBean) { visit((TTask) aBean); }

    @Override public void visit(TSignal aBean) { visit((TRootElement) aBean); }

    @Override public void visit(TSignalEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TStandardLoopCharacteristics aBean) { visit((TLoopCharacteristics) aBean); }

    @Override public void visit(TStartEvent aBean) { visit((TCatchEvent) aBean); }

    @Override public void visit(TSubChoreography aBean) { visit((TChoreographyActivity) aBean); }

    @Override public void visit(TSubConversation aBean) { visit((TConversationNode) aBean); }

    @Override public void visit(TSubProcess aBean) { visit((TActivity) aBean); }

    @Override public void visit(TTask aBean) { visit((TActivity) aBean); }

    @Override public void visit(TTerminateEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TText aBean) { }

    @Override public void visit(TTextAnnotation aBean) { visit((TArtifact) aBean); }

    @Override public void visit(TTimerEventDefinition aBean) { visit((TEventDefinition) aBean); }

    @Override public void visit(TTransaction aBean) { visit((TSubProcess) aBean); }

    @Override public void visit(TUserTask aBean) { visit((TTask) aBean); }

    /*
    @Override public void visit(Bounds aBean) { }

    @Override public void visit(Font aBean) { }

    @Override public void visit(Point aBean) { }

    @Override public void visit(org.omg.spec.dd._20100524.di.DiagramElement.Extension aBean) { }
    */

    // Methods not present in BaseVisitor

    public void visit(TActivity aBean) { visit((TFlowNode) aBean); }

    public void visit(TArtifact aBean) { visit((TBaseElement) aBean); }

    public void visit(TBaseElement aBean) { }  // top of class hierarchy for BPMN semantic elements

    public void visit(TBaseElementWithMixedContent aBean) { }  // top of expression/formal expression hierarchy

    public void visit(TCatchEvent aBean) { visit((TEvent) aBean); }

    public void visit(TChoreographyActivity aBean) { visit((TFlowNode) aBean); }

    public void visit(TConversationNode aBean) { visit((TBaseElement) aBean); }

    public void visit(TEvent aBean) { visit((TFlowNode) aBean); }

    public void visit(TEventDefinition aBean) { visit((TRootElement) aBean); }

    public void visit(TFlowElement aBean) { visit((TBaseElement) aBean); }

    public void visit(TFlowNode aBean) { visit((TFlowElement) aBean); }

    public void visit(TLoopCharacteristics aBean) { visit((TBaseElement) aBean); }

    public void visit(TRootElement aBean) { visit((TBaseElement) aBean); }

    public void visit(TThrowEvent aBean) { visit((TEvent) aBean); }
}
