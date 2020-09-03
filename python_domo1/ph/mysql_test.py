#!/use/bin/env python3
# -*- coding=utf-8 -*-
__author__ = "penghuang"

'''
mysql test
'''

import mysql_util


def insert_resource():
    connection, cursor = mysql_util.get_connect_cursor()
    sql = "insert into resource (resource_name,permission) values ('hh', 'dd')"
    mysql_util.extue_insert_update_delete(cursor, sql)
    mysql_util.commit_(connection)
    mysql_util.close_connection_cursor(connection, cursor)
    

def query_resource():
    connection, cursor = mysql_util.get_connect_cursor()
    sql = "select * from resource"
    result = mysql_util.extue_query(cursor, sql)
    print(result)
    mysql_util.close_connection_cursor(cursor, connection)
if __name__ == "__main__":
    # insert_resource()
    query_resource()