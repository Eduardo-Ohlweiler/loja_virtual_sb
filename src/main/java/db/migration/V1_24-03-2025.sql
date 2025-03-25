SELECT CONSTRAINT_NAME FROM information_schema.constraint_column_usage
WHERE TABLE_NAME = 'usuarios_acesso' AND COLUMN_NAME = 'acesso_id'
AND constraint_name <> 'unique_acesso_user';

ALTER TABLE usuarios_acesso DROP CONSTRAINT "uk8bak9jswon2id2jbunuqlfl9e";