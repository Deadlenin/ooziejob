drop table if exists report;
drop table if exists dicts_in_hdfs;
create table report (
  id                  bigserial primary key,
  name                varchar(256),
  partition_field     varchar(256),
  path_to_hdfs        varchar(1000),
  sql_for_criteria    varchar(1000),
  user_owner          varchar(256),
  id_table            varchar(256),
  id_bar_codes_table  varchar(256),
  support_upload_name boolean DEFAULT false NOT NULL,
  should_show_value   boolean DEFAULT true  NOT NULL,
  support_criteria_file_update  boolean DEFAULT false NOT NULL
);

create table criteria (
  id          bigserial primary key,
  id_report   BIGINT references report (id),
  value       varchar(256) not null,
  value_human varchar(256)
);
