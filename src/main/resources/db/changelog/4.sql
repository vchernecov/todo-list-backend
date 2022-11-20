alter table todo_task_list
    add constraint todo_task_list_unique_user_id
        unique (user_id);
