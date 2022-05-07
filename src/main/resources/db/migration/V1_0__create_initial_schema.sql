CREATE TABLE places (
    id BINARY(16) NOT NULL,
    name VARCHAR(100) NOT NULL,
    name_district VARCHAR(100) NOT NULL,
    reference_district VARCHAR(100) NOT NULL,
    created_at DATETIME NULL,
    created_by BINARY(16) NULL,
    updated_at DATETIME NULL,
    updated_by BINARY(16) NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;