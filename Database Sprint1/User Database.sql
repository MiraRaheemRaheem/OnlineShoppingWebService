drop table if exists STORE;

drop table if exists USER_;

drop table if exists USER_TYPE;

/*==============================================================*/
/* Table: STORE                                                 */
/*==============================================================*/
create table STORE
(
   STORE_ID             int not null AUTO_INCREMENT,
   USERID               int not null,
   STORE_NAME           varchar(50) not null,
   primary key (STORE_ID)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER_
(
   USERID               int not null AUTO_INCREMENT,
   USER_TYPE                 int not null,
   USER_NAME                 varchar(100) not null,
   BDATE                date not null,
   USER_PASSWORD             varchar(100) not null,
   EMAIL                varchar(100) not null,
   GENDER               varchar(7) not null,
   MOBILE_NUM           varchar(15) not null,
   primary key (USERID)
);

/*==============================================================*/
/* Table: USER_TYPE                                             */
/*==============================================================*/
create table USER_TYPE
(
   TYPE_ID                 int not null AUTO_INCREMENT,
   USER_TYPE                 varchar(20) not null,
   primary key (TYPE_ID)
);

alter table STORE add constraint FK_OWN foreign key (USERID)
      references USER_ (USERID) on delete restrict on update restrict;

alter table USER_ add constraint FK_IS foreign key (USER_TYPE)
      references USER_TYPE (TYPE_ID) on delete restrict on update restrict;
