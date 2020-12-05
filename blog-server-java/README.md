项目页面

blog index 翻页

detail 评论

user detail

```sql
-- 创建数据库
CREATE DATABASE `mo`;

-- 用户表
CREATE TABLE `mo_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `dynasty` varchar(20) DEFAULT NULL COMMENT '朝代',
  `last_login_time` datetime DEFAULT null COMMENT '最后登录时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 文章表
CREATE TABLE `mo_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '作者_id',
   `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
```

数据准备

```bash
pip install requests parsel myquery
```

```python
# -*- coding: utf-8 -*-

"""
获取古诗文网列表数据
eg: https://www.gushiwen.cn/
"""

from pprint import pprint

import requests
from parsel import Selector
from myquery import DataBase


def get_data(url):
    """
    :return: list
    """
    response = requests.get(url)

    sel = Selector(text=response.text)
    rows = sel.css(".main3 .left .sons")

    lst = []
    for row in rows:

        title = row.css("b::text").extract_first()
        author = row.css(".source a::text").extract()
        content = row.css(".contson").xpath("string(.)").extract_first()

        if not title:
            continue

        # 标题，作者，作者时代，内容
        item = {
            "title": title.strip(),
            "author": author[0].strip(),
            "author_time": author[1].strip("〔〕"),
            "content": content.replace('\n', ''),
        }

        pprint(item)
        lst.append(item)

    return lst


def get_user_id(con, user_name, dynasty):
    """姓名user_name 和 朝代dynasty 唯一确定一位作者"""
    user = con.select_one("select id from mo_user where name = :name and dynasty = :dynasty limit 1", {
        "name": user_name,
        "dynasty": dynasty
    })

    if not user:
        user_table = con.table("mo_user")

        user_id = user_table.insert_one({
            "name": user_name,
            "dynasty": dynasty,
        })
    else:
        user_id = user['id']

    return user_id


def get_blog_id(con, user_id, title):
    """user_id 和 title 唯一确定一篇文章"""
    blog = con.select_one("select id from mo_blog where user_id = :user_id and title = :title limit 1", {
        "user_id": user_id,
        "title": title
    })

    if blog:
        return blog['id']


def main():
    # 准备数据表
    con = DataBase(db_url="mysql://root:123456@127.0.0.1:3306/mo?charset=utf8&autocommit=true")

    blog_table = con.table("mo_blog")

    for page in range(1, 11):
        url = f"https://www.gushiwen.cn/default.aspx?page={page}"

        for item in get_data(url):
            print(item)
            user_id = get_user_id(con, item['author'], item['author_time'])

            # 去重处理
            blog_id = get_blog_id(con, user_id, item['title'])

            if not blog_id:
                data = {
                    "user_id": user_id,
                    "title": item['title'],
                    "content": item['content']
                }

                blog_table.insert_one(data)

    con.close()


if __name__ == '__main__':
    main()

```