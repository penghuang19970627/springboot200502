#!/use/bin/env python3
# -*- coding=utf-8 -*-
__author__ = "penghuang"

'''
graph test
'''

import plotly.graph_objs as go
import plotly

def zx_sd_line():
    trace_1 = go.Scatter(
        x=[1, 2, 3, 4],
        y=[18, 58, 46, 88],
        name="散点图",
        mode="markers"
    )

    trace_2 = go.Scatter(
        x=[1, 2, 3, 4],
        y=[45, 76, 98, 23],
        name="折线图"
    )
    line_data = [trace_1, trace_2]

    # 设置页面布局
    layout = go.Layout(title="折线图测试", xaxis={"title": "季度"}, yaxis={"title": "产量"})
    # 融合图轨数据
    figure = go.Figure(data=line_data, layout=layout)
    #输出
    plotly.offline.plot(figure, filename="/upload/zx_sd_line.html", image="png")

def zz_bar():
    trace_1 = go.Bar(
        x=[1, 2, 3, 4],
        y=[52, 46, 86, 35],
        name="第一产业"
    )
    trace_2 = go.Bar(
        x=[1, 2, 3, 4],
        y=[67, 36, 76, 95],
        name="第二产业"
    )
    trace_3 = go.Bar(
        x=[1, 2, 3, 4],
        y=[22, 46, 76, 95],
        name="第三产业"
    )
    trace_4 = go.Bar(
        x=[1, 2, 3, 4],
        y=[52, 36, 66, 15],
        name="第四产业"
    )
    bar_data = [trace_1, trace_2, trace_3, trace_4]

    # 设置页面布局
    layout = go.Layout(title="柱状图测试", xaxis={"title": "季度"}, yaxis={"title": "产量"})
    # 融合图轨数据
    figure = go.Figure(data=bar_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename="/upload/zz_bar.html", image="png")

def bt_pie():
    trace_1 = go.Pie(
        labels=["产品一", "产品二", "产品三", "产品四", "产品五"],
        values=[11.11, 22.22, 33.33, 45.52, 62.31]
    )
    pie_data = [trace_1]

    # 设置页面布局
    layout = go.Layout(title="饼图测试")
    # 融合图轨数据
    figure = go.Figure(data=pie_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename="/upload/bt_pie.html", image="png")

if __name__ == "__main__":
    # zx_sd_line()
    # zz_bar()
    bt_pie()