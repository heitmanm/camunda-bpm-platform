/**
 * Copyright (C) 2011, 2012 camunda services GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.application.impl.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.EjbProcessApplication;


/**
 * 
 * @author Daniel Meyer
 * @author Roman Smirnov
 * 
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN) 
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@ProcessApplication
public class DefaultEjbProcessApplication extends EjbProcessApplication {
  
  protected Map<String, String> properties = new HashMap<String, String>();
  
  @PostConstruct
  public void deploy() {
    super.deploy();
  }

  @PreDestroy
  public void undeploy() {
    super.undeploy();
  }
  
  public Map<String, String> getProperties() {
    return properties;
  }
    
}