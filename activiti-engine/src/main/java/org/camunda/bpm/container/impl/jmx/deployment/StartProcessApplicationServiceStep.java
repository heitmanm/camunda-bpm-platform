package org.camunda.bpm.container.impl.jmx.deployment;

import static org.camunda.bpm.container.impl.jmx.deployment.Attachments.PROCESSES_XML_RESOURCES;
import static org.camunda.bpm.container.impl.jmx.deployment.Attachments.PROCESS_APPLICATION;
import static org.camunda.bpm.container.impl.jmx.deployment.Attachments.PROCESS_ARCHIVE_DEPLOYMENT_MAP;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.deployment.spi.ProcessesXml;
import org.camunda.bpm.container.impl.jmx.JmxRuntimeContainerDelegate.ServiceTypes;
import org.camunda.bpm.container.impl.jmx.kernel.MBeanDeploymentOperation;
import org.camunda.bpm.container.impl.jmx.kernel.MBeanDeploymentOperationStep;
import org.camunda.bpm.container.impl.jmx.kernel.MBeanServiceContainer;
import org.camunda.bpm.container.impl.jmx.kernel.MbeanService;
import org.camunda.bpm.container.impl.jmx.services.JmxProcessApplication;
import org.camunda.bpm.engine.application.ProcessApplicationRegistration;

/**
 * <p>This deployment operation step starts an {@link MbeanService} for the process application.</p>
 * 
 * @author Daniel Meyer
 *
 */
public class StartProcessApplicationServiceStep extends MBeanDeploymentOperationStep {

  public String getName() {
    return "Start Process Application Service";
  }

  public void performOperationStep(MBeanDeploymentOperation operationContext) {
    
    final ProcessApplication processApplication = operationContext.getAttachment(PROCESS_APPLICATION);
    final Map<URL, ProcessesXml> processesXmls = operationContext.getAttachment(PROCESSES_XML_RESOURCES);
    final Map<String, ProcessApplicationRegistration> processArchiveDeploymentMap = operationContext.getAttachment(PROCESS_ARCHIVE_DEPLOYMENT_MAP);
    final MBeanServiceContainer serviceContainer = operationContext.getServiceContainer();
    
    // create service
    JmxProcessApplication mbean = new JmxProcessApplication(processApplication.getReference());    
    mbean.setProcessesXmls(new ArrayList<ProcessesXml>(processesXmls.values()));
    mbean.setDeploymentMap(processArchiveDeploymentMap);
    
    // start service
    serviceContainer.startService(ServiceTypes.PROCESS_APPLICATION, processApplication.getName(), mbean);
  }

}