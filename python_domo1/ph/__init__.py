#!/use/bin/env python3
# -*- coding=utf-8 -*-
__author__ = "penghuang"

import re


# ====输入输出====
# name = input()
# print(name)
a = 13
b = "penghuang"
print(a)
print("hello world")
print("hello", "world", "penghuang")
print("hello %s" % a)
print("hello %s" % (a,))
print("hello %s - %s" % (a, b))

# ====数据类型====
a = 12
print("a = %s,数据类型为：%s" % (a, type(a)))
a = 11.13
print("a = %s,数据类型为：%s" % (a, type(a)))
b = float(a)
print("b = %s,数据类型为：%s" % (b, type(b)))
a = "12"
print("a = %s,数据类型为：%s" % (a, type(a)))
a = 1 < 7
print("a = %s,数据类型为：%s" % (a, type(a)))
a = True or False
print("a = %s,数据类型为：%s" % (a, type(a)))
a = True and False
print("a = %s,数据类型为：%s" % (a, type(a)))
a = not False
print("a = %s,数据类型为：%s" % (a, type(a)))
a = None
print("a = %s,数据类型为：%s" % (a, type(a)))
a = b'asd'
print("a = %s,数据类型为：%s" % (a, type(a)))
a = ["ph", "pc", 13, True, None]
print("a = %s,数据类型为：%s" % (a, type(a)))
a = ("pd", 16, None, True)
print("a = %s,数据类型为：%s" % (a, type(a)))
a = {"name": "ph", "age": "23"}
print("a = %s,数据类型为：%s" % (a, type(a)))
a = set(["ph", "pc", 13, True, None])
print("a = %s,数据类型为：%s" % (a, type(a)))

# ====运算符====
print((2+99*2)/4)
print(48 // 5) # 取整
print(88 % 5)
print(2 ** 5)

# ====字符串====
print(u'彭皝')
print(r'建华')
print(b'auau')

# ----ASCII码转换----
print("98-->%s;a-->%s"%(chr(98), ord('a')))
# ----encode && decode
print("sadas".encode("ascii"))
print("彭皝".encode("utf8")) # 无法显示ASCII，只能是utf8，格式为\x**
print(b'sadas'.decode("ascii"))
print(b'\xe5\xbd\xad\xe7\x9a\x9d'.decode("utf8"))
# ----function----
print("字符长度为：%s"%len("zxcvbn"))
print("中文字符长度为: %s"%len("彭皝"))
print("转码后字符长度：%s"%len("zxcvbn".encode("ascii")))
print("转码后中文字符长度：%s"%len("彭皝".encode("utf8"))) # 对于bytes计算字节数
print("qazwsxedccxcxc".replace("cx","---")) # 替换
print("qazwsxedccxcxc".find("cx")) # 第一次出现下标
print("qazwsxedccxcxc".rfind("cx")) # 最后一次出现下标
print(" ".isspace())
print("sadas".isspace())
print(" asd".isspace())
print("asd ".isspace())
print(" sad ".isspace())
print("%s---%2d---%02d"%(15, 4, 6)) # 2d不足两位左边补空格 02d不足两位左边补0
print("%f----%.2f"%(22.655, 333.6555)) # .2f（保留两位小数，四舍五入）
print("%x"%65338)# 格式化为十六进制
print("%s%%%s"%("5", "7"))
print(list("%s" % x for x in range(3, 15)))
print(["1", "2", "3"])
print(list(range(1, 5)))
print("{0} :成绩提高了{1:.1f}%".format("彭皝", 1.3568))
print("{0}:成绩提高了{1}%".format("彭皝", 1.6899))
print("{0}:成绩提高了{1}%".format("彭皝", "%.1f"%1.6899))
print("<".join(list("%s" % x for x in range(1, 8))))

# ====正则表达式====
# ==== 匹配字符串 ====
email_re = "^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$"
if re.match(email_re, "penghuang@163.com"):
    print("ok")
else:
    print("error")
# ----切分字符串----
print("a b c".split())
print(re.split(r'\s+', "a b c"))
print(re.split(r"[\s\,\;]+", "a,b;;c  d"))
# ----分组----
math = re.match(r"^(\d{3})-(\d{3,8})$", "010-123456")
print(math.group(0))
print(math.group(1))
print(math.group(2))
news_line = r'印尼新冠肺炎累计确诊病例已升至180646例，新增3075例。目前，有129971名患者被治愈，' \
            r'另有7616例死亡病例。过去24小时，新增治愈患者1914人，死亡病例增加111例。'
news_line_re = r'^印尼新冠肺炎累计确诊病例已升至(\d+)例，新增(\d+)例。目前，有(\d+)名患者被治愈，' \
               r'另有(\d+)例死亡病例。过去(\d+)小时，新增治愈患者(\d+)人，死亡病例增加(\d+)例。$'
news_line_math = re.match(news_line_re, news_line)
print(news_line_math.group(0))
print(news_line_math.group(1))
print(news_line_math.group(2))
print(news_line_math.group(3))
print(news_line_math.group(4))
print(news_line_math.group(5))
print(news_line_math.group(6))
print(news_line_math.group(7))

print("-----compile分组提取-----")
news_line_compile = re.compile(news_line_re)
print(re.search(news_line_compile, news_line).group(1))
print(re.search(news_line_compile, news_line).group(2))
print(re.search(news_line_compile, news_line).group(3))
print(re.search(news_line_compile, news_line).group(4))
print(re.search(news_line_compile, news_line).group(5))
print(re.search(news_line_compile, news_line).group(6))
print(re.search(news_line_compile, news_line).group(7))
# ----贪婪匹配----
print(re.match(r'^(\d+)(0*)$', "150033200").groups())
print(re.match(r'^(\d+?)(0*)$', "150033200").groups())

# ====list & tuple====
li = ["sda", 15, None, True]
print("添加前：%s"%li)
li2 = range(1, 9)
print(li2)
li.insert(2, "彭皝")
print("根据下标添加：%s"%li)
li.append("jianhua")
print("尾部添加：%s"%li)
li.pop()
print("删除尾部:%s"%li)
li.pop(0)
print("根据下标删除：%s"%li)
li += li2
print("尾部拼接list:%s"%li)
li[0] = "指定值"
print(li)
print("-----元祖-----")
li3 = list(range(5, 14))
tup = ("元祖", 15, None, False, li3)
li3[0] = "看看"
print(tup)
tup2 = tuple(range(3, 11))
print(tup2)
# 元祖只有一个元素的时候，在后面加"，"以免误解成数学计算意义上的括号
print((1))
print((1,))

# ====dict & set====
d = {"name": "彭皝","age": 23, "money": 5000}
print(d.get("name1", "moren"))
d["name"] = "老杨"
d["work"] = "上班"
print(d)
d.pop("work")
print(d)
print("长度：%s"%len(d))
print("-------set-------")
s = set(["彭皝", 23, 160.12, None, False])
s2 = {"xcv", 561, 65.23, True, None}
s.add("建华")
print(s)
s.pop()
print(s)
s.remove("建华")
print(s)
print("共有元素set:%s"%(s & s2))
print("不同元素set:%s"%(s | s2))

# ====条件判断====
a = 10
if a <= 10:
    print("aaa")
elif 10 <= a <= 20 :
    print("bbb")
else:
    print("ccc")
a = " "
if a and a.strip():
    print(a)
else:
    print("Null String")
a, b, c = 1, 2, 3
print(a if (b > c) else c)

# ====循环====
a = list(range(10))
for i in a :
    print("循环遍历：%s"%i)
i = 0
while (i < 10):
    print("while循环：%s"%i)
    i = i + 1

# ====函数====
print("________函数________")
def test(a):
    a  += 3
    return a
print(test(8))
def test2(x, y="彭皝"):
    print(x,y)
def test3(*num):
    count = 0
    for i in num:
        count += i
    return count
def test4(name, age, **kv):
    if "city" in kv:
        print("name:%s, age:%s, city:%s"%(name, age, kv.get("city")))
    else:
        print("name:%s, age:%s, city:%s"%(name, age, "成都"))
def test5(name, *, city):
    print("name:%s, city:%s"%(name, city))
if __name__ == "__main__":
    print("main方法：%s" % test(8))
    test2("name:","建华")
    test2("name:")
    print(test3())
    print(test3(1,2,3,4,5))
    print(test3(*list(range(1, 9))))
    test4("彭皝", **{"age": 23})
    test4("建华", **{"age": 22, "city": "贵阳"})
    test5("张颖", city="咸阳")

import math
# ==== 内置函数 ====
print(int("25")) # 数据类型转换函数，注意，如果定义变量名和函数名一样，则不会调用该函数，会报错
print(float("22.8"))
print(str(22))
print(abs(-111))# abs函数，求绝对值
print(max(12, 34, 123.4)) # max函数，求最大值
print(min(-21, -11, 0, 22.3)) # min函数，求最小值
print(" aa bb  cc  ".strip())# 字符串去前后空格
print("['6K-8K']".strip('[\'\']')) # 移除字符串头尾指定的字符
print(hex(333)) # hex函数，将十进制数转十六进制
print(math.sqrt(5)) # 求平方根
print(sum(range(1, 101))) # 求和
print(sum(list(range(101))))
print("penghuANg".capitalize()) # 将字符串第一个字符变成大写，其他小写
