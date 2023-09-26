CREATE DATABASE IF NOT EXISTS expense_tracker;

USE expense_tracker;

CREATE TABLE IF NOT EXISTS `users`(
    `id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(255),
    `username` VARCHAR(255),
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
    `date` DATE NOT NULL,
    `expense_type` INT NOT NULL,
    `transaction_amount` DECIMAL NOT NULL,
    `transaction_type` INT NOT NULL,
    `description` text,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)
)
