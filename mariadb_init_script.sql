CREATE DATABASE IF NOT EXISTS KtJournalPrompts;

use KtJournalPrompts;

-- describe journal_prompt;

CREATE USER IF NOT EXISTS 'ktjpusr' IDENTIFIED BY 'ktjpheaven';

GRANT USAGE ON *.* TO 'ktjpusr'@'%' IDENTIFIED BY 'ktjpheaven';
grant all privileges on KtJournalPrompts.* TO 'ktjpusr'@'%';

FLUSH PRIVILEGES;

-- database privileges
GRANT CREATE ON KtJournalPrompts to 'ktjpusr'@'%';

-- table privileges
GRANT SELECT ON KtJournalPrompts to 'ktjpusr'@'%';
GRANT UPDATE ON KtJournalPrompts to 'ktjpusr'@'%';
GRANT INSERT ON KtJournalPrompts to 'ktjpusr'@'%';

FLUSH PRIVILEGES;
SHOW GRANTS FOR 'ktjpusr';

SELECT user, host, plugin
FROM mysql.user;

select distinct(category)
from journal_prompt;
