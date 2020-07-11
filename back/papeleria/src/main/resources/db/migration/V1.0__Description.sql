

create table PRODUCTO(
id_producto serial ,
nombre varchar,
cantidad int,
costo_unitario int,
costo_total int,
precio int,
precio_venta int,
primary key (id_producto)
);


create table PRODUCTOS_TEMPORAL(
id_producto_temporal serial,
cantidad int,
id_producto  int,
constraint fk_producto_temporal foreign key (id_producto) references PRODUCTO(id_producto) on delete cascade on update cascade
);


create table HISTORIAL(
id_historial serial,
fecha date,
total_generado int,
ganancia int,
inversion int,
primary key (id_historial)
);

