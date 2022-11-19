alter table todo_task_list
    add constraint todo_task_list_pk
        primary key (id);

alter table todo_task
    add constraint todo_task_todo_task_list_fk
        foreign key (todo_task_list_id) references todo_task_list (id);

