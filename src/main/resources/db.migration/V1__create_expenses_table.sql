CREATE TYPE expense_category AS ENUM ('FOOD', 'TRANSPORT', 'ENTERTAINMENT');

CREATE TABLE expenses (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(12,2) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    category expense_category NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
