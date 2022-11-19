alter table todo_task_list
    drop column todo_task_id;

alter table todo_task
    add todo_task_list_id integer;

