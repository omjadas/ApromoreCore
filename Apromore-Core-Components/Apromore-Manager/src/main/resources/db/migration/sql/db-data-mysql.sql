

INSERT INTO `native_type` VALUES (1,'EPML 2.0','epml');
INSERT INTO `native_type` VALUES (2,'XPDL 2.2','xpdl');
INSERT INTO `native_type` VALUES (3,'PNML 1.3.2', 'pnml');
INSERT INTO `native_type` VALUES (4,'YAWL 2.2', 'yawl');
INSERT INTO `native_type` VALUES (5,'BPMN 2.0', 'bpmn');
INSERT INTO `native_type` VALUES (6,'AML fragment', 'aml');




INSERT INTO `role` VALUES (1,'80da507e-cdd7-40f4-a9f8-b2d2edb12856','ROLE_ADMIN','Ultimate power to administer other users');
INSERT INTO `role` VALUES (2,'0ecd70b4-a204-41cd-a246-e3fcef88f6fe','ROLE_USER','Allowed to log in');
INSERT INTO `role` VALUES (3,'72503ce0-d7cd-47b3-a33c-1b741d7599a1','ROLE_MANAGER','Allowed to administer folders, models and event logs');
INSERT INTO `role` VALUES (4,'e0ae8c02-bfe0-11ea-992a-17a70e2c7d0f','ROLE_ANALYST','Allowed access to all tools except predictive monitoring training');
INSERT INTO `role` VALUES (5,'43fbd3c8-bfe1-11ea-9694-abeaa8ed1c0f','ROLE_OBSERVER','Allowed access to all tools in read-only mode');
INSERT INTO `role` VALUES (6,'4ff46cbc-bfe1-11ea-abec-aba9c1ea1178','ROLE_DESIGNER','Allowed access to the BPMN editor');
INSERT INTO `role` VALUES (7,'596fcfd4-bfe1-11ea-8564-4f9db22c941e','ROLE_DATA_SCIENTIST','Allowed access to all tools including predictive monitoring training');
INSERT INTO `role` VALUES (8,'64e734a6-bfe1-11ea-b4cf-2bed596c2920','ROLE_OPERATIONS','Allowed access to the predictive monitoring dashboard (runtime)');




INSERT INTO `user` VALUES (1,'75f4a46a-bd32-4fbb-ba7a-c50d06414fac','james',   '2012-05-23 11:52:48','Cameron', 'James',       NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (2,'aaf24d0d-58f2-43b1-8dcc-bf99717b708f','chathura','2012-05-23 11:59:51','Chathura','Ekanayake',   NULL,2,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (3,'a393f9c2-e2ee-49ed-9b6a-a1269811764c','arthur',  '2012-05-23 12:07:24','Arthur',  'Ter Hofstede',NULL,3,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (4,'b6701ee5-227b-493e-9b01-85aa33acd53b','Macri',   '2012-05-23 20:08:03','Marie',   'Fauvet',      NULL,4,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (5,'c81da91f-facc-4eff-b648-bdc1a2a5ebbe','larosa',  '2012-05-23 20:24:37','Marcello','La Rosa',     NULL,5,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (6,'c03eff4d-3672-4c91-bfea-36c67e2423f5','felix',   '2012-05-23 20:37:44','Felix',   'Mannhardt',   NULL,6,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (7,'fbcd5a9a-a224-40cb-8ab9-b12436d92835','raboczi', '2012-05-23 20:40:26','Simon',   'Raboczi',     NULL,7,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (8,'ad1f7b60-1143-4399-b331-b887585a0f30','admin',   '2012-05-28 16:51:05','Test',    'User',        NULL,8,NULL,NULL,NULL,NULL,NULL);



INSERT INTO `membership` VALUES (1,1,'5f4dcc3b5aa765d61d8327deb882cf99','username','','cam.james@gmail.com','Test question','test',1,0,'2012-05-23 20:37:44',0,0);
INSERT INTO `membership` VALUES (2,2,'5f4dcc3b5aa765d61d8327deb882cf99','username','','c.ekanayake@qut.edu.au','Test question','test',1,0,'2012-05-28 16:51:05',0,0);
INSERT INTO `membership` VALUES (3,3,'5f4dcc3b5aa765d61d8327deb882cf99','username','','arthur@yawlfoundation.org','Test question','test',1,0,'2012-06-16 11:43:16',0,0);
INSERT INTO `membership` VALUES (4,4,'5f4dcc3b5aa765d61d8327deb882cf99','username','','marie-christine.fauvet@qut.edu.au','Test question','test',1,0,'2012-06-16 11:56:00',0,0);
INSERT INTO `membership` VALUES (5,5,'5f4dcc3b5aa765d61d8327deb882cf99','username','','m.larosa@qut.edu.au','Test question','test',1,0,'2012-06-16 12:01:35',0,0);
INSERT INTO `membership` VALUES (6,6,'5f4dcc3b5aa765d61d8327deb882cf99','username','','felix.mannhardt@smail.wir.h-brs.de','Test question','test',1,0,'2012-06-16 12:08:50',0,0);
INSERT INTO `membership` VALUES (7,7,'5f4dcc3b5aa765d61d8327deb882cf99','username','','raboczi@gmail.com','Test question','test',1,0,'2012-06-16 12:35:25',0,0);
INSERT INTO `membership` VALUES (8,8,'5f4dcc3b5aa765d61d8327deb882cf99','username','','admin','Test question','test',1,0,'2012-06-16 14:10:14',0,0);



INSERT INTO `group` VALUES (1,'uuid1-james',   'james',   'USER');
INSERT INTO `group` VALUES (2,'uuid2-chathura','chathura','USER');
INSERT INTO `group` VALUES (3,'uuid3-arthur',  'arthur',  'USER');
INSERT INTO `group` VALUES (4,'uuid4-Macri',   'Macri',   'USER');
INSERT INTO `group` VALUES (5,'uuid5-larosa',  'larosa',  'USER');
INSERT INTO `group` VALUES (6,'uuid6-felix',   'felix',   'USER');
INSERT INTO `group` VALUES (7,'uuid7-raboczi', 'raboczi', 'USER');
INSERT INTO `group` VALUES (8,'uuid8-admin',   'admin',   'USER');
INSERT INTO `group` VALUES (9,'uuid9-public',  'public',  'PUBLIC');




INSERT INTO `user_group` VALUES (1,1),(1,9);
INSERT INTO `user_group` VALUES (2,2),(2,9);
INSERT INTO `user_group` VALUES (3,3),(3,9);
INSERT INTO `user_group` VALUES (4,4),(4,9);
INSERT INTO `user_group` VALUES (5,5),(5,9);
INSERT INTO `user_group` VALUES (6,6),(6,9);
INSERT INTO `user_group` VALUES (7,7),(7,9);
INSERT INTO `user_group` VALUES (8,8),(8,9);



INSERT INTO `user_role` VALUES (1,8);
INSERT INTO `user_role` VALUES (2,1);
INSERT INTO `user_role` VALUES (2,2);
INSERT INTO `user_role` VALUES (2,3);
INSERT INTO `user_role` VALUES (2,4);
INSERT INTO `user_role` VALUES (2,5);
INSERT INTO `user_role` VALUES (2,6);
INSERT INTO `user_role` VALUES (2,7);
INSERT INTO `user_role` VALUES (2,8);



INSERT INTO `permission` VALUES (1,'dff60714-1d61-4544-8884-0d8b852ba41e','View users','View other user data');
INSERT INTO `permission` VALUES (2,'2e884153-feb2-4842-b291-769370c86e44','Edit users','Modify other user data');
INSERT INTO `permission` VALUES (3,'d9ade57c-14c7-4e43-87e5-6a9127380b1b','Edit groups','Create, populate and delete groups');
INSERT INTO `permission` VALUES (4,'ea31a607-212f-447e-8c45-78f1e59b1dde','Edit roles','Modify other user roles');



INSERT INTO `role_permission` VALUES (1,1);
INSERT INTO `role_permission` VALUES (1,2);
INSERT INTO `role_permission` VALUES (1,3);
INSERT INTO `role_permission` VALUES (1,4);
INSERT INTO `role_permission` VALUES (2,1);
INSERT INTO `role_permission` VALUES (3,3);




INSERT INTO `search_history` VALUES (1,1,8,'airport');
INSERT INTO `search_history` VALUES (2,2,8,'gold coast');
INSERT INTO `search_history` VALUES (3,3,8,'goldcoast');
