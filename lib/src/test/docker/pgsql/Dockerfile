FROM postgres:9

ADD 01-test.sql /docker-entrypoint-initdb.d/
COPY maven/ /docker-entrypoint-initdb.d/ 
