-- init.sql
CREATE TABLE IF NOT EXISTS tasks
(
    description  TEXT NOT NULL,
    date_created INTEGER DEFAULT (cast(strftime('%s', 'now') as int)),
    date_updated INTEGER DEFAULT (cast(strftime('%s', 'now') as int))
);
