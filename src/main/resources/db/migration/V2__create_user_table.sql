CREATE TABLE app_user (

    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL

);

ALTER TABLE task
ADD COLUMN user_id BIGINT NOT NULL;

ALTER table task
add constraint fk_task_user
foreign key (user_id)
REFERENCES app_user(id);