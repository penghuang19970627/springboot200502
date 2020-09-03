#!/use/bin/env python3
# -*- coding=utf-8 -*-
__author__ = "penghuang"

'''
mysql util
'''

import pymysql

def get_connect_cursor():
    connection = pymysql.connect(host='localhost', user='root', passwd='123456', db='boot1', charset='utf8')
    cursor = connection.cursor()
    return connection, cursor

def extue_insert_update_delete(cursor, sql):
    result = cursor.execute(sql)
    return result

def extue_query(cursor, sql):
    cursor.execute(sql)
    return cursor.fetchall()

def commit_(connection):
    connection.commit()

def close_connection_cursor(cursor, connection):
    cursor.close()
    connection.colse()

