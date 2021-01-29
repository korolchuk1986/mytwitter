create table message_likes(
    message_id int8 not null references message,
    user_id int8 not null references usr,
    primary key (message_id, user_id)
)
