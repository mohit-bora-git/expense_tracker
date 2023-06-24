CREATE DATABASE IF NOT EXISTS expense_tracker;

USE expense_tracker;

CREATE TABLE IF NOT EXISTS `users`(
    `id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(255),
    `nick_name` VARCHAR(255),
    `email` NOT NULL VARCHAR(255),
    `password` NOT NULL VARCHAR(255),
    `transaction_limit` DECIMAL,
    `profile_image_url` VARCHAR(255),
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`),
    INDEX `users_email`(`email`),

)

CREATE TABLE IF NOT EXISTS `expenses`(
    `id` BIGINT AUTO_INCREMENT,
    `expense_type` NOT NULL INT,
    `transaction_amount` NOT NULL DECIMAL,
    `transaction_type` NOT NULL INT,
    `description` text,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `user_id` NOT NULL BIGINT
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)
)