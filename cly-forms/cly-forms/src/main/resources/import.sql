insert into area (nombrearea) VALUES ('INFORMATICA');
INSERT INTO estados_impresoras (estadoimpresora) VALUES ('ACTIVO');
insert into marca (nombre) values ('EPSON');
insert into modelo (modelo) values ('6590');
insert into modelo (modelo) values ('5790');
insert into modelo (modelo) values ('1180');
insert into roles (nombre) values ('ROLE_USER');
insert into roles (nombre) values ('ROLE_ADMIN');
insert into usuarios (apellido, email, enabled, nombre, `password`,username) values('a','a',1,'a','$2a$12$Q5mzmlrjogMPYVRd585yI.uireROiINIpZkWCIWazMgdIrGmdb9gq','a');
insert into usuarios_roles VALUES (1,1);
insert into usuarios_roles VALUES (1,2);
/* INSERT into impresora (fecha_mov, numeroserie, observacion, area_id,estado_id, marca_id, modelo_id) values(NOW(), 'PRUEBA 1', 'OBSERVACION PRUEBA 1', 1, 1, 1, 1);
 *//* INSERT into incidencias (fecha_inicio,fecha_fin, impresora_id) values (NOW(), NOW(), 1);
insert into obs_incidencias (fecha_obs, observacion, incidencia_id) values (NOW(), 'PRUEBA OBSERVACION INCIDENCIAS', 1); */