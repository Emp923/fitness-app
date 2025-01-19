-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER fitness_owner
WITH PASSWORD 'fitnessdb';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO fitness_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO fitness_owner;

CREATE USER fitness_appuser
WITH PASSWORD 'fitnessdb';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO fitness_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO fitness_appuser;