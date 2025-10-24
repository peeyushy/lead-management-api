INSERT INTO public.users (
    id,
    created_at,
    email,
    full_name,
    password,
    role,
    team_leader_id,
    updated_at,
    username
) VALUES (
    1,
    CURRENT_TIMESTAMP,
    'admin@example.com',
    'Admin User',
    '$2a$10$lXYpiurVnTqO7wUHHZWrGOdrJxcwg/uYU32sh7WCtjqAswfGLFKL2', -- use a proper bcrypt hash
    'ADMIN',
    NULL,        -- admin typically not part of a team; else use appropriate team_leader_id
    CURRENT_TIMESTAMP,
    'admin'
);


INSERT INTO public.lead_notes (
    id,
    created_at,
    created_by_user_id,
    note,
    updated_at,
    updated_by_user_id,
    lead_id
) VALUES (
    1,
    CURRENT_TIMESTAMP,          -- created_at
    1,                       -- created_by_user_id (example user ID)
    'This is a test note.',    -- note text
    CURRENT_TIMESTAMP,          -- updated_at
    1,                       -- updated_by_user_id (example user ID)
    1                          -- lead_id (example lead ID)
);


$2a$10$lXYpiurVnTqO7wUHHZWrGOdrJxcwg/uYU32sh7WCtjqAswfGLFKL2