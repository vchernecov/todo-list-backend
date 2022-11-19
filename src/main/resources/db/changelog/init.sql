create table todo_task
(
    id               bigint    not null
        constraint todo_task_pk
            primary key,
    title            text,
    body             text,
    state            text,
    due_date         timestamp not null,
    create_date_time timestamp,
    update_date_time timestamp
);

create index todo_task_due_date_index
    on todo_task (due_date);

create table todo_task_list
(
    id           bigint not null,
    todo_task_id integer
        constraint todo_task_list_todo_task__fk
            references todo_task (id),
    user_id      integer
);

