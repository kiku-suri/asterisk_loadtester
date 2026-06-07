#!/bin/bash

DB_NAME="asterisk_loadtest"
DB_USER="root"

BACKUP_DIR="/opt/loadtester/backups/database"

TIMESTAMP=$(date +"%Y%m%d_%H%M%S")

BACKUP_FILE="${BACKUP_DIR}/${DB_NAME}_${TIMESTAMP}.sql"

mkdir -p "${BACKUP_DIR}"

echo "Starting Database Backup..."

mysqldump \
-u ${DB_USER} \
-p \
${DB_NAME} \
> ${BACKUP_FILE}

gzip ${BACKUP_FILE}

echo "Backup Completed"

echo "File:"
echo "${BACKUP_FILE}.gz"
