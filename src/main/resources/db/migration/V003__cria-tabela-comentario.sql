CREATE TABLE comment (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_service_id BIGINT NOT NULL,
    comment_description TEXT NOT NULL,
    date_send DATETIME NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_oder_service
FOREIGN KEY (order_service_id) REFERENCES order_service (id);