#!/use/bin/env python3
# -*- coding=utf-8 -*-
__author__ = "penghuang"

'''
excel test
'''

# import openpyxl
import random
import excel_util

def wirte_excel():
    header = ["第一季度", "第二季度", "第三季度", "第四季度"]
    file_path = "/upload/jidubaobiao.xlsx"
    body = list()
    for item in range(1, 10):
        line = list()
        for i in range(1, len(header)+1):
            line.append(i*random.randint(1, 10))
        body.append(line)
    excel_util.create_excel(header, body, file_path)
    # 获取workbook对象
    #wb = openpyxl.Workbook()
    # 获取sheet对象
    #active_sheet = wb.get_active_sheet()
    # 操作数据
    #active_sheet.append(["第一季度", "第二季度", "第三季度", "第四季度"])
    #for i in range(1, 10):
    #    active_sheet.append([i * random.randint(1, 10), i * random.randint(1, 10),
    #                         i * random.randint(1, 10), i * random.randint(1, 10)])
    #wb.save(filename="/upload/jidubaobiao.xlsx")

if __name__ == "__main__":
    wirte_excel()

