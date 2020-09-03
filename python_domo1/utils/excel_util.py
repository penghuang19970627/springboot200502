#!/use/bin/env python3
# -*- coding=utf-8 -*-
__author__ = "penghuang"

'''
excel util
'''

import openpyxl

def create_excel(header, body, file_path):
    # 获取workbook对象
    wb = openpyxl.Workbook()
    # 获取sheet对象
    active_sheet = wb.get_active_sheet
    active_sheet.append(header)
    # 操作数据
    for item in body:
        active_sheet.append(item)

    wb.save(filename=file_path)

if __name__ == "__main__":
    l = list(range(1, 10))
    print(l[0])