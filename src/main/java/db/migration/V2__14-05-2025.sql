/*SELECT CONSTRAINT_NAME FROM information_schema.constraint_column_usage
WHERE TABLE_NAME = 'usuarios_acesso' AND COLUMN_NAME = 'acesso_id'
AND constraint_name <> 'unique_acesso_user';

ALTER TABLE usuarios_acesso DROP CONSTRAINT "uk8bak9jswon2id2jbunuqlfl9e";*/
DO $$
BEGIN
    -- Verifica se a constraint uk8bak9jswon2id2jbunuqlfl9e existe
    IF EXISTS (
        SELECT 1
        FROM information_schema.table_constraints
        WHERE table_name = 'usuarios_acesso'
        AND constraint_name = 'uk8bak9jswon2id2jbunuqlfl9e'
        AND constraint_type = 'UNIQUE'
    ) THEN
        -- Remove a constraint se ela existir
        EXECUTE 'ALTER TABLE usuarios_acesso DROP CONSTRAINT uk8bak9jswon2id2jbunuqlfl9e';
    END IF;
END $$;